package fxviewer;

import javax.swing.SwingWorker;

import javafx.scene.control.TextArea;


public class TCRunSingle extends SwingWorker<String, String> {
	
	TextArea textArea;	
	String tcName;

	public TCRunSingle(String tcName) {
		this.tcName = tcName;
	}

	@Override
	protected String doInBackground() throws Exception {
		return null;
	}

}
