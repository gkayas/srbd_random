import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utils {
	private static HashMap<String, String> devInfo = new HashMap<String, String>(); 
		
	private static void createDirectory(File directory) {
		if(!directory.exists()){
			try {
				directory.mkdir();
			} catch (SecurityException e) {
				System.out.println("[SDKAutomator] Can not create platform specific result folder...");
			}
		}
	}
	
	public static void createInitialDirectory() {
		createDirectory(new File(getValue(Constants.LOG_PATH)));
		createDirectory(new File(getValue(Constants.DEFAULT_REPORT_PATH)));
		createDirectory(new File(getValue(Constants.AUTOMATION_WORKSPACE)));
	}
	
	public static void readParameterVariable() {
		String filePath;
		filePath = "./" + Constants.CONFIG_FILE;
		
		try {		
			Scanner sc = new Scanner(new File(filePath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String tokens[] = line.split("=");
				
				if(tokens.length == 1) {
					devInfo.put(tokens[0].trim(), "");
				} else {
					devInfo.put(tokens[0].trim(), tokens[1].trim());
				}
			}
			
			sc.close();
			
			File file = new File("..");
			String suffixPath = file.getCanonicalPath();
			
			String rootPath = "";
			if(isWindows())	{
				rootPath = System.getenv("WINDIR");
				rootPath = rootPath.replace("\\Windows", "");
			} else {
				rootPath = System.getProperty("user.home");
			}
				
			devInfo.put(Constants.PROJECT, suffixPath + File.separatorChar + getValue(Constants.PROJECT_NAME));
			devInfo.put(Constants.RUNNER, suffixPath + File.separatorChar + getValue(Constants.RCPTT_VERSION) + File.separatorChar + "eclipse");
			devInfo.put(Constants.AUT_PATH, getValue(Constants.SDK_PATH) + File.separatorChar + "ide");
			devInfo.put(Constants.SDB_PATH, getValue(Constants.SDK_PATH) + File.separatorChar + "tools" + File.separatorChar);
			
			
			devInfo.put(Constants.AUTOMATION_WORKSPACE, rootPath + File.separatorChar + "tizen-studio-sdkautomator");			
			devInfo.put(Constants.RUNNER_WORKSPACE, getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "runner-workspace");
			devInfo.put(Constants.AUT_WORKSPACE, getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "aut-workspace");
			devInfo.put(Constants.AUT_OUT, getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "aut-out-");
			devInfo.put(Constants.JUNIT_REPORT, getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "results.xml");			
			devInfo.put(Constants.SOURCE_XML_PATH, getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "results.junit.xml");
			
			devInfo.put(Constants.LOG_PATH, suffixPath + File.separatorChar + "sdkautomator" + File.separatorChar + "log");
			
			devInfo.put(Constants.DEFAULT_REPORT_PATH, suffixPath + File.separatorChar + "sdkautomator" + File.separatorChar + "results");
			
			File reportFile = new File(getValue(Constants.REPORT_PATH));
			if(!reportFile.exists()) {
				devInfo.put(Constants.REPORT_PATH, getValue(Constants.DEFAULT_REPORT_PATH));
				writeParameterVariable();
			}
		} catch (FileNotFoundException e) {
			System.out.println("[SDKAutomator] ParameterVariable file not found...");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("[SDKAutomator] ParameterVariable file format is not correct...");
			System.exit(0);
		}	
	}
	
	public static boolean writeParameterVariable() {
		String configPath;
		configPath = "./" + Constants.CONFIG_FILE;
		try {
			PrintWriter pw = new PrintWriter(new File(configPath));
			pw.write(Constants.SERVER_ID + "="+devInfo.get(Constants.SERVER_ID)+"\n");
			pw.write(Constants.SERVER_PASSWORD + "="+devInfo.get(Constants.SERVER_PASSWORD)+"\n");
			pw.write(Constants.SERVER_LINK + "="+devInfo.get(Constants.SERVER_LINK)+"\n");
			pw.write(Constants.PROJECT_NAME + "="+devInfo.get(Constants.PROJECT_NAME)+"\n");
			pw.write(Constants.RCPTT_VERSION + "="+devInfo.get(Constants.RCPTT_VERSION)+"\n");
			pw.write(Constants.RCPTT_LAUNCHER + "="+devInfo.get(Constants.RCPTT_LAUNCHER)+"\n");
			pw.write(Constants.SDK_PATH + "="+devInfo.get(Constants.SDK_PATH)+"\n");
			pw.write(Constants.REPORT_PATH + "="+devInfo.get(Constants.REPORT_PATH));
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println(configPath + " file not found...");
			return false;
		} catch (Exception e) {
			System.out.println(configPath + " file not found...");
		}

		return true;
	}
	
	private static String getSDBVersion() {
		String sdbVersion = "2.2.88";
		StringBuffer msg = new StringBuffer();
		try {			
			Thread runner = new Thread(new Runnable() {			
		    
				@Override
				public void run() {
					
					Process process = null;
			        try {
			        	if (Utils.isWindows()) {
			        		String [] cmd = {"cmd", "/c",devInfo.get(Constants.SDB_PATH)+"sdb","version"};
			        		process = new ProcessBuilder(cmd).start();
			        	} else {
			        		String [] cmd = {devInfo.get(Constants.SDB_PATH)+"sdb","version"};
			        		process = new ProcessBuilder(cmd).start();
						}			        	
			            
			            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			            
			            String message;
			            while((message=reader.readLine()) != null) {
			            	msg.append(message);
			            	System.out.println(message);
			            }	        	
			            reader.close();
			            process.destroy();		            
			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
						process.destroy();		  
					}
				}				
			}) ;		
			runner.start();
			runner.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		Scanner sc = new Scanner(msg.toString());
		while(sc.hasNext()) {
			sdbVersion = sc.next();
		}
		sc.close();		
		return sdbVersion;
	}

	public static String getCommonPlatform(String profile) {
		if(profile.equalsIgnoreCase("Mobile")) {
			return "2.4";
		} else if(profile.equalsIgnoreCase("Wearable")) {
			return "2.3.1";
		}

		return "2.4";
	}
	
	private static String getEmulatorType(String platform, String profile) {
		platform = (platform.equalsIgnoreCase("Common")) ? Utils.getCommonPlatform(profile) : platform;		
		
		if(profile.equalsIgnoreCase("Mobile")) {
			if(platform.equals("3.0")) {
				return "WVGA";
			} else if(platform.equals("2.4")) {
				return "WVGA";
			} else if(platform.equals("2.3")) {
				return "WVGA";
			} else if(platform.equals("2.3.1")) {
				return "WVGA";
			} else if(platform.equals("2.3.2")) {
				return "WVGA";
			}
		} else if(profile.equalsIgnoreCase("Wearable")) {
			if(platform.equals("3.0")) {
				return "SQUARE";
			} else if(platform.equals("2.4")) {
				return "SQUARE";
			} else if(platform.equals("2.3")) {
				return "SQUARE";
			} else if(platform.equals("2.3.1")) {
				return "SQUARE";
			} else if(platform.equals("2.3.2")) {
				return "CIRCLE";
			}
		}
		return "WVGA";
	}
	
	public static boolean writeRcpttParameterVariable(String platform, String profile) {
		String fileName = getValue(Constants.PROJECT)+File.separatorChar+"Common"+File.separatorChar+"Configurations"+File.separatorChar+"ParametersVariable.ctx";
		try {
			Scanner sc = new Scanner(new File(fileName));
			StringBuffer sb = new StringBuffer();
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				String [] parts = line.split("=");
				if(parts[0].equals(Constants.SDB_VERSION)) {
					sb.append(Constants.SDB_VERSION+"="+getSDBVersion());
				}  else if(parts[0].equals(Constants.SERVER_LINK)) {
					sb.append(Constants.SERVER_LINK+"="+Utils.getValue(Constants.SERVER_LINK));
				} else if(parts[0].equals(Constants.SERVER_PASSWORD)) {
					sb.append(Constants.SERVER_PASSWORD+"="+Utils.getValue(Constants.SERVER_PASSWORD));
				} else if(parts[0].equals(Constants.SERVER_ID)) {
					sb.append(Constants.SERVER_ID+"="+Utils.getValue(Constants.SERVER_ID));
				} else if(parts[0].equals(Constants.PROFILE_NAME)) {
					sb.append(Constants.PROFILE_NAME+"="+profile);
				} else if(parts[0].equals(Constants.PLATFORM_VERSION)) {
					sb.append(Constants.PLATFORM_VERSION+"="+platform);
				} else if(parts[0].equals(Constants.EMULATOR_TYPE)) {
					sb.append(Constants.EMULATOR_TYPE+"="+getEmulatorType(platform, profile));
				} else if(parts[0].equals(Constants.EMULATOR_ARCH)) {
					sb.append(Constants.EMULATOR_ARCH+"="+"x86");
				} else if(parts[0].equals(Constants.PROJECT)) {
					File current = new File(Utils.getValue(Constants.PROJECT));
					String projectPath = current.getCanonicalPath();

					if(isWindows()) {
						projectPath = projectPath.replace("\\", "\\\\");
						projectPath = projectPath.replace(":", "\\:");
						sb.append(Constants.PROJECT+"="+projectPath);
					} else {
						sb.append(Constants.PROJECT+"="+projectPath);
					}
					
				} else {
					sb.append(line);
				}
				
				sb.append("\n");
			}
			
			sc.close();
			PrintWriter pw = new PrintWriter(new File(getValue(Constants.PROJECT)+File.separatorChar+"Common"+File.separatorChar+"Configurations"+File.separatorChar+"ParametersVariable.ctx"));
		
			pw.write(sb.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			System.out.println(fileName + " file not found...");
			return false;
		}
		
		return true;
	}
	
	public static Iterator<String> getKeys() {
		return devInfo.keySet().iterator();
	}
	
	public static String getValue(String key) {
		return devInfo.get(key);
	}
	
	public static String setValue(String key, String value) {
		return devInfo.put(key, value);
	}
	
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}


	public static String prepareTcStringFromList(ArrayList<Testcase> tcList) {
		
		String tcString = "";
		
		for (Testcase testcase : tcList) {
			tcString += testcase.getName()+".test"+";";
		}
		tcString = tcString.substring(0, tcString.length()-1);		
		
		return tcString;
	}

	public static ArrayList<Testcase> readFailTcListFromResultFile() {
		Scanner sc;
		ArrayList<Testcase> failTcList = new ArrayList<Testcase>();
		
		try {
			sc = new Scanner(new File(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv"));
			System.out.println("[SDKAutomator] Start reading fail TCs from " + Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv");

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
				
				if(status.equals("Fail")) {
					Testcase tc = new Testcase();
					tc.setTcmID(tcmID);
					tc.setName(name);
					tc.setModule(module);
					tc.setPlatform(platform);
					tc.setProfile(profile);
					tc.setStatus(status);
					tc.setMsg(msg);
					
					failTcList.add(tc);	
					
					System.out.println(line);
				}				
			}	
			
			sc.close();
		} catch(Exception e) {
			System.out.println(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv" +" - file is not available...");
			return null;
		}
		return failTcList;
	}

	public static ArrayList<TCSuite> readSuiteList() {
		ArrayList<TCSuite> suiteList = new ArrayList<TCSuite>();
		Scanner sc;
		try {
			sc = new Scanner(new File("suitelists"));

			while(sc.hasNextLine()) {
				String line  = sc.nextLine();
				line.trim();
				if(line.length() == 0) {
					continue;
				}
				String [] parts = line.split(",");
				if(parts.length < 6) {
					System.out.println("[SDKAutomator] wrong formatted suitelists file...");
					sc.close();
					suiteList.clear();
					return suiteList;
				}
				String tcmID = parts[0];
				String tcName = parts[1];
				String module = parts[2];
				String suiteName = parts[3];
				String platform = parts[4];
				String profile = parts[5];

				Testcase tc = new Testcase();
				tc.setName(tcName);
				tc.setTcmID(tcmID);
				tc.setModule(module);

				TCSuite suite = new TCSuite();
				suite.setName(suiteName);
				suite.setPlatform(platform);
				suite.setProfile(profile);

				if(!suiteList.contains(suite)) {
					suite.addTc(tc);
					suiteList.add(suite);
				} else {
					suiteList.get(suiteList.indexOf(suite)).addTc(tc);;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("[SDKAutomator] suitelists - file is not available...");
		}

		return suiteList;
	}
	
	public static ArrayList<SuiteGroup> readSuiteGroup() {
		ArrayList<SuiteGroup> suiteGroupList = new ArrayList<SuiteGroup>();
		Scanner sc;
		try {
			sc = new Scanner(new File("suitelists"));
			
			System.out.println("[SDKAutomator] Start reading suitelists file...");
			
			while(sc.hasNextLine()) {
				String line  = sc.nextLine();
				System.out.println(line);
				
				line.trim();
				if(line.length() == 0) {
					continue;
				}
				
				String [] parts = line.split(",");
				if(parts.length < 6) {
					System.out.println("[SDKAutomator] Wrong formatted suitelists file...");
					sc.close();
					suiteGroupList.clear();
					return suiteGroupList;
				}
				
				
				String tcmID = parts[0];
				String tcName = parts[1];
				String module = parts[2];
				String suiteName = parts[3];
				String platform = parts[4];
				String profile = parts[5];

				SuiteGroup suiteGroup = new SuiteGroup(platform, profile);
				
				if(suiteGroupList.contains(suiteGroup)) {
					suiteGroup = suiteGroupList.get(suiteGroupList.indexOf(suiteGroup));
				} else { 
					suiteGroupList.add(suiteGroup);
				}
				
				Testcase tc = new Testcase();
				tc.setName(tcName);
				tc.setTcmID(tcmID);
				tc.setModule(module);

				TCSuite suite = new TCSuite();
				suite.setName(suiteName);
				suite.setPlatform(platform);
				suite.setProfile(profile);

				if(!suiteGroup.getSuiteList().contains(suite)) {
					suite.addTc(tc);
					suiteGroup.getSuiteList().add(suite);
				} else {
					suiteGroup.getSuiteList().get(suiteGroup.getSuiteList().indexOf(suite)).addTc(tc);;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("[SDKAutomator] suitelists - file is not available...");
		}

		return suiteGroupList;
	}
	
	public static ArrayList<CSVRow> readMasterSuiteList(String project) {
		String filePath;
		if(project.equals("BVT")) {
			filePath = System.getProperty("user.dir")+File.separatorChar+"suite"+File.separatorChar+"SuitList__BVT_Tizen_Studio.csv";
		} else if(project.equals("Non-BVT")) {
			filePath = System.getProperty("user.dir")+File.separatorChar+"suite"+File.separatorChar+"SuitList__Non_BVT_Tizen_Studio.csv";
		} else if(project.equals("ECP-CLI")) {
			filePath = System.getProperty("user.dir")+File.separatorChar+"suite"+File.separatorChar+"SuitList__ECP_CLI_Tizen_Studio.csv";
		} else {
			filePath = System.getProperty("user.dir")+File.separatorChar+"suite"+File.separatorChar+"SuitList__ECP_CLI_Tizen_Studio.csv";
		}
		
		File selectedFile = new File(filePath);

		ArrayList<CSVRow> csvRowList = new ArrayList<CSVRow>();
		
		try {
			Scanner sc = new Scanner(selectedFile);
			if(sc.hasNextLine()) {
				sc.nextLine();
			}

			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String [] parts = line.split(",");
				if(parts.length < 6) {
					System.out.println(filePath + " - Wrong formatted file!");
					sc.close();
					csvRowList.clear();
					return csvRowList;
				}
				String tcmID = parts[0];
				String tcID = parts[1];
				String module = parts[2];
				String suite = parts[3];
				String platform = parts[4];
				String profile = parts[5];
				
				CSVRow csvRow = new CSVRow();
				csvRow.setTcmID(tcmID);
				csvRow.setTcID(tcID);
				csvRow.setModule(module);
				csvRow.setSuite(suite);
				csvRow.setPlatform(platform);
				csvRow.setProfile(profile);
				
				csvRowList.add(csvRow);
			}	
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(filePath + " - file is not available.");
		}
			
		return csvRowList;
	}

		
	public static boolean readExecutedFile(ArrayList<TCSuite> ignorSuiteList) {
		boolean isSpIgnored = false;
		File executedSuiteFile = new File(Constants.EXECUTED_SUITE_FILE);

		try {
			if(!executedSuiteFile.exists()) {
				executedSuiteFile.createNewFile();
			}
			
			Scanner sc = new Scanner(executedSuiteFile);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				if(line.length() > 0) {
					if(line.trim().equals("SetupPrerequisites")) {
						isSpIgnored  = true;
						continue;
					}
					
					TCSuite suite = new TCSuite();
					suite.setName(line);
					ignorSuiteList.add(suite);
				}
			}
			sc.close();	
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return isSpIgnored;
	}
	
	public static String readTCList(String value) {
		File tcListFile = new File(value);
		if(!tcListFile.exists()) {
			System.out.println(value + " file does not exits...!!!");
			return "";
		} 
		String tcString = "";
		try {
			Scanner sc = new Scanner(tcListFile);
			
			boolean notEmpty = false;
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				System.out.println(line);
				
				tcString += line+".test"+";";
				notEmpty = notEmpty||(line.length()>0);
			}
			sc.close();
			
			if(!notEmpty) {
				System.out.println(value + " file is empty...!!!");
				return "";
			} else {
				tcString = tcString.substring(0, tcString.length()-1);
			}
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
		
		return tcString;
	}
	
	public static void deleteFile(String filePath) {
		File file = new File(filePath);	  
		try {
			Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
   public static void deleteFilesInFolder(String folderPath) {
        try {
            final Path directory = Paths.get(folderPath);
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(!directory.equals(file)) {
                    	Files.deleteIfExists(file);
                    }
                	return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                	if(!directory.equals(dir)) {
                		Files.deleteIfExists(dir);
                	}
                    return FileVisitResult.CONTINUE;
                }
            });
            
        } catch (Exception e) {
            //System.out.println("[SDKAutomator] Folder not found: "+folderPath);
        }
    }
   
   public static void writeToExecutedFile(String content) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(".executed_suite.db"),true));
			
			pw.append(content+"\n");
			pw.flush();
			pw.close();			
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}

	public static void saveJUnitXML(String fileName) {
		createDirectory(new File(Utils.getValue(Constants.LOG_PATH)));
		File inputXml = new File(Utils.getValue(Constants.SOURCE_XML_PATH));
		File outputXml = new File(Utils.getValue(Constants.LOG_PATH) + File.separatorChar + fileName + ".xml");
		
		try {
			if(inputXml.exists()) {
				copyFile(inputXml, outputXml);
			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println(Utils.getValue(Constants.SOURCE_XML_PATH)+" - file is not available...");
		}
	}
	
	
	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
   public static String getLogFileName() {
	   String logDir = Utils.getValue(Constants.LOG_PATH);
	   createDirectory(new File(logDir));

	   Date date = new Date();
	   SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
	   String logFileName = logDir + File.separatorChar + format.format(date)+"_sdkautomator.log";
	   return logFileName;
	}

}
