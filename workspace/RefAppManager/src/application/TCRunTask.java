package application;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingWorker;

import javafx.scene.control.TextArea;
import refapp.ReportUtils;
import refapp.SuiteRunner;
import refapp.TCRunner;
import refapp.TestInfo;

public class TCRunTask extends SwingWorker<String, String> {
	TextArea textArea;
	TCRunner runner;
	MainController controller;

	public TCRunTask(TCRunner runner, MainController controller) {
		this.runner = runner;
		this.controller = controller;
	}

	@Override
	public String doInBackground() {
		ReportUtils.resetAllTcResutl(runner.getSuiteName());
		
		try {
			runner = SuiteRunner.updateRunner();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<TestInfo> tcList = runner.getTestInfo();

		controller.resetTctable(tcList);
		SuiteRunner.runModule(runner.getSuiteName());
		return null;
	}
}
