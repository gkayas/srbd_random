package device;

import java.util.List;

import org.sikuli.script.*;


public class Target
{

	//Image related APIs.
	//1
	public boolean verifyImage(String image) {
		return SikuiliUtils.verifyImage(image);
	}

	//2
	public boolean verifyImage(Double similarity, String image) {
		return SikuiliUtils.verifyImage(similarity, image);
	}

	//3
	public boolean verifyImage(Double[] area, String image) {
		return SikuiliUtils.verifyImage(area, image);
	}

	//4
	public boolean verifyImage(Double[] area, Double similarity, String image) {
		return SikuiliUtils.verifyImage(area, similarity, image);
	}

	//5
	public boolean verifyImage(String image, String innerImage) {
		return SikuiliUtils.verifyImage(image, innerImage);
	}

	//6
	public boolean verifyImage(Double similarity, String image, String innerImage) {
		return SikuiliUtils.verifyImage(similarity, image, innerImage);
	}

	//7
	public boolean verifyImage(Double[] area, String image, String innerImage) {
		return SikuiliUtils.verifyImage(area, image, innerImage);
	}

	//7
	public boolean verifyImage(Double[] area, Double similarity, String image, String innerImage) {
		return SikuiliUtils.verifyImage(area, similarity, image, innerImage);
	}

	//8
	public boolean tapImage(String image) {
		return SikuiliUtils.tapImage(image);
	}

	//9
	public boolean tapImage( Double similarity,String image) {
		return SikuiliUtils.tapImage(similarity, image);
	}

	//10
	public boolean tapImage( Double[] area,String image) {
		return SikuiliUtils.tapImage(area, image);
	}

	//11
	public boolean tapImage( Double[] area, Double similarity, String image) {
		return SikuiliUtils.tapImage(area, similarity, image);
	}

	//10
	public boolean tapImage(String image, String innerImage) {
		return SikuiliUtils.tapImage(image, innerImage);
	}

	//11
	public boolean tapImage(Double[] area, String image, String innerImage) {
		return SikuiliUtils.tapImage(area, image, innerImage);
	}

	//12
	public boolean tapImage(Double[] area, Double similarity, String image, String innerImage) {
		return SikuiliUtils.tapImage(area, similarity, image, innerImage);
	}

	//13
	public Boolean longPressImage(String image)
	{
		return SikuiliUtils.longPressImage(image);
	}

	//14
	public Boolean longPressImage(Double[] area, String image)
	{
		return SikuiliUtils.longPressImage(area, image);
	}

	//15
	public Boolean longPressImage(Double similarity, String image)
	{
		return SikuiliUtils.longPressImage(similarity, image);
	}

	//16
	public Boolean longPressImage(Double[] area, Double similarity, String image)
	{
		return SikuiliUtils.longPressImage(area, image);
	}

	//17
	public Boolean longPressImage(String image, String innerImage)
	{
		return SikuiliUtils.longPressImage(image, innerImage);
	}

	//18
	public Boolean longPressImage(Double[] area, String image, String innerImage)
	{
		return SikuiliUtils.longPressImage(area, image, innerImage);
	}

	//19
	public Boolean longPressImage(Double similarity, String image, String innerImage)
	{
		return SikuiliUtils.longPressImage(similarity, image, innerImage);
	}

	//20
	public Boolean longPressImage(Double[] area, Double similarity, String image, String innerImage)
	{
		return SikuiliUtils.longPressImage(area, similarity, image, innerImage);
	}

	//21
	public boolean waitForImage(long waitMilisecond, String image)
	{
		return SikuiliUtils.waitForImage(waitMilisecond,image);
	}


	//Text related APIs

	//1
	public boolean verifyText(String... textList)
	{
		return SikuiliUtils.verifyText(textList);
	}

	//2
	public boolean verifyText(Double[] area,String... textList)
	{
		return SikuiliUtils.verifyText(area,textList);
	}

	//3
	public boolean verifyText(Double similarity,String... textList)
	{
		return SikuiliUtils.verifyText(similarity,textList);
	}

	//4
	public boolean verifyText(Double[] area, Double similarity,String... textList)
	{
		return SikuiliUtils.verifyText(area, similarity, textList);
	}

