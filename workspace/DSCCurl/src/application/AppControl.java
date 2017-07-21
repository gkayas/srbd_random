package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import models.*;
import utils.HTMLParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class AppControl implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private TableView<StockInfo> tcTable;
	
	@FXML
	private CheckBox selectAllCheck;
	
	@FXML
	private TableColumn<StockInfo, Boolean> checkBox;

	@FXML
	private TableColumn<StockInfo, String> stockname;

	@FXML
	private TableColumn<StockInfo, String> currentPrice;

	@FXML
	private TableColumn<StockInfo, String> changeBdt;

	@FXML
	private TableColumn<StockInfo, String> changePer;
	
	@FXML
	private ComboBox<String> refreshTimeCombo;
	
	@FXML
	private Button saveTimeoutBtn;

	@FXML
	private Button saveBtn;

	@FXML
	private Button refreshBtn;

	@FXML
	private Button loadBtn;

	@FXML
	private Button settingsBtn;
	
	public static Timer timer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stockname.setStyle("-fx-alignment: CENTER;");
		currentPrice.setStyle("-fx-alignment: CENTER;");
		changeBdt.setStyle("-fx-alignment: CENTER;");
		changePer.setStyle("-fx-alignment: CENTER;");
		
		stockname.setCellValueFactory(new PropertyValueFactory<StockInfo, String>("comName"));
		currentPrice.setCellValueFactory(new PropertyValueFactory<StockInfo, String>("lastPrice"));
		changeBdt.setCellValueFactory(new PropertyValueFactory<StockInfo, String>("changeInBdt"));
		changePer.setCellValueFactory(new PropertyValueFactory<StockInfo, String>("changeInPer"));
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
		
		checkBox.setCellValueFactory(new PropertyValueFactory<StockInfo, Boolean>("checkBox"));
		checkBox.setCellFactory(new Callback<TableColumn<StockInfo, Boolean>, TableCell<StockInfo, Boolean>>() {

			public TableCell<StockInfo, Boolean> call(TableColumn<StockInfo, Boolean> p) {
				CheckBoxTableCell<StockInfo, Boolean> cell = new CheckBoxTableCell<StockInfo, Boolean>();
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						StockInfo app = (StockInfo)cell.getTableRow().getItem();

						if(!app.isSelected()) {
							selectAllCheck.setSelected(false);
							
						} else {
							evaluateSelectAllCheckValue();
							
						}
					}

				});
				return cell;
			}
		});
		ObservableList<String> optionsTimeout = 
			    FXCollections.observableArrayList(
			        "5 Sec",
			        "10 Sec",
			        "20 Sec",
			        "30 Sec",
			        "60 Sec",
			        "2 min"
		);
		refreshTimeCombo.setItems(optionsTimeout);
		refreshTimeCombo.getSelectionModel().select(2);
		
		timer = new Timer();
		timer.scheduleAtFixedRate( new TimerTask() {
			
			@Override
			public void run() {
//				HTMLParser.test();
				//addAllToTcTable(HTMLParser.getData());
				resetTctable(HTMLParser.getData());
			}
		},
		0, 20000);
		
	}

	public void addToTcTable(StockInfo tc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tcTable.getItems().add(tc);
			}
		});
	}

	public void addAllToTcTable(List<StockInfo> appList, List<StockInfo> selectStocks) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (StockInfo app : appList) {
					if(!getTableRows().contains(app)) {
						if(selectStocks.contains(app)) {
							app.setSelected(true);
						}
						
						addToTcTable(app);
					}
				}
			}
		});
	}
	
	public List<StockInfo> getTableRows() {
		return tcTable.getItems();
	}
	
	private void checkAllTableRow(Boolean value) {
		List<StockInfo> rows = getTableRows();
		for (StockInfo appInfo : rows) {
			appInfo.setCheckBox(value);
		}		
	}

	private void evaluateSelectAllCheckValue() {
		List<StockInfo> rows = getTableRows();
		boolean isChecked = true;
		for (StockInfo appInfo : rows) {
			if(!appInfo.isSelected()){
				isChecked = false;
				break;
			}
		}

		selectAllCheck.setSelected(isChecked);
	}

	private boolean isAllRowUnchecked() {
		List<StockInfo> rows = getTableRows();
		boolean unchecked = true;
		for (StockInfo appInfo : rows) {
			if(appInfo.isSelected()){
				unchecked = false;
				break;
			}
		}
		return unchecked;
	}
	
	public ArrayList<StockInfo> removeAllFromTcTable() {
		ArrayList<StockInfo> selectedStocks = new ArrayList<StockInfo>();
		List<StockInfo> stocks = getTableRows();
		for (StockInfo stockInfo : stocks) {
			if(stockInfo.isSelected()) {
				selectedStocks.add(stockInfo);
			}
		}
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<StockInfo> data = tcTable.getItems();				
				data.remove(0, data.size());

			}
		});
		return selectedStocks;
	} 
	
	public void resetTctable(List<StockInfo> newList) {

		ArrayList<StockInfo> selectStocks = removeAllFromTcTable();
		System.out.println("Selected stock count: "+ selectStocks.size());
		addAllToTcTable(newList, selectStocks);
	}
	
	public void saveBtnClicked() throws IOException {
		System.out.println("Save buttin clicked");
		List<StockInfo> stockList = getTableRows();
		PrintWriter pw = new PrintWriter(new File("stock_info.txt"));
		for (StockInfo stockInfo : stockList) {
			if(stockInfo.isSelected()) {
				pw.append(stockInfo.toString()+"\n");
			}
			
		}
		pw.flush();
		pw.close();
	}
	
	public void refreshBtnClicked(){
		System.out.println("refreash button clicked.");
		resetTctable(HTMLParser.getData());
		
	}
	
public void saveTimeoutBtnClicked(){
		System.out.println("save timer button clicked.");
		timer.cancel();
		timer = new Timer();
		System.out.println("timer canceled");
		String time = refreshTimeCombo.getSelectionModel().getSelectedItem();
		int timeout = 0;
		 if(time.equals("5 Sec")) {
			 timeout = 5000;
		 } else if(time.equals("10 Sec")) {
			 timeout = 10000;
		 } else if(time.equals("20 Sec")) {
			 timeout = 20000;
		 } else if(time.equals("30 Sec")) {
			 timeout = 30000;
		 } else if(time.equals("60 Sec")) {
			 timeout = 60000;
		 }else if(time.equals("2 min")) {
			 timeout = 120000;
		 }
		 System.out.println("New timeout: " + timeout);
		 timer.scheduleAtFixedRate( new TimerTask() {
				
				@Override
				public void run() {
					resetTctable(HTMLParser.getData());
				}
			},
			0, timeout);
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		
	}
}
