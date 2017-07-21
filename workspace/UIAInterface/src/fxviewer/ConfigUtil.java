package fxviewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


public class ConfigUtil {

	static HashMap<String, String> devInfo = new HashMap<String, String>();


	public static String getValue(final String key) {
		return devInfo.get(key);
	}

	static void setValue(String key,String value) {
		devInfo.put(key, value);
	}

	public static void readDeviceConfig() {

		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.DEVICE_CONFIG_FILE;

		try {
			Scanner sc = new Scanner(new File(configPath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String tokens[] = line.split("=");
				if(tokens.length == 1){
					devInfo.put(tokens[0].trim(), "");
				}else{
					devInfo.put(tokens[0].trim(), tokens[1].trim());
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(configPath + " file not found");
		} catch (Exception e) {
			System.out.println("Wrong formated Config File.");
		}
	}

	public static void readUIAToolConfig() {

		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.UIATOOL_CONFIG_FILE;

		try {
			Scanner sc = new Scanner(new File(configPath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String tokens[] = line.split("=");
				if(tokens.length == 1){
					devInfo.put(tokens[0].trim(), "");
				}else{
					devInfo.put(tokens[0].trim(), tokens[1].trim());
				}

			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(configPath + " file not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void writeIntoDeviceConfig()  {
		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.DEVICE_CONFIG_FILE;

		try {
			PrintWriter pw = new PrintWriter(new File(configPath));

			pw.write(Constants.LIVE_MODE + "="+devInfo.get(Constants.LIVE_MODE)+"\n");
			pw.write(Constants.PLATFORM_VERSION + "="+devInfo.get(Constants.PLATFORM_VERSION)+"\n");
			pw.write(Constants.PROFILE_NAME + "="+devInfo.get(Constants.PROFILE_NAME)+"\n");
			pw.write(Constants.PROFILE_TYPE + "="+devInfo.get(Constants.PROFILE_TYPE)+"\n");
			pw.write(Constants.SCREEN_WIDTH + "="+devInfo.get(Constants.SCREEN_WIDTH)+"\n");
			pw.write(Constants.SCREEN_HEIGHT + "="+devInfo.get(Constants.SCREEN_HEIGHT)+"\n");
			pw.write(Constants.EMULATOR_TYPE + "="+devInfo.get(Constants.EMULATOR_TYPE)+"\n");
			pw.write(Constants.ASSERT_MODE + "="+devInfo.get(Constants.ASSERT_MODE)+"\n");
			pw.write(Constants.REFRESH_TIME + "="+devInfo.get(Constants.REFRESH_TIME)+"\n");
			pw.write(Constants.SEARCH_TIME + "="+devInfo.get(Constants.SEARCH_TIME)+"\n");
			pw.write(Constants.SHOW_LOG_MESSAGE + "="+devInfo.get(Constants.SHOW_LOG_MESSAGE)+"\n");
			pw.write(Constants.CAPTURE_REF_IMAGE_PATH + "="+devInfo.get(Constants.CAPTURE_REF_IMAGE_PATH)+"\n");
			pw.write(Constants.SDK_PATH + "="+devInfo.get(Constants.SDK_PATH)+"\n");
			pw.write(Constants.DEFAULT_WATCHAPP_PACKAGEID + "="+devInfo.get(Constants.DEFAULT_WATCHAPP_PACKAGEID));

			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
		}
	}

	public static void writeIntoUIAToolConfig() {
		String configPath;
		configPath = System.getProperty("user.dir") + "/" + Constants.UIATOOL_CONFIG_FILE;

		try {
			PrintWriter pw = new PrintWriter(new File(configPath));
			pw.write(Constants.ARCH + "="+devInfo.get(Constants.ARCH)+"\n");
			pw.write(Constants.COMPILER + "="+devInfo.get(Constants.COMPILER)+"\n");
			pw.write(Constants.BUILD_CONFIG + "="+devInfo.get(Constants.BUILD_CONFIG)+"\n");
			pw.write(Constants.SAMPLE_PROFILE + "="+devInfo.get(Constants.SAMPLE_PROFILE)+"\n");
			pw.write(Constants.SAMPLE_SERVER + "="+devInfo.get(Constants.SAMPLE_SERVER)+"\n");
			pw.write(Constants.USER + "="+devInfo.get(Constants.USER)+"\n");
			pw.write(Constants.USER_PWD + "="+devInfo.get(Constants.USER_PWD)+"\n");
			pw.write(Constants.KEY_PATH + "="+devInfo.get(Constants.KEY_PATH)+"\n");
			pw.write(Constants.KEY_NAME + "="+devInfo.get(Constants.KEY_NAME)+"\n");
			pw.write(Constants.KEY_PWD + "="+devInfo.get(Constants.KEY_PWD)+"\n");
			pw.write(Constants.REPORT_PATH + "="+devInfo.get(Constants.REPORT_PATH)+"\n");
			pw.write(Constants.EXPLORATORY_MODE + "="+devInfo.get(Constants.EXPLORATORY_MODE)+"\n");

			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
		}
	}

}