	//5
	public boolean verifyAnyText(String... textList)
	{
		return SikuiliUtils.verifyAnyText(textList);
	}

	//6
	public boolean verifyAnyText(Double similarity, String... textList)
	{
		return SikuiliUtils.verifyAnyText(similarity,textList);
	}

	//6
	public boolean verifyAnyText( Double[] area, String... textList)
	{
		return SikuiliUtils.verifyAnyText(area,textList);
	}

	//6
	public boolean verifyAnyText( Double[] area,Double similarity, String... textList)
	{
		return SikuiliUtils.verifyAnyText(area, similarity, textList);
	}

	//7
	public boolean tapText(String text)
	{
		return SikuiliUtils.tapText(text);
	}

	//8
	public boolean tapText(Double similarity, String  text)
	{
		return SikuiliUtils.tapText(similarity, text);
	}

	//9
	public boolean tapText(Double[] area, String  text)
	{
		return SikuiliUtils.tapText(area, text);
	}

	//10
	public boolean tapText(Double[] area, Double similarity, String  text)
	{
		return SikuiliUtils.tapText(area, similarity, text);
	}

	//11
	public boolean tapAnyText(String... textList)
	{
		return SikuiliUtils.tapAnyText(textList);
	}

	//12
	public boolean tapAnyText(Double similarity, String... textList)
	{
		return SikuiliUtils.tapAnyText(similarity, textList);
	}

	//13
	public boolean tapAnyText(Double[] area, String... textList)
	{
		return SikuiliUtils.tapAnyText(area, textList);
	}

	//12
	public boolean tapAnyText(Double[] area, Double similarity, String... textList)
	{
		return SikuiliUtils.tapAnyText(area, similarity, textList);
	}

	//13
	public Boolean longPressText(String text)
	{
		return SikuiliUtils.longPressText(text);
	}

	//14
	public Boolean longPressText(Double[] area, String text)
	{
		return SikuiliUtils.longPressText(area, text);
	}

	//15
	public Boolean longPressText(Double similarity, String text)
	{
		return SikuiliUtils.longPressText(similarity, text);
	}

	//16
	public Boolean longPressText(Double[] area, Double similarity, String text)
	{
		return SikuiliUtils.longPressText(area, similarity, text);
	}

	//17
	public Boolean typeText(String text)
	{
		return SikuiliUtils.typeText(text);
	}

	//18
	public void showAllText()
	{
		SikuiliUtils.showAllText();
	}

	//19
	public void showAllText(Double[] area)
	{
		SikuiliUtils.showAllText(area);
	}

	//20
	public void showAllText(Double similarity)
	{
		SikuiliUtils.showAllText(similarity);
	}

	//21
	public void showAllText(Double[] area, Double similarity)
	{
		SikuiliUtils.showAllText(area, similarity);
	}

	//22
	public List<Match> getAllText()
	{
		return SikuiliUtils.getAllText();
	}

	//23
	public List<Match> getAllText(Double[] area)
	{
		return SikuiliUtils.getAllText(area);
	}

	//24
	public List<Match> getAllText(Double similarity)
	{
		return SikuiliUtils.getAllText(similarity);
	}

	//25
	public List<Match> getAllText(Double[] area, Double similarity)
	{
		return SikuiliUtils.getAllText(area, similarity);
	}

	//26
	public boolean waitForText(long waitMilisecond,String... textList)
	{
		return SikuiliUtils.waitForText(waitMilisecond,textList);
	}

	//27
	public void findTextandtypeText(Double[] area, Double xCord, Double yCord)
	{
		SikuiliUtils.findTextandtypeText(area, xCord, yCord);
	}


	//Additional APIs

	//1
	public void focus()
	{
		SikuiliUtils.focus();
	}

	//2
	public Boolean pressMenu()
	{
		return SikuiliUtils.pressMenu();
	}

	//3
	public Boolean pressEnter()
	{
		return SikuiliUtils.pressEnter();
	}

	//4
	public Boolean pressBack()
	{
		return SikuiliUtils.pressBack();
	}

