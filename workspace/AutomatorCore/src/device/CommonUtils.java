package device;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;


public class CommonUtils
{

	//config.ini keys
	static String projectPath = null;
	static Logger logger;
    static String mainLogFile;

    static HashMap<String, String> devInfo = new HashMap<String, String>();

    public static void initLogger(String logFileName) {
    	logger = Logger.getRootLogger();
    	logger.setLevel(Level.ALL);
    	PatternLayout layout = new PatternLayout("[%d{dd/MM/yyyy HH:mm:ss}][%p] %m%n");
    	logger.addAppender(new ConsoleAppender(layout));
    	try
    	{
    		RollingFileAppender fileAppender = new RollingFileAppender(layout, "log/" + logFileName);
    		logger.addAppender(fileAppender);
    	}
    	catch (IOException e)
    	{
    		System.out.println("Failed to add appender !!");
    	}
    	tieSystemOutAndErrToLog();
	}

    static void tieSystemOutAndErrToLog() {
        System.setOut(createLoggingSysOut(System.out));
        System.setErr(createLoggingSysErr(System.err));
    }

    private static PrintStream createLoggingSysOut(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
           		logger.info(string);
            }
            public void println(final String string) {
            	logger.info(string);
            }
        };
    }

    private static PrintStream createLoggingSysErr(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                logger.error(string);
            }
            public void println(final String string) {
                logger.error(string);
            }
        };
    }
	public static void readDeviceConfig()
	{
		String configPath;
		configPath = ResourceUtils.getProjectPath() + Constants.DEVICE_CONFIG_FILE;

		try
		{
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
			System.out.println("[AutomatorCore] " + configPath + " file not found");
		} catch (Exception e) {
			System.out.println("[AutomatorCore] " + "Wrong formated device_config.ini file.");
		}
	}

	public static void setProjectPath(String path)
	{
		projectPath = path;
	}

	static String getRegxSubstrByGroupID(String inStr, String pattern, int groupID)
	{

		Pattern r = Pattern.compile(pattern);

		String str = inStr.replaceAll("\"", Matcher.quoteReplacement("\\\""));
		Matcher m = r.matcher(str);

		if (m.find()) {
			//	System.out.println("GROUP$$$"+m.group(2));
		} else {
			// System.out.println("No Match found");
			return null;
		}

		return m.group(groupID).toString();
	}

	static String saveErrorScreenShot(String fileName){
		String ret = "";
		String errorFilePath = ResourceUtils.getErrorScreenshotPath();
		//DeviceUtils.saveScreenshot(errorFilePath);
		SikuiliUtils.focus();
		SikuiliUtils.getDeviceRegion().saveScreenCapture(errorFilePath, fileName);
		return ret;
	}

//	static boolean isMusicAvailable(){
//		boolean isAvail = false;
//		//TODO: Have to Implement Later
//		return isAvail;
//	}
//
//	static boolean isImageAvailable(){
//		boolean isAvail = false;
//		//TODO: Have to Implement Later
//		return isAvail;
//	}
//
//	static boolean isVideoAvailable(){
//		boolean isAvail = false;
//		//TODO: Have to Implement Later
//		return isAvail;
//	}


	static void sleep(long miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void cleanscreenshots()
	{
		File file = new File(ResourceUtils.getScreenshotPath());
		if(file.exists())
		{
			File[] files = file.listFiles();
			for(File f:files)
			{
				f.delete();
			}
		}
		else
		{
			file.mkdir();
		}
	}
}
