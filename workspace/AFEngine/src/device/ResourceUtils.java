package device;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sdk.Resources;

public class ResourceUtils {

	public static String getDeviceConfingValue(String key)
	{
		String value = null;
		try
		{
			value = CommonUtils.devInfo.get(key);
		}
		catch(Exception e)
		{
			
		}
		if(value == null)
		{
			CommonUtils.readDeviceConfig();
			value = CommonUtils.devInfo.get(key);
		}
		return value;
		
	}
	
	public static String getProjectPath()
	{
		if(CommonUtils.projectPath == null) {
			CommonUtils.projectPath = System.getProperty("user.dir");
		}
		
		return CommonUtils.projectPath + "/";
	}
	
	public static String getTestVersion()
	{
		return getDeviceConfingValue(Constants.PLATFORM_VERSION);
	}
	
	public static String getProfileName(){
		return getDeviceConfingValue(Constants.PROFILE_NAME);
	}

	public static String getProfileType(){
		return getDeviceConfingValue(Constants.PROFILE_TYPE);
	}
	
	public static String getScreenWidth() {
		return getDeviceConfingValue(Constants.SCREEN_WIDTH);
	}
	
	public static String getSdkPath() {
		return getDeviceConfingValue(Constants.SDK_PATH);
	}
	
	public static String getSdkDataPath() {
		String configPath = getSdkPath() + File.separator + Constants.SDK_INFO;
		String sdDataPath = null;
		try 
		{		
			Scanner sc = new Scanner(new File(configPath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				if(line.contains(Constants.SDK_INFO_TIZEN_SDK_DATA_PATH_TAG)){
					sdDataPath = line.split("=")[1].trim();
					sc.close();
					return sdDataPath;
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("[AutomatorCore] " + e.getMessage());
		}
		return sdDataPath;
	}

	static String getScreenHeight() {
		return getDeviceConfingValue(Constants.SCREEN_HEIGHT);
	}
	
	public static String getEmulatorType()
	{
		return getDeviceConfingValue(Constants.EMULATOR_TYPE);
	}
		
	static long getSearchTime(){
		return Integer.parseInt(getDeviceConfingValue(Constants.SEARCH_TIME));
	}
	
	static long getRefreshTime(){
		return Integer.parseInt(getDeviceConfingValue(Constants.REFRESH_TIME));
	}	
	
	public static int getLogMessage(){
		return Integer.parseInt(getDeviceConfingValue(Constants.SHOW_LOG_MESSAGE));
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String getResourcePath(){
		String resPath="";
		resPath = getProjectPath() + Constants.RESOURCE_ROOT;
		return resPath;
	}
	
	public static String getScriptsPath(){
		String path = "";
		path = getProjectPath() + Constants.SCRIPTS_ROOT;
		return path;
	}
	
	public static String getXmlPath(){
		String path = "";
		path = getProjectPath() + Constants.XML_ROOT;
		return path;
	}
	
	public static String getLibPath(){
		String path = "";
		path = getProjectPath() + Constants.LIB_PATH;
		return path;
	}
	
	public static String getScreenshotPath(){
		String path = "";
		path = getProjectPath() + Constants.SCREENSHOT_PATH;
		return path;
	}
	

	
	public static boolean getAssertMode(){
		return Integer.parseInt(getDeviceConfingValue(Constants.ASSERT_MODE)) == 1;
	}
	
	
	public static String getDeviceType(){
		return getDeviceConfingValue(Constants.PLATFORM_ARCHITECTURE).indexOf("arm") >=0 ? "target" : "emulator";
	}
	
	public static String getErrorScreenshotPath(){
		String path = "";
		path = getProjectPath() + Constants.ERROR_PATH;
		return path;
	}
	
	public static String getRefImagePath(){
		String path = "";
		path = getProjectPath() + Constants.REF_IMAGE_PATH;
		return path;
	}
	
	public static String getTestImagePath(){
		String path = "";
		path = getProjectPath() + Constants.TEST_IMAGE_PATH;
		return path;
	}
	
	public static String getTestVideoPath(){
		String path = "";
		path = getProjectPath() + Constants.TEST_VIDEO_PATH;
		return path;
	}
	
	public static String getTestMusicPath(){
		String path = "";
		path = getProjectPath() + Constants.TEST_MUSIC_PATH;
		return path;
	}
	
	public static Boolean isVersion_2_4(){
		Boolean result = false;
		if (ResourceUtils.getTestVersion().equals(Resources.PLATFORM_2_4))
			result = true;
		return result;
	}
	
	public static Boolean isVersion_2_3(){
		Boolean result = false;
		if (ResourceUtils.getTestVersion().equals(Resources.PLATFORM_2_3))
			result = true;
		return result;
	}
	
	public static Boolean isVersion_2_3_1(){
		Boolean result = false;
		if (ResourceUtils.getTestVersion().equals(Resources.PLATFORM_2_3_1))
			result = true;
		return result;
	}
	
	public static Boolean isVersion_2_3_2(){
		Boolean result = false;
		if (ResourceUtils.getTestVersion().equals(Resources.PLATFORM_2_3_2))
			result = true;
		return result;
	}
	
	public static Boolean isVersion_3_0(){
		Boolean result = false;
		if (ResourceUtils.getTestVersion().equals(Resources.PLATFORM_3_0))
			result = true;
		return result;
	}
	
}