	//5
	public Boolean pressHome()
	{
		return SikuiliUtils.pressHome();
	}
	
	public Boolean pressVolPlus()
	{
		return SikuiliUtils.pressVolPlus();
	}
	
	public Boolean pressVolMinus()
	{
		return SikuiliUtils.pressVolMinus();
	}
	
	public Boolean pressMute()
	{
		return SikuiliUtils.pressMute();
	}
	
	public Boolean pressChannelPlus()
	{
		return SikuiliUtils.pressChannelPlus();
	}
	
	public Boolean pressChannelMinus()
	{
		return SikuiliUtils.pressChannelMinus();
	}
	
	public Boolean pressUp()
	{
		return SikuiliUtils.pressUp();
	}
	
	public Boolean pressDown()
	{
		return SikuiliUtils.pressDown();
	}
	
	public Boolean pressRight()
	{
		return SikuiliUtils.pressRight();
	}
	
	public Boolean pressLeft()
	{
		return SikuiliUtils.pressLeft();
	}
	
	public Boolean pressPower()
	{
		return SikuiliUtils.pressPower();
	}
	
	public Boolean pressOne()
	{
		return SikuiliUtils.pressOne();
	}
	
	public Boolean pressTwo()
	{
		return SikuiliUtils.pressTwo();
	}
	
	public Boolean pressThree()
	{
		return SikuiliUtils.pressThree();
	}
	
	public Boolean pressFour()
	{
		return SikuiliUtils.pressFour();
	}
	
	public Boolean pressFive()
	{
		return SikuiliUtils.pressFive();
	}
	
	public Boolean pressSix()
	{
		return SikuiliUtils.pressSix();
	}
	
	public Boolean pressSeven()
	{
		return SikuiliUtils.pressTwo();
	}
	
	public Boolean pressEight()
	{
		return SikuiliUtils.pressEight();
	}
	
	public Boolean pressNine()
	{
		return SikuiliUtils.pressNine();
	}
	
	public Boolean pressZero()
	{
		return SikuiliUtils.pressZero();
	}
	
	public Boolean pressOneTwoThree()
	{
		return SikuiliUtils.pressOneTwoThree();
	}
	
	public Boolean pressDotDotDot()
	{
		return SikuiliUtils.pressDotDotDot();
	}
	
	public Boolean pressPlay()
	{
		return SikuiliUtils.pressPlay();
	}
	
	public Boolean pressOk()
	{
		return SikuiliUtils.pressOk();
	}

	//6
	public Boolean longPressHome()
	{
		return SikuiliUtils.longPressHome();
	}

	//7
	public Boolean pressCoord(Double xCord, Double yCord)
	{
		return SikuiliUtils.tapOnScreen(xCord, yCord);
	}

	//8
	public Boolean longPressCoord(Double xCord, Double yCord)
	{
		return SikuiliUtils.longPressCoord(xCord, yCord);
	}

	//9
	public void openNotificationBar()
	{
		SikuiliUtils.openNotificationBar();
	}

	//10
	public void swipe(double srcx, double srcy, double desx, double desy)
	{
		SikuiliUtils.swipe(srcx, srcy, desx, desy);
	}

	//11
	public Region getRegion()
	{
		return SikuiliUtils.getDeviceRegion();
	}

	//12
	public void setAssertMode(boolean assertMode)
	{
		SikuiliUtils.setAssertMode(assertMode);
	}

	//13
	public void waitForMiliSeconds(long miliSeconds)
	{
		CommonUtils.sleep(miliSeconds);
	}

	//14
	public List<String> runCommand(String arguments)
	{
		return DeviceUtils.runCommand(arguments);
	}

	//15
	public void cliProjectCreate(String appType, String templateName, String profileVersion, String projectName,  String workspacePath)
	{
		DeviceUtils.cliProjectCreate(appType, templateName, profileVersion, projectName, workspacePath);
	}

	//16
	public boolean cliProjectBuild(String appType, String buildRootStrap, String compiler, String buildConfiguration,  String projectPath)
	{
		return DeviceUtils.cliProjectBuild(appType, buildRootStrap, compiler, buildConfiguration, projectPath);
	}

