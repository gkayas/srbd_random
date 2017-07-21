package application;

import javax.swing.SwingWorker;

import javafx.scene.control.TextArea;
import refapp.SuiteRunner;
import refapp.TCRunner;


public class TCRunSingle extends SwingWorker<String, String> {
	
	TextArea textArea;
	TCRunner runner;
	String tcName;

	public TCRunSingle(TCRunner runner, String tcName) {
		this.runner = runner;
		this.tcName = tcName;
	}

	@Override
	protected String doInBackground() throws Exception {
		
		SuiteRunner.runTestCase(runner.getSuiteName(),tcName);
		return null;
	}

}
