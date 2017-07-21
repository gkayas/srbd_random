package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import utils.*;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Parent root;
		try {

			root = FXMLLoader.load(getClass().getResource("AppScene.fxml"));
				
			
			Scene scene = new Scene(root);
	        stage.setTitle("DSE Stock Information [v0.1]");
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();  
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {

		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    Platform.exit();
	    System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
