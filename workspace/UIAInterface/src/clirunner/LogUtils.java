package clirunner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
	
	public static String getLogFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String logFileName =  format.format(date)+"_uiautomator.log";
		return logFileName;
	}
}
