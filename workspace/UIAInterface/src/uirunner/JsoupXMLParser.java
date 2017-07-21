package uirunner;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import fxviewer.AppController;
import fxviewer.ConfigUtil;
import fxviewer.Constants;
import fxviewer.SampleAppController;
import fxviewer.StoreAppController;

public class JsoupXMLParser {

	// public static ArrayList<AppInfo> parseXML(String filePath) {
	// ArrayList<AppInfo> appInfoList = new ArrayList<AppInfo>();
	// try {
	// Document doc = Jsoup.parse(new File(filePath), "UTF-8");
	// Elements apps = doc.select("app");
	//
	// for (Element app : apps) {
	// String name = app.attr("name");
	// String binary = app.attr("binary");
	//
	// ArrayList<TestInfo> testcases = new ArrayList<TestInfo>();
	// Elements childern = app.children().select("TC");
	//
	// for (Element child : childern) {
	// String tcName = child.attr("name");
	// TestInfo testcase = new TestInfo(tcName, TCResult.NT);
	// testcase.setLevel(Integer.parseInt(child.attr("level")));
	// testcase.setPreCondition(child.attr("precondition"));
	// testcases.add(testcase);
	// }
	//
	// appInfoList.add(new AppInfo(testcases, name, binary));
	// }
	//
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return appInfoList;
	// }

