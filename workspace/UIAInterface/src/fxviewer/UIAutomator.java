package fxviewer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import clirunner.AppInfo;
import clirunner.ConfigReader;
import clirunner.Execution;
import clirunner.LogUtils;
import clirunner.ReportUtils;
import clirunner.XMLUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class UIAutomator extends Application {
	PrintStream console;

	@Override
	public void start(Stage stage)  {
		Parent root;
		console = System.out;

		try {
			root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can not load fxml file");
			return;
		}

		Scene scene = new Scene(root);
        stage.setTitle("UI Automator [v0.2] [StoreApp Automator]");
        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("icons/main-icon.png")));
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}

	@Override
	public void stop() {
		System.setOut(console);

		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    Platform.exit();
	    System.exit(0);
	}

	public static void runProject(String mode, String project ,String reportFilePath) {

		try {
			ConfigReader reader = new ConfigReader();

			if(reportFilePath == null){
				reportFilePath = Paths.get(reader.getReportpath(), ReportUtils.getReportFileName(project)).toString();
			}else{
				if(!reportFilePath.endsWith(".xls")){
					reportFilePath = Paths.get(reportFilePath, ReportUtils.getReportFileName(project)).toString();
				}
			}

			ReportUtils.checkConfig(project, reader.getSdkPath(), reportFilePath);

			Execution.suspend(false);
			String logFileName = LogUtils.getLogFileName();

			ArrayList<AppInfo> appInfos = XMLUtils.parseXML(project, mode);

			if (appInfos != null){
				appInfos = Execution.compileRunProject(project, mode, appInfos,logFileName);
				ReportUtils.saveProjectReport(reportFilePath, appInfos, project, mode);
			}else{
				System.out.println("[MESSAGE] No plan found to execute " + mode + " with " + project);
			}
		}
		catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	public static void runTestCase(String mode, String testCasePath ,String reportFilePath) {

		try {
			String project = "common";
			ConfigReader reader = new ConfigReader();

			if(reportFilePath == null){
				reportFilePath = Paths.get(reader.getReportpath(), ReportUtils.getReportFileName(project)).toString();
			}else{
				if(!reportFilePath.endsWith(".xls")){
					reportFilePath = Paths.get(reportFilePath, ReportUtils.getReportFileName(project)).toString();
				}
			}

			ReportUtils.checkConfig(project, reader.getSdkPath(), reportFilePath);

			Execution.suspend(false);
			String logFileName = LogUtils.getLogFileName();

			if(new File(testCasePath).exists()){
				Execution.compileRunTestcase(testCasePath,logFileName, reportFilePath);
			}else{
				System.out.println("[MESSAGE] No testcase (" + testCasePath + ") found to execute");
			}
		}
		catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}


	public static void runUI(String[] args) {
		launch(args);
	}



}
