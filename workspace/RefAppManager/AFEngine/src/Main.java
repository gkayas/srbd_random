import org.junit.runner.*;
import org.junit.runner.notification.Failure;

import base.AFEngine;
import device.CommonUtils;


public class Main
{
	public static String deviceConfingPath = null;
	
	public static void main(String[] args)
	{
		try
		{
			deviceConfingPath = args[0];
			CommonUtils.setDeviceConfigPath(deviceConfingPath);
		}
		catch(Exception ex)
		{
			deviceConfingPath = "";
		}
		
		AFEngine.initialize();
		
		Result result = JUnitCore.runClasses(TestCase.class);
		for (Failure failure : result.getFailures()) 
			System.err.println(failure.getMessage());	
		
		AFEngine.deinitialize();
		System.exit(0);
	}
}
