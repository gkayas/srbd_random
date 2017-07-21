package fxviewer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uirunner.ReportUtils;
import uirunner.SampleAppInfo;

public class CommonUseController implements Initializable, EventHandler<ActionEvent> /*,ChangeListener*/{

	@FXML
	private Button browseBtn;

	@FXML
	private Button saveBtn;

	@FXML
	private Button runBtn;

	@FXML
	private Button exportReportBtn;

	@FXML
	private Button deviceInterfaceBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private TextArea scriptView;

	@FXML
	private TextArea console;

	private String tcResult = "NT";
	private long elapseTime = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		runBtn.setDisable(true);
		saveBtn.setDisable(true);
		exportReportBtn.setDisable(true);

		setConsole();
		ConfigUtil.readUIAToolConfig();
		ConfigUtil.readDeviceConfig();
		scriptView.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.isEmpty()){
		    	runBtn.setDisable(false);
		    	saveBtn.setDisable(false);
		    }else{
		    	runBtn.setDisable(true);
		    	saveBtn.setDisable(true);
		    }
		});

	}


	private void setConsole() {
		if(console == null)
			System.out.println("[ERROR] Console is null");
		else{
			CustomConsole customConsole = new CustomConsole(console);
			PrintStream ps = new PrintStream(customConsole);

			System.setOut(ps);
			System.setErr(ps);
		}
	}

	public void clearConsole() {
		Platform.runLater(() -> console.clear());
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}

	public void browseBtnClicked(){
		setConsole();
		try {
			setConsole();

			FileChooser fc = new FileChooser();
			fc.setTitle("Browse script file");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script file (*.tc)", "*.tc");
			fc.getExtensionFilters().add(extFilter);
			fc.setInitialDirectory(new File("./src/common"));
			File selectedFile = fc.showOpenDialog(null);

			if (selectedFile != null) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
				String line = null;
				String code = "";
				while((line = bufferedReader.readLine()) != null){
					code += line + "\n";
				}
				bufferedReader.close();
				scriptView.setText(code);
			}

		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	private void writeCode() {
		BufferedReader buf = null;
		StringBuffer sb = new StringBuffer();
		try {
			buf = new BufferedReader(new FileReader("AFEngine/template/Template"));

			if (null != buf) {
				String s = null;

				while ((s = buf.readLine()) != null) {

					sb.append(s + "\n");
				}
			}
			PrintWriter pw = new PrintWriter(new File("AFEngine/src/TestCase.java"));
			pw.write(sb.toString());
			String code = scriptView.getText().replaceAll("\n", "\n\t\t");
			pw.append("\n\t@Test\n\tpublic void testCases() throws Exception\n\t{ \n\t\t" + code);
			pw.append("\n\t}\n}");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	private boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

	private static String getLogFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String logFileName =  format.format(date)+"_uiautomator.log";
		return logFileName;
	}

	private void performCompileRun() {
		runBtn.setDisable(true);
		browseBtn.setDisable(true);
		exportReportBtn.setDisable(true);

		String logFileName = getLogFileName();
		new Thread(new Runnable() {

			@Override
			public void run() {
				String currentWorkingDirectory1 = System.getProperty("user.dir");
				ProcessBuilder compile, run;
				Process compileProcess, runProcess;

				try {
					if (!isWindows()) {
						compile = new ProcessBuilder("javac", "-d",
								currentWorkingDirectory1 + "/AFEngine/bin", "-cp", currentWorkingDirectory1
										+ "/AFEngine/lib/*" + ":" + currentWorkingDirectory1 + "/AFEngine/src",
								currentWorkingDirectory1 + "/AFEngine/src/Main.java");
					} else {
						compile = new ProcessBuilder("cmd", "/c", "javac", "-d",
								currentWorkingDirectory1 + "/AFEngine/bin", "-cp", currentWorkingDirectory1
									+ "/AFEngine/lib/*" + ";" + currentWorkingDirectory1 + "/AFEngine/src",
								currentWorkingDirectory1 + "/AFEngine/src/Main.java");
					}

					compileProcess = compile.start();

					String line = "";
					BufferedReader msgReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
					BufferedReader errReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));

					while ((line = msgReader.readLine()) != null) {
						System.out.println(line);
					}

					int returnCode = compileProcess.waitFor();

					msgReader.close();
					errReader.close();
					compileProcess.destroy();

					if (returnCode == 0) {
						if (!isWindows()) {
							run = new ProcessBuilder(
									"java", "-cp", currentWorkingDirectory1 + "/AFEngine/lib/*:"
											+ currentWorkingDirectory1 + "/AFEngine/bin", "Main", currentWorkingDirectory1, logFileName);
						} else {
							run = new ProcessBuilder("cmd", "/c",
									"java", "-cp", currentWorkingDirectory1 + "/AFEngine/lib/*;"
											+ currentWorkingDirectory1 + "/AFEngine/bin", "Main", currentWorkingDirectory1, logFileName);
						}

						runProcess = run.start();
						msgReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
						errReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

						StringBuffer msgSb = new StringBuffer();
						StringBuffer errSb = new StringBuffer();
						String sm,se;
						while(true) {
							try{
								runProcess.exitValue();
								break;
							}catch(IllegalThreadStateException ex){

							}

							while (msgReader.ready()) {
								sm = msgReader.readLine();
								msgSb.append(sm);

								System.out.println(msgSb.toString());
								msgSb = new StringBuffer();

								if(sm.contains("STATUS") && sm.contains("Result") && sm.contains("Time")) {
									tcResult = sm.split("\\[Result")[1].split("\\]")[0].split("->")[1].trim();
									elapseTime = Integer.parseInt(sm.split("\\[Time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim());
								}
							}

							while (errReader.ready()) {
								se = errReader.readLine();
								errSb.append(se);
								System.out.println(errSb.toString());
								errSb = new StringBuffer();
							}
							Thread.sleep(10);
						}

						msgReader.close();
						errReader.close();
						runProcess.destroy();
					}

				} catch (Exception e) {
					System.out.println("[ERROR] " + e.getMessage());
				}

				runBtn.setDisable(false);
				browseBtn.setDisable(false);
				exportReportBtn.setDisable(false);
			}


		}).start();
	}

	public void runBtnClicked(){
		tcResult = "NT";
		elapseTime = 0;
		setConsole();
		clearConsole();
		writeCode();
		performCompileRun();
	}

	public void exportReportBtnClicked(){
		setConsole();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Browse applications");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Report files (*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);
		File reportDirectory = new File("./reports");

		if (! reportDirectory.exists())
			reportDirectory.mkdir();

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		fileChooser.setInitialFileName(format.format(date)+"_testcase.xls");
		fileChooser.setInitialDirectory(reportDirectory);
		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile != null){
			String reportFilePath=selectedFile.getAbsolutePath();
			if(!reportFilePath.contains(".xls"))
				reportFilePath += ".xls";
			ReportUtils.exportCommonUseReport(reportFilePath,tcResult, elapseTime);
		}
	}

	public void saveBtnClicked(){
		setConsole();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Browse script directory");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script file (*.tc)", "*.tc");
		fileChooser.getExtensionFilters().add(extFilter);
		File srcDirectory = new File("./src/common");

		if (! srcDirectory.exists())
			srcDirectory.mkdir();

		fileChooser.setInitialDirectory(srcDirectory);
		fileChooser.setInitialFileName("untitled.tc");
		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile != null){
			String srcFilePath=selectedFile.getAbsolutePath();
			if(!srcFilePath.contains(".tc"))
				srcFilePath += ".tc";
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(srcFilePath)));
				bufferedWriter.write(scriptView.getText());
				bufferedWriter.close();
			} catch (IOException e) {
				System.out.println("[ERROR] " + e.getMessage());
			}
		}
	}

	public void deviceInterfaceBtnClicked(){
		deviceInterfaceBtn.setDisable(true);
		String[] cmd = new String[3];

		if(isWindows())
		{
			cmd[0] = "cmd";
			cmd[1] = "/c";
		}
		else {

			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
		}

		cmd[2] = "java -jar run-device-interface.jar";

		ProcessBuilder builder = new ProcessBuilder(cmd);

        builder.redirectErrorStream(true);
        new Thread(new Runnable() {

			@Override
			public void run() {
				try
				{
					Process process = builder.start();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			        String line;
			        while (true)
			        {
			            line = bufferedReader.readLine();
			            if (line == null) { break; }
			        }
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				deviceInterfaceBtn.setDisable(false);
			}
        }).start();

	}

	public void settingsBtnClicked(){
		setConsole();

		try {
			Stage settingsStage = new Stage();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CommonSettingsScene.fxml")));
			settingsStage.setScene(scene);
			settingsStage.setTitle("Common Setting Window");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			settingsStage.initModality(Modality.APPLICATION_MODAL);
			settingsStage.show();

		} catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

}
