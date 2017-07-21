import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main extends Application{
	static PrintStream console;
	static int processReturnValue = -1;
	static String argsValue = "sdkautomator.jar ";

	public static void main(String[] args) {		
		Options options;
		options = new Options();
		createOptions(options);

		Utils.readParameterVariable();
		Utils.createInitialDirectory();
		
		String help = parseOptions(options, args, "help");
		if(help.equals("help")) {
			showHelp(options, args);
			System.exit(0);
		}		
				
		if(args.length <= 0 || args.length == 2) {
			if(args[0].equals("run") && args[1].equals("tc")) {
				runTestCase();
			} else {
				showHelp(options, args);
			}
			
			System.exit(0);
		}
		
		for (String string : args) {
			argsValue += string + " "; 
		}


		if(args.length == 1) {				
			if(args[0].equals("run")) {
				runAllSuites();
			} else if(args[0].equals("rerun")){
				rerunAllSuites();
			} else if(args[0].equals("setup")){
				launch(args);
			} else if(args[0].equals("clear")){
				clearWorkspace();
			} else {
				showHelp(options, args);
			}

			System.exit(0);
		} 
			
		if(args.length == 3) {
			if(args[0].equals("run")) {
				String reportFilePath = parseOptions(options, args, "reportfilepath");
				
				if(!reportFilePath.isEmpty()) {
					File file = new File(reportFilePath);
					if(file.isDirectory() && file.exists()) {
						Utils.setValue(Constants.REPORT_PATH, reportFilePath);
						runAllSuites();
						System.exit(0);
					} else {
						System.out.println("[SDKAutomator] Incorrect parameter: --reportfilepath " + reportFilePath);
						System.exit(0);
					}
				}
			} else if(args[0].equals("rerun")) {
				String reportFilePath = parseOptions(options, args, "reportfilepath");
				
				if(!reportFilePath.isEmpty()) {
					File file = new File(reportFilePath);
					if(file.isDirectory() && file.exists()) {
						Utils.setValue(Constants.REPORT_PATH, reportFilePath);
						rerunAllSuites(); 
						System.exit(0);
					} else {
						System.out.println("[SDKAutomator] Incorrect parameter: --reportfilepath " + reportFilePath);
						System.exit(0);
					}
				}
			} else {
				showHelp(options, args);
				System.exit(0);
			}
		}

		if(args.length >= 3) {
			if(args[0].equals("run")) {
				ArrayList<String> suiteModules = new ArrayList<String>();
				ArrayList<String> selectedModule = new ArrayList<String>();
				ArrayList<String> selectedProfile = new ArrayList<String>();
				ArrayList<String> selectedPlatform = new ArrayList<String>();
				
				String project = parseOptions(options, args, "project");
				if(project.isEmpty()) {
					System.out.println("[SDKAutomator] Incorrect Parameter. --project (BVT/ECP-CLI) option need to set");
					System.exit(0);
				}

				String platform = parseOptions(options, args, "platform");
								
				if(platform.isEmpty() || platform.equals("all")) {
					selectedPlatform.add("3.0");
					selectedPlatform.add("2.4");
					selectedPlatform.add("2.3.2");
					selectedPlatform.add("2.3.1");
					selectedPlatform.add("2.3");
					selectedPlatform.add("Common");
				} else {
					String [] platforms = platform.split(";");
					for (String value : platforms) {
						selectedPlatform.add(value);
					}
				}

				String profile = parseOptions(options, args, "profile");
				
				if(profile.isEmpty() || profile.equals("all")) {
					selectedProfile.add("mobile");
					selectedProfile.add("wearable");
				} else {
					String [] profiles = profile.split(";");
					for (String value : profiles) {
						selectedProfile.add(value);
					}
				}			

				String reportFilePath = parseOptions(options, args, "reportfilepath");

				if(!reportFilePath.isEmpty()) {					
					File file = new File(reportFilePath);
					if(file.isDirectory() && file.exists()) {
						Utils.setValue(Constants.REPORT_PATH, reportFilePath);
					} else {
						System.out.println("[SDKAutomator] Incorrect parameter: --reportfilepath " + reportFilePath);
						System.exit(0);
					}
				}

				ArrayList<CSVRow> csvRowList = Utils.readMasterSuiteList(project);
				
				if(csvRowList.size() <= 0) {
					System.out.println(project + " - suitelist is not available...");
					System.exit(0);
				}				
						
				for (CSVRow csvRow : csvRowList) {
					if(!suiteModules.contains(csvRow.getModule())) {
						suiteModules.add(csvRow.getModule());
					}
				}					

				String module = parseOptions(options, args, "module");
				
				
				if(module.isEmpty() || module.equals("all")) {
					for (String modules : suiteModules) {
						selectedModule.add(modules);
					}				
				} else {
					String [] modules = module.split(";");
					for (String value : modules) {
						if(suiteModules.contains(value)) {
							selectedModule.add(value);
						} else {
							System.out.println("[SDKAutomator] Incorrect parameter: --module " + value);
							System.exit(0);
						}					
					}
				}

				try {
					PrintWriter pw = new PrintWriter(new File("suitelists"));

					for (CSVRow csvRow : csvRowList) {
						if(selectedModule.contains(csvRow.getModule()) && 
								selectedPlatform.contains(csvRow.getPlatform()) && 
								selectedProfile.contains(csvRow.getProfile().toLowerCase())) {
							pw.append(csvRow.getTcmID()+","+csvRow.getTcID()+","+csvRow.getModule()+","+csvRow.getSuite()+","+csvRow.getPlatform()+","+csvRow.getProfile()+"\n");
						}
					}

					pw.flush();
					pw.close();

				} catch (FileNotFoundException e) {		
					e.printStackTrace();
				}

				runAllSuites();
				System.exit(0);
			} else if(args[0].equals("rerun")) {
				showHelp(options, args);		
			} else {
				showHelp(options, args);
			}
		}

		System.exit(0);
	}	
	
	private static void showHelp(Options options, String[] args)	{
		HelpFormatter formatter = new HelpFormatter();
		System.out.println("");
		System.out.println("####################################################");
		System.out.println("\t\tHow to use SDKAutomator");
		System.out.println("####################################################");
		System.out.println("");

		if(args.length > 0) {
			if(args[0].equals("run")) {
				formatter.printHelp("sdkautomator.java run", "", options, "", true);			
			} else if(args[0].equals("rerun")) {
				Options tmpOptions = new Options();
				tmpOptions.addOption(options.getOption("help"));
				tmpOptions.addOption(options.getOption("reportfilepath"));
				formatter.printHelp("sdkautomator.java rerun", "", tmpOptions, "", true);
			} else if(args[0].equals("clear")) {
				System.out.println("[SDKAutomator] usage: sdkautomator.java clear");
			} else if(args[0].equals("setup")) {				
				System.out.println("[SDKAutomator] usage: sdkautomator.java setup");
			} else {
				System.out.println("[SDKAutomator] usage: sdkautomator.java clear");
				System.out.println("[SDKAutomator] usage: sdkautomator.java setup\n");
				
				formatter.printHelp("sdkautomator.java run", "", options, "", true);
				
				System.out.println("");
				
				Options tmpOptions = new Options();
				tmpOptions.addOption(options.getOption("help"));
				tmpOptions.addOption(options.getOption("reportfilepath"));
				formatter.printHelp("sdkautomator.java rerun", "", tmpOptions, "", true);
			}
		} else {
			System.out.println("[SDKAutomator] usage: sdkautomator.java clear");
			System.out.println("[SDKAutomator] usage: sdkautomator.java setup\n");
			
			formatter.printHelp("sdkautomator.java run", "", options, "", true);
			
			System.out.println("");
			
			Options tmpOptions = new Options();
			tmpOptions.addOption(options.getOption("help"));
			tmpOptions.addOption(options.getOption("reportfilepath"));
			formatter.printHelp("sdkautomator.java rerun", "", tmpOptions, "", true);
		}
	}
	
	private static void createOptions(Options options)	{
		Option helpOption = Option.builder("h")
				.longOpt( "help" )
				.desc( "Help"  )
				.hasArg(false)
				.build();
		options.addOption( helpOption );
		
		Option projectOption = Option.builder("p")
				.longOpt( "project" )
				.desc( "project type"  )
				.hasArg(true)
				.argName( "BVT/ECP-CLI" )
				.build();
		options.addOption( projectOption );

		Option reportPathOption = Option.builder("r")
				.longOpt( "reportfilepath" )
				.desc( "path of report file"  )
				.hasArg(true)
				.argName( "PATH" )
				.build();
		options.addOption( reportPathOption );

		Option platFromOption = Option.builder("pl")
				.longOpt( "platform" )
				.desc( "platfrom version"  )
				.hasArg(true)
				.argName( "all or 2.3;2.3.1;2.3.2;2.4;3.0;Common" )
				.build();
		options.addOption( platFromOption );

		Option profileOption = Option.builder("pr")
				.longOpt( "profile" )
				.desc( "profile name"  )
				.hasArg(true)
				.argName( "all or mobile;wearable" )
				.build();
		options.addOption( profileOption );

		Option moduleOption = Option.builder("m")
				.longOpt( "module" )
				.desc( "modules name"  )
				.hasArg(true)
				.argName( "module1;module2" )
				.build();
		options.addOption( moduleOption );
	}

	private static String parseOptions(Options options, String[] args, String option) {
		CommandLineParser parser = new DefaultParser();
		String value = "";
		boolean setOption = false;
		try {
			CommandLine line = parser.parse( options, args );

			if( line.hasOption( option )) {
				setOption = true;
				value = line.getOptionValue( option );

				if(value != null) {
					value.trim();
				}
			}
		} catch( ParseException exp ) {
			System.out.println( "Unexpected option: " + exp.getMessage() );
			System.exit(0);
		}

		if(setOption) {
			if(option == "project") {
				if(!(value.equals("BVT") || value.equals("ECP-CLI"))) {
					System.out.println("[SDKAutomator] Incorrect parameter: --project " + value);
					System.exit(0);
				}
			}
			else if(option == "platform") {
				if(!value.equals("all")) {
					ArrayList<String> allPlatform = new ArrayList<String>();
					allPlatform.add("3.0");
					allPlatform.add("2.4");
					allPlatform.add("2.3.2");
					allPlatform.add("2.3.1");
					allPlatform.add("2.3");
					allPlatform.add("Common");
					
					String [] platforms = value.split(";");
					for (String platform : platforms) {
						if(!allPlatform.contains(platform)) {
							System.out.println("[SDKAutomator] Incorrect parameter: --platform " + value);
							System.exit(0);
						}
					}
				}
			} else if(option == "profile") {
				if(!value.equals("all")) {
					if(!(value.toLowerCase().equals("mobile") || 
							value.toLowerCase().equals("wearable") || 
							value.toLowerCase().equals("mobile;wearable") || 
							value.toLowerCase().equals("wearable;mobile"))) {
						System.out.println("[SDKAutomator] Incorrect parameter: --profile " + value);
						System.exit(0);
					}
				}				
			} else if(option == "help") {				
				return "help";
			}
		}
		
		return value;
	}

	private void openSetupWindow(Stage stage) {
		Parent root;
		console = System.out;

		System.out.println("[SDKAutomator] SDKAutomator v0.2");
		System.out.println("[SDKAutomator] Open setup window...");

		try {
			root = FXMLLoader.load(getClass().getResource("RcpttRunnerScene.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[SDKAutomator] Can not load RcpttRunnerScene.fxml file");
			return;
		}

		Scene scene = new Scene(root);
		stage.setTitle("SDKAutomator [v0.2] [Setup]");
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();   
	}

	private static void runProcess(String... args ) {

		try {
			final String [] cmd = args;
			Thread runner = new Thread(new Runnable() {			

				@Override
				public void run() {

					Process process = null;
					try {
						process = new ProcessBuilder(cmd).start();

						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

						String message;
						while((message=reader.readLine()) != null) {
							System.out.println(message);
						}

						processReturnValue = process.waitFor();  

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
	}	

	private static void runAllSuites() {
		Utils.deleteFilesInFolder(Utils.getValue(Constants.LOG_PATH));
		Utils.deleteFilesInFolder(Utils.getValue(Constants.REPORT_PATH));
		
		UtilsLog.initLogger(Utils.getLogFileName());
				
		System.out.println("[SDKAutomator] SDKAutomator v0.2");
		System.out.println("[SDKAutomator] Start execution with: " + argsValue);
		System.out.println("[SDKAutomator] Setup ParametersVariable...");
		
		Iterator<String> keys = Utils.getKeys();
		while(keys.hasNext()) {
			String key = keys.next();
			System.out.println(key + "=" + Utils.getValue(key));
		}
				
		ArrayList<TCSuite> ignorSuiteList  = new ArrayList<TCSuite>();

		boolean isSpIgnored = Utils.readExecutedFile(ignorSuiteList);

		if(!isSpIgnored) {
			setupPrequisites();

			if(processReturnValue == 0 || processReturnValue == 56) {
				Utils.saveJUnitXML("run_SetupPrerequisites");
				
				boolean isSpPassed = Report.isTcPassFromXml("SetupPrerequisites");

				if(isSpPassed) {
					Utils.writeToExecutedFile("SetupPrerequisites");
					System.out.println("[SDKAutomator] SetupPrerequisites completed successfully...");
				} else {
					System.out.println("[SDKAutomator] Please make sure SetupPrerequisites pass before running suite...");
					return;
				}
			} else {
				System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
				return;
			}
		}

		ArrayList<SuiteGroup> suiteGroupList =  Utils.readSuiteGroup();
		
		if(suiteGroupList.size() <= 0) {
			System.out.println("[SDKAutomator] suitelists - contents is not availble...");
			return;
		}		

		for (SuiteGroup suiteGroup : suiteGroupList) {				
			setupPlatformEnv(suiteGroup.getPlatform(), suiteGroup.getProfile());
			
			if(processReturnValue == 0 || processReturnValue == 56) {
				boolean isSpPassed = Report.isTcPassFromXml("SetupPlatformEnv");
				Utils.saveJUnitXML(suiteGroup.getProfile() + "-" + suiteGroup.getPlatform() + "-run_SetupPlatformEnv");
				
				if(!isSpPassed) {
					System.out.println("[SDKAutomator] SetupPlatformEnv failed for " + suiteGroup.getProfile() + "-" +  suiteGroup.getPlatform());
					continue;
				}
			} else {
				System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
				continue;
			}
			
			//
			//runSuites(suiteGroup.getSuiteString());
			
			Iterator<TCSuite> suiteListIterator = suiteGroup.getSuiteList().iterator();
			TCSuite firstSuite = suiteListIterator.next();
			String runString = firstSuite.getName() + ";";
			int tcCount = firstSuite.getTcList().size();
			
			
			//If single suitelist
			if(!suiteListIterator.hasNext()) {
				runString = runString.substring(0, runString.length() - 1);
				startRunSuites(suiteGroup, runString);
			}
			
			//if multi suitelist
			while(suiteListIterator.hasNext()) {
				TCSuite suite = suiteListIterator.next();
				
				if((tcCount + suite.getTcList().size()) < 100) {
					runString += suite.getName() + ";";
					tcCount = tcCount + suite.getTcList().size();	
				} else {
					runString = runString.substring(0, runString.length() - 1);
					startRunSuites(suiteGroup, runString);
					
					runString = suite.getName() + ";";
				    tcCount = suite.getTcList().size();				    
				}
				
				if(!suiteListIterator.hasNext()) {
					runString = runString.substring(0, runString.length() - 1);
					startRunSuites(suiteGroup, runString);
				}
			}			
		}

		System.out.println("[SDKAutomator] Finish execution with run...");	
	}
	
	private static void startRunSuites(SuiteGroup suiteGroup, String runString) {
		runSuites(runString);
		
		if(processReturnValue == 0 || processReturnValue == 56) {
			try {
				ArrayList<Testcase> failTcList;

				failTcList = Report.prepareReportForGroupRun(suiteGroup);
				
				if(failTcList != null) {
					String [] parts = runString.split(";");
					String fileNamePart;
					if(parts.length > 1) {
						fileNamePart = parts[0] + "~" + parts[parts.length-1];
					} else {
						fileNamePart = parts[0];
					}
					
					Utils.saveJUnitXML(suiteGroup.getProfile() + "-" + suiteGroup.getPlatform() + "-run_Suites-" + fileNamePart);
					
					if(failTcList.size() > 0) {
						reRunFailTCs(suiteGroup.getPlatform(), suiteGroup.getProfile(), failTcList);
	
						if(processReturnValue == 0 || processReturnValue == 56) {
							try {
								failTcList = Report.prepareReportForGroupRun(suiteGroup);
								if(failTcList != null) {
									Utils.saveJUnitXML(suiteGroup.getProfile() + "-" + suiteGroup.getPlatform() + "-runrerun_Suites-" + fileNamePart);
								} else {
									System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
								}							
							} catch (IOException e) {
								//e.printStackTrace();
							}
						} else {
							System.out.println("[SDKAutomator] RCPTT runner launch failed for rerun: Profile: " + suiteGroup.getProfile() + " and Platfrom: " + suiteGroup.getPlatform() + " for Suites: " + runString + " and  processReturnValue: " + processReturnValue + "...");
						}
					}
					
					System.out.println("[SDKAutomator] Suites: " + runString + " run completed.");
					System.out.println("[SDKAutomator] Please check report - " + Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv");
				} else {
					System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		} else {
			System.out.println("[SDKAutomator] RCPTT runner launch failed for run: Profile: " + suiteGroup.getProfile() + " and Platfrom: " + suiteGroup.getPlatform() + " for Suites: " + runString + " and  processReturnValue: " + processReturnValue + "...");
		}  
	}
	
	private static void rerunAllSuites() {
		UtilsLog.initLogger(Utils.getLogFileName());
		
		System.out.println("[SDKAutomator] SDKAutomator v0.2");
		System.out.println("[SDKAutomator] Start execution with: " + argsValue);
		System.out.println("[SDKAutomator] Setup ParametersVariable...");
		
		Iterator<String> keys = Utils.getKeys();
		while(keys.hasNext()) {
			String key = keys.next();
			System.out.println(key + "=" + Utils.getValue(key));
		}
	
		ArrayList<Testcase> failInResult = Utils.readFailTcListFromResultFile();

		if(failInResult == null) 
			return;

		if(failInResult.size() <= 0) {
			System.out.println(Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv" + " - contains no fail TCs to rerun...");
			return;
		}


		ArrayList<TCSuite> ignorSuiteList  = new ArrayList<TCSuite>();

		boolean isSpIgnored = Utils.readExecutedFile(ignorSuiteList);

		if(!isSpIgnored) {
			setupPrequisites();

			if(processReturnValue == 0 || processReturnValue == 56) {
				Utils.saveJUnitXML("rerun_SetupPrerequisites");
				
				boolean isSpPassed = Report.isTcPassFromXml("SetupPrerequisites");

				if(isSpPassed) {
					Utils.writeToExecutedFile("SetupPrerequisites");
					System.out.println("[SDKAutomator] SetupPrerequisites completed successfully...");
				} else {
					System.out.println("[SDKAutomator] SetupPrerequisites failed, please make sure SetupPrerequisites pass before running suite...");
					return;					
				}
			} else {
				System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
				return;
			}
		}
		
		ArrayList<SuiteGroup> suiteGroupList =  Utils.readSuiteGroup();
		
		if(suiteGroupList.size() <= 0) {
			System.out.println("[SDKAutomator] suitelists - contents is not availble...");
			return;
		}
		
		
		//Add fail TCs to suiteGroup's suite's faillist;		
		for (Testcase failTc : failInResult) {
			boolean tcFound = false;
			for (SuiteGroup suiteGroup : suiteGroupList) {
				if(tcFound) {
					break;
				}
				ArrayList<TCSuite> tcSuiteList = suiteGroup.getSuiteList();
				for (TCSuite tcSuite : tcSuiteList) {
					if(tcSuite.containsTc(failTc)) {
						Testcase prevTc = tcSuite.getTcList().get(tcSuite.getTcList().indexOf(failTc));
						if(prevTc.getTcmID().equals(failTc.getTcmID())) {
							tcSuite.addToFailList(prevTc);
							tcFound = true;
							break;
						}
					}
				}
			}
		}

		for (SuiteGroup suiteGroup : suiteGroupList) {		
			ArrayList<Testcase> failTcList = suiteGroup.getFailTcList();

			if(failTcList.size() > 0) {
				setupPlatformEnv(suiteGroup.getPlatform(), suiteGroup.getProfile());
				
				if(processReturnValue == 0 || processReturnValue == 56) {
					boolean isSpPassed = Report.isTcPassFromXml("SetupPlatformEnv");
					Utils.saveJUnitXML(suiteGroup.getProfile() + "-" + suiteGroup.getPlatform() + "-rerun_SetupPlatformEnv");
					
					if(!isSpPassed) {
						System.out.println("[SDKAutomator] SetupPlatformEnv failed for " + suiteGroup.getProfile() + "-" +  suiteGroup.getPlatform());
						continue;
					}
				} else {
					System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
					continue;
				}
				
				reRunFailTCs(suiteGroup.getPlatform(), suiteGroup.getProfile(), failTcList);

				if(processReturnValue == 0 || processReturnValue == 56) {
					try {
						failTcList = Report.prepareReportForGroupRun(suiteGroup);
						if(failTcList != null) {
							Utils.saveJUnitXML(suiteGroup.getProfile() + "-" + suiteGroup.getPlatform() + "-rerun_Suites");
						} else {
							System.out.println("[SDKAutomator] RCPTT runner launch failed: processReturnValue: " + processReturnValue + "...");
						}
					} catch (IOException e) {
						//e.printStackTrace();
					}
				} else {
					System.out.println("[SDKAutomator] RCPTT runner launch failed for rerun: Profile: " + suiteGroup.getProfile() + " and Platfrom: " + suiteGroup.getPlatform() + " and  processReturnValue: " + processReturnValue + "...");
				}
			}
		}

		System.out.println("[SDKAutomator] Suite rerun completed.");
		System.out.println("[SDKAutomator] Please check report - " + Utils.getValue(Constants.REPORT_PATH) + File.separatorChar + "report.csv");		
		System.out.println("[SDKAutomator] Finish execution with rerun...");
	}	
	
		

	private static void runSuites(String suites) {
		processReturnValue = -1;	

		System.out.println("[SDKAutomator] Start executing suites: "+ suites);

		Utils.deleteFile(Utils.getValue(Constants.SOURCE_XML_PATH));
	
		if (Utils.isWindows()) {
			runProcess("cmd", "/c", "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",  
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-suites",suites);
		} else {
			runProcess( "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT),  
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m", 
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-suites",suites);
		}
	}
	
	private static void runTestCase() {
		UtilsLog.initLogger(Utils.getLogFileName());
		
		System.out.println("[SDKAutomator] SDKAutomator v0.2");
	    Utils.deleteFile(Utils.getValue(Constants.SOURCE_XML_PATH));
		
		System.out.println("[SDKAutomator] Start executing tclist..........................");
	
		String tclist = "";
		tclist = Utils.readTCList("tclist");
		
		if(tclist.length() == 0) {
			return;
		}
		
		
		processReturnValue = -1;
				
		if (Utils.isWindows()) {
			runProcess("cmd", "/c", "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT),  
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",  
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-tests",tclist);
		} else {
			runProcess( "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
						"-application","org.eclipse.rcptt.runner.headless",  
						"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
						"-aut",Utils.getValue(Constants.AUT_PATH),  
						"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
						"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT),  
						"-testOptions","testExecTimeout=300;autStartupTimeout=600",
						"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
						"-reuseExistingWorkspace",  
						"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
						"-tests",tclist);
		}
		
		if(processReturnValue == 0 || processReturnValue == 56) {
			//try {
				//Report.prepareReportForRun("tclist");
				System.out.println("[SDKAutomator] run tclist completed, please check junit.xml file");
			//} catch (IOException e) {				
				//e.printStackTrace();
			//}
		} else {
			System.out.println("[SDKAutomator] Report save failed for tclist: " + " processReturnValue: " + processReturnValue + "...............");
		}
	}

	private static void reRunFailTCs(String platform, String profile, ArrayList<Testcase> tcList) {
		processReturnValue = -1;

		Utils.deleteFile(Utils.getValue(Constants.SOURCE_XML_PATH));

		String tclist;
		tclist = Utils.prepareTcStringFromList(tcList);

		if(tclist.length() == 0) {
			return;
		}

		System.out.println("[SDKAutomator] Start executing failed TCs for : " + profile + "-" + platform);
		System.out.println("[SDKAutomator] Failed TCs : " + tclist);

		if (Utils.isWindows()) {
			runProcess("cmd", "/c", "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-tests",tclist);

		} else {
			runProcess( "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-tests",tclist);
		}
	}

	private static void clearWorkspace() {
		System.out.println("[SDKAutomator] SDKAutomator v0.2");
		System.out.println("[SDKAutomator] Refreshing workspace, please wait...");

		Utils.deleteFile(Constants.EXECUTED_SUITE_FILE);
		Utils.deleteFilesInFolder(Utils.getValue(Constants.REPORT_PATH));
		Utils.deleteFilesInFolder(Utils.getValue(Constants.AUTOMATION_WORKSPACE));

		System.out.println("[SDKAutomator] Automation workspace clear successfully...");
	}

	private static void setupPrequisites() {
		processReturnValue = -1;

		System.out.println("[SDKAutomator] Initializing SetupPrerequisites..."); 

		if(!Utils.writeRcpttParameterVariable("2.4", "Mobile")) {
			return;
		}

		Utils.deleteFile(Utils.getValue(Constants.SOURCE_XML_PATH));
		Utils.deleteFilesInFolder(Utils.getValue(Constants.AUTOMATION_WORKSPACE) + File.separatorChar + "runner-workspace");

		if (Utils.isWindows()) {			
			runProcess("cmd", "/c", "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-import",Utils.getValue(Constants.PROJECT),  
					"-tests","SetupPrerequisites.test");

		} else {
			runProcess( "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-import",Utils.getValue(Constants.PROJECT),  
					"-tests","SetupPrerequisites.test");
		}
	}

	private static void setupPlatformEnv(String platform, String profile) {
		processReturnValue = -1;

		System.out.println("[SDKAutomator] Initializing setupPlatformEnv..."); 
		platform = (platform.equalsIgnoreCase("Common")) ? Utils.getCommonPlatform(profile) : platform;		
		System.out.println("[SDKAutomator] SetupPlatformEnv " + profile + "-" + platform); 


		if(!Utils.writeRcpttParameterVariable(platform, profile)) {
			return;
		}

		Utils.deleteFile(Utils.getValue(Constants.SOURCE_XML_PATH));

		if (Utils.isWindows()) {			
			runProcess("cmd", "/c", "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT), 
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-import",Utils.getValue(Constants.PROJECT),  
					"-tests","SetupPlatformEnv.test");

		} else {
			runProcess( "java","-jar",Utils.getValue(Constants.RUNNER)+"/plugins/"+Utils.getValue(Constants.RCPTT_LAUNCHER)+".jar",  
					"-application","org.eclipse.rcptt.runner.headless",  
					"-data",Utils.getValue(Constants.RUNNER_WORKSPACE),  
					"-aut",Utils.getValue(Constants.AUT_PATH),  
					"-autWsPrefix",Utils.getValue(Constants.AUT_WORKSPACE),  
					"-autConsolePrefix",Utils.getValue(Constants.AUT_OUT),  
					"-testOptions","testExecTimeout=300;autStartupTimeout=600",
					"-autVMArgs","-Xms512m;-Xmx1024m;-XX:PermSize=512m;-XX:MaxPermSize=1024m",
					"-reuseExistingWorkspace",  
					"-junitReport",Utils.getValue(Constants.JUNIT_REPORT),  
					"-import",Utils.getValue(Constants.PROJECT),  
					"-tests","SetupPlatformEnv.test");
		}
	}	

	@Override
	public void start(Stage primaryStage) throws Exception {
		openSetupWindow(primaryStage);
	}

	@Override
	public void stop() {
		System.out.println("[SDKAutomator] Close setup window...");
		System.setOut(console);

		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Platform.exit();
		System.exit(0);
	}
}
