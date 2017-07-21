package device;

import org.junit.*;

import java.util.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import org.sikuli.basics.*;
import org.sikuli.script.*;

public class SikuiliUtils {

	protected Region region;

	static boolean waitMode = true;
	static boolean assertMode = ResourceUtils.getAssertMode();
	static String assertMessage = null;

	static boolean getAssertMode()
	{
		return assertMode;
	}

	static void setWaitMode(boolean mode)
	{
		waitMode = mode;
	}

	static void setAssertMode(boolean mode)
	{
		assertMode = mode;
	}

	static String getMessage() {
		return assertMessage;
	}

	static void setMessage(String apiName,boolean result,Double[] area, Double similarity,long waitMilisecond,String... searchList) {
		if(result)
			assertMessage = "[AutomatorCore][MATCH] API: "+apiName +"(";
		else
			assertMessage = "[AutomatorCore][NOT MATCH] API: "+apiName +"(";

		String areaMessage = "";
		for(int index=0;index<searchList.length;index++)
		{
			if( searchList[index] != null)
			{
				if(index != 0)
					assertMessage += " ,";
				assertMessage += "\""+ searchList[index] +"\"";
			}
		}
		assertMessage += ")";

		if (area == null)
			areaMessage = "[0.0, 0.0, 1.0, 1.0]";
		else
		{
			areaMessage = "["+area[0]+","+area[1]+","+area[2]+","+area[3]+"]";
		}

		//assertMessage += " with similarity: "+similarity+" area: "+ areaMessage + " search time: "+ waitMilisecond + " ";
		assertMessage += " similarity: "+String.format( "%.3f", similarity)+ " search: "+ (waitMilisecond / 1000) + " times";
	}

	static void assertResult(String message, boolean result) {
		assertResult(getAssertMode(),message,result);
	}

	static void assertResult(boolean assertionMode,String message, boolean result) {
		if(assertionMode && !result)
		{
			System.out.println(message);
			Assert.assertTrue("", result);
		}
		else
		{
			if(ResourceUtils.getLogMessage() == 1)
			{
				System.out.println(message);
			}
		}

		//setAssertMode(ResourceUtils.getAssertMode());
	}

	static Region getDeviceRegion() {
		Region region = null;
		Screen screen = new Screen();
		try {
			region = screen.find(Constants.DEVICE_INTERFACE_ANCHOR);
		} catch (FindFailed e) {
			System.out.println("[AutomatorCore] Device Interface full view is not in screen");
			return null;
		}

//		if(ResourceUtils.getProfileName().equals("Mobile")) {
//			region = new Region(region.x - 224, region.y - 811, 480, 800);
//		} else if(ResourceUtils.getProfileName().equals("Wearable")) {
//			if(ResourceUtils.getScreenWidth().equals(ResourceUtils.getScreenHeight())) {
//				region = new Region(region.x - 163, region.y - 371, 360, 360);
//			} else {
//				region = new Region(region.x - 163, region.y - 491, 360, 480);
//			}
//		}

		if(ResourceUtils.getProfileName().equals("Mobile")) {
			if(ResourceUtils.getRotation().contains("90")){
				region = new Region(region.x - 549, region.y - 490, 800, 480);
			}else{
				region = new Region(region.x - 390, region.y - 810, 480, 800);
			}
		} else if(ResourceUtils.getProfileName().equals("Wearable")) {
			if(ResourceUtils.getScreenWidth().equals(ResourceUtils.getScreenHeight())) {
				region = new Region(region.x - 161, region.y - 370, 360, 360);
			} else {
				if(ResourceUtils.getRotation().contains("90")){
					region = new Region(region.x - 221, region.y - 370, 480, 360);
				}else{
					region = new Region(region.x - 161, region.y - 490, 360, 480);
				}
			}
		}


		return region;
	}

	static ArrayList<Double> convertToSikuiliCoordinate(Double xCord,Double yCord) {
		ArrayList<Double> deviceCord = new ArrayList<Double>();

		Region deviceInterfaceRegion = getDeviceRegion();

		Double xScaled = xCord * deviceInterfaceRegion.w;
		Double yScaled = yCord * deviceInterfaceRegion.h;

		Double xCordSikuili = xScaled + deviceInterfaceRegion.x;
		Double yCordSikuili = yScaled + deviceInterfaceRegion.y;
		deviceCord.add(xCordSikuili);
		deviceCord.add(yCordSikuili);
		return deviceCord;
	}

