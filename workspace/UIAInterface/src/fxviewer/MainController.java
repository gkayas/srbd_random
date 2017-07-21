package fxviewer;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javax.swing.text.Position;

import com.sun.javafx.application.HostServicesDelegate;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private Tab storeAppTab;

	@FXML
	private Tab sampleAppTab;

	@FXML
	private Tab commonUseTab;

	@FXML
	private Tab helpTab;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			storeAppTab.setContent((Node) FXMLLoader.load(getClass().getResource("StoreAppScene.fxml")));
			storeAppTab.setOnSelectionChanged(new EventHandler<Event>() {
	            @Override
	            public void handle(Event t) {
	                if (storeAppTab.isSelected()) {
	                	Stage stage = (Stage)storeAppTab.getTabPane().getScene().getWindow();
	            		stage.setTitle("UI Automator [v0.2] [StoreApp Automator]");
	                }
	            }
	        });

			sampleAppTab.setContent((Node) FXMLLoader.load(getClass().getResource("SampleAppScene.fxml")));
			sampleAppTab.setOnSelectionChanged(new EventHandler<Event>() {
	            @Override
	            public void handle(Event t) {
	                if (sampleAppTab.isSelected()) {
	                	Stage stage = (Stage)sampleAppTab.getTabPane().getScene().getWindow();
	            		stage.setTitle("UI Automator [v0.2] [SampleApp Automator]");
	                }
	            }
	        });

			commonUseTab.setContent((Node) FXMLLoader.load(getClass().getResource("CommonUseScene.fxml")));
			commonUseTab.setOnSelectionChanged(new EventHandler<Event>() {
	            @Override
	            public void handle(Event t) {
	                if (commonUseTab.isSelected()) {
	                	Stage stage = (Stage)commonUseTab.getTabPane().getScene().getWindow();
	            		stage.setTitle("UI Automator [v0.2] [Common Use]");
	                }
	            }
	        });

			helpTab.setOnSelectionChanged(new EventHandler<Event>() {
	            @Override
	            public void handle(Event t) {
	            	Stage stage = (Stage)helpTab.getTabPane().getScene().getWindow();
	            	stage.setTitle("UI Automator [v0.2] [Help]");
	            }
	        });


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void hyperlinkClicked()
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

                    Desktop.getDesktop().browse(new URI("http://suprem.sec.samsung.net/confluence/display/TUIAC/%5BSQE%5D+UI+Automator"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }


			}
		}).start();
	}

//	private void setConsole(TextArea console) {
//		if(console == null)
//			System.out.println("[ERROR] Console is null");
//
//		CustomConsole customConsole = new CustomConsole(console);
//		PrintStream ps = new PrintStream(customConsole);
//
//		System.setOut(ps);
//		System.setErr(ps);
//	}
}
