import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RcpttRunnerController  implements Initializable, EventHandler<ActionEvent>/*,  ,ChangeListener*/{

	@FXML
	private TableView<TableModel> tcTable;

	@FXML
	private ComboBox<String> bvtCombo;
	
	@FXML
	private TableColumn<TableModel, Boolean> checkBox;

	@FXML
	private TableColumn<TableModel, String> name;

	@FXML
	private TableColumn<TableModel, String> count;

	@FXML
	private CheckBox platform30Check;

	@FXML
	private CheckBox platform24Check;

	@FXML
	private CheckBox platform23Check;
	
	@FXML
	private CheckBox platform232Check;

	@FXML
	private CheckBox platform231Check;

	@FXML
	private CheckBox platformCommonCheck;

	@FXML
	private CheckBox profileMobileCheck;
	
	@FXML
	private CheckBox profileWearbleCheck;
	
	@FXML
	private CheckBox selectAllCheck;

	@FXML
	private Button loadBtn;

	@FXML
	private Button saveBtn;

	@FXML
	private Button refreshBtn;
	
	@FXML
	private Button settingsBtn;

	@FXML
	private TextArea console;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		count.setStyle("-fx-alignment: CENTER;");
		
		ObservableList<String> optionsArch = 
				FXCollections.observableArrayList(
						"BVT",
						"Non-BVT",
						"ECP-CLI"
						);
		bvtCombo.setItems(optionsArch);
		bvtCombo.getSelectionModel().select("BVT");
		
		saveBtn.setDisable(true);

		selectAllCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox chk = (CheckBox) event.getSource();
				if (chk instanceof CheckBox) {
					boolean isSelected = chk.selectedProperty().getValue();
					checkAllTableRow(isSelected);
				}
			}
		});
		
		platform30Check.setSelected(true);
		platform24Check.setSelected(true);
		platform23Check.setSelected(true);
		platform232Check.setSelected(true);
		platform231Check.setSelected(true);
		platformCommonCheck.setSelected(true);
		profileMobileCheck.setSelected(true);
		profileWearbleCheck.setSelected(true);
				
		name.setCellValueFactory(new PropertyValueFactory<TableModel, String>("name"));
		count.setCellValueFactory(new PropertyValueFactory<TableModel, String>("count"));

		checkBox.setCellValueFactory(new PropertyValueFactory<TableModel, Boolean>("checkBox"));		
		checkBox.setCellFactory(new Callback<TableColumn<TableModel, Boolean>, TableCell<TableModel, Boolean>>() {

            public TableCell<TableModel, Boolean> call(TableColumn<TableModel, Boolean> p) {
            	CheckBoxTableCell< TableModel, Boolean> cell = new CheckBoxTableCell<TableModel, Boolean>();
            	cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						TableModel app = (TableModel)cell.getTableRow().getItem();
						
						if(!app.checkBoxProperty().getValue()) {
							selectAllCheck.setSelected(false);							
						} else {
							evaluateSelectAllCheckValue();
						}
					}
				});
                return cell;
            }
        });
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() instanceof CheckBox) {
			//CheckBox chk = (CheckBox) event.getSource();			
		}
	}	
	
	public void loadBtnClicked() throws FileNotFoundException {		
		removeAllFromTcTable();
		
		String project = bvtCombo.getSelectionModel().getSelectedItem();
		ArrayList<CSVRow> csvRowList = Utils.readMasterSuiteList(project);
		
		if(csvRowList.size() <= 0) {
			System.out.println(project + " - suitelist is not availble...");
			return;
		}
			
		ArrayList<TableModel> tableModleList = new ArrayList<TableModel>();
		for (CSVRow csvRow : csvRowList) {
			TableModel model = new TableModel();
			model.name.setValue(csvRow.getModule());
		
			if(tableModleList.contains(model)) {
				TableModel target = (tableModleList.get(tableModleList.indexOf(model)));
				int count = Integer.valueOf(target.count.getValue())+1;
				target.count.setValue(""+count);
				target.getCsvRowList().add(csvRow);
			} else {
				model.count.setValue(""+1);
				model.getCsvRowList().add(csvRow);
				tableModleList.add(model);
			}
		}
		
		addAllToTcTable(tableModleList);
		selectAllCheck.setSelected(true);
		saveBtn.setDisable(false);
	}

	public void saveBtnClicked() {
		List<TableModel>rows = getTableRows();
		
		String project = bvtCombo.getSelectionModel().getSelectedItem();
		ArrayList<CSVRow> csvRowList = Utils.readMasterSuiteList(project);
		
		if(csvRowList.size() <= 0) {
			System.out.println(project + " - suitelist is not availble...");
			return;
		}
		
		try {
			boolean flag = false;
			PrintWriter pw = new PrintWriter(new File("suitelists"));
			
			ArrayList<String> selectedModule = new ArrayList<String>();

			for (TableModel tableModel : rows) {
				if(tableModel.checkBoxProperty().getValue()) {
					flag = true;
					selectedModule.add(tableModel.name.getValue());
					
//					ArrayList<CSVRow>rowList = tableModel.getCsvRowList();					
//					for (CSVRow csvRow : rowList) {
//						if(isPlatformChecked(csvRow.getPlatform()) && isProfileChecked(csvRow.getProfile())) {
//							pw.append(csvRow.getTcmID()+","+csvRow.getTcID()+","+csvRow.getModule()+","+csvRow.getSuite()+","+csvRow.getPlatform()+","+csvRow.getProfile()+"\n");
//						}
//					}					
				}
			}
			
			for (CSVRow csvRow : csvRowList) {
				if(selectedModule.contains(csvRow.getModule()) &&  isPlatformChecked(csvRow.getPlatform()) && isProfileChecked(csvRow.getProfile())) {
					pw.append(csvRow.getTcmID()+","+csvRow.getTcID()+","+csvRow.getModule()+","+csvRow.getSuite()+","+csvRow.getPlatform()+","+csvRow.getProfile()+"\n");
				}
			}
			
			
			pw.flush();
			pw.close();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK);
			if(flag) {
				alert.setHeaderText("All selected contents saved successfully");	
			} else {
				alert.setHeaderText("No content selected. Please select content and try to save.");
			}			
			alert.showAndWait();
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
	}

	public void refreshBtnClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("All content will be removed from table. Do you want to refresh?");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			removeAllFromTcTable();
			selectAllCheck.setSelected(false);
			saveBtn.setDisable(true);
		}
	}

	public void settingsBtnClicked() {
		try {
			Stage settingsStage = new Stage();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RcpttRunnerSettingsScene.fxml")));
			settingsStage.setScene(scene);
			settingsStage.setTitle("SDKAutomator Setting Window");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			settingsStage.initModality(Modality.APPLICATION_MODAL);
			settingsStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public TextArea getConsole() {
		return console;
	}

	public void clearConsole() {
		Platform.runLater(() -> console.clear());
	}	

	public List<TableModel> getTableRows() {
		return tcTable.getItems();
	}
	
	public void addToTcTable(TableModel tc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tcTable.getItems().add(tc);
			}
		});
	}

	public void addAllToTcTable(List<TableModel> appList) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (TableModel app : appList) {
					if(!getTableRows().contains(app)) {
						addToTcTable(app);
					}
				}
			}
		});
	}
	
	public void removeAllFromTcTable() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<TableModel> data = tcTable.getItems();				
				data.remove(0, data.size());
			}
		});
	} 

	public void resetTctable(List<TableModel> newList) {
		removeAllFromTcTable();
		addAllToTcTable(newList);
	}

	private void checkAllTableRow(Boolean value) {
		List<TableModel> rows = getTableRows();
		for (TableModel appInfo : rows) {
			appInfo.setCheckBox(value);
		}		
	}

	private void evaluateSelectAllCheckValue() {
		List<TableModel> rows = getTableRows();
		boolean isChecked = true;
		for (TableModel appInfo : rows) {
			if(!appInfo.checkBoxProperty().getValue()){
				isChecked = false;
				break;
			}
		}
		selectAllCheck.setSelected(isChecked);
	}
	
	private boolean isPlatformChecked(String platform) {
		if(platform.equals("3.0")) {
			return platform30Check.isSelected() ? true : false;
		} else if(platform.equals("2.4")) {
			return platform24Check.isSelected() ? true : false;
		} else if(platform.equals("2.3")) {
			return platform23Check.isSelected() ? true : false;
		} else if(platform.equals("2.3.2")) {
			return platform232Check.isSelected() ? true : false;
		} else if(platform.equals("2.3.1")) {
			return platform231Check.isSelected() ? true : false;
		} else if(platform.equals("Common")) {
			return platformCommonCheck.isSelected() ? true : false;
		}
		
		return false;
	}

	private boolean isProfileChecked(String profile) {
		if(profile.equalsIgnoreCase("Mobile")) {
			return profileMobileCheck.isSelected() ? true : false;
		} else if(profile.equalsIgnoreCase("Wearable")) {
			return profileWearbleCheck.isSelected() ? true : false;
		} 
		
		return false;
	}	
}