	static Region deviceToSikuliRegion(Double[] area) {
		ArrayList<Double> start = convertToSikuiliCoordinate(area[0], area[1]);
		ArrayList<Double> end = convertToSikuiliCoordinate(area[2], area[3]);

		Region region = new Region(start.get(0).intValue(), start.get(1)
				.intValue(), end.get(0).intValue() - start.get(0).intValue(),
				end.get(1).intValue() - start.get(1).intValue());
		return region;
	}

	private static String getImagePath(String bitmap)
	{
		if(bitmap.contains("/") || bitmap.contains("\\"))
		{
			return ResourceUtils.getRefImagePath()+ bitmap;
		}
		return bitmap;
	}

	static Match findBitMap(Double[] area, Double similarity,String bitmap1, String bitmap2) {
		Region region = null;
		Match match = null;

		if(area == null)
			region = getDeviceRegion();
		else
			region = deviceToSikuliRegion(area);

		Settings.MinSimilarity = similarity;

		try
		{
			bitmap1 = getImagePath(bitmap1);

			if (bitmap2 != null)
			{
				bitmap2 = getImagePath(bitmap2);
				match = region.find(bitmap1).find(bitmap2);
				//System.out.println("Image " + bitmap1 + " match similarity : "+match.getScore());
			}
			else
			{
				match = region.find(bitmap1);
				//System.out.println("Image " + bitmap2 + " match similarity : "+match.getScore());
			}

		}
		catch (Exception e)
		{
			match = null;
		}

		Settings.MinSimilarity = Constants.DEFAULT_SIMILARITY;
		return match;
	}

	static List<Match> getTextList(Double[] area, Double similarity) {
		Region region = null;

		if(area == null)
			region = getDeviceRegion();
		else
			region = deviceToSikuliRegion(area);

		Settings.MinSimilarity = similarity;

		List<Match> matchList = region.listText();

		Settings.MinSimilarity = Constants.DEFAULT_SIMILARITY;
		return matchList;
	}


	static Match findText(Double[] area, Double similarity,String text) {
		List<Match> matchList =  getTextList(area, similarity);
		for (Match match : matchList) {
			if(match.getText().indexOf(text) >= 0)
				return match;
		}
		return null;
	}

	static void focus()
	{
		App.focus(Constants.DEVICE_INTERFACE_TITLE);
		CommonUtils.sleep(1000);
	}

	static void click(Region region)
	{
		region.hover();
		CommonUtils.sleep(100);
		region.mouseDown(Button.LEFT);
		CommonUtils.sleep(90);
		region.mouseUp();
	}

	static void click(Match match)
	{
		match.hover();
		CommonUtils.sleep(100);
		match.mouseDown(Button.LEFT);
		CommonUtils.sleep(90);
		match.mouseUp();
	}

	static boolean doubleClick(Region region)
	{
		boolean r = false;
		region.hover();
		CommonUtils.sleep(100);
		if(region.doubleClick() == 1) {
			r = true;
		}
		CommonUtils.sleep(90);
		return r;
	}

	static boolean doubleClick(Match match)
	{
		boolean r = false;
		match.hover();
		CommonUtils.sleep(100);
		if(match.doubleClick() == 1) {
			r = true;
		}
		CommonUtils.sleep(90);
		return r;
	}

	//verifyImage

