package refapp;

import base.AFEngine;



public class Main {
	public static void main(String[] args) throws Exception{

		AFEngine.initialize();
		ReportUtils.initialize();

		if(args.length > 0) {
			System.out.println(args[0] + " " + args[1] + " " + args[2]);
			if(args[0].equals("runModule")) {
				SuiteRunner.runModule(args[1]);
			}
			else if(args[0].equals("runTestCase")) {		
				SuiteRunner.reRunTestCase(args[1], args[2]);
			}
			else if(args[0].equals("reRunTestCase")) {		
				SuiteRunner.reRunTestCase(args[1], args[2]);
			}	
		}
		else {
			SuiteRunner.runModule("Gallery");
			//SuiteRunner.runTestCase("Gallery","Gallery_080");
			//SuiteRunner.reRunTestCase("Calendar","Calendar_211");
		
			//SuiteRunner.runModule("Calendar");
			//SuiteRunner.runTestCase("Calendar","Calendar_218");
			//SuiteRunner.reRunTestCase("Calendar","Calendar_211");
		}
		
		AFEngine.deinitialize();
		System.exit(0);
	}
}
