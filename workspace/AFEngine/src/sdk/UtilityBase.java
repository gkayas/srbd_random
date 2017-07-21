package sdk;

import java.io.*;
import java.util.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import org.sikuli.script.*;
import org.sikuli.basics.Settings;

import device.ResourceUtils;

public abstract class UtilityBase {
	public static Screen screen;
	public static Region simulatorRegion, simulatormenuregion, simulatorConfigurationReg, simulatorConfigurationMenuReg,
			simulatorConfigurationPanelReg;
	public static Region timelineBottomRegion, timelineRegion, profileRightPanel, profilebottomPanel,
			remoteInspectorRegion;
	public static Region emulatorQHDRegion, emulatorQHD1xRegion, emulatorWVGARegion, emulatorHDRegion;
	public static Region emulatorWearable, emulatorManagerRegion, emulatorManagerAbout, emulatorControlPanelReion;
	public static String osName = System.getProperty("os.name").toLowerCase();
	public static boolean res = false, result = false;

	public boolean FileExistsCheck(String path, String fileName){
		File directory = new File(path);
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.getName().equals(fileName)){
                res = true;
            } else if (file.isDirectory()){
            	FileExistsCheck(file.getAbsolutePath(), fileName);
            }
        }
        if(res) result = res;
        return result;
	}
	public boolean FileExistsCheck(String fileName){
		result = false;
		res = false;
		String versionImagePath = ResourceUtils.getRefImagePath()+ ResourceUtils.getTestVersion() + "/sdk";
		File directory = new File(versionImagePath);
        File[] fList = directory.listFiles();
        for (File file : fList){
        	if (file.getName().equals(fileName)){
            	res = true;
            } else if (file.isDirectory()){
                res = FileExistsCheck(file.getAbsolutePath(), fileName);
            }
        }
        if(res) result = res;
        return result;
	}
	
	
	public String ImageSelection(String res){
		System.out.println("ImageSelection");
		String emuType = null, newStr = null;
		
		if(System.getProperty("os.name").equals("Windows 10")){
			newStr = res.split("\\.")[0] + "-win10.png";
			if(!FileExistsCheck(newStr)){
				newStr = res;
			}
		}else{
			newStr = res;
		}
		if(ResourceUtils.getEmulatorType().toUpperCase().equals("HD")){
			 emuType = newStr.split("\\.")[0] + "-hd.png";
			 if(!FileExistsCheck(emuType)){
				 emuType = newStr;
			 }
		}else if(ResourceUtils.getEmulatorType().toUpperCase().equals("QHD")){
			emuType = newStr.split("\\.")[0] + "-qhd.png";
			if(!FileExistsCheck(emuType)){
				 emuType = newStr;
			 }
		}else{
			emuType = newStr.split("\\.")[0] + "-wvga.png";
			if(!FileExistsCheck(emuType)){
				 emuType = newStr;
			 }
		}
		return emuType;
	}
	
	public void SetEmulatorDisplayTimeout(String EmulatorType) throws Exception {
		if(EmulatorType.toLowerCase().equals("qhd")){
			DefinedEmulatorQHDRegion();
			emulatorQHDRegion.click(Resources.EMULATOR_QHD_HOME_BUTTON);
			
			if (emulatorQHDRegion.exists(Resources.EMULATOR_QHD_SCREEN_LOCKED) != null) {
				Match scrollScreen = emulatorQHDRegion.find(Resources.EMULATOR_QHD_SCREEN_LOCKED);
				screen.dragDrop(scrollScreen.above(100), scrollScreen.above(490));
				if (emulatorQHDRegion.exists(Resources.EMULATOR_QHD_APPS_PAGE_MENU_BUTTON) != null)
					emulatorQHDRegion.click(Resources.EMULATOR_QHD_APPS_PAGE_MENU_BUTTON);
				wait(1000);
				emulatorQHDRegion.click(Resources.EMULATOR_QHD_SETTING_ICON);
				wait(1000);
				emulatorQHDRegion.click(Resources.EMULATOR_QHD_SETTRING_DISPLAY_MENU);
				emulatorQHDRegion.click(Resources.EMULATOR_QHD_SETTRING_SCREEN_TIMEOUT);
				if (emulatorQHDRegion.exists(Resources.EMULATOR_QHD_SETTRING_SCREEN_TIME_ALWAYS_ON) != null)
					emulatorQHDRegion.click(Resources.EMULATOR_QHD_SETTRING_SCREEN_TIME_ALWAYS_ON);
				else
					emulatorQHDRegion.click(Resources.EMULATOR_QHD_SETTRING_SCREEN_TEN_MINS);
			}
		}else if(EmulatorType.toLowerCase().equals("hd")){
			
		}else if(EmulatorType.toLowerCase().equals("wearable")){
			DefinedEmulatorWearableRegion();
			// + "wait(2000);\n"
			emulatorWearable.click(Resources.WEB_IDE_WEARABLE_HOME_BUTTON);
			Match match = screen.find(Resources.WEB_IDE_WEARABLE_HOME_BUTTON);
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			emulatorWearable.click(Resources.WEB_IDE_WEARABLE_SETTINGS_APP_ICON);
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			emulatorWearable.find(Resources.EMULATOR_WEARABLE_SETTING_DISPLAY).click();
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			toggleViewState(Resources.EMULATOR_WEARABLE_ALWAYS_ON_FALSE);
			
		}
		
		else if(EmulatorType.toLowerCase().equals("wearable")){
			DefinedEmulatorWearableRegion();
			// + "wait(2000);\n"
			emulatorWearable.click(Resources.WEB_IDE_WEARABLE_HOME_BUTTON);
			Match match = screen.find(Resources.WEB_IDE_WEARABLE_HOME_BUTTON);
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			emulatorWearable.click(Resources.WEB_IDE_WEARABLE_SETTINGS_APP_ICON);
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			emulatorWearable.find(Resources.EMULATOR_WEARABLE_SETTING_DISPLAY).click();
			emulatorWearable.dragDrop(match.left(350).below(250), match.left(350).above(200));
			toggleViewState(Resources.EMULATOR_WEARABLE_ALWAYS_ON_FALSE);
			
		} else {
			DefinedEmulatorWVGARegion();
			emulatorWVGARegion.click(Resources.EMULATOR_QHD_HOME_BUTTON);
			if (emulatorWVGARegion.exists(Resources.EMULATOR_SCREEN_LOCKED) != null) {
				Match scrollScreen = emulatorWVGARegion.find(Resources.EMULATOR_SCREEN_LOCKED);
				screen.dragDrop(scrollScreen, scrollScreen.above(220));
			}
			if (emulatorWVGARegion.exists("emulator-new-apps-page.png") != null)
				emulatorWVGARegion.click("emulator-new-apps-page.png");
			wait(1000);
			emulatorWVGARegion.click(Resources.EMULATOR_SETTINGS_APP_ICON);
			wait(1000);
			emulatorWVGARegion.click(Resources.EMULATOR_SETTINGS_APP_DISPLAY);
			emulatorWVGARegion.click(Resources.EMULATOR_SETTINGS_SCREEN_TIMEOUT);
			if (emulatorWVGARegion.exists(Resources.EMULATOR_SCREEN_TIME_ALWAYS_ON) != null)
				emulatorWVGARegion.click(Resources.EMULATOR_SCREEN_TIME_ALWAYS_ON);
			else
				emulatorWVGARegion.click("emulator-screen-timeout-ten-min.png");
		}
	}
	
	
	public double getFileSize(String path) {
		File file = new File(path);
		double bytes = file.length();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);
		return megabytes;
	}

	public void ClickDown(int downCount) {
		for (int count = 0; count < downCount; count++) {
			screen.type(Key.DOWN);
			wait(250);
		}
		screen.type(Key.ENTER);
		// screen.type(Key.RIGHT);
	}

	public void openEmulatorControlPannel(String baseImage) throws Exception {
		this.ClickVmContextMenu(baseImage,Resources.POS_CONTROL_PANEL,0);
		wait(1500);
		if(screen.exists(Resources.CONTROL_PANNEL_ALREADY_OPEN_OK) != null)
			screen.click(Resources.CONTROL_PANNEL_ALREADY_OPEN_OK);
		wait(1000);
		this.focusApp("Emulator Control Panel", Resources.EMULATOR_CONTROL_MANAGER);
	}
	
	// public void ClickLeft(int leftCount)
	// {
	// for(int count = 0;count<leftCount;count++)
	// {
	// screen.type(Key.LEFT);
	// }
	// screen.type(Key.ENTER);
	// }

	/*
	 * border image: vm current condition border image menuPosition: position in
	 * context menu ex: 7 for control panel trePosition: position in tree menu
	 * ex: 3 for scale 1/2X, Else 0 for no tree menu EX:
	 * ClickVmContextMenu(Resources.EMULATOR_PHONE_BORDER,Resources.
	 * POS_CONTROL_PANEL,0);
	 */
	public void ClickVmContextMenu(String boderImage, int menuPosition, int treePosition) throws Exception {
		this.focusVm();
		wait(1000);
		screen.find(boderImage).highlight(1);
		screen.rightClick(boderImage);
		wait(2000);
		if(Settings.isMac() && ResourceUtils.isVersion_2_3_1() && menuPosition != 1)
		ClickDown(menuPosition-1);
		else if(ResourceUtils.isVersion_2_4() && menuPosition !=1 && menuPosition !=2)
        ClickDown(menuPosition+1);	
		else
	    ClickDown(menuPosition);	
        wait(1000);
		if (treePosition != 0) {
			screen.type(Key.RIGHT);
			wait(1000);
			ClickDown(treePosition - 1);
		}
	}
	
	public void ClickVmContextMenuLarge(String boderImage, int menuPosition, int treePosition) throws Exception {
		UtilityMac.focusVmLarge();
		wait(1000);
		screen.find(boderImage).highlight(1);
		screen.rightClick(boderImage);
		wait(2000);
		if(Settings.isMac() && ResourceUtils.isVersion_2_3_1() && menuPosition != 1)
		ClickDown(menuPosition-1);
		else
	    ClickDown(menuPosition);
        wait(1000);
		if (treePosition != 0) {
			screen.type(Key.RIGHT);
			wait(500);
			ClickDown(treePosition - 1);
		}
	}
	
		public void ClickVmContextMenuWithoutFocus(String boderImage, int menuPosition, int treePosition) throws Exception {
		//this.focusVm();
		wait(1000);
		screen.find(boderImage).highlight(1);
		screen.rightClick(boderImage);
		wait(2000);
		if(Settings.isMac() && ResourceUtils.isVersion_2_3_1() && menuPosition != 1)
		ClickDown(menuPosition-1);
		else if(ResourceUtils.isVersion_2_4() && menuPosition !=1 && menuPosition !=2)
	    ClickDown(menuPosition+1);	
		else
	    ClickDown(menuPosition);	
        wait(1000);
		if (treePosition != 0) {
			screen.type(Key.RIGHT);
			wait(500);
			ClickDown(treePosition - 1);
		}
	}

	public void ClickVmContextMenudetail(String boderImage, int menuPosition, int treePosition, int subtrePos)
			throws Exception {
		this.focusVm();
		screen.find(boderImage).highlight(1);
		screen.rightClick(boderImage);
		wait(2000);
		ClickDown(menuPosition);

		wait(1000);
		if (treePosition != 0) {
			//screen.type(Key.RIGHT);
			wait(500);
			ClickDown(treePosition - 1);
			wait(1000);
			if (subtrePos != 0) {
				//screen.type(Key.RIGHT);
				wait(500);
				// screen.type(Key.DOWN);
				ClickDown(subtrePos - 1);
			}
		}
	}

	public void DisableAutoUpdate() throws Exception {
		Settings.MinSimilarity = 0.90;
		while (screen.exists(Resources.UPDATE_ENABLE_BUTTON) != null) {
			screen.click(Resources.UPDATE_ENABLE_BUTTON);
			wait(1000);
		}
		Settings.MinSimilarity = 0.7;
	}

	public void ClickQHDEmulatorEditIcon() throws Exception {
		if (ResourceUtils.isVersion_2_3() || ResourceUtils.isVersion_2_3_1()) {
			screen.click(Resources.EMULATOR_QHD_HOME_BUTTON);
			screen.click(Resources.EMULATOR_QHD_EDIT_ICON);
		}else if(ResourceUtils.isVersion_2_4()){
			this.EnterEmulatorAppsPage("QHD", Resources.EMULATOR_QHD_TASK_SWITCHER_CLEARALL_CROSS_BUTTON,
					Resources.EMULATOR_QHD_APPS_PAGE_MENU_BUTTON);
			wait(1000);
			screen.click(Resources.EMULATOR_QHD_EDIT_ICON);
			wait(1000);
			screen.click("emulator-qhd-edit-button.png");
		}
		else if(ResourceUtils.isVersion_3_0()){
			this.EnterEmulatorAppsPage("QHD", Resources.EMULATOR_QHD_TASK_SWITCHER_CLEARALL_CROSS_BUTTON,
					Resources.EMULATOR_QHD_APPS_PAGE_MENU_BUTTON);
			wait(1000);
			screen.click(Resources.EMULATOR_QHD_EDIT_ICON);
			wait(1000);
			screen.click("emulator-qhd-edit-button.png");
		}
	}//end of ClickQHDEmulatorEditIcon function

	public void EnterEmulatorAppsPage(String EmulatorType, String CrossButton, String AppsPageMenu) throws Exception {
		if (ResourceUtils.isVersion_2_3() || ResourceUtils.isVersion_2_3_1()) {
			if (EmulatorType.equals("WVGA")) {
				this.DefinedEmulatorWVGARegion();
				this.closeAllRunningApp(emulatorWVGARegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("QHD")) {
				this.DefinedEmulatorQHDRegion();
				emulatorQHDRegion.click(Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("HD")) {
				this.DefinedEmulatorHDRegion();
				emulatorHDRegion.click(Resources.EMULATOR_QHD_HOME_BUTTON);
			}
		} else if(ResourceUtils.isVersion_2_4()){
			if (EmulatorType.equals("WVGA")) {
				this.DefinedEmulatorWVGARegion();
				this.closeAllRunningApp(emulatorWVGARegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("QHD")) {
				this.DefinedEmulatorQHDRegion();
				this.closeAllRunningApp(emulatorQHDRegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("HD")) {
				this.DefinedEmulatorHDRegion();
				this.closeAllRunningApp(emulatorHDRegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			}

			if (screen.exists(AppsPageMenu) != null)
				screen.click(AppsPageMenu);
		}
		else if(ResourceUtils.isVersion_3_0()){
			if (EmulatorType.equals("WVGA")) {
				this.DefinedEmulatorWVGARegion();
				this.closeAllRunningApp(emulatorWVGARegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("QHD")) {
				this.DefinedEmulatorQHDRegion();
				this.closeAllRunningApp(emulatorQHDRegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			} else if (EmulatorType.equals("HD")) {
				this.DefinedEmulatorHDRegion();
				this.closeAllRunningApp(emulatorHDRegion, CrossButton, Resources.EMULATOR_QHD_HOME_BUTTON);
			}

			if (screen.exists(AppsPageMenu) != null)
				screen.click(AppsPageMenu);
		}
	}//end of EnterEmulatorAppsPage function

	public void closeAllRunningApp(Region reg, String CrossButton, String HomeButton) throws Exception {
		reg.hover(HomeButton);
		Match match = reg.find(HomeButton);
		match.mouseDown(Button.LEFT);
		wait(3000);
		match.mouseUp();
		if (reg.exists(CrossButton) != null) {
			reg.click(CrossButton);
			wait(1000);
		}
		reg.click(HomeButton);
		reg.click(Resources.EMULATOR_BACK_BUTTON);
	}

	public void collapseSimulatorTabs() throws Exception {
		this.focusSimulator();
		Settings.MinSimilarity = 0.99;
		while (screen.exists(Resources.SIMULATOR_TAB_COLLAPSE_BUTTON) != null) {
			screen.click(Resources.SIMULATOR_TAB_COLLAPSE_BUTTON);
			screen.type(Key.PAGE_UP);
			wait(2000);
		}
		if (Settings.isMac())
			Settings.MinSimilarity = 0.6;
		else
			Settings.MinSimilarity = 0.7;

	}

	public void wait(int miliSeconds) {
		try {
			Thread.sleep(miliSeconds);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void focusVm() throws Exception {
		if (ResourceUtils.isVersion_2_3() || ResourceUtils.isVersion_2_3_1()) {
			if (App.focus("Emulator -") == null)
				throw new Exception("Vm not running");
		} else {
			if (App.focus("Emulator") == null)
				throw new Exception("Vm not running");
		}
	}

	public void focusSimulator() throws Exception {
		wait(5000);
		if (App.focus("Web Simulator") == null)
			throw new Exception("Simulator not running");
	}

	public void maximizeWindow() throws Exception {
		if(!isMac())
		screen.type(Key.F11);
	}

	public void focusCssPreview() throws Exception {
		if (App.focus("CSS Preview") == null)
			throw new Exception("CSS Preview not running");
	}

	public void focusHtmlPreview() throws Exception {
		if (App.focus("Preview") == null)
			throw new Exception("Preview not running");
	}

	public void focusWebInspector() throws Exception {
		wait(5000);
		if (App.focus("Web Inspector") == null)
			throw new Exception("Web Inspector not running");
	}

	public void focusEmulatorManager() throws Exception {
		if(screen.exists(Resources.EMULATOR_MANAGER_ALREADY_OPEN_OK) != null)
			screen.type(Key.ENTER);
		if (App.focus("Manager") == null)
			throw new Exception("Emulator Manager not running");
	}

	public void focusEmulatorControlPanel() throws Exception {
		if (App.focus("Emulator Control Panel") == null)
			throw new Exception("Emulator Control Panel not running");
	}

	public void focusApp(String appName, String appImage) throws Exception {
		//App.focus(appName);
		wait(1);

		if (App.focus(appName).getPID() == 1)
			throw new Exception(appName + " not focus");
	}

	public void focusWearable(String appName, String appImage) throws Exception {
		if (App.focus(appName) == null)
			throw new Exception(appName + " not focus");
	}

	public void closeApp(String appName, String appImage) throws Exception {
		App.close(appName);
	}

	public int readXML(String fileName, String tag, String value) throws Exception {
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(tag);
			if (nList.getLength() > 0) {
				Node nNode = nList.item(0);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (tag == "RAM") {
						String temp = eElement.getElementsByTagName("size").item(0).getTextContent().toString();
						if (temp.compareTo(value) == 0)
							return 1;
					} else if (tag == "resolution") {
						String temp = eElement.getElementsByTagName("width").item(0).getTextContent().toString();
						if (temp.compareTo(value) == 0)
							return 1;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(fileName + " is not found.");
		}
		return 0;
	}

	public Region SetDefinedRegion(Region regionName, String imageName, int xIndex, int yIndex, int width, int heigth)
			throws Exception {
		try {
			Region reg2 = regionName.find(imageName);
			Region test = new Region(reg2.getX() + xIndex, reg2.getY() + yIndex, width, heigth);
			test.highlight(2);
			return test;
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedRemoteInspectorRegion() throws Exception {
		try {
			Region reg = screen.find(Resources.REMOTE_INSPECTOR_WEB_TIMELINE_PANEL);
			if(isMac()){
				remoteInspectorRegion = new Region(reg.getX() - 252, reg.getY() - 26, 940, screen.h - (reg.getY()));
			}else{
				remoteInspectorRegion = new Region(reg.getX() - 247, reg.getY() - 80, 943, screen.h - (reg.getY() - 80));
			}
			remoteInspectorRegion.highlight(2);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedSimulatorRegion() throws Exception {
		try {
			simulatorRegion = SetDefinedRegion(screen, Resources.SIMULATOR_RELOAD_BUTTON, -20, -35, 940, 1010);
			// simulatorRegion.highlight(4);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedSimulatorMenuRegion() throws Exception {
		try {
			simulatormenuregion = SetDefinedRegion(screen, Resources.SIMULATOR_RELOAD_BUTTON, -20, 30, 350, 945);
			// simulatormenuregion.highlight(3);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedEmulatorWVGARegion() throws Exception {
		try {
			this.focusVm();
			wait(1000);
			Region reg = screen.find(Resources.EMULATOR_TOP);
			emulatorWVGARegion = new Region(reg.getX() - 78, reg.getY() - 12, reg.getW() + 195, reg.getH() + 460);
			emulatorWVGARegion.highlight(1);
		} catch (Exception e) {
			throw new Exception("Failed to Set EmulatorWVGA Region.");
		}
	}

	public void DefinedEmulatorQHDRegion() throws Exception {
		try {
			this.focusVm();
			Region reg = screen.find(Resources.EMULATOR_TOP);
			emulatorQHDRegion = new Region(reg.getX() - 100, reg.getY() - 10, reg.getW() + 213, reg.getH() + 536);
			emulatorQHDRegion.highlight(1);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedEmulatorHDRegion() throws Exception {
		try {
			this.focusVm();
			emulatorHDRegion = SetDefinedRegion(screen, Resources.EMULATOR_TOP, -160, -25, 430, 750);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedEmulatorQHD1XRegion() throws Exception {
		try {
			this.focusVm();
			Region reg = screen.find(Resources.EMULATOR_TOP_1X);
			emulatorQHD1xRegion = new Region(reg.getX() - 260, reg.getY() + 32, reg.getW() + 460, reg.getH() + 940);
			emulatorQHD1xRegion.highlight(2);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedEmulatorControlPanelRegion() throws Exception {
		try {
			this.focusEmulatorControlPanel();
			emulatorControlPanelReion = App.focusedWindow();
			emulatorControlPanelReion.highlight(2);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}
	public void ClickSimulatorNetworkManagementMenu() throws Exception {
		if (simulatormenuregion.exists(Resources.SIMULATOR_NETWORK_MANAGEMENT_NFC_OFF) == null
				&& simulatormenuregion.exists(Resources.SIMULATOR_NETWORK_MANAGEMENT_NFC_ON) == null) {
			simulatormenuregion.click(Resources.SIMULATOR_NETWORK_MANAGEMENT_MENU);
		}
	}

	public void DefinedInspectorTimelineBottomRegion() throws Exception {
		try {
			Region X = screen.find(Resources.REMOTE_INSPECTOR_WEB_TIMELINE_PANEL);
			if(isMac()){
				timelineBottomRegion = new Region(X.getX() - 246, X.getY() + 905, X.getW() + 893, screen.h - X.getY());
			}else{
				timelineBottomRegion = new Region(X.getX() - 246, X.getY() + 940, X.getW() + 893, screen.h - X.getY());
			}
			timelineBottomRegion.highlight(4);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}

	}

	public void DefinedInspectorTimelineRegion() throws Exception {
		try {
			Region X = screen.find(Resources.REMOTE_INSPECTOR_WEB_TIMELINE_PANEL);
			timelineRegion = new Region(X.getX() - 246, X.getY() + 55, 933, 891);
			timelineRegion.highlight(4);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}

	}

	public void DefinedInspectorProfileRightPanelRegion() throws Exception {
		try {
			Region X = screen.find(Resources.REMOTE_INSPECTOR_WEB_PROFILE_PANNEL);
			profileRightPanel = new Region(X.getX() - 90, X.getY() + 55, 730, 910);
			profileRightPanel.highlight(1);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}

	}

	public void DefinedInspectorProfileBottonPanelRegion() throws Exception {
		try {
			Region X = screen.find(Resources.REMOTE_INSPECTOR_WEB_PROFILE_PANNEL);
			if(isMac()){
				profilebottomPanel = new Region(X.getX() - 280, X.getY() + 920, 914, screen.h - (X.getY() + 930));
			}else{
				profilebottomPanel = new Region(X.getX() - 290, X.getY() + 950, 914, screen.h - (X.getY() + 950));
			}
			profilebottomPanel.highlight(1);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}

	}

	public Region DefineVisibilityPanelRegion() throws Exception {
		try {
			Region X = screen.find("ide-web-simulator-panel-setting-button.png");
			Region test = new Region(X.getX() - 260, X.getY() + 50, 320, 400);
			// test.highlight(2);
			return test;
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefineConfigurationSettingsRegion() throws Exception {
		try {
			simulatorConfigurationReg = SetDefinedRegion(simulatorRegion, Resources.SIMULATOR_RELOAD_BUTTON, 55, 75,
					822, 610);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefineConfigurationSettingsMenuRegion() throws Exception {
		try {
			simulatorConfigurationMenuReg = SetDefinedRegion(simulatorRegion, Resources.SIMULATOR_RELOAD_BUTTON, 70,
					165, 210, 470);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefineConfigurationSettingsPanelRegion() throws Exception {
		try {
			simulatorConfigurationPanelReg = SetDefinedRegion(simulatorRegion, Resources.SIMULATOR_RELOAD_BUTTON, 280,
					165, 580, 470);
		} catch (Exception ex) {
			throw new Exception("Exception Occured...");
		}
	}

	public void removeDuplicate(String path) {
		try {
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			Set<String> set = new HashSet<String>();
			while ((strLine = br.readLine()) != null) {
				set.add(strLine);
			}
			in.close();
			Iterator<String> it = set.iterator();
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			while (it.hasNext()) {
				bw.write(it.next());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void DefinedEmulatorWearableRegion() throws Exception {
		try {
			this.focusWearable("Emulator -", Resources.WEARABLE_STANDARD_EMULATOR);
			// this.focusWearable(appName, appImage);
			// Region emulatorWearable =
			// screen.find("emulator-wearable-top.png");
			emulatorWearable = SetDefinedRegion(screen, "emulator-wearable-top.png", -62, 15, 420, 400);
			// emulatorQHDRegion = new Region(reg.getX()-100, reg.getY()-10,
			// reg.getW() + 213, reg.getH() + 536);
			// emulatorQHDRegion.highlight(2);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void DefinedEmulatorManagerRegion() throws Exception {
		try {
			this.focusEmulatorManager();
			emulatorManagerRegion = SetDefinedRegion(screen, "emulatorManagerRegion.png", -4, -4, 853, 532);
			// emulatorManagerRegion.highlight(2);
		} catch (Exception e) {
			throw new Exception("Exception Occured...");
		}
	}

	public void WaitForImage(Region reg, String imageName) {
		for (int time = 0; time < 20; time++) {
			if (reg.exists(imageName) == null)
				wait(3000);
			else
				break;
		}
	}

	public void CreateFolder(String targetLocation) {
		File targetFile = new File(targetLocation);
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
	}

	public void copyDirectory(String sourceLocation, String targetLocation) throws IOException
	{
		copyDirectory(new File(sourceLocation),new File(targetLocation));
	}
	
	public void DeleteFile(String path){
		try{
			File f = new File(path); 
			if(f.exists() == true){
				f.delete();
				System.out.println("Exist");
			}else{
				System.out.println("Not Exist");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}//end of DeleteFile
	
	private void copyDirectory(File sourceLocation, File targetLocation) throws IOException {

		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				if (!targetLocation.getParentFile().exists())
					targetLocation.getParentFile().mkdir();
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
			}
		} else {

			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}

	public static boolean isWindows() {

		return (osName.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (osName.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0);

	}

	public void makeOnCpuVt() throws Exception {
		Settings.MinSimilarity = 0.99;
		if (screen.exists(Resources.EMULATOR_MANAGER_CPUVT_OFF) != null) {
			screen.click(Resources.EMULATOR_MANAGER_CPUVT_OFF);
			wait(1000);
		}
		Settings.MinSimilarity = 0.7;
	}
	
	public void makeOnGPU() throws Exception {
		Settings.MinSimilarity = 0.99;
		if (screen.exists(Resources.EMULATOR_MANAGER_GPU_OFF) != null) {
			screen.click(Resources.EMULATOR_MANAGER_GPU_OFF);
			wait(1000);
		}
		Settings.MinSimilarity = 0.7;
	}
	
	public void toggleViewState(String image) throws Exception {
		Settings.MinSimilarity = 0.95;
		if (screen.exists(image) != null) {
			screen.click(image);
			wait(1000);
		}
		Settings.MinSimilarity = 0.7;
	}
	
	public void copyImageToAllVersion(String sourceLocation, String moduleName) throws IOException
	{
		
		String fileName = new File(sourceLocation).getName();
		
		File sourceFile = new File(sourceLocation);
		
		String testVersions [] = {"2.3", "2.3.1", "2.3.2", "2.4" , "3.0"};
		
		String dest[] = new String[9] ;
		int temp = 0;
		
		for (int i = 0; i < testVersions.length; i++) 
		{
			dest[i + temp++] = ResourceUtils.getRefImagePath() + testVersions[i] + "/sdk/" + moduleName + "/windows/" ;
			dest[i + temp++]  =  ResourceUtils.getRefImagePath() + testVersions[i] + "/sdk/" + moduleName + "/linux/"  ;	
			dest[i + temp] =  ResourceUtils.getRefImagePath() + testVersions[i] + "/sdk/" + moduleName + "/mac/"  ;
		}
		
		for (String destPath : dest) {
			
			File destFile =  new File(destPath+fileName);			
			if(!destFile.exists())
			{
				copyFile(sourceFile, destFile);
				System.out.println(destFile + " Copied");
			}
			else
			{
				//System.out.println(destFile + " Already Exists");
			}
			
		}		
		
	}
	
	public void copyFile (File sourceLocation, File targetLocation) throws IOException
	{
		InputStream in = new FileInputStream(sourceLocation);
		OutputStream out = new FileOutputStream(targetLocation);

		// Copy the bits from instream to outstream
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
	
	public void copyNewlyAddedImages(String moduleName) throws IOException
	{
		File Imgaes [] = new File(ResourceUtils.getRefImagePath() + ResourceUtils.getTestVersion() + "/sdk/" + moduleName + "/" + this.osName).listFiles();
		
		for (int i = 0; i < Imgaes.length; i++) {
			copyImageToAllVersion(Imgaes[i].toString(), moduleName);
			
		}
	}


}
