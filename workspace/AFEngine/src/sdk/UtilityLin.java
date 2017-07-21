package sdk;

import org.sikuli.script.*;

public class UtilityLin extends UtilityBase
{
	public UtilityLin()
	{
		osName = Resources.OS_NAME_LIN;
		screen = new Screen();
	}
	
	public void closeApp(String appName,String appImage) throws Exception 
	{
		this.focusApp(appName,appImage);
		screen.type(Key.F4,Key.ALT);
		wait(2000);
		
		if(appName.compareTo("Emulator -")==0)
		{
			if(screen.exists(Resources.EMULATOR_POWER_OFF_BUTTON) != null)
				screen.click(Resources.EMULATOR_POWER_OFF_BUTTON);
		}
	}
}
