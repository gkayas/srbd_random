package refapp;

import java.io.File;
import java.util.List;

import device.Target;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class TCRunner {
	private List<TestInfo> testInfo;
	private int depth = 0;
	//private Boolean isDeviceConnected;
	private String reRunTcName = null;
	private String suiteName = null;
	private String ERROR_SCREENSHOT_PATH = "error/";

	public TCRunner() {
		// TODO Auto-generated constructor stub
		//isDeviceConnected = false;
		//isDeviceConnected = DeviceUtils.setPrimaryDevice();
	}

	public void setTestInfo(List<TestInfo> testInfo) {
		this.testInfo = testInfo;
	}

	public List<TestInfo> getTestInfo() {
		return this.testInfo;
	}

	public void runAllTC(String suiteName) throws Exception {
		//if(!isDeviceConnected)return;
		TestResult result;
		cleanErrorPath(suiteName);
		for (TestInfo t : testInfo) {
			t.setIsMethodExists(false);
			result = runTc(suiteName, t.getTcName());
			updateResult(result, t, suiteName, false);
		}

	}

	private void updateResult(TestResult result, TestInfo t,String module,boolean isResultUpdatedinExcel) {
		TestInfo t_info = t;

		if (result != null) {
			if (result.failureCount() == 0 && result.errorCount() == 0) {
				t_info.setResult(TCResult.Pass);
				ReportUtils.updateExcelResult(module,t_info.getTcName(),t_info.getResult().name());
				ResultUtils.showOutput("[Result] " + t_info.getTcName() + " ===> " + t_info.getResult().name());
				return;
			} else{
				if(!isResultUpdatedinExcel) {
					t_info.setResult(TCResult.Fail);
				}
				saveErrorScreenShot(module,t_info.getTcName());
				System.out.println("Inside Failed screen capture.");
				ResultUtils.showOutput("[Result] " + t_info.getTcName() + " ===> " + TCResult.Fail);
			}

			if(!isResultUpdatedinExcel){
				ReportUtils.updateExcelResult(module,t_info.getTcName(),t_info.getResult().name());
			}
			
		}else if(result == null && t_info.isMethodExists()) {
			t_info.setResult(TCResult.NT);
			ResultUtils.showOutput("[Result] " + t_info.getTcName() + " ===> " + t_info.getResult().name());

			if(!isResultUpdatedinExcel) {
				ReportUtils.updateExcelResult(module,t_info.getTcName(),t_info.getResult().name());
			}
		}
	}


	private static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if(files!=null) {
			for(File f: files) {
				if(f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	private void cleanErrorPath(String moduleName){
		File errorScreenDir = new File(ERROR_SCREENSHOT_PATH + moduleName);
		deleteFolder(errorScreenDir);
		errorScreenDir.mkdir();
	}

	private void saveErrorScreenShot(String moduleName,String tcName) {
		File screenshotPath = new File(ERROR_SCREENSHOT_PATH+moduleName);
		if(!screenshotPath.exists())
			screenshotPath.mkdir();
		else{
			File[] files = screenshotPath.listFiles();
			for (File screenShot : files) {
				if(screenShot.getName().contains(tcName))
					screenShot.delete();
			}
		}
		String screenShotAbsPath = new Target().saveScreenCapture(ERROR_SCREENSHOT_PATH + moduleName, tcName);
		File screenShotFile = new File(screenShotAbsPath);
		screenShotFile.renameTo(new File(ERROR_SCREENSHOT_PATH + moduleName + "/" + tcName + ".png"));

	}

	public TestResult runTc(String suiteNme, String tcName) throws Exception {
		TestResult result = null;
		TestInfo tInfo = getTCInfoByName(tcName);

		Boolean isExist = false;

		Class<?> c1 = Class.forName("suites." + suiteNme);
		try {
			c1.getDeclaredMethod(tcName);
			isExist = true;
			tInfo.setIsMethodExists(true);
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		}


		if(isExist) {
			if (tInfo.getPreCondition() == "" || getTCInfoByName(tInfo.getPreCondition()).getResult() == TCResult.Pass) {

				Class<?> c = Class.forName("suites." + suiteNme);

				try {
					c.getDeclaredMethod(tcName);
					TestCase t = (TestCase) c.getConstructor(String.class).newInstance(tcName);
					result = t.run();
				} catch (NoSuchMethodException e) {
					System.out.println("Method not exists");
				}

			}
		}

		return result;

	}

	public void runSingleTC(String suiteName, String tcName) throws Exception{
		//if(!isDeviceConnected)return;
		TestResult result = null;
		TestInfo t_info = getTCInfoByName(tcName);
		TCResult prevResult = t_info.getResult();

		Class<?> c = Class.forName("suites." + suiteName);
		try {
			c.getDeclaredMethod(tcName);
			TestCase t = (TestCase) c.getConstructor(String.class).newInstance(tcName);
			result = t.run();
		} catch (NoSuchMethodException e) {
			//System.out.println("Method not exists");
			e.printStackTrace();
		}

		//if(prevResult == TCResult.Pass);
		System.out.println(prevResult +" ::# ");
		updateResult(result, t_info,suiteName, prevResult == TCResult.Pass);

	}

	public TestResult reRunTC(String suiteName, String tcName) {

		//if(!isDeviceConnected)return;
		boolean isResultAlreadyUpdated = true;
		String preCondition = "";
		TestResult result = null ;
		if (tcName != "") {
			if(depth == 0) reRunTcName = tcName;
			depth++;
			for (int i = 0; i < testInfo.size(); i++) {
				if (testInfo.get(i).getTcName().contains(tcName)) {
					preCondition = testInfo.get(i).getPreCondition();
					TestResult preResult = reRunTC(suiteName, preCondition);

					if (preResult != null && !(preResult.failureCount() == 0 && preResult.errorCount() == 0)) { 
						return preResult;
					}
				}
			}

			try {
				result = runTc(suiteName, tcName);
			
				if(tcName.contains(reRunTcName)) {
					isResultAlreadyUpdated = false;
					depth = 0;
				} else {

					if( !(result.failureCount() == 0 && result.errorCount() == 0) ) {
						System.out.println("%%%% For preReq exe [Fail]: " + tcName);
						ResultUtils.showOutput("[Result] " + tcName + " ===> Fail");
						depth = 0;
						return result;
					}

				}

				updateResult(result, getTCInfoByName(tcName),suiteName,isResultAlreadyUpdated);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return result;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public TestInfo getTCInfoByName(String name) {
		TestInfo tcInfo = null;
		for (int i = 0; i < testInfo.size(); i++) {
			if (testInfo.get(i).getTcName().contains(name)) {
				tcInfo = testInfo.get(i);
				break;
			}
		}
		return tcInfo;
	}

}
