package fxviewer;


import javafx.scene.control.TextArea;

public class TCReRunTask extends javax.swing.SwingWorker<String, String> {
	TextArea textArea;

	StoreAppController controller;

	public TCReRunTask( StoreAppController controller) {
		this.controller = controller;
	}

	@Override
	public String doInBackground() {

//		List<TestInfo> tcList = runner.getTestInfo();
//		
//		for (TestInfo testInfo : tcList) {
//			if(testInfo.getResult() != TCResult.Pass ) {
//				//System.out.println("@#@#" + testInfo.getTcName());
//				SuiteRunner.reRunTestCase(runner.getSuiteName(), testInfo.getTcName());
//			}
//		}
		return null;
	}
}
