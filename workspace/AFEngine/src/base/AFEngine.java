package base;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import device.*;
import rmi.Stub;

public class AFEngine {
	
	public static void initialize()
	{
		CommonUtils.readDeviceConfig();
		
	}

	public static void initialize(String projectPath,String logFileName, String rmiPort)
	{
		CommonUtils.initLogger(logFileName);
		CommonUtils.setProjectPath(projectPath);
		CommonUtils.rmiPort = rmiPort;
		initialize();
	}

	public static void deinitialize()
	{
		//TODO
		CommonUtils.rmiPort = "";
	}
}
