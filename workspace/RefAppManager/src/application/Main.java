package application;
	
import java.io.IOException;
import java.io.PrintStream;

import base.AFEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import refapp.ReportUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	PrintStream console;
	@Override
	public void start(Stage stage) throws IOException {
		
		AFEngine.initialize();
		ReportUtils.initialize();
		 console = System.out;
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
	}
	
//	@Override
//	public void init() {
//		try {
//			super.init();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//	}
	
	
	@Override
	public void stop() {
		
//		console.close();
		System.setOut(console);
		AFEngine.deinitialize();
		//System.exit(0);
		
		try {
			super.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
