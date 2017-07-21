package refapp;

import java.util.List;

import device.Target;
import junit.framework.TestCase;
import junit.framework.TestResult;
import device.*;

public class TCRunner {
	private List<TestInfo> testInfo;
	//private Boolean isDeviceConnected;
	
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
		TestResult result;
		for (TestInfo t : testInfo) {
			t.setIsMethodExists(false);
			result = runTc(suiteName, t.getTcName());
			updateResult(result, t, suiteName);

		}

	}

	private void updateResult(TestResult result, TestInfo t,String module) {
		TestInfo t_info = t;
		
		if (result != null) {
			if (result.failureCount() == 0 && result.errorCount() == 0) {
				t_info.setResult(TCResult.PASS);
			} else{
				t_info.setResult(TCResult.FAIL);
				new Target().saveScreenCapture("error",t_info.getTcName());
			}
			
			ResultUtils.showOutput("[Result] " + t_info.getTcName() + " ===> " + t_info.getResult().name());
			ReportUtils.updateExcelResult(module,t_info.getTcName(),t_info.getResult().name());
		}
		else if(result == null && t_info.isMethodExists())
		{
			ResultUtils.showOutput("[Result] " + t_info.getTcName() + " ===> " + t_info.getResult().name());
			ReportUtils.updateExcelResult(module,t_info.getTcName(),t_info.getResult().name());
		}
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
			
		}
		
	
		if(isExist)
		{
			

		if (tInfo.getPreCondition() == "" || getTCInfoByName(tInfo.getPreCondition()).getResult() == TCResult.PASS) {
			
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
		
		Class<?> c = Class.forName("suites." + suiteName);
		try {
			c.getDeclaredMethod(tcName);
			TestCase t = (TestCase) c.getConstructor(String.class).newInstance(tcName);
			result = t.run();
		} catch (NoSuchMethodException e) {
			//System.out.println("Method not exists");
		}
		
		updateResult(result, t_info,suiteName);
	}

	public void reRunTC(String suiteName, String tcName) {
		//if(!isDeviceConnected)return;
		String preCondition = "";

		if (tcName != "") {
			for (int i = 0; i < testInfo.size(); i++) {
				if (testInfo.get(i).getTcName().contains(tcName)) {
					preCondition = testInfo.get(i).getPreCondition();
					reRunTC(suiteName, preCondition);
				}
			}
			TestResult result;
			try {
				result = runTc(suiteName, tcName);
				updateResult(result, getTCInfoByName(tcName),suiteName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

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
