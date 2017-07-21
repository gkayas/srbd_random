package clirunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Execution {

	public static void suspend(boolean status) {
		String line;
		if(status) {
			line = "1";
		}else{
			line = "0";
		}
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./temp/suspend"));
			bufferedWriter.write(line);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void checkArguments(String[] args) {
		if(args.length == 0){
			System.out.println("[MESSAGE] No Option found");
			System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar ui/cli");
			System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
			System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp run/rerun");
			System.exit(0);
		}else if(args.length == 1){
			if(args[0].equals("cli")){
				System.out.println("[MESSAGE] Need more option");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
			}else if(!args[0].equals("ui")){
				System.out.println("[MESSAGE] Option " + args[0] + " not found");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar ui");
				System.exit(0);
			}

		}else if(args.length == 2){
			if (!args[0].equals("cli")){
				System.out.println("[MESSAGE] Option " + args[0] + " " + args[1] + " not valid");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp run/rerun");
				System.exit(0);
			}
			if (!args[1].equals("storeapp") && !args[1].equals("sampleapp")) {
				System.out.println("[MESSAGE] Option " + args[0] + " " + args[1] + " not valid");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
				System.exit(0);
			}
		}else if(args.length == 3){

			if (!args[0].equals("cli")){
				System.out.println("[MESSAGE] Option " + args[0] + " " + args[1] + " " + args[2] + " not valid");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp run/rerun");
				System.exit(0);
			}

			if (!args[1].equals("storeapp") && !args[1].equals("sampleapp")) {
				System.out.println("[MESSAGE] Option " + args[1] + " not valid");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli storeapp/sampleapp run/rerun");
				System.exit(0);
			}

			if (!args[2].equals("run") && !args[2].equals("rerun")) {
				System.out.println("[MESSAGE] Option " + args[0] + " " + args[1] + " " + args[2] + " not valid");
				System.out.println("[MESSAGE] Usage : java -jar uiautomator.jar cli " + args[1] + " run/rerun");
				System.exit(0);
			}
		}
	}

	private static boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

	private static AppInfo getMatchedAppInfo(ArrayList<AppInfo> appInfos, AppInfo ainfo) {
		if(ainfo.getRequiredVersion() == null){
			for (AppInfo appInfo : appInfos) {
				if(appInfo.getAppName().equals(ainfo.getAppName()))
					return appInfo;
			}
		}else{
			for (AppInfo appInfo : appInfos) {
				if(appInfo.getAppName().equals(ainfo.getAppName()) && appInfo.getProfile().equals(ainfo.getProfile())
						&& appInfo.getRequiredVersion().equals(ainfo.getRequiredVersion()) && appInfo.getAppType().equals(ainfo.getAppType())){
					return appInfo;
				}
			}
		}
		return null;
	}

	public static ArrayList<AppInfo> compileRunProject(String project, String mode, ArrayList<AppInfo> appInfos, String logFileName) {

		if (appInfos == null){
			System.out.println("Nothing found to execute");
			return null;
		}

		String currentWorkingDirectory = System.getProperty("user.dir");
		ProcessBuilder compile, run;
		Process compileProcess, runProcess;

		try {
			if (!isWindows()) {
				compile = new ProcessBuilder("/bin/sh", "-c","javac -d "+
						currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
						+ "/AFEngine/lib/*" + ":"+currentWorkingDirectory + "/lib/*:" + currentWorkingDirectory + "/src "+
						currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/" + project + "/*.java");
			} else {
				compile = new ProcessBuilder("cmd", "/c", "javac -d "+
						currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
						+ "/AFEngine/lib/*" + ";"+currentWorkingDirectory + "/lib/*;" + currentWorkingDirectory + "/src "+
						currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/" + project + "/*.java");
			}

			compileProcess = compile.start();

			String line = "";
			BufferedReader msgReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
			BufferedReader errReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));

			while ((line = msgReader.readLine()) != null) {
				System.out.println(line);
			}

			int returnCode = compileProcess.waitFor();

			msgReader.close();
			errReader.close();
			compileProcess.destroy();

			if (returnCode == 0) {
				if (!isWindows()) {
					run = new ProcessBuilder("/bin/sh", "-c",
							"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*:" + currentWorkingDirectory + "/lib/*:"
									+ currentWorkingDirectory + "/bin apprunner.Main " + project + " " + mode + " " + logFileName);
				} else {
					run = new ProcessBuilder("cmd", "/c",
							"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*;" + "lib/*;"
									+ currentWorkingDirectory + "/bin apprunner.Main " + project + " " + mode + " " + logFileName);
				}

				runProcess = run.start();
				msgReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
				errReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

				StringBuffer msgSb = new StringBuffer();
				StringBuffer errSb = new StringBuffer();
				String sm,se;

				while(true) {
					try{
						runProcess.exitValue();
						break;
					}catch(IllegalThreadStateException ex){

					}

					while (msgReader.ready()) {
						sm = msgReader.readLine();
						msgSb.append(sm);

						System.out.println(msgSb.toString());

						if(sm.contains("STATUS") && sm.contains("result") && sm.contains("time")) {
							AppInfo appInfo = new AppInfo();
							String result = null;
							String tcName = null;
							String elaspseTime = null;

							if(project.toLowerCase().equals(EnumProject.STOREAPP.toString())){
								result = sm.split("\\[result")[1].split("\\]")[0].split("->")[1].trim();
								elaspseTime = sm.split("\\[time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim();

								sm = sm.split("\\[STATUS\\]")[1].trim();
					    		String[] parts = sm.split("\\]");

					    		ArrayList<String> data =new ArrayList<String>();
					    		for (String part : parts) {
					    			data.add(part.replaceAll("\\[", ""));
								}

								appInfo.setAppName(data.get(0));
					    		tcName = data.get(1);

							}else{
								result = sm.split("\\[result")[1].split("\\]")[0].split("->")[1].trim();
								elaspseTime = sm.split("\\[time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim();

					    		sm = sm.split("\\[STATUS\\]")[1].trim();
					    		String[] parts = sm.split("\\]");

					    		ArrayList<String> data =new ArrayList<String>();
					    		for (String part : parts) {
					    			data.add(part.replaceAll("\\[", ""));
								}

					    		appInfo.setAppName(data.get(0));
					    		appInfo.setAppType(data.get(1));
					    		appInfo.setProfile(data.get(2));
					    		appInfo.setRequiredVersion(data.get(3));
					    		tcName = data.get(4);
							}
							AppInfo matchedAppInfo = getMatchedAppInfo(appInfos, appInfo);
							TCResult tcResult = result.equals(TCResult.Pass+"")? TCResult.Pass : result.equals(TCResult.Fail+"")?TCResult.Fail : result.equals(TCResult.NT+"")?TCResult.NT:TCResult.NA;
							matchedAppInfo.setTCResult(tcName, tcResult);
							matchedAppInfo.setTCTime(tcName, Integer.parseInt(elaspseTime));
						}

						msgSb = new StringBuffer();
						//								}

					}

					while (errReader.ready()) {
						se = errReader.readLine();
						errSb.append(se);
						System.out.println(errSb.toString());
						errSb = new StringBuffer();
					}

					Thread.sleep(10);
				}

				msgReader.close();
				errReader.close();
				runProcess.destroy();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		prepareRerunXml(project, appInfos);
		return appInfos;
	}

	private static void writeCode(String testCasePath) {
		BufferedReader buf = null;
		StringBuffer sb = new StringBuffer();
		try {
			buf = new BufferedReader(new FileReader("AFEngine/template/Template"));
			if (null != buf) {
				String s = null;
				while ((s = buf.readLine()) != null) {
					sb.append(s + "\n");
				}
			}
			sb.append("\n\t@Test\n\tpublic void testCases() throws Exception\n\t{");

			buf = new BufferedReader(new FileReader(testCasePath));
			if (null != buf) {
				String s = null;
				while ((s = buf.readLine()) != null) {
					sb.append("\n\t\t");
					sb.append(s);
				}
			}

			sb.append("\n\t}\n}");

			PrintWriter pw = new PrintWriter(new File("AFEngine/src/TestCase.java"));
			pw.write(sb.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	public static void compileRunTestcase(String testCasePath,String logFileName, String reportFilePath) {
		writeCode(testCasePath);

		String tcResult = "NT";
		long elapseTime = 0;

		String currentWorkingDirectory1 = System.getProperty("user.dir");
		ProcessBuilder compile, run;
		Process compileProcess, runProcess;

		try {
			if (!isWindows()) {
				compile = new ProcessBuilder("javac", "-d",
						currentWorkingDirectory1 + "/AFEngine/bin", "-cp", currentWorkingDirectory1
								+ "/AFEngine/lib/*" + ":" + currentWorkingDirectory1 + "/AFEngine/src",
						currentWorkingDirectory1 + "/AFEngine/src/Main.java");
			} else {
				compile = new ProcessBuilder("cmd", "/c", "javac", "-d",
						currentWorkingDirectory1 + "/AFEngine/bin", "-cp", currentWorkingDirectory1
							+ "/AFEngine/lib/*" + ";" + currentWorkingDirectory1 + "/AFEngine/src",
						currentWorkingDirectory1 + "/AFEngine/src/Main.java");
			}

			compileProcess = compile.start();

			String line = "";
			BufferedReader msgReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
			BufferedReader errReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));

			while ((line = msgReader.readLine()) != null) {
				System.out.println(line);
			}

			int returnCode = compileProcess.waitFor();

			msgReader.close();
			errReader.close();
			compileProcess.destroy();

			if (returnCode == 0) {
				if (!isWindows()) {
					run = new ProcessBuilder(
							"java", "-cp", currentWorkingDirectory1 + "/AFEngine/lib/*:"
									+ currentWorkingDirectory1 + "/AFEngine/bin", "Main", currentWorkingDirectory1, logFileName);
				} else {
					run = new ProcessBuilder("cmd", "/c",
							"java", "-cp", currentWorkingDirectory1 + "/AFEngine/lib/*;"
									+ currentWorkingDirectory1 + "/AFEngine/bin", "Main", currentWorkingDirectory1, logFileName);
				}

				runProcess = run.start();
				msgReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
				errReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

				StringBuffer msgSb = new StringBuffer();
				StringBuffer errSb = new StringBuffer();
				String sm,se;
				while(true) {
					try{
						runProcess.exitValue();
						break;
					}catch(IllegalThreadStateException ex){

					}

					while (msgReader.ready()) {
						sm = msgReader.readLine();
						msgSb.append(sm);

						System.out.println(msgSb.toString());
						msgSb = new StringBuffer();

						if(sm.contains("STATUS") && sm.contains("Result") && sm.contains("Time")) {
							tcResult = sm.split("\\[Result")[1].split("\\]")[0].split("->")[1].trim();
							elapseTime = Integer.parseInt(sm.split("\\[Time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim());
						}
					}

					while (errReader.ready()) {
						se = errReader.readLine();
						errSb.append(se);
						System.out.println(errSb.toString());
						errSb = new StringBuffer();
					}
					Thread.sleep(10);
				}

				msgReader.close();
				errReader.close();
				runProcess.destroy();
				ReportUtils.saveTestcaseReport(reportFilePath, testCasePath, tcResult, elapseTime);
			}

		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}


	}

	private static void prepareRerunXml(String project, ArrayList<AppInfo> appInfos) {
		File file = new File ("./res/xml/" + project + "_rerun.xml");
		if(file.exists()) file.delete();
		if (hasFailApp(appInfos)){
			XMLUtils.writeXml(project, appInfos);
		}

	}

	public static boolean hasFailApp(ArrayList<AppInfo> appInfos) {
		for (AppInfo appInfo : appInfos) {
			if(appInfo.getAppResult() == TCResult.Fail)
				return true;
		}
		return false;
	}


}
