package refapp;

import java.io.IOException;


public class SuiteRunner {
	
	public static boolean isTCRerun = false;
	
	private static TCRunner runner = null;
	
	public static TCRunner getRunner() {
		return runner;
	}

	public static TCRunner updateRunner() throws IOException {
		return getTCRunner(runner.getSuiteName());
	}
	
	public static TCRunner getTCRunner(String moduleName) throws IOException {
		XMLReader reader = new XMLReader();
		reader.setXmlPath(moduleName + ".xml");
	    runner = new TCRunner();
	    runner.setSuiteName(moduleName);
		runner.setTestInfo(reader.getTestInfo(moduleName));
		return runner;
	}
	
	public static void runModule(String moduleName){
		
		try {
			
			if(runner == null) {
				runner = getTCRunner(moduleName);	
			}	
			
			runner.runAllTC(moduleName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void runTestCase(String moduleName,String testCaseName){
		try {
			if(runner == null) {
				runner = getTCRunner(moduleName);	
			}
			runner.runSingleTC(moduleName,testCaseName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void reRunTestCase(String moduleName,String testCaseName){
		try {
			isTCRerun = true;
			if(runner == null) {
				runner = getTCRunner(moduleName);	
			}
			runner.reRunTC(moduleName,testCaseName);
			isTCRerun = false;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isTCRerun()
	{
		return isTCRerun;
	}
}
