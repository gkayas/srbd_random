package application;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.TextArea;
import junit.framework.TestResult;
import refapp.ReportUtils;
import refapp.SuiteRunner;
import refapp.TCResult;
import refapp.TCRunner;
import refapp.TestInfo;

public class TCReRunTask extends javax.swing.SwingWorker<String, String> {
	TextArea textArea;
	TCRunner runner;
	MainController controller;

	public TCReRunTask(TCRunner runner, MainController controller) {
		this.runner = runner;
		this.controller = controller;
	}

	@Override
	public String doInBackground() {

		List<TestInfo> tcList = runner.getTestInfo();
		for (TestInfo testInfo : tcList) {
			if(testInfo.getResult() != TCResult.Pass ) {
				System.out.println("@#@#" + testInfo.getTcName());
				SuiteRunner.reRunTestCase(runner.getSuiteName(), testInfo.getTcName());
			}
		}
		return null;
	}
}
