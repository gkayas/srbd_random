package sdk;

import org.sikuli.script.App;
import org.sikuli.script.Screen;

import device.ResourceUtils;

public class UtilityWin extends UtilityBase {
	public UtilityWin() {
		osName = Resources.OS_NAME_WIN;
		screen = new Screen();
	}

	public void focusVm() throws Exception {
		if(ResourceUtils.isVersion_2_3() || ResourceUtils.isVersion_2_3_1()) {
			if (App.focus("Emulator -") == null)
				throw new Exception("Vm not running");
		}else if(ResourceUtils.isVersion_2_4() || ResourceUtils.isVersion_2_3_2()){
			boolean isVmFocused = false;
			if(screen == null) System.out.println("NOK");
			if (screen.exists(ImageSelection(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU)) != null) {
				System.out.println("Found App");
				screen.doubleClick(ImageSelection(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU));
				//screen.click(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU);
				isVmFocused = true;
			}
			if (!isVmFocused)
				throw new Exception(" Vm not focused");
		}
		else if(ResourceUtils.isVersion_3_0()){
			boolean isVmFocused = false;
			if(screen == null) System.out.println("NOK");
			if (screen.exists(ImageSelection(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU)) != null) {
				System.out.println("Found App");
				screen.doubleClick(ImageSelection(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU));
				//screen.click(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU);
				isVmFocused = true;
			}
			if (!isVmFocused)
				throw new Exception(" Vm not focused");
		}

	}//end of focusVm function

	public void focusApp(String appName, String appImage) throws Exception {
		if (appName.equals("Emulator Control Panel")) {
			if (App.focus(appName) == null)
				throw new Exception(appName + " not focus");
		} 
//			else {
//			boolean isVmFocused = false;
//			if (screen.exists(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU) != null) {
//				System.out.println("Found App");
//				screen.doubleClick(Resources.EMULATOR_ICON_IN_WINDOWS_TASK_MENU);
//				isVmFocused = true;
//			}
//			if (!isVmFocused)
//				throw new Exception(" Vm not focused");
//		}
	}//end of focusApp function

}
