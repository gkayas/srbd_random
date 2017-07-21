import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Style;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Th;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Report {

	private static Html html;
	private static Table tcTable;
	private static Table summaryTable;
	
	static int failCount;
	static int passCount;
	static int failCountTotal;
	static int passCountTotal;
	static double executionTime;
	static double executionTimeTotal;
	
	static String MAIN_SUITE_NAME; 
	
	static ArrayList<Suite> suiteList;
	
	public static ArrayList<Testcase> prepareReportForRun(TCSuite suite) throws IOException  {
		File inputXml = new File(Utils.getValue(Constants.SOURCE_XML_PATH));
	
		if(!inputXml.exists()) {
			System.out.println(Utils.getValue(Constants.SOURCE_XML_PATH)+" file is not available...");
			return null;
		}
		
		ArrayList<Testcase> testCaseList = new ArrayList<Testcase>();
		File resultFile =new File(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv");
		
		if(resultFile.exists()) {
			readExitingResultFile(testCaseList);			
		}  
		
		Document docInputXml = Jsoup.parse(inputXml, "UTF-8");
		docInputXml.outputSettings().prettyPrint(false);
		Elements elements = docInputXml.select("testcase");
		
		ArrayList<Testcase> failTcList = new ArrayList<Testcase>();

		//add data to tcList
		for (Element element : elements) {
			 Testcase tc = new Testcase();
			
			tc.setSuiteName(suite.getName());
			tc.setPlatform(suite.getPlatform());
			tc.setProfile(suite.getProfile());
			tc.setName(element.attr("name"));
			
			ArrayList<Testcase> suitetcList = suite.getTcList();
			for (Testcase testcase : suitetcList) {
				if(testcase.equals(tc)) {
					tc.setTcmID(testcase.getTcmID());
					tc.setModule(testcase.getModule());
					break;
				}
			}
			
			Elements children = element.children();

			if(children.size() > 0) {				
				if(!failTcList.contains(tc)) {
					failTcList.add(tc);
				}
				
				tc.setStatus("Fail");
				
				for (Element child : children) {
					String msg = child.attr("message");					
					
					if(msg.contains("Exception occur in rcptt code")) {
						String failureText = child.html();
						Pattern pattern = Pattern.compile("\\s*data\\s*\\=\\s*.*\\r?\\n\\s*message\\s*\\=\\s*(.*)\\s*");
						Matcher matcher = pattern.matcher(failureText);
						
						while(matcher.find()) {
							String s = matcher.group(1).trim().replaceAll("&quot;", "\"");
							if(!s.contains("The Window \"Save Resource\"") && !s.contains("The Window \"Save Resources\"")) {
								msg =  matcher.group(1).trim();	
							}
						}
					}

					msg = msg.trim().replaceAll(",",  " ").replaceAll("\n", " ").replaceAll("\r", "").replaceAll("&quot;", "\"");
					tc.setMsg(msg);
				}
				
			} else {
				if(element.attr("incomplete").equals("true")) {
					if(!failTcList.contains(tc)) {
						failTcList.add(tc);
					}
					tc.setIncomplete(true);
					tc.setStatus("Fail");
					tc.setMsg("Failed to close report node: RCPTT Connection reset");
				} else {
					tc.setStatus("Pass");
				}
			}
			
			if(testCaseList.contains(tc)) {
				Testcase prevTc = null;
				
				for (Testcase testCase : testCaseList) {
					if(testCase.equals(tc)) {
						if(testCase.getTcmID().equals(tc.getTcmID())) {
							prevTc = testCase;
							break;
						}
					}
				}
				
				if(prevTc == null) {
					testCaseList.add(tc);
				} else {
					if(tc.isIncomplete()) {
						prevTc.setStatus("Fail");
						prevTc.setMsg("Failed to close report node: RCPTT Connection reset");
					} else {
						prevTc.copy(tc);
					}
				}				
			}else {
				testCaseList.add(tc);
			} 
		}
		
		dumpListToResultFile(testCaseList);
		return failTcList;
	}
	
	public static ArrayList<Testcase> prepareReportForGroupRun(SuiteGroup suiteGroup) throws IOException  {
		File inputXml = new File(Utils.getValue(Constants.SOURCE_XML_PATH));
	
		if(!inputXml.exists()) {
			System.out.println(Utils.getValue(Constants.SOURCE_XML_PATH)+" file is not available...");
			return null;
		}
		
		ArrayList<Testcase> testCaseList = new ArrayList<Testcase>();
		File resultFile =new File(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv");
		
		if(resultFile.exists()) {
			readExitingResultFile(testCaseList);			
		}  
		
		Document docInputXml = Jsoup.parse(inputXml, "UTF-8");
		docInputXml.outputSettings().prettyPrint(false);
		Elements elements = docInputXml.select("testcase");
		
		ArrayList<Testcase> failTcList = new ArrayList<Testcase>();

		//add data to tcList
		for (Element element : elements) {
			Testcase tc = new Testcase();			
			tc.setName(element.attr("name"));
			
			TCSuite suite = suiteGroup.getSuiteForTc(tc);
			if(suite == null) {
				continue;
			}
			tc.setSuiteName(suite.getName());
			tc.setPlatform(suite.getPlatform());
			tc.setProfile(suite.getProfile());
			
			ArrayList<Testcase> suiteTcList = suite.getTcList();
			for (Testcase testcase : suiteTcList) {
				if(testcase.equals(tc)) {
					tc.setTcmID(testcase.getTcmID());
					tc.setModule(testcase.getModule());
					break;
				}
			}
			
			Elements children = element.children();

			if(children.size() > 0) {				
				if(!failTcList.contains(tc)) {
					failTcList.add(tc);
				}
				
				tc.setStatus("Fail");
				
				for (Element child : children) {
					String msg = child.attr("message");					
					
					if(msg.contains("Exception occur in rcptt code")) {
						String failureText = child.html();
						Pattern pattern = Pattern.compile("\\s*data\\s*\\=\\s*.*\\r?\\n\\s*message\\s*\\=\\s*(.*)\\s*");
						Matcher matcher = pattern.matcher(failureText);
						
						while(matcher.find()) {
							String s = matcher.group(1).trim().replaceAll("&quot;", "\"");
							if(!s.contains("The Window \"Save Resource\"") && !s.contains("The Window \"Save Resources\"")) {
								msg =  matcher.group(1).trim();	
							}
						}
					}

					msg = msg.trim().replaceAll(",",  " ").replaceAll("\n", " ").replaceAll("\r", "").replaceAll("&quot;", "\"");
					tc.setMsg(msg);
				}
				
			} else {
				if(element.attr("incomplete").equals("true")) {
					if(!failTcList.contains(tc)) {
						failTcList.add(tc);
					}
					tc.setIncomplete(true);
					tc.setStatus("Fail");
					tc.setMsg("Failed to close report node: RCPTT Connection reset");
				} else {
					tc.setStatus("Pass");
				}
			}
			
			if(testCaseList.contains(tc)) {
				Testcase prevTc = null;
				
				for (Testcase testCase : testCaseList) {
					if(testCase.equals(tc)) {
						if(testCase.getTcmID().equals(tc.getTcmID())) {
							prevTc = testCase;
							break;
						}
					}
				}
				
				if(prevTc == null) {
					testCaseList.add(tc);
				} else {
					if(tc.isIncomplete()) {
						prevTc.setStatus("Fail");
						prevTc.setMsg("Failed to close report node: RCPTT Connection reset");
					} else {
						prevTc.copy(tc);
					}
				}				
			}else {
				testCaseList.add(tc);
			} 
		}
		
		dumpListToResultFile(testCaseList);
		return failTcList;
	}
	

	private static ArrayList<String> readFailList() throws FileNotFoundException {
		ArrayList<String> failTcList = new ArrayList<String>();
		Scanner sc = new Scanner(new File(Utils.getValue(Constants.FAIL_LIST)));
		while(sc.hasNextLine()) {
			String line = sc.nextLine().trim();
			if(line.length()!=0 && !failTcList.contains(line)) {
				failTcList.add(line);
			}
		}
		
		sc.close();
		
		return failTcList;
	}
	
	private static void dumpListToResultFile(ArrayList<Testcase> list) {
		try {
			ArrayList<XMLReportModule> reportModuleList = new ArrayList<XMLReportModule>();
			XMLReportModule.totalTc = 0;
			XMLReportModule.totalPassTc = 0;
			XMLReportModule.totalFailTc = 0;
			XMLReportModule.totalNATc = 0;
			XMLReportModule.totalNETc = 0;
			
			PrintWriter pw = new PrintWriter(new File(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv"));
			pw.write("TCM ID,TC ID,Module,Platform,Profile,Result,Message\n");
			for (Testcase testcase : list) {
				pw.append(testcase.getTcmID()+","+testcase.getName()+","+testcase.getModule()+","+testcase.getPlatform()+","+testcase.getProfile()+","+testcase.getStatus()+","+testcase.getMsg()+"\n");
				XMLReportModule reportModule = new XMLReportModule(testcase.getModule());
				if(reportModuleList.contains(reportModule)) {
					reportModule = reportModuleList.get(reportModuleList.indexOf(reportModule));
				} else {
					reportModuleList.add(reportModule);
				}
				reportModule.increaseTcCount();
				XMLReportModule.totalTc++; 
				if(testcase.getStatus().equals("Pass")) {
					reportModule.increasePassTcCount();
					XMLReportModule.totalPassTc++;
				} else if(testcase.getStatus().equals("NA")) {
					reportModule.increaseNATcCount();
					XMLReportModule.totalNATc++;
				} else if(testcase.getStatus().equals("Fail")) {
					reportModule.increaseFailTcCount();
					XMLReportModule.totalFailTc++;
				} else {
					reportModule.increaseNETcCount();
					XMLReportModule.totalNETc++;
				}
			}
			
			pw.flush();
			pw.close();
			writeResultXML(reportModuleList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
		
	public static void writeResultXML(ArrayList<XMLReportModule> moduleListList) {
		try {
			
			org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			
			org.w3c.dom.Element root = doc.createElement("report");
			doc.appendChild(root);
			org.w3c.dom.ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", " href=\"style-report.css\"");
			doc.insertBefore(pi, root);
			//creating summary table
			org.w3c.dom.Element summary = doc.createElement("summary");
			root.appendChild(summary);
			
			org.w3c.dom.Element summaryTitle = doc.createElement("data-caption");
			summaryTitle.setTextContent("Summary");
			summary.appendChild(summaryTitle);
			
			org.w3c.dom.Element titleRow = doc.createElement("summary-row");
			summary.appendChild(titleRow);
			
			org.w3c.dom.Element h1 = doc.createElement("data-header");
			h1.setTextContent("Total TCs");
			titleRow.appendChild(h1);
			org.w3c.dom.Element h2 = doc.createElement("data-header");
			h2.setTextContent("Pass");
			titleRow.appendChild(h2);
			org.w3c.dom.Element h3 = doc.createElement("data-header");
			h3.setTextContent("Fail");
			titleRow.appendChild(h3);
			org.w3c.dom.Element h4 = doc.createElement("data-header");
			h4.setTextContent("NA");
			titleRow.appendChild(h4);
			org.w3c.dom.Element h5 = doc.createElement("data-header");
			h5.setTextContent("NE");
			titleRow.appendChild(h5);
			org.w3c.dom.Element h6 = doc.createElement("data-header");
			h6.setTextContent("Pass Rate");
			titleRow.appendChild(h6);
			
			org.w3c.dom.Element dataRow = doc.createElement("data-row");
			summary.appendChild(dataRow);
			
			org.w3c.dom.Element v1 = doc.createElement("data-cell");
			v1.setTextContent(""+XMLReportModule.totalTc);
			dataRow.appendChild(v1);
			org.w3c.dom.Element v2 = doc.createElement("data-cell");
			v2.setTextContent(""+XMLReportModule.totalPassTc);
			dataRow.appendChild(v2);
			org.w3c.dom.Element v3 = doc.createElement("data-cell");
			v3.setTextContent(""+XMLReportModule.totalFailTc);
			dataRow.appendChild(v3);
			org.w3c.dom.Element v4 = doc.createElement("data-cell");
			v4.setTextContent(""+XMLReportModule.totalNATc);
			dataRow.appendChild(v4);
			org.w3c.dom.Element v5 = doc.createElement("data-cell");
			v5.setTextContent(""+XMLReportModule.totalNETc);
			dataRow.appendChild(v5);
			org.w3c.dom.Element v6 = doc.createElement("data-cell");
			v6.setTextContent(""+XMLReportModule.getGolbalPassRate());
			dataRow.appendChild(v6);
			
			// end of creating summary table
			
			//creating content table
			org.w3c.dom.Element content = doc.createElement("data");
			root.appendChild(content);
			
			org.w3c.dom.Element contentTitle = doc.createElement("data-caption");
			contentTitle.setTextContent("Result Details");
			content.appendChild(contentTitle);
			
			org.w3c.dom.Element contentTitleRow = doc.createElement("data-row");
			content.appendChild(contentTitleRow);
			org.w3c.dom.Element head1 = doc.createElement("data-header");
			head1.setTextContent("Module");
			contentTitleRow.appendChild(head1);
			org.w3c.dom.Element head2 = doc.createElement("data-header");
			head2.setTextContent("TCs");
			contentTitleRow.appendChild(head2);
			org.w3c.dom.Element head3 = doc.createElement("data-header");
			head3.setTextContent("Pass");
			contentTitleRow.appendChild(head3);
			org.w3c.dom.Element head4 = doc.createElement("data-header");
			head4.setTextContent("Fail");
			contentTitleRow.appendChild(head4);
			org.w3c.dom.Element head5 = doc.createElement("data-header");
			head5.setTextContent("NA");
			contentTitleRow.appendChild(head5);
			org.w3c.dom.Element head6 = doc.createElement("data-header");
			head6.setTextContent("NE");
			contentTitleRow.appendChild(head6);
			org.w3c.dom.Element head7 = doc.createElement("data-header");
			head7.setTextContent("Pass Rate");
			contentTitleRow.appendChild(head7);
			
			for (XMLReportModule module : moduleListList) {
				org.w3c.dom.Element row = doc.createElement("data-row");
				content.appendChild(row);
				
				org.w3c.dom.Element val1 = doc.createElement("data-cell");
				val1.setTextContent(module.getName());
				row.appendChild(val1);
				org.w3c.dom.Element val2 = doc.createElement("data-cell");
				val2.setTextContent(module.getTcCount()+"");
				row.appendChild(val2);
				org.w3c.dom.Element val3 = doc.createElement("data-cell");
				val3.setTextContent(""+module.getPassTc());
				row.appendChild(val3);
				org.w3c.dom.Element val4 = doc.createElement("data-cell");
				val4.setTextContent(""+module.getFailTc());
				row.appendChild(val4);
				org.w3c.dom.Element val5 = doc.createElement("data-cell");
				val5.setTextContent(""+module.getNaTc());
				row.appendChild(val5);
				org.w3c.dom.Element val6 = doc.createElement("data-cell");
				val6.setTextContent(""+module.getNeTc());
				row.appendChild(val6);
				org.w3c.dom.Element val7 = doc.createElement("data-cell");
				val7.setTextContent(""+module.getPassRate());
				row.appendChild(val7);
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    transformer.transform(new DOMSource(doc), result);

		    PrintWriter pw = new PrintWriter(new File( "report.xml"));
//		    pw.write("<?xml-stylesheet href=\"style1.css\"?>\n");
		    pw.write(writer.toString());
		    pw.flush();
		    pw.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	private static void readExitingResultFile(ArrayList<Testcase> testCaseList) throws IOException {
		Scanner sc = new Scanner(new File(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv"));
		
		sc.nextLine();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			String parts[] = line.split(",");
			if(parts.length < 6) {
				System.out.println(line);				
				throw new IOException(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv" + " - Wrong formated report file...");
			}
			String tcmID = parts[0];
			String name = parts[1];
			String module = parts[2];
			String platform = parts[3];
			String profile = parts[4];
			String status = parts[5];
			String msg = "";
			if(parts.length == 7 ) {
				msg = parts[6];
			}
			
			
			Testcase tc = new Testcase();
			tc.setTcmID(tcmID);
			tc.setName(name);
			tc.setModule(module);
			tc.setPlatform(platform);
			tc.setProfile(profile);
			tc.setStatus(status);
			tc.setMsg(msg);
			testCaseList.add(tc);
		}		
		sc.close();
	}
	
	public static boolean isTcPassFromXml(String tcName) {
		File inputXml = new File(Utils.getValue(Constants.SOURCE_XML_PATH));
		Document docInputXml;
		try {
			docInputXml = Jsoup.parse(inputXml , "UTF-8");
			docInputXml.outputSettings().prettyPrint(false);
			Elements elements = docInputXml.select("testcase");

			for (Element element : elements) {
				
				if(element.attr("name").equals(tcName)) {
					Elements children = element.children();

					if(children.size() > 0) {
						for (Element child : children) {
							String msg = child.attr("message");					
							
							if(msg.contains("Exception occur in rcptt code")) {
								String failureText = child.html();
								Pattern pattern = Pattern.compile("\\s*data\\s*\\=\\s*.*\\r?\\n\\s*message\\s*\\=\\s*(.*)\\s*");
								Matcher matcher = pattern.matcher(failureText);
								
								while(matcher.find()) {
									String s = matcher.group(1).trim().replaceAll("&quot;", "\"");
									if(!s.contains("The Window \"Save Resource\"") && !s.contains("The Window \"Save Resources\"")) {
										msg =  matcher.group(1).trim();	
									}
								}
							}		
							
							msg = msg.trim().replaceAll(",",  " ").replaceAll("\n", " ").replaceAll("\r", "").replaceAll("&quot;", "\"");
							System.out.println(tcName + " failed. Error: " + msg);
						}
						return false;
					} else {
						if(element.attr("incomplete").equals("true")) {
							System.out.println(tcName + " failed. Error: Failed to close report node: Connection reset");
							return false;
						} else {
							return true;
						}
					}
				}				
			}
		} catch (IOException e) {
			System.out.println(Utils.getValue(Constants.SOURCE_XML_PATH)+" - file is not available...");
		}
		return false;
	}
	
}
