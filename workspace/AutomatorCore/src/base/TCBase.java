package base;

import java.io.*;

import org.sikuli.basics.*;
import org.sikuli.script.*;

import device.*;
import junit.framework.*;
import sdk.UtilityBase;
import sdk.UtilityLin;
import sdk.UtilityMac;
import sdk.UtilityWin;


public class TCBase extends TestCase
{
	public static Target device;
	public static Screen screen;
	public static UtilityBase utility;
	
	public TCBase()
	{
		super();		
	}
	
	public TCBase(String s)
	{
		super(s);
		configure();
	}
	
	private static void configure() 
	{
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		utility = Settings.isWindows()? new UtilityWin() : (Settings.isLinux()?new UtilityLin() : new UtilityMac());
		screen = new Screen(); 
		device = new Target();
		screen.setAutoWaitTimeout(10);		
		Settings.OcrDataPath = ResourceUtils.getLibPath();
		setImagePaths();
	}

	private static void addImagePath(String Path){
		File file = new File(Path);
		if(file.exists()){
			if(file.isDirectory() && file.getName().indexOf(".svn") < 0){
				ImagePath.add(file.getAbsolutePath());
				File[] fileList = file.listFiles();
				for (File f : fileList) {
					addImagePath(f.getAbsolutePath());
					
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private static void setImagePaths() 
	{
		
		ImagePath.add(ResourceUtils.getProjectPath() + "AutomatorCore/res");
		
		addImagePath(ResourceUtils.getRefImagePath());
		
	}	
}