	static boolean verifyImage(String bitmap) {
		return verifyImage(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean verifyImage(Double[] area, String bitmap) {
		return verifyImage(area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean verifyImage(Double similarity,String bitmap) {
		return verifyImage( null, similarity,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean verifyImage( Double[] area,Double similarity,String bitmap) {
		return verifyImage(area, similarity,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean verifyImage(String bitmap1, String bitmap2) {
		return verifyImage(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean verifyImage(Double[] area,String bitmap1, String bitmap2) {
		return verifyImage(area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean verifyImage(Double similarity,String bitmap1, String bitmap2) {
		return verifyImage(null, similarity,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean verifyImage(Double[] area,Double similarity,String bitmap1, String bitmap2) {
		return verifyImage(area, similarity,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}
	//

	static boolean verifyImage(long waitMilisecond,String bitmap) {
		return verifyImage(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap, null);
	}

	static boolean verifyImage(Double[] area,long waitMilisecond, String bitmap) {
		return verifyImage(area, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap, null);
	}

	static boolean verifyImage(Double similarity,long waitMilisecond,String bitmap) {
		return verifyImage( null, similarity,waitMilisecond,bitmap, null);
	}

	static boolean verifyImage( Double[] area,Double similarity,long waitMilisecond,String bitmap) {
		return verifyImage(area, similarity,waitMilisecond,bitmap, null);
	}

	static boolean verifyImage(long waitMilisecond,String bitmap1, String bitmap2) {
		return verifyImage(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean verifyImage(Double[] area,long waitMilisecond,String bitmap1, String bitmap2) {
		return verifyImage(area, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean verifyImage(Double similarity,long waitMilisecond,String bitmap1, String bitmap2) {
		return verifyImage(null, similarity,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean verifyImage(Double[] area, Double similarity,long waitMilisecond,String bitmap1, String bitmap2) {
		waitForNewScreen();
		setMessage("verifyImage", false, area, similarity, waitMilisecond, bitmap1, bitmap2);
		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findBitMap(area, similarity, bitmap1, bitmap2);
			if( match != null)
			{
				setMessage("verifyImage", true, area, match.getScore(), time, bitmap1,bitmap2);
				assertResult(getMessage(),true);
				return true;
			}
			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}


	//tapImage

	static boolean tapImage(String bitmap) {
		return tapImage(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean tapImage( Double[] area,String bitmap) {
		return tapImage( area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean tapImage(Double similarity,String bitmap) {
		return tapImage(null, similarity,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean tapImage(Double[] area,Double similarity,String bitmap) {
		return tapImage(area, similarity,ResourceUtils.getSearchTime(),bitmap, null);
	}

	static boolean tapImage(String bitmap1, String bitmap2) {
		return tapImage(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean tapImage(Double[] area,String bitmap1, String bitmap2) {
		return tapImage(area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean tapImage(Double similarity,String bitmap1, String bitmap2) {
		return tapImage(null, similarity,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean tapImage(Double[] area, Double similarity,String bitmap1, String bitmap2) {
		return tapImage(area, similarity,ResourceUtils.getSearchTime(),bitmap1, bitmap2);
	}

	static boolean tapImage(long waitMilisecond,String bitmap) {
		return tapImage(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap, null);
	}

	static boolean tapImage( Double[] area,long waitMilisecond,String bitmap) {
		return tapImage( area, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap, null);
	}

	static boolean tapImage(Double similarity,long waitMilisecond,String bitmap) {
		return tapImage(null, similarity,waitMilisecond,bitmap, null);
	}

	static boolean tapImage(Double[] area,Double similarity,long waitMilisecond,String bitmap) {
		return tapImage(area, similarity,waitMilisecond,bitmap, null);
	}

	static boolean tapImage(long waitMilisecond,String bitmap1, String bitmap2) {
		return tapImage(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean tapImage(Double[] area,long waitMilisecond,String bitmap1, String bitmap2) {
		return tapImage(area, Constants.DEFAULT_SIMILARITY,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean tapImage(Double similarity,long waitMilisecond,String bitmap1, String bitmap2) {
		return tapImage(null, similarity,waitMilisecond,bitmap1, bitmap2);
	}

	static boolean tapImage(Double[] area, Double similarity,long waitMilisecond,String bitmap1, String bitmap2) {

		waitForNewScreen();
		setMessage("tapImage", false, area, similarity, waitMilisecond, bitmap1, bitmap2);

		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findBitMap(area, similarity, bitmap1, bitmap2);
			if( match != null)
			{
				click(match);
				//match.click();
				setMessage("tapImage", true, area, match.getScore(), time, bitmap1,bitmap2);
				assertResult(getMessage(),true);
				return true;
			}

			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}


	//WaitForImage
	static boolean waitForImage(long waitMilisecond, String bitmap1) {

		//setAssertMode(false);
		setMessage("WaitForImage", false, null, Constants.DEFAULT_SIMILARITY, waitMilisecond, bitmap1, null);
		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findBitMap(null, Constants.DEFAULT_SIMILARITY, bitmap1, null);
			if( match != null)
			{
				setMessage("WaitForImage", true, null, match.getScore(), time, bitmap1, null);
				assertResult(getMessage(),true);
				return true;
			}
			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}

		assertResult(getMessage(),false);
		return false;
	}

	//show text

	static void showAllText()
	{
		showAllText(null,Constants.DEFAULT_SIMILARITY);
	}

	static void showAllText(Double[] area)
	{
		showAllText(area, Constants.DEFAULT_SIMILARITY);
	}

	static void showAllText(Double similarity)
	{
		showAllText(null,similarity);
	}

	static void showAllText(Double[] area, Double similarity)
	{
		List<Match> matchList = getAllText(area, similarity);
		for (Match match : matchList) {
			System.out.println("[AutomatorCore] " + match.getText());
		}
	}

	static List<Match> getAllText()
	{
		return getAllText(null, Constants.DEFAULT_SIMILARITY);
	}

	static List<Match> getAllText(Double[] area)
	{
		return getAllText(area, Constants.DEFAULT_SIMILARITY);
	}

	static List<Match> getAllText(Double similarity)
	{
		return getAllText(null, similarity);
	}

	static List<Match> getAllText(Double[] area, Double similarity)
	{
		waitForNewScreen();

		Region region = null;
		if(area == null)
			region = getDeviceRegion();
		else
			region = deviceToSikuliRegion(area);

		Settings.MinSimilarity = similarity;
		List<Match> matchList = region.listText();

		Settings.MinSimilarity = Constants.DEFAULT_SIMILARITY;

		return matchList;
	}

	//verifyText

	static boolean verifyText(String... textList)
	{
		return verifyText(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyText(Double[] area,String... textList)
	{
		return verifyText(area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyText(Double similarity,String... textList)
	{
		return verifyText(null, similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyText(Double[] area,Double similarity,String... textList)
	{
		return verifyText(area, similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyText(long waitMilisecond,String... textList)
	{
		return verifyText(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean verifyText(Double[] area,long waitMilisecond,String... textList)
	{
		return verifyText(area, Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean verifyText(Double similarity,long waitMilisecond,String... textList)
	{
		return verifyText(null, similarity,waitMilisecond,textList);
	}

	static boolean verifyText(Double[] area,Double similarity,long waitMilisecond,String... textList)
	{
		waitForNewScreen();
		boolean isFound = false;

		for (String text : textList)
		{
			isFound = false;
			setMessage("verifyText", isFound, area, similarity, waitMilisecond, text);
			for(long time = 1000; time <= waitMilisecond; time += 1000)
			{
				Match match = findText(area, similarity, text);
				if( match == null)
				{
					if(time != waitMilisecond)
						CommonUtils.sleep(1000);
				}
				else
				{
					isFound = true;
					setMessage("verifyText", isFound, area, match.getScore(), time, text);
					assertResult(getMessage(),isFound);
					break;
				}
			}
			if(!isFound)
			{
				break;
			}
		}
		if(!isFound)
			assertResult(getMessage(),isFound);
		return isFound;
	}

	//verifyAnyText

	static boolean verifyAnyText(String... textList)
	{
		return verifyAnyText(null, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyAnyText(Double[] area,String... textList)
	{
		return verifyAnyText(area, Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyAnyText(Double similarity,String... textList)
	{
		return verifyAnyText(null, similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyAnyText(Double[] area,Double similarity,String... textList)
	{
		return verifyAnyText(area, similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean verifyAnyText(long waitMilisecond,String... textList)
	{
		return verifyAnyText(null, Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean verifyAnyText(Double[] area,long waitMilisecond,String... textList)
	{
		return verifyAnyText(area, Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean verifyAnyText(Double similarity,long waitMilisecond,String... textList)
	{
		return verifyAnyText(null, similarity,waitMilisecond,textList);
	}

	static boolean verifyAnyText(Double[] area,Double similarity,long waitMilisecond,String... textList)
	{
		waitForNewScreen();
		boolean isFoond = false;

		for (String text : textList)
		{
			isFoond = false;
			setMessage("verifyAnyText", false, area, similarity, waitMilisecond, text);
			for(long time = 1000; time <= waitMilisecond; time += 1000)
			{
				Match match = findText(area, similarity, text);
				if( match == null)
				{
					if(time != waitMilisecond)
						CommonUtils.sleep(1000);
				}
				else
				{
					isFoond = true;
					setMessage("verifyAnyText", isFoond, area, match.getScore(), time, text);
					assertResult(getMessage(),isFoond);
					return isFoond;
				}
			}
			assertResult(false,getMessage(),isFoond);
		}
		setMessage("verifyAnyText", false, area, similarity, waitMilisecond, textList);
		assertResult(getMessage(),isFoond);
		return isFoond;
	}

	// tapText

	static boolean tapText(String text)
	{
		return tapText(null,Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),text);
	}

	static boolean tapText(Double[] area,String text)
	{
		return tapText(area,Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),text);
	}

	static boolean tapText(Double similarity,String text)
	{
		return tapText(null,similarity,ResourceUtils.getSearchTime(),text);
	}

	static boolean tapText(Double[] area,Double similarity,String text)
	{
		return tapText(area,similarity,ResourceUtils.getSearchTime(),text);
	}

	static boolean tapText(long waitMilisecond,String text)
	{
		return tapText(null,Constants.DEFAULT_SIMILARITY,waitMilisecond,text);
	}

	static boolean tapText(Double[] area,long waitMilisecond,String text)
	{
		return tapText(area,Constants.DEFAULT_SIMILARITY,waitMilisecond,text);
	}

	static boolean tapText(Double similarity,long waitMilisecond,String text)
	{
		return tapText(null,similarity,waitMilisecond,text);
	}

	static boolean tapText(Double[] area,Double similarity,long waitMilisecond,String text)
	{
		waitForNewScreen();
		setMessage("tapText", false, area, similarity, waitMilisecond, text);

		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findText(area, similarity, text);
			if( match != null)
			{
				click(match);
				//match.click();
				setMessage("tapText", true, area, match.getScore(), time, text);
				assertResult(getMessage(),true);
				return true;
			}
			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}

	// tapAnyText


	static boolean tapAnyText(String... textList)
	{
		return tapAnyText(null,Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean tapAnyText(Double[] area,String... textList)
	{
		return tapAnyText(area,Constants.DEFAULT_SIMILARITY,ResourceUtils.getSearchTime(),textList);
	}

	static boolean tapAnyText(Double similarity,String... textList)
	{
		return tapAnyText(null,similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean tapAnyText(Double[] area,Double similarity,String... textList)
	{
		return tapAnyText(area,similarity,ResourceUtils.getSearchTime(),textList);
	}

	static boolean tapAnyText(long waitMilisecond,String... textList)
	{
		return tapAnyText(null,Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean tapAnyText(Double[] area,long waitMilisecond,String... textList)
	{
		return tapAnyText(area,Constants.DEFAULT_SIMILARITY,waitMilisecond,textList);
	}

	static boolean tapAnyText(Double similarity,long waitMilisecond,String... textList)
	{
		return tapAnyText(null,similarity,waitMilisecond,textList);
	}

	static boolean tapAnyText(Double[] area,Double similarity,long waitMilisecond,String... textList)
	{
		boolean isFoond = false;
		waitForNewScreen();
		for (String text : textList)
		{
			isFoond = false;
			setMessage("tapAnyText", false, area, similarity, waitMilisecond, text);
			for(long time = 1000; time <= waitMilisecond; time += 1000)
			{
				Match match = findText(area, similarity, text);
				if( match == null)
				{
					if(time != waitMilisecond)
						CommonUtils.sleep(1000);
				}
				else
				{
					click(match);
					//match.click();
					isFoond = true;
					setMessage("tapAnyText", isFoond, area, match.getScore(), time, text);
					assertResult(getMessage(),isFoond);
					return isFoond;
				}
			}
			assertResult(false,getMessage(),isFoond);
		}
		setMessage("tapAnyText", false, area, similarity, waitMilisecond, textList);
		assertResult(getMessage(),isFoond);
		return isFoond;
	}

	//waitForText
	static boolean waitForText(long waitMilisecond,String... textList)
	{
		boolean isFound = false;
		//setAssertMode(false);
		for (String text : textList)
		{
			isFound = false;
			setMessage("waitForText", isFound, null, Constants.DEFAULT_SIMILARITY, waitMilisecond, text);
			for(long time = 1000; time <= waitMilisecond; time += 1000)
			{
				Match match = findText(null, Constants.DEFAULT_SIMILARITY, text);
				if( match == null)
				{
					if(time != waitMilisecond)
						CommonUtils.sleep(1000);
				}
				else
				{
					isFound = true;
					setMessage("waitForText", isFound, null, match.getScore(), time, text);
					break;
				}
			}
			if(!isFound)
			{
				break;
			}
		}

		assertResult(getMessage(),isFound);
		return isFound;
	}

	//findTextandtypeText
	static void findTextandtypeText(Double[] area, Double xCord, Double yCord)
	{
		List<Match> matchList = getAllText(area);
		tapOnScreen(xCord, yCord);
		for(Match match : matchList) {
			typeText(match.getText());
		}
	}

	static void waitForNewScreen()
	{
		waitForNewScreen(0.95,3);
	}

	static void waitForNewScreen(long times)
	{
		waitForNewScreen(0.95, times);
	}

	static void waitForNewScreen(Double similarity)
	{
		waitForNewScreen(similarity, 3);
	}

	private static boolean isKFound(Region region) {
		List<Match> matchList = region.listText();
		for (Match match : matchList) {
			if(match.getText().indexOf("K") >= 0) {
				return true;
			}
		}
		return false;
	}

	static void waitForNewScreen(Double similarity,long times)
	{
		if(waitMode) {
			boolean isStable = false;
			long startTime = System.currentTimeMillis();

			Region region = getDeviceRegion();
			region.h = 30;
			region.y = region.y - 93;

			while(true) {
				isStable = false;

				long foundStartTime = System.currentTimeMillis();

				while(isKFound(region)){
					startTime = System.currentTimeMillis();
					long timeDiff = System.currentTimeMillis() - foundStartTime;
					if(timeDiff >= 1000){
						isStable =true;
						//System.out.println("[PRESS]: K stable for " + timeDiff + " sec.................");
						break;
					}

					CommonUtils.sleep(100);
				}

				if(isStable) {
					break;
				}

				long endTime = System.currentTimeMillis();
				if(endTime - startTime >= 5000){
					//System.out.println("[PRESS]: NO K found... " + (endTime - startTime) + " sec.................");
					break;
				}
			}

			CommonUtils.sleep(1500);
		}


//		if(waitMode)
//		{
//			Region deviceRegion = getDeviceRegion();
//			Settings.MinSimilarity = similarity;
//			String tempPath = ResourceUtils.getProjectPath() + "temp/";
//
//			while(true)
//			{
//				boolean isStable = true;
//
//				String deviceScreenImage = saveScreenCapture(tempPath, "initial");
//
//				for (int i = 1; i <= times; i++)
//				{
//					if(deviceRegion.exists(deviceScreenImage) == null)
//					{
//						new File(deviceScreenImage).delete();
//						isStable = false;
//						break;
//					}
//
//					CommonUtils.sleep(CommonUtils.getRefreshTime());
//				}
//
//				if(isStable)
//				{
//					new File(deviceScreenImage).delete();
//					Settings.MinSimilarity = Constants.DEFAULT_SIMILARITY;
//					break;
//				}
//
//				System.out.println("waitForNewScreen() screen does not match................");
//			}
//		}

		setWaitMode(true);
	}

	static String saveDeviceScreen(Double[] area, String path,String name)
	{
		focus();

		Region region;
		if(area == null)
			region = getDeviceRegion();
		else
			region = deviceToSikuliRegion(area);

		if(region != null) {
			File screenshotPath = new File(path);
			if(!screenshotPath.exists())
				screenshotPath.mkdirs();
			String screenShotAbsPath = region.saveScreenCapture(path, name);

			if(screenShotAbsPath != null){
				if(!name.contains(".png")) name += ".png";
				File screenShotFile = new File(screenShotAbsPath);
				File renameFile = new File(path + "/" + name);
				screenShotFile.renameTo(renameFile);
				return renameFile.getAbsolutePath();
			}
		}
		return null;
	}

	static Boolean tapOnScreen(Double xCord, Double yCord){
		if(xCord < 0 || yCord < 0 || xCord > 1 || yCord > 1)
			return false;

		boolean r = true;
		waitForNewScreen();
		click(getPoint(xCord, yCord));

		return r;
	}

	static void tapOnScreen() {
		waitForNewScreen();
		Point point = MouseInfo.getPointerInfo().getLocation();
		Location location = new Location(point.getX(),point.getY());
		location.click();
	}

	static Boolean doubleTapOnScreen(Double xCord, Double yCord){
		if(xCord < 0 || yCord < 0 || xCord > 1 || yCord > 1)
			return false;

		waitForNewScreen();
		return doubleClick(getPoint(xCord, yCord));
	}

	static Boolean longPressText(String text){
		return longPressText(null, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), text);
	}

	static Boolean longPressText(Double similarity, String text){
		return longPressText(null, similarity, ResourceUtils.getSearchTime(), text);
	}

	static Boolean longPressText(Double[] area, String text){
		return longPressText(area, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), text);
	}

	static Boolean longPressText(Double[] area, Double similarity, String text){
		return longPressText(area, similarity, ResourceUtils.getSearchTime(), text);
	}

	static Boolean longPressText(Double[] area, Double similarity, long waitMilisecond, String text){
		waitForNewScreen();

		setMessage("longPressText", false, area, similarity, waitMilisecond, text);

		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findText(area, similarity, text);
			if( match != null)
			{
				match.hover();
				match.mouseDown(Button.LEFT);
				CommonUtils.sleep(3000);
				match.mouseUp();

				setMessage("longPressText", true, area, match.getScore(), time, text);
				assertResult(getMessage(),true);
				return true;
			}
			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}

	static Boolean longPressImage(String bitmap){
		return longPressImage(null, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), bitmap, null);
	}

	static Boolean longPressImage(Double similarity, String bitmap){
		return longPressImage(null, similarity, ResourceUtils.getSearchTime(), bitmap, null);
	}

	static Boolean longPressImage(Double[] area, String bitmap){
		return longPressImage(area, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), bitmap, null);
	}

	static Boolean longPressImage(Double[] area, Double similarity, String bitmap){
		return longPressImage(area, similarity, ResourceUtils.getSearchTime(), bitmap, null);
	}


	static Boolean longPressImage(String bitmap1, String bitmap2){
		return longPressImage(null, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), bitmap1, bitmap2);
	}

	static Boolean longPressImage(Double similarity, String bitmap1, String bitmap2){
		return longPressImage(null, similarity, ResourceUtils.getSearchTime(), bitmap1, bitmap2);
	}

	static Boolean longPressImage(Double[] area, String bitmap1, String bitmap2){
		return longPressImage(area, Constants.DEFAULT_SIMILARITY, ResourceUtils.getSearchTime(), bitmap1, bitmap2);
	}

	static Boolean longPressImage(Double[] area, Double similarity, String bitmap1, String bitmap2){
		return longPressImage(area, similarity, ResourceUtils.getSearchTime(), bitmap1, bitmap2);
	}

	static Boolean longPressImage(Double[] area, Double similarity,long waitMilisecond,String bitmap1, String bitmap2){
		waitForNewScreen();

		setMessage("longPressImage", false, area, similarity, waitMilisecond, bitmap1, bitmap2);

		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findBitMap(area, similarity, bitmap1, bitmap2);
			if( match != null)
			{
				match.hover();
				match.mouseDown(Button.LEFT);
				CommonUtils.sleep(3000);
				match.mouseUp();

				setMessage("longPressImage", true, area, match.getScore(), time, bitmap1,bitmap2);
				assertResult(getMessage(),true);
				return true;
			}

			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}

	static Boolean longPressCoord(Double xCord, Double yCord){
		if(xCord < 0 || yCord < 0 || xCord > 1 || yCord > 1)
			return false;

		waitForNewScreen();

		Region region;
		region = getPoint(xCord,yCord);
		region.hover();
		region.mouseDown(Button.LEFT);
		CommonUtils.sleep(3000);
		region.mouseUp();

		return true;
	}

	static Boolean pressEnter() {
		Boolean status = false;
		return status;
	}

	static Boolean typeText(String text){
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
//		r.delayType(50);
		r.type(text);
//		for(char c : text.toCharArray()){
//			r.type(c+"");
//			CommonUtils.sleep(100);
//		}

		return true;
	}

	static Match findHWKey(String bitmap) {
		Match match = null;
		Settings.MinSimilarity = Constants.DEFAULT_SIMILARITY;

		Region region = getDeviceRegion();
		region.h =  region.h + 37;

		try
		{
			match = region.find(bitmap);
		}
		catch (Exception e)
		{
			match = null;
		}

		return match;
	}

	static boolean tapHWKey(String bitmap) {
		waitForNewScreen(0.9);
		long waitMilisecond = ResourceUtils.getSearchTime();
		setMessage("tapHWKey", true, null, Settings.MinSimilarity, waitMilisecond, bitmap);
		for(long time = 1000; time <= waitMilisecond; time += 1000)
		{
			Match match = findHWKey(bitmap);
			if( match != null)
			{
				click(match);
				//match.click();
				setMessage("tapHWKey", true, null, match.getScore(), time, bitmap);
				assertResult(getMessage(),true);
				return true;
			}
			if(time != waitMilisecond)
				CommonUtils.sleep(1000);
		}
		assertResult(getMessage(),false);
		return false;
	}


	static Boolean pressBack() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("B",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
		//return tapHWKey(Constants.DEVICE_INTERFACE_BACK);
	}

	static Boolean pressHome() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("H",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;

		//return tapHWKey(Constants.DEVICE_INTERFACE_HOME);
	}

	static Boolean pressMenu() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("M",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;

		//return tapHWKey(Constants.DEVICE_INTERFACE_MENU);
	}
	
	static Boolean pressVolPlus() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("V",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressVolMinus() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("W",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;

	}
	
	static Boolean pressMute() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("N",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;

	}
	
	static Boolean pressChannelPlus() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("C",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressChannelMinus() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("D",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressUp() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("U",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressDown() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("E",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressRight() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("R",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressLeft() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("L",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressPower() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("P",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressOne() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("1",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressTwo() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("2",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressThree() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("3",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressFour() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("4",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressFive() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("5",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressSix() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("6",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressSeven() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("7",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressEight() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("8",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressNine() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("9",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressZero() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("0",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressOneTwoThree() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("O",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressDotDotDot() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("F",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressPlay() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("Q",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	
	static Boolean pressOk() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("K",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}
	

	static Boolean longPressHome() {
		waitForNewScreen(0.9);

		focus();
		Region r = getDeviceRegion();
		r.type("L",KeyModifier.CTRL+KeyModifier.SHIFT+KeyModifier.ALT);
		return true;
	}

	static Boolean tapOnMiddle(){
		Region region = getDeviceRegion();
		click(region);
		return true;
	}

	static Region getPoint(double x,double y)
	{
		ArrayList<Double> sikuiliCoordinate = convertToSikuiliCoordinate(x, y);
		return new Region(sikuiliCoordinate.get(0).intValue(), sikuiliCoordinate.get(1).intValue(), 1, 1);
	}

	static void openNotificationBar(){
		swipe(0.5, 0.015, 0.5, 0.7);
	}

	static void swipeUp(double x, double y, double distance)
	{
		Region region = getDeviceRegion();
		try {
			region.dragDrop(getPoint(x,y), getPoint(x,y-distance));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	static void swipe(double srcx, double srcy, double desx, double desy)
	{
		waitForNewScreen();
		Region region = getDeviceRegion();
		try {
			region.dragDrop(getPoint(srcx,srcy), getPoint(desx,desy));
			CommonUtils.sleep(100);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	static void scroll(int dirction,int scroollCount, long waitMilisecond)
	{
		Region region = getDeviceRegion();
		region.hover();
		region.wheel(1, scroollCount);
		CommonUtils.sleep(waitMilisecond);
	}

	static void scroollUp(int scroollCount)
	{
		scroollUp(scroollCount,Constants.DEVICE_SCROLL_TIME);
	}

	static void scroollUp(int scroollCount,long waitMilisecond)
	{
		scroll(-1,scroollCount, waitMilisecond);
	}

	static void scroollDown(int scroollCount)
	{
		scroollDown(scroollCount,Constants.DEVICE_SCROLL_TIME);
	}

	static void scroollDown(int scroollCount,long waitMilisecond)
	{
		scroll(1,scroollCount, waitMilisecond);
	}
}
