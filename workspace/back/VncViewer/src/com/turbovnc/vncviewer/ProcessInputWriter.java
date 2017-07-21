package com.turbovnc.vncviewer;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ProcessInputWriter {
	private Process process;
	JTextArea textArea;
	public ProcessInputWriter(Process p, JTextArea ta){
		process = p;
		textArea = ta;
	}
	
	public void writeOutput() {
	
		 Runnable run = new Runnable() {
			
			@Override
			public void run() {
				StringBuffer sb = new StringBuffer();
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				BufferedReader msgReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

				String error = "";
				String msg = "";
				try {
					while ((error = errorReader.readLine()) != null || (msg = msgReader.readLine()) != null) {
						if (error != null) {
							sb.append("ERR :" + error + "\n");
							textArea.append(error + "\n");
						}
						if (msg != null) {
							sb.append("MSG :" + msg + "\n");
							textArea.append(msg + "\n");
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				process.destroy();
			}
		};
		Thread th = new Thread(run);
		th.start();
	}
}
