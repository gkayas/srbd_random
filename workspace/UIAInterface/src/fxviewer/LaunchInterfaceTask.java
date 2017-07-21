//package fxviewer;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class LaunchInterfaceTask extends javax.swing.SwingWorker<String, String>{
//
//	StoreAppController controller;
//	Process process;
//	public LaunchInterfaceTask(StoreAppController controller) {
//		this.controller = controller;
//	}
//	
//	@Override
//	protected String doInBackground() throws Exception {
//		System.out.println("[LOG]: Start running Device Interface.......");
//
//		//controller.toggleInterfaceBtn();
//		runProcess("java","-jar","device-interface.jar");
//		
//		if(process.exitValue() != 0) {
//			System.out.println("[LOG]: Device Interface launched failed or stopped.......");
//		}
//		
//		//controller.toggleInterfaceBtn();
//		return null;
//	}
//
//	public void stopPrecess() {
//		process.destroyForcibly();
//	}
//	
//	public  Process runProcess(String... args ) {
//		String result = "";
//		try {
//			process = new ProcessBuilder(args).start();
//
//			process.waitFor();
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			StringBuffer sb = new StringBuffer();
//			String s = "";
//			process.destroy();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return process;
//	}
//}