	//17
	public boolean cliProjectPackage(String appType, String buildConfiguration, String certPath, String keyName,String keyPWD, String projectPath)
	{
		return DeviceUtils.cliProjectPackage(appType, buildConfiguration, certPath, keyName, keyPWD, projectPath);
	}

	//18
	public boolean installApp(String packageID, String path)
	{
		return DeviceUtils.installApp(EnumCommand.SDB, packageID, path);
	}

	//19
	public boolean installApp(EnumCommand command, String packageID, String path)
	{
		return DeviceUtils.installApp(command, packageID, path);
	}

	//20
	public boolean launchApp(String appID)
	{
		return launchApp(EnumCommand.SDB, EnumAppLauncher.NORMAL,null, appID);
	}
	
	//20
	public boolean launchAppParam(String appID, String argV)
	{
		return DeviceUtils.launchApp(EnumCommand.SDB, EnumAppLauncher.NORMAL, null, appID, argV);
	}

	//21
	public boolean launchApp(String packageID, String appID)
	{
		return launchApp(EnumCommand.SDB, EnumAppLauncher.NORMAL, packageID, appID);
	}

	//22
	public boolean launchApp(EnumAppLauncher appLauncher, String packageID, String appID)
	{
		return DeviceUtils.launchApp(EnumCommand.SDB, appLauncher, packageID, appID, "");
	}

	//23
	public boolean launchApp(EnumCommand command,EnumAppLauncher appLauncher, String packageID, String appID)
	{
		return DeviceUtils.launchApp(command, appLauncher, packageID, appID, "");
	}

	//24
	public boolean closeApp(String packageID, String appID)
	{
		return closeApp(2500, EnumAppLauncher.NORMAL, packageID, appID);
	}

	//25
	public boolean closeApp(EnumAppLauncher appLauncher, String packageID, String appID)
	{
		return closeApp(2500, appLauncher, packageID, appID);
	}

	//26
	public boolean closeApp(long waitMilisecond,EnumAppLauncher appLauncher, String packageID, String appID)
	{
		return DeviceUtils.closeApp(waitMilisecond,appLauncher, packageID, appID);
	}

	//27
	public boolean restartApp(String packageID, String appID)
	{
		return DeviceUtils.restartApp(packageID, appID);
	}

	//28
	public boolean restartApp(long waitMilisecond, String packageID, String appID)
	{
		return DeviceUtils.restartApp(waitMilisecond,packageID, appID);
	}

	//29
	public boolean uninstallApp(String packageID)
	{
		return DeviceUtils.uninstallApp(EnumCommand.SDB, packageID);
	}

	//30
	public boolean uninstallApp(EnumCommand command, String packageID)
	{
		return DeviceUtils.uninstallApp(command, packageID);
	}

	//31
	public boolean detectCrash(String appCrashFileName)
	{
		return DeviceUtils.detectCrash(appCrashFileName);
	}

	//32
	public String saveDeviceScreen(Double[] area, String path,String name)
	{
		return SikuiliUtils.saveDeviceScreen(area, path, name);
	}

	//33
	public String saveDeviceScreen(String path,String name)
	{
		return SikuiliUtils.saveDeviceScreen(null,path, name);
	}

	//34
	public Boolean pressCoordTwice(Double xCord, Double yCord)
	{
		return SikuiliUtils.doubleTapOnScreen(xCord, yCord);
	}


	//Internal APIs

	public void setWaitMode(boolean waitMode)
	{
		SikuiliUtils.setWaitMode(waitMode);
	}

	public Boolean tapOnMiddle()
	{
		return SikuiliUtils.tapOnMiddle();
	}

	public void waitForNewScreen()
	{
		SikuiliUtils.waitForNewScreen();
	}

	public void waitForNewScreen(long times)
	{
		SikuiliUtils.waitForNewScreen(times);
	}

	public void waitForNewScreen(Double similarity)
	{
		SikuiliUtils.waitForNewScreen(similarity);
	}

	public void waitForNewScreen(Double similarity,long times)
	{
		SikuiliUtils.waitForNewScreen(similarity, times);
	}

	public void cleanscreenshots()
	{
		CommonUtils.cleanscreenshots();
	}

}