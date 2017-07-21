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

public class SampleAppSettingsController implements Initializable{

	@FXML
	private Button saveBtn;

	@FXML
	private Button closeBtn;

	@FXML
	private Button browseSDKPathBtn;

	@FXML
	private Button browseReprotPathBtn;

	@FXML
	private Button browseKeyPathBtn;

	@FXML
	private TextField sdkPathTxt;

	@FXML
	private TextField reportPathTxt;

	@FXML
	private TextField sampleServerTxt;

	@FXML
	private TextField userNameTxt;

	@FXML
	private TextField userPassTxt;

	@FXML
	private TextField keyPathTxt;

	@FXML
	private TextField keyNameTxt;

	@FXML
	private TextField keyPassTxt;

	@FXML
	private ComboBox<String> archCombo;

	@FXML
	private ComboBox<String> profileCombo;

	@FXML
	private ComboBox<String> compilerCombo;

	@FXML
	private ComboBox<String> buildConfigCombo;

	@FXML
	private ComboBox<String> expolatoryModeCombo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ConfigUtil.readDeviceConfig();
		ConfigUtil.readUIAToolConfig();
		ObservableList<String> optionsArch =
			    FXCollections.observableArrayList(
			        "x86",
			        "x86_64",
			        "arm",
			        "aarch64"
		);
		archCombo.setItems(optionsArch);

		ObservableList<String> optionsProfile =
			    FXCollections.observableArrayList(
			        "Mobile",
			        "Wearable",
			        "TV"
		);
		profileCombo.setItems(optionsProfile);

		ObservableList<String> optionsCompiler =
			    FXCollections.observableArrayList(
			        "gcc",
			        "llvm"
		);
		compilerCombo.setItems(optionsCompiler);

		ObservableList<String> optionsBuildConfig =
			    FXCollections.observableArrayList(
			        "Debug",
			        "Release"
		);
		buildConfigCombo.setItems(optionsBuildConfig);


		ObservableList<String> optionsExpolatoryMode =
			    FXCollections.observableArrayList(
			        "Automatic",
			        "Manual"
		);
		expolatoryModeCombo.setItems(optionsExpolatoryMode);

		sdkPathTxt.setText(ConfigUtil.getValue(Constants.SDK_PATH));
		reportPathTxt.setText(ConfigUtil.getValue(Constants.REPORT_PATH));
		archCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.ARCH));
		profileCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.SAMPLE_PROFILE));
		compilerCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.COMPILER));
		buildConfigCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.BUILD_CONFIG));
		sampleServerTxt.setText(ConfigUtil.getValue(Constants.SAMPLE_SERVER));
		userNameTxt.setText(ConfigUtil.getValue(Constants.USER));
		userPassTxt.setText(ConfigUtil.getValue(Constants.USER_PWD));
		keyPathTxt.setText(ConfigUtil.getValue(Constants.KEY_PATH));
		keyNameTxt.setText(ConfigUtil.getValue(Constants.KEY_NAME));
		keyPassTxt.setText(ConfigUtil.getValue(Constants.KEY_PWD));
		expolatoryModeCombo.getSelectionModel().select(ConfigUtil.getValue(Constants.EXPLORATORY_MODE));

		String path = keyPathTxt.getText();
		if(path != null){
			if(!new File(path).exists()){
				keyNameTxt.setDisable(true);
				keyPassTxt.setDisable(true);
			}

		}else{
			keyNameTxt.setDisable(true);
			keyPassTxt.setDisable(true);
		}
	}

	public void browseBtnClicked(ActionEvent event) {

		if(event.getSource().equals(browseReprotPathBtn)) {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Select Report Directory");
			File defaultDirectory = new File("./reports");
			chooser.setInitialDirectory(defaultDirectory);
			File selectedDirectory = chooser.showDialog(null);

			if (selectedDirectory != null){
				reportPathTxt.setText(selectedDirectory.getAbsolutePath());
			}

		} else if(event.getSource().equals(browseSDKPathBtn)) {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Select Tizen SDK/Studio Path");
			File defaultDirectory = new File(System.getProperty("user.home"));
			chooser.setInitialDirectory(defaultDirectory);
			File selectedDirectory = chooser.showDialog(null);

			if (selectedDirectory != null){
				sdkPathTxt.setText(selectedDirectory.getAbsolutePath());
			}
		} else if(event.getSource().equals(browseKeyPathBtn)) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select author key");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("key (*.p12)", "*.p12");
			fileChooser.getExtensionFilters().add(extFilter);
			File defaultDirectory = new File(System.getProperty("user.home"));
			fileChooser.setInitialDirectory(defaultDirectory);
			File selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null){
				keyPathTxt.setText(selectedFile.getAbsolutePath());
				keyNameTxt.setDisable(false);
				keyPassTxt.setDisable(false);
			}
		}
	}

	public void closeBtnClicked(ActionEvent event) {
	    Stage stage = (Stage) closeBtn.getScene().getWindow();
	    stage.close();
	}

	public void saveBtnClicked(ActionEvent event) {
		ConfigUtil.setValue(Constants.SDK_PATH, sdkPathTxt.getText());
		ConfigUtil.setValue(Constants.REPORT_PATH, reportPathTxt.getText());
		ConfigUtil.setValue(Constants.ARCH, archCombo.getSelectionModel().getSelectedItem());
		ConfigUtil.setValue(Constants.SAMPLE_PROFILE, profileCombo.getSelectionModel().getSelectedItem());
		ConfigUtil.setValue(Constants.COMPILER, compilerCombo.getSelectionModel().getSelectedItem());
		ConfigUtil.setValue(Constants.BUILD_CONFIG, buildConfigCombo.getSelectionModel().getSelectedItem());
		ConfigUtil.setValue(Constants.SAMPLE_SERVER, sampleServerTxt.getText());
		ConfigUtil.setValue(Constants.USER, userNameTxt.getText() + "");
		ConfigUtil.setValue(Constants.USER_PWD, userPassTxt.getText() + "");
		ConfigUtil.setValue(Constants.KEY_PATH, keyPathTxt.getText() + "");
		ConfigUtil.setValue(Constants.KEY_NAME, keyNameTxt.getText() + "");
		ConfigUtil.setValue(Constants.KEY_PWD, keyPassTxt.getText() + "");
		ConfigUtil.setValue(Constants.EXPLORATORY_MODE, expolatoryModeCombo.getSelectionModel().getSelectedItem());

		ConfigUtil.writeIntoDeviceConfig();
		ConfigUtil.writeIntoUIAToolConfig();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText("UIAutomator for sampleapp configuration saved successfully");
		alert.showAndWait();
	}

}
