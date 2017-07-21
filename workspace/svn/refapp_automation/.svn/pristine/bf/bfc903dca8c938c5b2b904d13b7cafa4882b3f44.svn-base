package refapp;

import java.io.IOException;


public class SuiteRunner {
	
	public static boolean isTCRerun = false;
	
	private static TCRunner getTCRunner(String moduleName) throws IOException {
		XMLReader reader = new XMLReader();
		reader.setXmlPath(moduleName + ".xml");
		TCRunner runner = new TCRunner();
		runner.setTestInfo(reader.getTestInfo());
		return runner;
	}
	
	public static void runModule(String moduleName){
		try {
			TCRunner runner = getTCRunner(moduleName);		
			runner.runAllTC(moduleName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void runTestCase(String moduleName,String testCaseName){
		try {
			TCRunner runner = getTCRunner(moduleName);		
			runner.runSingleTC(moduleName,testCaseName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void reRunTestCase(String moduleName,String testCaseName){
		try {
			isTCRerun = true;
			TCRunner runner = getTCRunner(moduleName);		
			runner.reRunTC(moduleName,testCaseName);
			isTCRerun = false;
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean isTCRerun()
	{
		return isTCRerun;
	}
}
