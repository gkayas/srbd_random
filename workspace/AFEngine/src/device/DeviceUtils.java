package device;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DeviceUtils {

	// Private Members
	// private static Process process;
	// private static String deviceID = null;
	private static String OS = System.getProperty("os.name").toLowerCase();

	static String updateDateTimeOfFiles(String path) {

		long dateTime = Calendar.getInstance().getTimeInMillis();

		File[] files = new File(path).listFiles();
		for (File file : files) {
			file.setLastModified(dateTime);
		}

		return null;

	}
	
	static void runPrivacyScript(String packageId){
	    runCommand("sdb root on");
	    runCommand("sdb shell touch /opt/share/askuser_disable");
	    runCommand("sdb root off");
	}

	static String sdbPush(String src, String des) {

		String status = "";
		Process pr = null;
		Runtime rt = Runtime.getRuntime();

		String[] cmd = new String[4];
		cmd[0] = "sdb";
		cmd[1] = "push";
		cmd[2] = new File(src).getAbsolutePath();
		cmd[3] = des;

		try {
			pr = rt.exec(cmd);
		} catch (IOException ex) {
			// System.out.println("error");
		}

		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));

		String line = "";
		try {
			while ((line = bfr.readLine()) != null) {
				status = line;
				pr.destroy();
			}
		} catch (IOException ex) {
			// System.out.println(ex.getMessage());
		}

		return status.trim();
	}

	// private static String getDeviceInfo() {
	// String deviceInfo = "";
	// deviceInfo = runCommand("sdb","shell","xwininfo","-root");
	// return deviceInfo;
	// }
	//
	// static int getScreenWidth() {
	// int width = -1;
	// String deviceInfo = getDeviceInfo();
	// width = Integer.parseInt(CommonUtils.getRegxSubstrByGroupID(deviceInfo,
	// Constants.REGX_PATTERN_SCREEN_WIDTH_FROM_DEVICE_INFO, 2));
	// return width;
	// }
	//
	// static int getScreenHeight() {
	// int height = -1;
	// String deviceInfo = getDeviceInfo();
	// height = Integer.parseInt(CommonUtils.getRegxSubstrByGroupID(deviceInfo,
	// Constants.REGX_PATTERN_SCREEN_HEIGHT_FROM_DEVICE_INFO, 2));
	// return height;
	// }

	// Public Static Methods

	// static Boolean isDirEmpty(String dir){
	//
	// String out = runCommand("sdb", "shell", "ls", dir);
	// String pattern = "(^.*\\*)(.*)";
	// String ls = CommonUtils.getRegxSubstrByGroupID(out, pattern, 2);
	// return ls.length()>0?false:true;
	// }

	// static void removeFiles(String path){
	// runCommand("sdb", "shell", "rm", "-rf", path);
	// }
	//
	// static void removeAlbums()
	// {
	// DeviceUtils.runCommand("sdb","shell","rm","-rf","/opt/usr/media/Pictures/");
	// }
	//
	// static void removeImageFiles()
	// {
	// DeviceUtils.runCommand("sdb","shell","rm","-rf",CommonUtils.devInfo.get(Constants.DEVICE_IMAGE_PATH));
	// }
	//
	// static void removeVideoFiles()
	// {
	// DeviceUtils.runCommand("sdb","shell","rm","-rf",CommonUtils.devInfo.get(Constants.DEVICE_VIDEO_PATH));
	// }
	//
	// static void removeMusicFiles()
	// {
	// DeviceUtils.runCommand("sdb","shell","rm","-rf",CommonUtils.devInfo.get(Constants.DEVICE_MUSIC_PATH));
	// }

	// public static void pushImageFiles(long waitMilisecond){
	// DeviceUtils.updateDateTimeOfFiles(Constants.TEST_IMAGE_PATH);
	// DeviceUtils.setCurrentDateTime();
	// DeviceUtils.sdbPush(ResourceUtils.getTestImagePath(),
	// CommonUtils.devInfo.get(Constants.DEVICE_IMAGE_PATH));
	// CommonUtils.sleep(waitMilisecond);
	// }

	// static void pushVideoFiles(long waitMilisecond){
	// //DeviceUtils.updateDateTimeOfFiles(Constants.TEST_VIDEO_PATH);
	// DeviceUtils.sdbPush(ResourceUtils.getTestVideoPath(),
	// CommonUtils.devInfo.get(Constants.DEVICE_VIDEO_PATH));
	// CommonUtils.sleep(waitMilisecond);
	// //DeviceUtils.pushFiles(Constants.TEST_VIDEO_PATH,
	// Constants.DEVICE_VIDEO_PATH);
	// }

	// public static void pushMusicFiles(long waitMilisecond){
	// DeviceUtils.sdbPush(ResourceUtils.getTestMusicPath(),
	// CommonUtils.devInfo.get(Constants.DEVICE_MUSIC_PATH));
	// //DeviceUtils.pushFiles(Constants.TEST_MUSIC_PATH,
	// Constants.DEVICE_MUSIC_PATH);
	// CommonUtils.sleep(waitMilisecond);
	// }

	static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	static List<String> runCommand(String arguments) {
		String[] cmd = new String[3];
		List<String> output = new ArrayList<String>();
		System.out.println("[AutomatorCore] " + arguments);

		if (isWindows()) {
			cmd[0] = "cmd";
			cmd[1] = "/c";
		} else {

			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
		}

		cmd[2] = arguments;

		ProcessBuilder builder = new ProcessBuilder(cmd);
		builder.redirectErrorStream(true);
		try {
			Process process = builder.start();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					process.destroy();
					break;
				}
				output.add(line);
				System.out.println("[AutomatorCore] " + line);
			}
		} catch (IOException e) {
			System.out.println("[AutomatorCore] " + e.getMessage());
		}
		return output;
	}

	static boolean restartApp(String packageID, String appID) {
		return restartApp(2500, packageID, appID);
	}

	static boolean restartApp(long waitMilisecond, String packageID, String appID) {
		closeApp(waitMilisecond, EnumAppLauncher.NORMAL, packageID, appID);
		return launchApp(EnumCommand.SDB, EnumAppLauncher.NORMAL, null, appID);
	}

	static void cliProjectCreate(String appType, String templateName, String profileVersion, String projectName,
			String workspacePath) {
		runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen create " + appType.toLowerCase() + "-project -n "
				+ projectName + " -p " + profileVersion + " -t " + "\"" + templateName + "\"" + " -- " + workspacePath);
	}

	static boolean cliProjectBuild(String appType, String buildRootStrap, String compiler, String buildConfiguration,
			String projectPath) {
		boolean isBuildSuccess = false;
		String buildCommand = "";
		String appName;

		if (appType.toLowerCase().equals("native")) {
			buildCommand = "build-native -r " + buildRootStrap + " --compiler " + compiler + " --configuration "
					+ buildConfiguration + " -- " + projectPath;
			try {
				appName = getAppName(Paths.get(projectPath, "project_def.prop").toString());
			} catch (IOException e) {
				System.out.println("[AutomatorCore] " + e.getMessage());
				return false;
			}
		} else {
			buildCommand = "build-web -- " + projectPath;
			appName = new File(projectPath).getName();
		}

		List<String> lines = runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen " + buildCommand);

		for (String line : lines) {
			if (line.contains("Error"))
				return false;
		}

		String buildVerdict = Paths.get(projectPath, buildConfiguration, appName).toString();
		String buildVerdictSharedLib = Paths.get(projectPath, buildConfiguration, "lib" + appName + ".so").toString();
		String buildVerdictStaticLib = Paths.get(projectPath, buildConfiguration, "lib" + appName + ".a").toString();

		if (appType.toLowerCase().equals("native")) {
			if (Files.exists(Paths.get(buildVerdict)))
				isBuildSuccess = true;
			else if (Files.exists(Paths.get(buildVerdictSharedLib)))
				isBuildSuccess = true;
			else if (Files.exists(Paths.get(buildVerdictStaticLib)))
				isBuildSuccess = true;
		} else {
			isBuildSuccess = !isDiffFile(Paths.get(projectPath), Paths.get(projectPath, ".buildResult"), appName);
		}
		return isBuildSuccess;
	}

	// private static ArrayList<String> getAllRootstraps(String profile, String
	// deviceType) {
	// ArrayList <String> rootstrapsList = new ArrayList<String>();
	// ProcessBuilder getRootstraps;
	// Process getRootstrapsProcess;
	// try {
	// if (!isWindows()) {
	// getRootstraps = new ProcessBuilder("/bin/sh",
	// "-c",ResourceUtils.getSdkPath()+"/tools/ide/bin/tizen list rootstrap");
	// } else {
	// getRootstraps = new ProcessBuilder("cmd", "/c",
	// ResourceUtils.getSdkPath()+"/tools/ide/bin/tizen list rootstrap");
	// }
	// getRootstrapsProcess = getRootstraps.start();
	// String line = "";
	// BufferedReader msgReader = new BufferedReader(new
	// InputStreamReader(getRootstrapsProcess.getInputStream()));
	//
	//
	// while ((line = msgReader.readLine()) != null) {
	// if(line.toLowerCase().contains(profile.toLowerCase()) &&
	// line.toLowerCase().contains(profile.toLowerCase())) {
	// rootstrapsList.add(new Scanner(line).next());
	// }
	// }
	// getRootstrapsProcess.waitFor();
	// msgReader.close();
	// getRootstrapsProcess.destroy();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return rootstrapsList;
	// }

	private static String getAppName(String projectFilePath) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(projectFilePath));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("APPNAME")) {
				bufferedReader.close();
				return line.split("=")[1].trim();
			}
		}
		bufferedReader.close();
		return null;
	}

	// private static String getRootStrap() {
	// String profile = null;
	// String version = null;
	// String deviceType = null;
	// String line = null;
	//
	// File modelConfigFile = new File(System.getProperty("user.dir") + "/"
	// +Constants.MODEL_CONFIG_FILE);
	// if(!modelConfigFile.exists()){
	// runCommand("sdb root on");
	// runCommand("sdb pull /etc/config/model-config.xml
	// "+modelConfigFile.getParent());
	// }
	// try{
	// BufferedReader bufferedReader = new BufferedReader(new
	// FileReader(modelConfigFile));
	//
	// while((line = bufferedReader.readLine()) != null){
	// if (line.contains(Constants.MODEL_CONFIG_TAG_PROFILE))
	// profile =
	// getTagValue(line,Constants.MODEL_CONFIG_TAG_PROFILE).toLowerCase();
	// else if (line.contains(Constants.MODEL_CONFIG_TAG_VERSION))
	// version = getTagValue(line,Constants.MODEL_CONFIG_TAG_VERSION);
	// else if (line.contains(Constants.MODEL_CONFIG_TAG_ARCH))
	// deviceType =
	// Boolean.parseBoolean(getTagValue(line,Constants.MODEL_CONFIG_TAG_ARCH)) ?
	// "device" : "emulator";
	// }
	// bufferedReader.close();
	//
	// ArrayList<String> allRootStarp = getAllRootstraps(profile,deviceType);
	//
	//
	//
	// return profile + "-" + version + "-" + deviceType + ".core";
	// }catch(Exception ex){
	// return null;
	// }
	// }
	//
	// private static String getTagValue(String line, String tag) {
	// return line.split(">")[1].trim().split("<")[0].trim();
	// }
	//
	// public static boolean isDeviceConnected() {
	// List<String> commandOutLines = runCommand("sdb devices");
	// for (String line : commandOutLines) {
	// if(line.matches(Constants.SDB_DEVICE_REGEX)) {
	// return true;
	// }
	// }
	// return false;
	// }

	static boolean cliProjectPackage(String appType, String buildConfiguration, String certPath, String keyName,
			String keyPWD, String projectPath) {
		SecurityProfile securityProfile = new SecurityProfile(certPath, keyName, keyPWD);
		securityProfile.addSecurityProfile();
		securityProfile.setDefault();

		String packageFormat;
		String directory;
		if (appType.toLowerCase().equals("native")) {
			packageFormat = "tpk";
			directory = Paths.get(projectPath, buildConfiguration).toString();
		} else {
			packageFormat = "wgt";
			directory = Paths.get(projectPath, ".buildResult").toString();
		}
		List<String> lines = runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen " + "package --type "
				+ packageFormat + " --sign " + securityProfile.getKeyName() + " -- " + directory);

		CommonUtils.sleep(1000);

		for (String line : lines) {
			if (line.contains("Failed")) {
				return false;
			}
		}
		boolean isPackageFound = false;
		int tmes = 0;
		while (true) {
			isPackageFound = isFileExistExtension(directory, "." + packageFormat);
			if (isPackageFound || ++tmes >= 10)
				break;
			CommonUtils.sleep(1000);
		}
		return isPackageFound;
	}

	private static boolean isFileExistExtension(String path, String extension) {
		File[] fileList = new File(path).listFiles();
		for (File file : fileList) {
			if (!file.isDirectory() && file.getName().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isDiffFile(Path path1, Path path2, String appName) {
		boolean isDiff = false;
		ArrayList<String> dir1List = new ArrayList<String>();
		ArrayList<String> dir2List = new ArrayList<String>();

		try (Stream<Path> paths = Files.walk(path1)) {
			paths.forEach(filePath -> {
				if (!filePath.toFile().getAbsolutePath().contains(".buildResult")
						&& !filePath.getFileName().toString().equals(appName)
						&& !filePath.toFile().getAbsolutePath().contains(".project")
						&& !filePath.toFile().getAbsolutePath().contains(".tproject")) {
					dir1List.add(filePath.getFileName().toString());
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try (Stream<Path> paths = Files.walk(path2)) {
			paths.forEach(filePath -> {
				if (!filePath.getFileName().toString().equals(".buildResult")
						&& !filePath.getFileName().toString().equals(appName)
						&& !filePath.toFile().getAbsolutePath().contains(".project")
						&& !filePath.toFile().getAbsolutePath().contains(".tproject")) {
					dir2List.add(filePath.getFileName().toString());
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (dir1List.isEmpty() || dir2List.isEmpty() || (dir1List.size() != dir2List.size())) {
			isDiff = true;
		}

		for (int i = 0; i < dir1List.size(); i++) {
			String f1 = dir1List.get(i);
			String f2 = dir2List.get(i);

			if (!f1.equals(f2)) {
				isDiff = true;
				break;
			}
		}

		return isDiff;
	}

	static boolean installApp(EnumCommand command, String packageID, String path) {
		System.out.println("[AutomatorCore] Disabling privacy popup");
		runPrivacyScript(packageID);		
		
		runCommand("sdb root off");

		if (command == EnumCommand.SDB) {
			runCommand("sdb uninstall " + packageID);
			runCommand("sdb install " + path);
		} else if (command == EnumCommand.CLI) {
			File packageFile = new File(path);
			runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen uninstall --pkgid " + packageID);
			runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen install --name " + packageFile.getName()
					+ " -- " + packageFile.getParent());
		}

		CommonUtils.sleep(1000);

		List<String> lines = runCommand("sdb shell \"pkgcmd -l | grep -w " + packageID + " | wc -l\"");

		for (String line : lines) {
			if (line.contains("1"))
				return true;
		}

		return false;
	}

	static boolean launchApp(EnumCommand command, EnumAppLauncher appType, String packageId, String appID) {
		boolean isAppLaunched = false;
		List<String> commandOutputlines;

		runCommand("sdb root on");
		runCommand("sdb shell rm -rf " + Constants.DEVICE_CRASH_REPORT_DIRECTORY + "*");

		if (appType == EnumAppLauncher.WATCH) {
			runCommand("sdb root on");
			runCommand("sdb shell date -s \"Wed Jan 1 00:00:00 KST 2016\"");
			String id = packageId;
			if (ResourceUtils.isVersion_3_0()) {
				id = appID;
			}
			runCommand("sdb shell vconftool set -t string db/wms/clocks_set_idle " + id + " -f ");

			CommonUtils.sleep(2000);

			commandOutputlines = runCommand("sdb shell \"ps aux | grep -w " + appID + " | grep -v grep | wc -l\"");
			for (String line : commandOutputlines) {
				if (line.contains("1")) {
					isAppLaunched = true;
					break;
				}
			}
		} else if (appType == EnumAppLauncher.WIDGET) {
			runCommand("sdb root off");
			runCommand("sdb shell launch_app org.tizen.widget_viewer_sdk " + appID);

			CommonUtils.sleep(2000);

			commandOutputlines = runCommand("sdb shell \"aul_test is_run " + appID + "\"");
			for (String line : commandOutputlines) {
				if (line.contains(appID + " is running")) {
					isAppLaunched = true;
					break;
				}
			}

		} else {
			runCommand("sdb root off");

			if (command == EnumCommand.SDB) {
				runCommand("sdb shell launch_app " + appID);
			} else {
				runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen run --pkgid " + packageId);
			}

			CommonUtils.sleep(2000);

			commandOutputlines = runCommand("sdb shell \"aul_test is_run " + appID + "\"");
			for (String line : commandOutputlines) {
				if (line.contains(appID + " is running")) {
					isAppLaunched = true;
					break;
				}
			}
		}

		return isAppLaunched;
	}

	static boolean closeApp(long waitMilisecond, EnumAppLauncher appLauncher, String packageID, String appID) {
		System.out.println("[AutomatorCore] Waiting for 1000 Milisecond before closing");
		CommonUtils.sleep(1000);
		
		boolean isAppRunning = true;
		List<String> psCommandOutputlines;

		if (appLauncher == EnumAppLauncher.WATCH) {
			runCommand("sdb root on");
			runCommand("sdb shell vconftool set -t string db/wms/clocks_set_idle " + packageID + " -f ");

			CommonUtils.sleep(waitMilisecond);

			runCommand("sdb root on");
			psCommandOutputlines = runCommand("sdb shell \"ps aux | grep -w " + appID + " | grep -v grep | wc -l\"");
			for (String line : psCommandOutputlines) {
				if (line.contains("0")) {
					isAppRunning = false;
					break;
				}
			}

		} else {
			runCommand("sdb root off");
			runCommand("sdb shell pkgcmd -k -n " + packageID);

			CommonUtils.sleep(waitMilisecond);

			runCommand("sdb root off");
			psCommandOutputlines = runCommand("sdb shell \"aul_test is_run " + appID + "\"");
			for (String line : psCommandOutputlines) {
				if (line.contains(appID + " is not running")) {
					isAppRunning = false;
					break;
				}
			}
		}

		if (!isAppRunning) {
			System.out.println("[AutomatorCore] " + "\"" + appID + "\" successfully closed");
			return true;
		}

		System.out.println("[AutomatorCore] " + "\"" + appID + "\"" + " close failed");
		return false;
	}

	static boolean uninstallApp(EnumCommand command, String packageID) {
		runCommand("sdb root off");

		if (command == EnumCommand.SDB) {
			runCommand("sdb uninstall " + packageID);

			CommonUtils.sleep(1000);

			List<String> lines = runCommand("sdb shell \"pkgcmd -l | grep -w " + packageID + " | wc -l\"");

			for (String line : lines) {
				if (line.contains("0"))
					return true;
			}
			return false;

		} else if (command == EnumCommand.CLI) {
			runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen uninstall --pkgid " + packageID);

			CommonUtils.sleep(1000);

			List<String> lines = runCommand("sdb shell \"pkgcmd -l | grep -w " + packageID + " | wc -l\"");

			for (String line : lines) {
				if (line.contains("0"))
					return true;
			}
			System.out.println("[AutomatorCore] Uninstall failed with tizen command, trying with sdb");
			runCommand("sdb uninstall " + packageID);
			CommonUtils.sleep(1000);
			return false;
		}
		return false;

	}

	static boolean detectCrash(String appCrashFileName) {
		for (int time = 0; time < 5; time++) {
			List<String> lines = runCommand("sdb shell ls " + Constants.DEVICE_CRASH_REPORT_DIRECTORY);
			for (String line : lines) {
				if (line.contains(appCrashFileName))
					return true;
			}
			CommonUtils.sleep(4000);
		}
		return false;
	}

}
