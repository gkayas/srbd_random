package sdk;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class UtilityMac extends UtilityBase
{
	
	public UtilityMac()
	{
		osName = Resources.OS_NAME_MAC;
		Settings.MinSimilarity = 0.6;
		screen = new Screen();
	}
	public void focusVm() throws Exception
	{
		boolean isVmFocused = false;
		screen.setAutoWaitTimeout(2);
		Settings.MinSimilarity = 0.6;
		
		for(int windowIndex = 0; windowIndex<Resources.MAX_WINDOW_COUNT; windowIndex++)
		{
				
				
				//wait(2000);
				if(screen.exists(Resources.EMULATOR_TOP) != null)
				{
					System.out.println("Found Vm");
					screen.click(Resources.EMULATOR_TOP);
					isVmFocused = true;
					//Settings.MinSimilarity = 0.7;
					break;
				}
				else
					screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
		}
		if(!isVmFocused)
			throw new Exception("Vm not focused");
	}
	
	public static void focusVmLarge() throws Exception
	{
		boolean isVmFocused = false;
		screen.setAutoWaitTimeout(2);
		Settings.MinSimilarity = 0.6;
		
		for(int windowIndex = 0; windowIndex<Resources.MAX_WINDOW_COUNT; windowIndex++)
		{
				
				
				//wait(2000);
				if(screen.exists(Resources.EMULATOR_ONEX_HOME_BUTTON) != null)
				{
					System.out.println("Found Vm");
					screen.click(Resources.EMULATOR_ONEX_HOME_BUTTON);
					isVmFocused = true;
					//Settings.MinSimilarity = 0.7;
					break;
				}
				else
					screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
		}
		if(!isVmFocused)
			throw new Exception("Vm not focused");
	}
	
	public void focusWearable(String appName,String appImage) throws Exception 
	{
		boolean isVmFocused = false;
		screen.setAutoWaitTimeout(2);
		Settings.MinSimilarity = 0.9;
		for(int windowIndex = 0; windowIndex<Resources.MAX_WINDOW_COUNT; windowIndex++)
		{
				
				
				//screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
				//wait(2000);
				
				if(screen.exists(appImage) != null)
				{
					System.out.println("Found App");
					screen.click(appImage);
					//Settings.MinSimilarity = 0.7;
					isVmFocused = true;
					break;
				}
				else
					screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
		}
		if(!isVmFocused)
			throw new Exception(appName + " not focused");
	}
	
	public void focusCssPreview() throws Exception 
	{
		if(App.focus("Google Chrome") == null)
			throw new Exception("CSS Preview not running");
	}
	
	public void focusHtmlPreview() throws Exception 
	{
		if(App.focus("Google Chrome") == null)
			throw new Exception("Preview not running");
	}
	
	public void focusWebInspector() throws Exception 
	{
		if(App.focus("Google Chrome") == null)
			throw new Exception("Web Inspector not running");
	}
	
	
	public void focusSimulator() throws Exception 
	{
		if(App.focus("Google Chrome") == null)
			throw new Exception("Simulator not running");
	}
	public void focusApp(String appName,String appImage) throws Exception 
	{
		boolean isVmFocused = false;
		screen.setAutoWaitTimeout(2);
		Settings.MinSimilarity = 0.6;
		for(int windowIndex = 0; windowIndex<Resources.MAX_WINDOW_COUNT; windowIndex++)
		{
				
				
				//screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
				//wait(2000);
				
				if(screen.exists(appImage) != null)
				{
					System.out.println("Found App");
					screen.click(appImage);
					//Settings.MinSimilarity = 0.7;
					isVmFocused = true;
					break;
				}
				else
					screen.type(Key.TAB,KeyModifier.CMD+KeyModifier.SHIFT);
		}
		if(!isVmFocused)
			throw new Exception(appName + " not focused");
	}
	
	public void closeApp(String appName,String appImage) throws Exception 
	{
		this.focusApp(appImage,appImage);
		screen.type("q",KeyModifier.CMD);
	}
}
