package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import refapp.ReportUtils;
import refapp.SuiteRunner;
import refapp.TCRunner;
import refapp.TestInfo;


public class MainController implements Initializable, ChangeListener{

	public static final String SUITE_FILES_FOLDER = "/home/kayes/workspace/RefAppManager/res/files/xml/";

	@FXML
	TreeView<String> suiteTree;

	@FXML
	private TableView<TestInfo> tcTable;

	@FXML
	private TableColumn<TestInfo, String> testName;

	@FXML
	private TableColumn<TestInfo, String> tcStatus;

	@FXML
	private TableColumn<TestInfo, String> tcRemark;

	@FXML
	private Button runBtn;

	@FXML
	private Button rerunBtn;

	@FXML
	private Button runsingleBtn;

	@FXML
	private TextArea console;
	//
	//	ObservableList<Testcase> data = FXCollections.observableArrayList(
	//			new Testcase("Suite1", "Tc1", "nan", "OKay here are some remarks."),
	//			new Testcase("Suite1", "Tc2", "nan", "OKay here are some remarks."),
	//			new Testcase("Suite1", "Tc3", "nan", "OKay here are some remarks.")
	//			);


	public void runBtnClicked() {
		clearConsole();
		TCRunner runner = SuiteRunner.getRunner();
		new TCRunTask(runner, this).execute();
	}

	public TextArea getConsole() {
		return console;
	}

	public void rerunBtnClicked() {
		clearConsole();
		TCRunner runner = SuiteRunner.getRunner();
		new TCReRunTask(runner, this).execute();
	}

	public void runsingleBtnClicked() {
		clearConsole();
		TCRunner runner = SuiteRunner.getRunner();
		TestInfo selectedTc =  tcTable.getSelectionModel().getSelectedItem();
		System.out.println(selectedTc.getTcName());
		new TCRunSingle(runner, selectedTc.getTcName()).execute();;
	}

	public void listFilesForFolder(final File folder, TreeItem<String> root) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, root);
			} else {
				// System.out.println(fileEntry.getName());
				TreeItem<String> item = new TreeItem<>(fileEntry.getName().split("\\.")[0]);
				root.getChildren().add(item);

			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Redirecting sysout to textArea
		
		if(console == null) System.out.println("Console null");
		CustomConsole customConsole = new CustomConsole(console);
		PrintStream ps = new PrintStream(customConsole);
		System.setOut(ps);
		System.setErr(ps);

		TreeItem<String> treeRoot = new TreeItem<>("Menu Tree");
		listFilesForFolder(new File(SUITE_FILES_FOLDER), treeRoot);

		suiteTree.getSelectionModel().selectedItemProperty().addListener(this);
		treeRoot.setExpanded(true);

		suiteTree.setRoot(treeRoot);

		testName.setCellValueFactory(new PropertyValueFactory<TestInfo, String>("testName"));

		tcStatus.setCellValueFactory(new PropertyValueFactory<TestInfo, String>("tcStatus"));
		
		tcRemark.setCellValueFactory(new PropertyValueFactory<TestInfo, String>("tcRemark"));
		
	}

	//	public void appendTextToConsole(String tag, String msg) {
	//
	//		Platform.runLater(() -> consoleTextArea.appendText(tag+" "+ msg+"\n"));		
	//		//		new ConsoleUpdater(console, tag+" "+ msg+"\n").execute();
	//
	//	}

	public void clearConsole() {

		Platform.runLater(() -> console.clear());
	}

	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {

		TreeItem<String> selectedItem = (TreeItem<String>) newValue;

		if(selectedItem.getValue().equals("Menu Tree")) {
			return;
		}

		TCRunner runner = null;
		try {
			runner = SuiteRunner.getTCRunner(selectedItem.getValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<TestInfo> tcList = runner.getTestInfo();
		//		removeAllFromTcTable();
		//		addAllToTcTable(tcList);
		resetTctable(tcList);
	}

	public void addToTcTable(TestInfo tc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tcTable.getItems().add(tc);

			}
		});

	}

	public void addAllToTcTable(List<TestInfo> tcList) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (TestInfo testcase : tcList) {
					addToTcTable(testcase);
				}
			}
		});
	}

	public void removeAllFromTcTable() {


		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ObservableList<TestInfo> data = tcTable.getItems();				
				data.remove(0, data.size());

			}
		});


	} 

	public void resetTctable(List<TestInfo> newList) {

		removeAllFromTcTable();
		addAllToTcTable(newList);
	}

}
