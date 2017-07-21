package base;

import device.*;

public class AutomatorCore {

	public static void initialize()
	{
		CommonUtils.readDeviceConfig();
	}

	public static void initialize(String projectPath,String logFileName)
	{
		CommonUtils.initLogger(logFileName);
		CommonUtils.setProjectPath(projectPath);
		initialize();
	}

	public static void deinitialize()
	{
		//TODO
	}
}
