package clirunner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class XMLUtils {

	public static ArrayList<AppInfo> parseXML(String project, String mode) {
		String filePath = getXmlPath(project, mode);
		ArrayList<AppInfo> appInfoList = new ArrayList<AppInfo>();
		try {
			Document doc = Jsoup.parse(new File(filePath), "UTF-8");
			Elements apps = doc.select(Constant.XML_TAG_APP);

			for (Element app : apps) {

				AppInfo appInfo = new AppInfo();

				appInfo.setJavaFileName(app.attr(Constant.XML_TAG_JAVA_FILE_NAME));
				if(filePath.toLowerCase().contains(EnumProject.STOREAPP.toString().toLowerCase())){
					String path = app.attr(Constant.XML_TAG_PACKAGE_PATH);
					appInfo.setBinaryPath(path);
					appInfo.setAppName(new File(path).getName());
				}else{
					appInfo.setRootstrap(app.attr(Constant.XML_TAG_ROOTSTRAP));
					appInfo.setAppName(app.attr(Constant.XML_TAG_APPNAME));
					appInfo.setRequiredVersion(app.attr(Constant.XML_TAG_REQUIRED_VERSION));
					appInfo.setProfile(app.attr(Constant.XML_TAG_PROFILE));
					appInfo.setAppType(app.attr(Constant.XML_TAG_APP_TYPE));
				}


				ArrayList<TestInfo> tcList = new ArrayList<TestInfo>();
				Elements childern = app.children().select(Constant.XML_TAG_TC);

				for (Element child : childern) {
					String tcName = child.attr(Constant.XML_TAG_NAME);
					TestInfo testcase = new TestInfo(tcName, TCResult.NT, 0);
					testcase.setLevel(Integer.parseInt(child.attr(Constant.XML_TAG_LEVEL)));
					testcase.setPreCondition(child.attr(Constant.XML_TAG_PRECONDITION));
					tcList.add(testcase);
				}
				appInfo.setTcList(tcList);
				appInfoList.add(appInfo);
			}


		} catch (IOException e) {
			return null;
		}

		return appInfoList;
	}

	private static String getXmlPath(String project, String mode) {
		return System.getProperty("user.dir") + "/res/xml/" + project +"_" + mode + ".xml";
	}

	public static void writeXml(String project, ArrayList<AppInfo> appList) {
		try {

			org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			org.w3c.dom.Element root = doc.createElement(Constant.XML_TAG_MENUTREE);
			doc.appendChild(root);

			for (AppInfo appInfo : appList) {
				if(appInfo.getAppResult() == TCResult.Fail){

					org.w3c.dom.Element row = doc.createElement(Constant.XML_TAG_APP);
					row.setAttribute(Constant.XML_TAG_JAVA_FILE_NAME, appInfo.getJavaFileName());

					if(project.toLowerCase().equals(EnumProject.STOREAPP.toString())){
						row.setAttribute(Constant.XML_TAG_PACKAGE_PATH, appInfo.getBinaryPath());
					}else{
						row.setAttribute(Constant.XML_TAG_APP_TYPE, appInfo.getAppType());
						row.setAttribute(Constant.XML_TAG_PROFILE, appInfo.getProfile());
						row.setAttribute(Constant.XML_TAG_REQUIRED_VERSION, appInfo.getRequiredVersion());
						row.setAttribute(Constant.XML_TAG_APPNAME, appInfo.getAppName());
						row.setAttribute(Constant.XML_TAG_ROOTSTRAP, appInfo.getRootstrap());
					}

					root.appendChild(row);

					for (TestInfo testInfo : appInfo.getTcList()) {
						org.w3c.dom.Element tc = doc.createElement(Constant.XML_TAG_TC);
						tc.setAttribute(Constant.XML_TAG_LEVEL, testInfo.getLevel()+"");
						tc.setAttribute(Constant.XML_TAG_NAME, testInfo.getTcName());
						tc.setAttribute(Constant.XML_TAG_PRECONDITION, testInfo.getPreCondition());
						row.appendChild(tc);
					}
				}

			}
			 // create Transformer object
		    Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    transformer.transform(new DOMSource(doc), result);

		    PrintWriter pw = new PrintWriter(getXmlPath(project,"rerun"));
		    pw.write(writer.toString());
		    pw.flush();
		    pw.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