	public static void writeStoreAppXml(StoreAppController controller, String mode) {
		try {

				java.util.List<StoreAppInfo> appList = controller.getTableRows();
				org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

				org.w3c.dom.Element root = doc.createElement("meunutree");
				doc.appendChild(root);

				for (StoreAppInfo appInfo : appList) {
					if(!appInfo.getCheckBox()) {
						continue;
					}
					org.w3c.dom.Element row = doc.createElement("app");
					row.setAttribute("javafile-name", appInfo.getJavaFileName());
					//row.setAttribute("path", appInfo.getFullPath());
					row.setAttribute("path", appInfo.getRelativePackagePath());
					root.appendChild(row);

					if(controller.isInstall()) {
						org.w3c.dom.Element install = doc.createElement("TC");
						install.setAttribute("level", "1");
						install.setAttribute("name", "install");
						install.setAttribute("precondition", "");
						row.appendChild(install);
					}
					if(controller.isLaunch()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "2");
						launch.setAttribute("name", "launch");
						launch.setAttribute("precondition", "install");
						row.appendChild(launch);
					}
					if(controller.isExploratory()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "3");
						launch.setAttribute("name", "exploratory");
						launch.setAttribute("precondition", "launch");
						row.appendChild(launch);
					}
					if(controller.isCloseCheck()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "3");
						launch.setAttribute("name", "close");
						launch.setAttribute("precondition", "launch");
						row.appendChild(launch);
					}
					if(controller.isUninstallCheck()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "2");
						launch.setAttribute("name", "uninstall");
						launch.setAttribute("precondition", "install");
						row.appendChild(launch);
					}
					if(controller.isCrash()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "3");
						launch.setAttribute("name", "detectCrash");
						launch.setAttribute("precondition", "launch");
						row.appendChild(launch);

					}

				}
				 // create Transformer object
			    Transformer transformer = TransformerFactory.newInstance().newTransformer();
			    StringWriter writer = new StringWriter();
			    StreamResult result = new StreamResult(writer);
			    transformer.transform(new DOMSource(doc), result);

			    PrintWriter pw = new PrintWriter("./res/xml/storeapp_"+mode+".xml");
			    pw.write(writer.toString());
			    pw.flush();
			    pw.close();


		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void writeSampleAppXml(SampleAppController controller, String mode) {
		try {

				java.util.List<SampleAppInfo> appList = (controller).getTableRows();
				org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

				org.w3c.dom.Element root = doc.createElement("meunutree");
				doc.appendChild(root);

				for (SampleAppInfo appInfo : appList) {
					if(!appInfo.getCheckBox()) {
						continue;
					}
					org.w3c.dom.Element row = doc.createElement("app");
					//row.setAttribute("project-type", appInfo.getProjectType());
					row.setAttribute("app-type", appInfo.appTypeProperty().getValue());
					row.setAttribute("profile", appInfo.profileProperty().getValue());
					row.setAttribute("required", appInfo.requiredVersionProperty().getValue());
					row.setAttribute("appname", appInfo.appNameProperty().getValue());
					row.setAttribute("rootstrap", ConfigUtil.getValue(Constants.SELECTED_ROOTSTRAP));
					row.setAttribute("javafile-name", appInfo.getJavaFileName());

					root.appendChild(row);

					if(controller.isBuild()) {
						org.w3c.dom.Element build = doc.createElement("TC");
						build.setAttribute("level", "1");
						build.setAttribute("name", "build");
						build.setAttribute("precondition", "");
						row.appendChild(build);
						if(controller.isPackageing()) {
							org.w3c.dom.Element packaging = doc.createElement("TC");
							packaging.setAttribute("level", "2");
							packaging.setAttribute("name", "packaging");
							packaging.setAttribute("precondition", "build");
							row.appendChild(packaging);
							if(controller.isInstall()) {
								org.w3c.dom.Element install = doc.createElement("TC");
								install.setAttribute("level", "3");
								install.setAttribute("name", "install");
								install.setAttribute("precondition", "packaging");
								row.appendChild(install);
								if(controller.isLaunch()) {
									org.w3c.dom.Element launch = doc.createElement("TC");
									launch.setAttribute("level", "4");
									launch.setAttribute("name", "launch");
									launch.setAttribute("precondition", "install");
									row.appendChild(launch);
									if(controller.isExploratory()) {
										org.w3c.dom.Element exploratory = doc.createElement("TC");
										exploratory.setAttribute("level", "5");
										exploratory.setAttribute("name", "exploratory");
										exploratory.setAttribute("precondition", "launch");
										row.appendChild(exploratory);
									}
									if(controller.isCloseCheck()) {
										org.w3c.dom.Element close = doc.createElement("TC");
										close.setAttribute("level", "5");
										close.setAttribute("name", "close");
										close.setAttribute("precondition", "");
										close.setAttribute("precondition", "launch");
										row.appendChild(close);
									}

									if(controller.isCrash()) {
										org.w3c.dom.Element crash = doc.createElement("TC");
										crash.setAttribute("level", "5");
										crash.setAttribute("name", "detectCrash");
										crash.setAttribute("precondition", "launch");
										row.appendChild(crash);

									}
								}
								if(controller.isUninstallCheck()) {
									org.w3c.dom.Element uninstall = doc.createElement("TC");
									uninstall.setAttribute("level", "4");
									uninstall.setAttribute("name", "uninstall");
									uninstall.setAttribute("precondition", "install");
									uninstall.setAttribute("precondition", "install");
									row.appendChild(uninstall);
								}
							}
						}
					}

					else if(controller.isPackageing()) {
						org.w3c.dom.Element packaging = doc.createElement("TC");
						packaging.setAttribute("level", "2");
						packaging.setAttribute("name", "packaging");
						packaging.setAttribute("precondition", "");
						row.appendChild(packaging);
						if(controller.isInstall()) {
							org.w3c.dom.Element install = doc.createElement("TC");
							install.setAttribute("level", "3");
							install.setAttribute("name", "install");
							install.setAttribute("precondition", "packaging");
							row.appendChild(install);
							if(controller.isLaunch()) {
								org.w3c.dom.Element launch = doc.createElement("TC");
								launch.setAttribute("level", "4");
								launch.setAttribute("name", "launch");
								launch.setAttribute("precondition", "install");
								row.appendChild(launch);
								if(controller.isExploratory()) {
									org.w3c.dom.Element exploratory = doc.createElement("TC");
									exploratory.setAttribute("level", "5");
									exploratory.setAttribute("name", "exploratory");
									exploratory.setAttribute("precondition", "launch");
									row.appendChild(exploratory);
								}
								if(controller.isCloseCheck()) {
									org.w3c.dom.Element close = doc.createElement("TC");
									close.setAttribute("level", "5");
									close.setAttribute("name", "close");
									close.setAttribute("precondition", "");
									close.setAttribute("precondition", "launch");
									row.appendChild(close);
								}

								if(controller.isCrash()) {
									org.w3c.dom.Element crash = doc.createElement("TC");
									crash.setAttribute("level", "5");
									crash.setAttribute("name", "detectCrash");
									crash.setAttribute("precondition", "launch");
									row.appendChild(crash);

								}
							}
							if(controller.isUninstallCheck()) {
								org.w3c.dom.Element uninstall = doc.createElement("TC");
								uninstall.setAttribute("level", "4");
								uninstall.setAttribute("name", "uninstall");
								uninstall.setAttribute("precondition", "install");
								uninstall.setAttribute("precondition", "install");
								row.appendChild(uninstall);
							}
						}
					}

					else if(controller.isInstall()) {
						org.w3c.dom.Element install = doc.createElement("TC");
						install.setAttribute("level", "3");
						install.setAttribute("name", "install");
						install.setAttribute("precondition", "");
						row.appendChild(install);
						if(controller.isLaunch()) {
							org.w3c.dom.Element launch = doc.createElement("TC");
							launch.setAttribute("level", "4");
							launch.setAttribute("name", "launch");
							launch.setAttribute("precondition", "install");
							row.appendChild(launch);
							if(controller.isExploratory()) {
								org.w3c.dom.Element exploratory = doc.createElement("TC");
								exploratory.setAttribute("level", "5");
								exploratory.setAttribute("name", "exploratory");
								exploratory.setAttribute("precondition", "launch");
								row.appendChild(exploratory);
							}
							if(controller.isCloseCheck()) {
								org.w3c.dom.Element close = doc.createElement("TC");
								close.setAttribute("level", "5");
								close.setAttribute("name", "close");
								close.setAttribute("precondition", "");
								close.setAttribute("precondition", "launch");
								row.appendChild(close);
							}

							if(controller.isCrash()) {
								org.w3c.dom.Element crash = doc.createElement("TC");
								crash.setAttribute("level", "5");
								crash.setAttribute("name", "detectCrash");
								crash.setAttribute("precondition", "launch");
								row.appendChild(crash);

							}
						}
						if(controller.isUninstallCheck()) {
							org.w3c.dom.Element uninstall = doc.createElement("TC");
							uninstall.setAttribute("level", "4");
							uninstall.setAttribute("name", "uninstall");
							uninstall.setAttribute("precondition", "install");
							uninstall.setAttribute("precondition", "install");
							row.appendChild(uninstall);
						}
					}
					else if(controller.isLaunch()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "4");
						launch.setAttribute("name", "launch");
						launch.setAttribute("precondition", "");
						row.appendChild(launch);
						if(controller.isExploratory()) {
							org.w3c.dom.Element exploratory = doc.createElement("TC");
							exploratory.setAttribute("level", "5");
							exploratory.setAttribute("name", "exploratory");
							exploratory.setAttribute("precondition", "launch");
							row.appendChild(exploratory);
						}
						if(controller.isCloseCheck()) {
							org.w3c.dom.Element close = doc.createElement("TC");
							close.setAttribute("level", "5");
							close.setAttribute("name", "close");
							close.setAttribute("precondition", "");
							close.setAttribute("precondition", "launch");
							row.appendChild(close);
						}

						if(controller.isCrash()) {
							org.w3c.dom.Element crash = doc.createElement("TC");
							crash.setAttribute("level", "5");
							crash.setAttribute("name", "detectCrash");
							crash.setAttribute("precondition", "launch");
							row.appendChild(crash);

						}

						if(controller.isUninstallCheck()) {
							org.w3c.dom.Element uninstall = doc.createElement("TC");
							uninstall.setAttribute("level", "4");
							uninstall.setAttribute("name", "uninstall");
							uninstall.setAttribute("precondition", "");
							row.appendChild(uninstall);
						}
					}
					else if(controller.isUninstallCheck()) {
						org.w3c.dom.Element uninstall = doc.createElement("TC");
						uninstall.setAttribute("level", "4");
						uninstall.setAttribute("name", "uninstall");
						uninstall.setAttribute("precondition", "");
						row.appendChild(uninstall);
					}
					/*if(controller.isBuild()) {
						org.w3c.dom.Element build = doc.createElement("TC");
						build.setAttribute("level", "1");
						build.setAttribute("name", "build");
						build.setAttribute("precondition", "");
						row.appendChild(build);
					}
					if(controller.isPackageing()) {
						org.w3c.dom.Element packaging = doc.createElement("TC");
						packaging.setAttribute("level", "2");
						packaging.setAttribute("name", "packaging");
						//packaging.setAttribute("precondition", "build");
						packaging.setAttribute("precondition", "");
						row.appendChild(packaging);
					}
					if(controller.isInstall()) {
						org.w3c.dom.Element install = doc.createElement("TC");
						install.setAttribute("level", "1");
						install.setAttribute("name", "install");
						install.setAttribute("precondition", "");
						row.appendChild(install);
					}
					if(controller.isLaunch()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "2");
						launch.setAttribute("name", "launch");
						//launch.setAttribute("precondition", "install");
						launch.setAttribute("precondition", "");
						row.appendChild(launch);
					}
					if(controller.isExploratory()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "3");
						launch.setAttribute("name", "exploratory");
						//launch.setAttribute("precondition", "launch");
						launch.setAttribute("precondition", "");
						row.appendChild(launch);
					}
					if(controller.isCloseCheck()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "3");
						launch.setAttribute("name", "close");
						launch.setAttribute("precondition", "");
						//launch.setAttribute("precondition", "launch");
						row.appendChild(launch);
					}
					if(controller.isUninstallCheck()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "2");
						launch.setAttribute("name", "uninstall");
						launch.setAttribute("precondition", "install");
						launch.setAttribute("precondition", "install");
						row.appendChild(launch);
					}
					if(controller.isCrash()) {
						org.w3c.dom.Element launch = doc.createElement("TC");
						launch.setAttribute("level", "1");
						launch.setAttribute("name", "detectCrash");
						launch.setAttribute("precondition", "launch");
						row.appendChild(launch);

					}
*/
				}
				 // create Transformer object
			    Transformer transformer = TransformerFactory.newInstance().newTransformer();
			    StringWriter writer = new StringWriter();
			    StreamResult result = new StreamResult(writer);
			    transformer.transform(new DOMSource(doc), result);

			    PrintWriter pw = new PrintWriter("./res/xml/sampleapp_"+mode+".xml");
			    pw.write(writer.toString());
			    pw.flush();
			    pw.close();


		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
