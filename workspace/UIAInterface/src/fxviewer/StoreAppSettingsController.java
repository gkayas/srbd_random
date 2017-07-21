package fxviewer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StoreAppSettingsController implements Initializable{

	@FXML
	private Button closeBtn;

	@FXML
	private Button browseResultPathBtn;

	@FXML
	private TextField reportPathTxt;

	@FXML
	private Button browseArchBtn;

	@FXML
	private TextField archTxt;

	@FXML
	private ComboBox<String> expolatoryModeCombo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConfigUtil.readUIAToolConfig();

		ObservableList<String> optionsExpolatoryMode =
			    FXCollections.observableArrayList(
			        "Automatic",
			        "Manual"
		);
		expolatoryModeCombo.setItems(optionsExpolatoryMode);

		reportPathTxt.setText(ConfigUtil.getValue(Constants.REPORT_PATH));
		expolatoryModeCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.EXPLORATORY_MODE));
	}

	public void browseBtnClicked(ActionEvent event) {
		if(event.getSource().equals(browseResultPathBtn)) {


			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Select Report Directory");
			File defaultDirectory = new File("./reports");
			chooser.setInitialDirectory(defaultDirectory);
			File selectedDirectory = chooser.showDialog(null);

			if (selectedDirectory != null){
				reportPathTxt.setText(selectedDirectory.getAbsolutePath());
			}


//			FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("Select Report File");
//			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("report files (*.xls)", "*.xls");
//			fileChooser.getExtensionFilters().add(extFilter);
//			File reportDirectory = new File("./reports");
//
//			if (!reportDirectory.exists())
//				reportDirectory.mkdir();
//
//			fileChooser.setInitialFileName("report.xls");
//			fileChooser.setInitialDirectory(reportDirectory);
//			File selectedFile = fileChooser.showSaveDialog(null);
//
//			if (selectedFile != null){
//				String reportFilePath = selectedFile.getAbsolutePath();
//				if(!reportFilePath.contains(".xls")) {
//					reportFilePath += ".xls";
//				}
//
//				reportPathTxt.setText(reportFilePath);
//			}
		}
	}

	public void closeBtnClicked(ActionEvent event) {
	    Stage stage = (Stage) closeBtn.getScene().getWindow();
	    stage.close();
	}

	public void saveBtnClicked(ActionEvent event) {
		ConfigUtil.setValue(Constants.REPORT_PATH, reportPathTxt.getText());
		ConfigUtil.setValue(Constants.EXPLORATORY_MODE, expolatoryModeCombo.getSelectionModel().getSelectedItem());

		ConfigUtil.writeIntoUIAToolConfig();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText("UIAutomator configuration saved successfully");
		alert.showAndWait();
	}

}
