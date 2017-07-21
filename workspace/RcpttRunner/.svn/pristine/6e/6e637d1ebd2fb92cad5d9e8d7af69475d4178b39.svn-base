

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;


public class UtilsLog {

    private static Logger logger;
    private static boolean getAppFocus = false;
 
    public static void initLogger(String logFileName) {
    	logger = Logger.getRootLogger();
    	logger.setLevel(Level.ALL);
    	PatternLayout layout = new PatternLayout("[%d{dd/MM/yyyy HH:mm:ss}][%p] %m%n");
    	logger.addAppender(new ConsoleAppender(layout));
    	try
    	{
    		RollingFileAppender fileAppender = new RollingFileAppender(layout, logFileName);
    		logger.addAppender(fileAppender);
    	}
    	catch (IOException e)
    	{
    		System.out.println("[SDKAutomator] Failed to add appender !!");
    	}
    	tieSystemOutAndErrToLog();
	}
    
    private static void tieSystemOutAndErrToLog() {
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
}
