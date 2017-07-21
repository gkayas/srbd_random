package device;

public class Constants {

	//Device Interface
	public final static String DEVICE_INTERFACE_ANCHOR = "di-anchor.png";
	public final static String DEVICE_INTERFACE_TITLE = "Device Interface";
	public final static String DEVICE_INTERFACE_HOME = "di-home.png";
	public final static String DEVICE_INTERFACE_MENU = "di-menu.png";
	public final static String DEVICE_INTERFACE_BACK = "di-back.png";
	public final static long DEVICE_SCROLL_TIME = 3000;

	//device-config.ini keys
	public final static String LIVE_MODE = "LIVE_MODE";
	public final static String PLATFORM_VERSION = "PLATFORM_VERSION";
	public final static String PROFILE_NAME = "PROFILE_NAME";
	public final static String PROFILE_TYPE = "PROFILE_TYPE";
	public final static String SCREEN_WIDTH = "SCREEN_WIDTH";
	public final static String SCREEN_HEIGHT = "SCREEN_HEIGHT";
	public final static String EMULATOR_TYPE = "EMULATOR_TYPE";
	public final static String ASSERT_MODE = "ASSERT_MODE";
	public final static String REFRESH_TIME = "REFRESH_WAIT_TIME";
	public final static String SEARCH_TIME = "SEARCH_WAIT_TIME";
	public final static String SHOW_LOG_MESSAGE = "SHOW_LOG_MESSAGE";
	public final static String CAPTURE_REF_IMAGE_PATH = "CAPTURE_REF_IMAGE_PATH";
	public final static String SDK_PATH = "SDK_PATH";
	public final static String PLATFORM_ARCHITECTURE = "PLATFORM_ARCHITECTURE";

	public static final String MODEL_CONFIG_FILE = "model-config.xml";
	public static final String SDB_DEVICE_REGEX = "^([a-zA-Z0-9_.-]+)\\s+([a-zA-Z0-9_.-]+)\\s+[a-zA-Z0-9_.-]+$";

	//Resource value
	public final static double DEFAULT_SIMILARITY = 0.7;
	public final static double DEFAULT_SIMILARITY_TEXT = 0.8;
	//public final static int DEFAULT_WAIT = 3000;

	//Resource Paths
	final static String RESOURCE_ROOT = "res/";
	final static String SCRIPTS_ROOT = "scripts/";
	final static String RES_FILE_PATH = RESOURCE_ROOT+"files/";
	public final static String REF_IMAGE_PATH = RESOURCE_ROOT +"ref_images/";
	public final static String TEST_IMAGE_PATH = RES_FILE_PATH+"image/";
	public final static String TEST_MUSIC_PATH = RES_FILE_PATH+"music/";
	public final static String TEST_VIDEO_PATH = RES_FILE_PATH+"video/";
	public final static String XML_ROOT = RES_FILE_PATH+"xml/";
	final static String ERROR_PATH = "error/";
	public final static String COMMON_IMAGE_PATH = "common";
	public final static String COMMON_DIR = "common";
	public final static String LIB_PATH = "AFEngine/lib";
	public final static String SCREENSHOT_PATH = "screenshots";
	public final static String DEVICE_CONFIG_FILE ="device_config.ini";
	public final static String SDK_INFO ="sdk.info";
	public final static String SDK_INFO_TIZEN_SDK_DATA_PATH_TAG ="TIZEN_SDK_DATA_PATH";
	public final static String DEVICE_CRASH_REPORT_DIRECTORY = "/opt/usr/share/crash/dump/";


	public final static String DEFAULT_CERT_NAME = "cert.p12";
	public final static String DEFAULT_CERT_KEY_NAME = "key";
	public final static String DEFAULT_CERT_PASSWORD = "password";

	//SDB Commands
	final static String SDB_DEVICES = "sdb devices";
	final static String SDB_DEVICE_INFO = "sdb shell xwininfo -root";


	//Patterns
	final static String REGX_PATTERN_SCREEN_WIDTH_FROM_DEVICE_INFO = "(^.*Relative upper-left Y:\\s*\\d+\\s*Width:\\s*)(\\d+)(.*$)";
	final static String REGX_PATTERN_SCREEN_HEIGHT_FROM_DEVICE_INFO = "(^.*Relative upper-left Y:\\s*\\d+\\s*Width:\\s*\\d+.*Height:\\s*)(\\d+)(.*$)";


	public static final String MODEL_CONFIG_TAG_PROFILE="profile\"";
	public static final String MODEL_CONFIG_TAG_VERSION="version\"";
	public static final String MODEL_CONFIG_TAG_ARCH="platform.core.cpu.arch.armv7";

}

