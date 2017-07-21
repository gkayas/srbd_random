package com.turbovnc.vncviewer;

public interface ICodeGenerator
{
	public void pressMenu();
	public void pressEnter();
	public void pressBack();
	public void pressHome();
	public void pressVolPlus();
	public void pressVolMinus();
	public void pressMute();
	public void pressChannelPlus();
	public void pressChannelMinus();
	public void pressUp();
	public void pressDown();
	public void pressRight();
	public void pressLeft();
	public void pressPower();
	public void pressOne();
	public void pressTwo();
	public void pressThree();
	public void pressFour();
	public void pressFive();
	public void pressSix();
	public void pressSeven();
	public void pressEight();
	public void pressNine();
	public void pressZero();
	public void pressOneTwoThree();
	public void pressDotDotDot();
	public void pressPlay();
	public void pressOk();
	public void longPressHome();
	public void tapImage(String imagePath);
	public void verifyImage(String fileName);
	public void verifyText();
	public void tapText();
	public void typeText();
	public void swipe();
	public void launchApp();
	public void generateAreaCode();
	public void tapOnScreen(int x, int y);
	public void doubleTapOnScreen(int x, int y);
	public void swipeOnScreen(int x1, int y1, int x2, int y2);
	public void longTapOnScreen(int x, int y);

}
