
package fxviewer;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import uirunner.JsoupXMLParser;
import uirunner.ReportUtils;
import uirunner.SampleAppInfo;
import uirunner.StoreAppInfo;
import uirunner.TCResult;

public class SampleAppController extends AppController implements Initializable, EventHandler<ActionEvent> /*,ChangeListener*/{
	@FXML
	private Label appResultLbl;

	@FXML
	private AnchorPane sampleAppAnchor;

	@FXML
	private TableView<SampleAppInfo> tcTable;

	@FXML
	private TableColumn<SampleAppInfo, Boolean> checkBox;

	@FXML
	private TableColumn<SampleAppInfo, String> appType;

	@FXML
	private TableColumn<SampleAppInfo, String> profile;

	@FXML
	private TableColumn<SampleAppInfo, String> requiredVersion;

	@FXML
	private TableColumn<SampleAppInfo, String> appName;

	@FXML
	private TableColumn<SampleAppInfo, String> result;

	@FXML
	private TableColumn<SampleAppInfo, String> build;

	@FXML
	private TableColumn<SampleAppInfo, String> packageing;

	@FXML
	private TableColumn<SampleAppInfo, String> install;

	@FXML
	private TableColumn<SampleAppInfo, String> launch;

	@FXML
	private TableColumn<SampleAppInfo, String> exploratory;

	@FXML
	private TableColumn<SampleAppInfo, String> close;

	@FXML
	private TableColumn<SampleAppInfo, String> uninstall;

	@FXML
	private TableColumn<SampleAppInfo, String> crash;


	@FXML
	private CheckBox buildCheck;

	@FXML
	private CheckBox packageCheck;

	@FXML
	private CheckBox installCheck;

	@FXML
	private CheckBox launchCheck;

	@FXML
	private CheckBox exploratoryCheck;

	@FXML
	private CheckBox closeCheck;

	@FXML
	private CheckBox uninstallCheck;

	@FXML
	private CheckBox crashCheck;

	@FXML
	private CheckBox selectAllCheck;

	@FXML
	private Button createAppList;

	@FXML
	private Button browseBtn;

	@FXML
	private Button exploratoryCodeBtn;

	@FXML
	private Button runBtn;

	@FXML
	private Button rerunBtn;

	@FXML
	private Button exportReportBtn;

	@FXML
	private Button refreshBtn;

	@FXML
	private Button runSuspendBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private TextArea console;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//setConsole();
		ConfigUtil.readUIAToolConfig();
		ConfigUtil.readDeviceConfig();
		appResultLbl.setStyle("-fx-alignment: CENTER;");
		appResultLbl.setText("");
		result.setStyle("-fx-alignment: CENTER;");
		build.setStyle("-fx-alignment: CENTER;");
		packageing.setStyle("-fx-alignment: CENTER;");
		install.setStyle("-fx-alignment: CENTER;");
		launch.setStyle("-fx-alignment: CENTER;");
		exploratory.setStyle("-fx-alignment: CENTER;");
		close.setStyle("-fx-alignment: CENTER;");
		uninstall.setStyle("-fx-alignment: CENTER;");
		crash.setStyle("-fx-alignment: CENTER;");

		buildCheck.setOnAction(this);
		packageCheck.setOnAction(this);
		installCheck.setOnAction(this);
		launchCheck.setOnAction(this);
		exploratoryCheck.setOnAction(this);
		uninstallCheck.setOnAction(this);
		closeCheck.setOnAction(this);
		crashCheck.setOnAction(this);

		selectAllCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox chk = (CheckBox) event.getSource();
				if (chk instanceof CheckBox) {
					boolean isSelected = chk.selectedProperty().getValue();
					checkAllTableRow(isSelected);
					runBtnCheck();
				}
			}

		});

//		buildCheck.setSelected(true);
//		packageCheck.setSelected(true);
//		installCheck.setSelected(true);
//		launchCheck.setSelected(true);
//		exploratoryCheck.setSelected(true);
//		uninstallCheck.setSelected(true);
//		closeCheck.setSelected(true);
//		crashCheck.setSelected(true);

		appType.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("appType"));

		profile.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("profile"));

		requiredVersion.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("requiredVersion"));

		appName.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("appName"));

		result.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("result"));

		build.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("build"));

		packageing.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("packageing"));

		install.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("install"));

		launch.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("launch"));

		exploratory.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("exploratory"));

		close.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("close"));

		uninstall.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("uninstall"));

		crash.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, String>("crash"));

		checkBox.setCellValueFactory(new PropertyValueFactory<SampleAppInfo, Boolean>("checkBox"));

		checkBox.setCellFactory(new Callback<TableColumn<SampleAppInfo, Boolean>, TableCell<SampleAppInfo, Boolean>>() {

			public TableCell<SampleAppInfo, Boolean> call(TableColumn<SampleAppInfo, Boolean> p) {

				CheckBoxTableCell<SampleAppInfo, Boolean> cell = new CheckBoxTableCell<SampleAppInfo, Boolean>();
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						SampleAppInfo app = (SampleAppInfo)cell.getTableRow().getItem();
						if(!app.getCheckBox()) {
							selectAllCheck.setSelected(false);
							runBtnCheck();
						} else {
							evaluateSelectAllCheckValue();
							runBtnCheck();
						}
					}

				});
				return cell;
			}
		});


		tcTable.setOnMouseClicked( new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				 if ( event.getClickCount() == 2  ) {
					 SampleAppInfo selectedAppInfo = tcTable.getSelectionModel().getSelectedItem();
					 try {
						 if(selectedAppInfo != null){
							 showJavaCodeWindow(selectedAppInfo);
						 }else{
							 System.out.println("[MESSAGE] No application found in table.");
						 }
					 } catch (FileNotFoundException e) {
						 System.out.println("[ERROR] " + e.getMessage());
					 }
				}
			}
		});
		runBtn.setDisable(true);
		rerunBtn.setDisable(true);
		refreshBtn.setDisable(true);
		settingsBtn.setDisable(false);
		runSuspendBtn.setDisable(true);
		exportReportBtn.setDisable(true);
		exploratoryCodeBtn.setDisable(true);
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

	@Override
	public void handle(ActionEvent event) {
		setConsole();

		if (event.getSource() instanceof CheckBox) {
			CheckBox chk = (CheckBox) event.getSource();
			if (buildCheck.isSelected()) {
				if(exploratoryCheck.isSelected() || closeCheck.isSelected() || crashCheck.isSelected()){
					packageCheck.setSelected(true);
					installCheck.setSelected(true);
					launchCheck.setSelected(true);
				}else if(launchCheck.isSelected() || uninstallCheck.isSelected()){
					packageCheck.setSelected(true);
					installCheck.setSelected(true);
				}else if(installCheck.isSelected()){
					packageCheck.setSelected(true);
				}
			}else if(packageCheck.isSelected()){
				if(exploratoryCheck.isSelected() || closeCheck.isSelected() || crashCheck.isSelected()){
					installCheck.setSelected(true);
					launchCheck.setSelected(true);
				}else if(launchCheck.isSelected() || uninstallCheck.isSelected()){
					installCheck.setSelected(true);
				}
			}else if(installCheck.isSelected()){
				if(exploratoryCheck.isSelected() || closeCheck.isSelected() || crashCheck.isSelected()){
					launchCheck.setSelected(true);
				}
			}else if(exploratoryCheck.isSelected() || closeCheck.isSelected() || crashCheck.isSelected()){
				launchCheck.setSelected(true);
			}else if(uninstallCheck.isSelected()){
				if(launchCheck.isSelected()){
					closeCheck.setSelected(true);
				}

			}

//			if ("Build".equals(chk.getText())) {
//				if(!chk.selectedProperty().getValue()) {
//					packageCheck.setSelected(false);
//				}
//			}if ("Package".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					buildCheck.setSelected(true);
//				}
//			}else if ("Exploratory".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					installCheck.setSelected(true);
//					launchCheck.setSelected(true);
//				}
//
//			} else if ("Launch".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					installCheck.setSelected(true);
//				} else {
//					exploratoryCheck.setSelected(false);
//					closeCheck.setSelected(false);
//					crashCheck.setSelected(false);
//				}
//			}  else if ("Close".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					installCheck.setSelected(true);
//					launchCheck.setSelected(true);
//				}
//			} else if ("Uninstall".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					installCheck.setSelected(true);
//				}
//			} else if ("Detect Crash".equals(chk.getText())) {
//				if(chk.selectedProperty().getValue()) {
//					installCheck.setSelected(true);
//					launchCheck.setSelected(true);
//				}
//			} else if ("Install".equals(chk.getText())) {
//				if(!chk.selectedProperty().getValue()) {
//					launchCheck.setSelected(false);
//					exploratoryCheck.setSelected(false);
//					closeCheck.setSelected(false);
//					uninstallCheck.setSelected(false);
//					crashCheck.setSelected(false);
//				}
//			}
		}
		runBtnCheck();
	}


	public void createAppListBtnClicked(ActionEvent event) {
		try {
			setConsole();
			clearConsole();
			appResultLbl.setText("");
			checkConfigValues();

			browseBtn.setDisable(true);
			settingsBtn.setDisable(true);

			if(getTableRows().size() > 0) {
				removeAllFromTcTable();
				runBtn.setDisable(true);
				rerunBtn.setDisable(true);
				exportReportBtn.setDisable(true);
				exploratoryCodeBtn.setDisable(true);
				refreshBtn.setDisable(true);
				selectAllCheck.setSelected(false);
			}
			File csvFile = new File(System.getProperty("user.dir") + "/res/app_list/sampleapps_" + ConfigUtil.getValue(Constants.SAMPLE_PROFILE).toLowerCase() + ".csv");
			writeAppList(csvFile);
			System.out.println("[MESSAGE] App list saved in " + csvFile.getAbsolutePath());
			updateTable(csvFile);
		} catch (Exception e) {
			if(ConfigUtil.getValue(Constants.SAMPLE_SERVER).contains(e.getMessage())){
				System.out.println("[ERROR] Please check internet connection or link and credentials.");
			}else{
				System.out.println("[ERROR] " + e.getMessage());
			}
		}
		browseBtn.setDisable(false);
		settingsBtn.setDisable(false);
	}

	private void writeAppList(File csvFile) throws IOException, JSONException {
		ArrayList<SampleApp> sampleAppList = new ArrayList<SampleApp>();
		ArrayList<String> urlList = getAppInfoJsonUrl(ConfigUtil.getValue(Constants.SAMPLE_SERVER),ConfigUtil.getValue(Constants.USER), ConfigUtil.getValue(Constants.USER_PWD));
		for (String link : urlList) {
			sampleAppList.addAll(getAppList(link, ConfigUtil.getValue(Constants.USER), ConfigUtil.getValue(Constants.USER_PWD)));
		}
		csvFile.getParentFile().mkdirs();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csvFile));
		bufferedWriter.write("appTypes,Profile,requiredVersion,sampleName" + System.getProperty("line.separator"));
		for (SampleApp sampleApp : sampleAppList) {
			bufferedWriter.write(sampleApp.getAppType() + "," + sampleApp.getProfile() + "," + sampleApp.getRequiredVersion() + "," + sampleApp.getAppName() + System.getProperty("line.separator"));
		}
		bufferedWriter.close();
	}


//	static String getJsonContent(String link,String user,String pass) throws IOException
//	{
////		 try {
//			 Connection urlConnection = Jsoup.connect(link);
//			 if(!user.isEmpty() & !pass.isEmpty())
//			 {
//				String userCredentials = user+":"+pass;
//				Base64.Encoder encoder = Base64.getEncoder();
//				byte[] b64=encoder.encode(userCredentials.getBytes());
//				String basicAuth = "Basic " + new String(b64);
//				urlConnection.header("Authorization", basicAuth);
//			 }
//
//			 Connection.Response response = urlConnection.timeout(60000).ignoreContentType(true).execute();
//			 return response.body();
//
////		} catch (IOException e) {
////			System.out.println(e.getMessage());
////			return null;
////		}
//	}
//
//	private static String getLink(String webContent, String sampleName, String profileName, String requiredVersion,String key)
//			throws JSONException {
//		JSONObject jsonObject = new JSONObject(webContent);
//    	JSONArray appTypes = jsonObject.getJSONArray("appTypes");
//	    for (int i = 0; i < appTypes.length(); i++) {
//	    	JSONObject content = appTypes.getJSONObject(i);
//    	    JSONArray sampleapps = content.getJSONArray("samples");
//
//    	    for (int j = 0; j < sampleapps.length(); j++) {
//    	    	JSONObject sampleapp =  sampleapps.getJSONObject(j);
//    	    	if(sampleapp.get("sampleName").equals(sampleName)){
//    	    		JSONArray platforms = sampleapp.getJSONArray("platformList");
//            	    for (int k = 0; k < platforms.length(); k++) {
//            	    	JSONObject platform =  platforms.getJSONObject(k);
//            	    	if(platform.get("profileName").toString().toLowerCase().equals(profileName.toLowerCase()) && platform.get("requiredVersion").toString().equals(requiredVersion)){
//            	    		return sampleapp.get(key).toString();
//            	    	}
//    				}
//    	    	}
//			}
//		}
//	    return null;
//	}

	private ArrayList<String> getAppInfoJsonUrl(String link, String userName, String password) throws IOException, JSONException {
		if(!link.contains(".json")){
			link +=  "/" + Constants.SAMPLE_SERVER_JSON_FILE;
		}

		ArrayList<String> links = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject(getJsonContent(link,userName,password));
		JSONArray appTypes = jsonObject.getJSONArray("appTypes");

		for (int i = 0; i < appTypes.length(); i++) {
	    	JSONObject content = appTypes.getJSONObject(i);
	    	links.add(content.getString("metaUrl"));
		}
	    return links;

	}

	private ArrayList<SampleApp> getAppList(String jsonLink, String userName, String password) throws JSONException, IOException {
		ArrayList<SampleApp> appList = new ArrayList<SampleApp>();

		JSONObject jsonObject = new JSONObject(getJsonContent(jsonLink,userName,password));
		JSONArray appTypes = jsonObject.getJSONArray("appTypes");

		for (int i = 0; i < appTypes.length(); i++) {
			//SampleAppInfo sampleappInfo = new SampleAppInfo();
	    	JSONObject content = appTypes.getJSONObject(i);
    	    JSONArray sampleapps = content.getJSONArray("samples");
//    	    String appdefLink = content.getString("defUrl");
//    	    sampleappInfo.setProjectType(appdefLink, userName, password);
    	    for (int j = 0; j < sampleapps.length(); j++) {
    	    	JSONObject sampleapp =  sampleapps.getJSONObject(j);
	    		JSONArray platforms = sampleapp.getJSONArray("platformList");
        	    for (int k = 0; k < platforms.length(); k++) {
        	    	JSONObject platform =  platforms.getJSONObject(k);
        	    	if(platform.getString("profileName").toLowerCase().equals(ConfigUtil.getValue(Constants.SAMPLE_PROFILE).toLowerCase())){
        	    		appList.add(new SampleApp(sampleapp.getString("sampleName"), content.getString("name"), platform.getString("profileName"), platform.getString("requiredVersion")));
        	    	}
				}

			}
		}
	    return appList;
	}

	static String getJsonContent(String link,String user,String pass) throws IOException
	{
		Connection urlConnection = Jsoup.connect(link);
		if(!user.isEmpty() & !pass.isEmpty())
		{
			String userCredentials = user+":"+pass;
			Base64.Encoder encoder = Base64.getEncoder();
			byte[] b64=encoder.encode(userCredentials.getBytes());
			String basicAuth = "Basic " + new String(b64);
			urlConnection.header("Authorization", basicAuth);
		}

		Connection.Response response = urlConnection.timeout(60000).ignoreContentType(true).execute();
		return response.body();
	}

	public void browseBtnClicked() {
		try {
			setConsole();
			clearConsole();
			checkConfigValues();

			FileChooser fc = new FileChooser();
			fc.setTitle("Browse sapp file.");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("sapp file (*.csv)", "*.csv");
			fc.getExtensionFilters().add(extFilter);
			fc.setInitialDirectory(new File("./res/app_list"));
			File selectedFile = fc.showOpenDialog(null);

			if (selectedFile != null) {
				appResultLbl.setText("");
				updateTable(selectedFile);
			}

		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}


	}

	private void checkConfigValues() throws Exception {
		File file = new File(ConfigUtil.getValue(Constants.SDK_PATH)+"/tools/ide/bin/tizen");
		if(!file.exists()){
			throw new Exception("SDK Path is not correct, please configure it after clicking Settings button");
		}
	}

	private void updateTable(File selectedFile) throws Exception {
		Stage stage = new Stage();
		int maxRootstrapVersion = getMaxRootstarpVersion();
		if(maxRootstrapVersion == 0){
			System.out.println("[ERROR] No rootstrap found for "+ConfigUtil.getValue(Constants.SAMPLE_PROFILE)+", Please check SDK installation");
		}else{
			System.out.println("[MESSAGE] App list is populating, Please wait...");
			final Task task = new Task<Void>() {
			    @Override
			    protected Void call() throws IOException, JSONException {
			    	ArrayList<SampleAppInfo> appinfoList = new ArrayList<SampleAppInfo>();
					Scanner sc = new Scanner(selectedFile);
					if(sc.hasNextLine()) {
						sc.nextLine();
					}
					ArrayList<String> lines = new ArrayList<String>();
					while(sc.hasNextLine()) {
						String line = sc.nextLine().trim();
						if(!line.isEmpty()){
							lines.add(line);
						}
					}
					int max = lines.size() ;

					try{
						for (int i = 0; i < lines.size(); i++) {
							String [] parts = lines.get(i).split(",");
							String appType = parts[0];
							String profile = parts[1];
							String requiredVersion = parts[2];
							String appName = parts[3];
							int requiredRootStrapVersion = getInt(requiredVersion);
							if(requiredRootStrapVersion <= maxRootstrapVersion){
								SampleAppInfo appInfo = new SampleAppInfo(appName,appType,profile,requiredVersion);
								appinfoList.add(appInfo);
								generateJavaFileFromAppInfo(appInfo);
							}
							updateProgress(i+1, max);

						}
					}catch (Exception e) {
						System.out.println("[ERROR] Wrong formated csv file");
					}

					sc.close();

					if(appinfoList.size() > 0) {
						addAllToTcTable(appinfoList);
						if(!isAllScenarioUnchecked()) {
							runBtn.setDisable(false);
							rerunBtn.setDisable(false);
						}

						refreshBtn.setDisable(false);
						exportReportBtn.setDisable(false);
						exploratoryCodeBtn.setDisable(false);

						selectAllCheck.setSelected(true);
					}

					System.out.println("[MESSAGE] App list preparation completed...");
			        return null;
			    }
			};
			final ProgressBar progress = new ProgressBar();
	        progress.progressProperty().bind(
	                task.progressProperty()
	        );
	        progress.progressProperty().addListener(new javafx.beans.value.ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					if((double)newValue == 1.0)
						stage.close();

				}
			});
	        VBox vbox = new VBox();
	        progress.setStyle("-fx-min-width: 400;");
	        Label label = new Label("Preparing App list, Please wait...");
	       // StackPane layout = new StackPane();
	        vbox.getChildren().add(0,label);
	        vbox.getChildren().add(1,progress);
	        stage.setScene(new Scene(vbox));
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
	        stage.setWidth(400);
	        final Thread thread = new Thread(task);
	        thread.setDaemon(true);
	        thread.start();
		}

	}

	private int getInt(String version) {
		String[] parts = version.split("\\.");
		int val = 0;
		int c = 2;
		for (int i = 0; i < parts.length; i++) {
			val += Integer.parseInt(parts[i])*(Math.pow(10, c--)) ;
		}
		return val;
	}

	private int getMaxRootstarpVersion() {
		int maxRootStrapVersion = 0;
		List<String> rootstrapList = getRootstraps();
		String sampleAppProfile = ConfigUtil.getValue(Constants.SAMPLE_PROFILE).toLowerCase();
		for (String rootstrap : rootstrapList) {
			if(rootstrap.contains(sampleAppProfile)){
				int version = getInt(rootstrap.split("-")[1].split("-")[0]);
				if(maxRootStrapVersion < version){
					maxRootStrapVersion = version;
				}
			}
		}
		return maxRootStrapVersion;
	}

//	private String getNativeJsonLink() throws IOException, JSONException {
//		String user = ConfigUtil.getValue(Constants.USER);
//		String pass = ConfigUtil.getValue(Constants.USER_PWD);
//		ArrayList<String> urlList = getAppInfoJsonUrl(ConfigUtil.getValue(Constants.SAMPLE_SERVER),user,pass);
//			for (String link : urlList) {
//				if(link.toLowerCase().contains("native"))
//					return link;
//			}
//		return null;
//	}

	public void exploratoryCodeBtnClicked() {
		setConsole();
		SampleAppInfo selected = tcTable.getSelectionModel().getSelectedItem();
		if(selected != null) {
			try {
				showExploratoryCodeWindow(selected);
			} catch (FileNotFoundException e) {
				System.out.println("[ERROR] " + e.getMessage());
			}
		}else {
			System.out.println("[MESSAGE] To write exploratory code please select any application from table");
		}
	}

	public void runBtnClicked() {
		try {
			setConsole();

			checkConfigValues();

			ConfigUtil.setValue(Constants.SELECTED_ROOTSTRAP, null);

			if(isBuild()){
				showRootstrapList();
			}else{
				performRun();
			}
		} catch (Exception e) {
			System.out.println("[MESSAGE] " + e.getMessage());
		}

	}

	private void showRootstrapList() {
		List<String> rootstrapsList =  getRootstraps();
		rootstrapsList.add("Auto");
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Select rootstrap");
		dialog.setHeaderText("Select rootstrap from the below list.");

		final ToggleGroup group = new ToggleGroup();
		List<RadioButton> buttons = new ArrayList<RadioButton>();
		for (String string : rootstrapsList) {
			RadioButton rootstrapsRadio = new RadioButton(string);
			rootstrapsRadio.setToggleGroup(group);
			buttons.add(rootstrapsRadio);
		}


		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

		int row = 0;
		for (RadioButton radioButton : buttons) {
			radioButton.setSelected(true);
			grid.add(radioButton, 1, row++);
		}

		ButtonType loginButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
		Node okButton = dialog.getDialogPane().lookupButton(loginButtonType);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		dialog.getDialogPane().setContent(grid);

		dialog.setResultConverter(button -> {
		    if (button == loginButtonType) {
		        return ((RadioButton)group.getSelectedToggle()).getText();
		    }
		    return null;
		});

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(selected -> {
		    ConfigUtil.setValue(Constants.SELECTED_ROOTSTRAP, selected);
		});
		if(ConfigUtil.getValue(Constants.SELECTED_ROOTSTRAP) != null){
			performRun();
		}
	}

	private void performRun() {
		JsoupXMLParser.writeSampleAppXml(this, "run");

		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("This plan saved successfully.\nPlan can also execute with commandline \"java -jar uiautomator.jar run -p sampleapp\" \nDo you want to run now?");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			clearConsole();
			appResultLbl.setText("");
			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			browseBtn.setDisable(true);
			refreshBtn.setDisable(true);
			settingsBtn.setDisable(true);
			runSuspendBtn.setDisable(false);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);
			createAppList.setDisable(true);
			checkBox.setEditable(false);
			selectAllCheck.setDisable(true);

			refreshTcTable();
			performCompileRun("run");
			writeSuspendFile("0");
		}
	}

	public void rerunBtnClicked() {
		setConsole();

		if(selectFailedRows() > 0) {
			clearConsole();
			appResultLbl.setText("");
			JsoupXMLParser.writeSampleAppXml(this, "rerun");

			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			browseBtn.setDisable(true);
			refreshBtn.setDisable(true);
			settingsBtn.setDisable(true);
			runSuspendBtn.setDisable(false);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);

			checkBox.setEditable(false);
			selectAllCheck.setDisable(true);

			refreshTcTable();
			performCompileRun("rerun");
			writeSuspendFile("0");
		}else {
			runBtnCheck();
			System.out.println("[MESSAGE] No failed TCs found to execute rerun.");
		}
	}

	public void exportReportBtnClicked() {
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
		fileChooser.setInitialFileName(format.format(date)+"_report_sampleapp.xls");
		fileChooser.setInitialDirectory(reportDirectory);
		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile != null){
			String reportFilePath=selectedFile.getAbsolutePath();
			if(!reportFilePath.contains(".xls"))
				reportFilePath += ".xls";
			ReportUtils.exportSampleappReport(reportFilePath,getTableRows());
		}
	}

	public void refreshBtnClicked() {
		setConsole();

		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("All application will be removed from table. Do you want to refresh ?");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			clearConsole();
			appResultLbl.setText("");
			removeAllFromTcTable();

			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
			exportReportBtn.setDisable(true);
			exploratoryCodeBtn.setDisable(true);
			refreshBtn.setDisable(true);

			selectAllCheck.setSelected(false);
		}
	}

	public void runSuspendBtnClicked() {
		setConsole();
		writeSuspendFile("1");
		runSuspendBtn.setDisable(true);
	}

	public void settingsBtnClicked() {
		setConsole();

		try {
			Stage settingsStage = new Stage();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SampleAppSettingsScene.fxml")));
			settingsStage.setScene(scene);
			settingsStage.setTitle("SampleApp Setting Window");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			settingsStage.initModality(Modality.APPLICATION_MODAL);
			settingsStage.show();

		} catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	public boolean isBuild() {
		return buildCheck.selectedProperty().getValue();
	}

	public boolean isPackageing() {
		return packageCheck.selectedProperty().getValue();
	}

	public boolean isInstall() {
		return installCheck.selectedProperty().getValue();
	}

	public boolean isLaunch() {
		return launchCheck.selectedProperty().getValue();
	}

	public boolean isExploratory() {
		return exploratoryCheck.selectedProperty().getValue();
	}

	public boolean isCloseCheck() {
		return closeCheck.selectedProperty().getValue();
	}

	public boolean isUninstallCheck() {
		return uninstallCheck.selectedProperty().getValue();
	}

	public boolean isCrash() {
		return crashCheck.selectedProperty().getValue();
	}

	public TextArea getConsole() {
		return console;
	}

	public void clearConsole() {
		Platform.runLater(() -> console.clear());
	}
	//
	public List<SampleAppInfo> getTableRows() {
		return tcTable.getItems();
	}

	private int selectFailedRows() {
		int count = 0;
		List<SampleAppInfo> rows = getTableRows();
		for (SampleAppInfo appInfo : rows) {
			if(appInfo.getResult().getValue().toString().equals(TCResult.Fail+"")) {
				count++;
				appInfo.setCheckBox(true);
			} else {
				appInfo.setCheckBox(false);
			}
		}

		if(rows.size() == count) {
			selectAllCheck.setSelected(true);
		} else {
			selectAllCheck.setSelected(false);
		}

		return count;
	}

	private void refreshTcTable() {
		List<SampleAppInfo> apps = getTableRows();
		for (SampleAppInfo appInfo : apps) {
			if(!appInfo.getCheckBox()) {
				continue;
			}

			if(isBuild())
				appInfo.buildProperty().setValue("NT");
			if(isPackageing())
				appInfo.packageingProperty().setValue("NT");
			if(isInstall())
				appInfo.installProperty().setValue("NT");
			if(isLaunch())
				appInfo.launchProperty().setValue("NT");
			if(isExploratory())
				appInfo.exploratoryProperty().setValue("NT");
			if(isCloseCheck())
				appInfo.closeProperty().setValue("NT");
			if(isUninstallCheck())
				appInfo.uninstallProperty().setValue("NT");
			if(isCrash())
				appInfo.crashProperty().setValue("NT");

			appInfo.resultProperty().set(appInfo.getAppResult() + "");
		}
	}

	public void addToTcTable(SampleAppInfo tc) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				tcTable.getItems().add(tc);
			}
		});
	}

	public void addAllToTcTable(List<SampleAppInfo> appList) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (SampleAppInfo app : appList) {
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
				ObservableList<SampleAppInfo> data = tcTable.getItems();
				data.remove(0, data.size());

			}
		});
	}

	public void resetTctable(List<SampleAppInfo> newList) {

		removeAllFromTcTable();
		addAllToTcTable(newList);
	}

	private void checkAllTableRow(Boolean value) {
		List<SampleAppInfo> rows = getTableRows();
		for (SampleAppInfo appInfo : rows) {
			appInfo.setCheckBox(value);
		}
	}

	private void evaluateSelectAllCheckValue() {
		List<SampleAppInfo> rows = getTableRows();
		boolean isChecked = true;
		for (SampleAppInfo appInfo : rows) {
			if(!appInfo.getCheckBox()){
				isChecked = false;
				break;
			}
		}

		selectAllCheck.setSelected(isChecked);
	}

	private boolean isAllRowUnchecked() {
		List<SampleAppInfo> rows = getTableRows();
		boolean unchecked = true;
		for (SampleAppInfo appInfo : rows) {
			if(appInfo.getCheckBox()){
				unchecked = false;
				break;
			}
		}
		return unchecked;
	}

	public void runBtnCheck() {
		if(!isAllRowUnchecked() &&
				!exploratoryCodeBtn.isDisable()) {
			if(!isAllScenarioUnchecked()) {
				runBtn.setDisable(false);
				rerunBtn.setDisable(false);
			} else {
				runBtn.setDisable(true);
				rerunBtn.setDisable(true);
			}
		} else {
			runBtn.setDisable(true);
			rerunBtn.setDisable(true);
		}
	}

	private boolean isAllScenarioUnchecked() {
		boolean checked = (buildCheck.selectedProperty().getValue() ||
				packageCheck.selectedProperty().getValue() ||
				installCheck.selectedProperty().getValue() ||
				uninstallCheck.selectedProperty().getValue() ||
				exploratoryCheck.selectedProperty().getValue() ||
				crashCheck.selectedProperty().getValue() ||
				closeCheck.selectedProperty().getValue() ||
				launchCheck.selectedProperty().getValue());

		return !checked;
	}

	private void writeSuspendFile(String line) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./temp/suspend"));
			bufferedWriter.write(line);
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	public void showJavaCodeWindow(SampleAppInfo selected) throws FileNotFoundException {
		File exploratoryFile = new File("./src/sampleapp/"+selected.getJavaFileName()+".java");
		String lines = readJavaCode(exploratoryFile.getAbsolutePath());
		JDialog frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setModal(true);
		frame.setTitle("Java Code: "+selected.appNameProperty().getValue());
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));

		GridBagConstraints codeContentConst = new GridBagConstraints();
		codeContentConst.fill = GridBagConstraints.BOTH;
		codeContentConst.weightx = 0.0;
		codeContentConst.weighty = 0.1;
		codeContentConst.gridwidth = 3;
		codeContentConst.gridx = 0;
		codeContentConst.gridy = 0;

		JPanel codeContent = new JPanel();

		codeContent.setLayout(new BoxLayout(codeContent, BoxLayout.X_AXIS));
		RTextScrollPane scrollPanel ;

		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setFont(textArea.getFont().deriveFont(14.0f));
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		textArea.setText(lines);
		scrollPanel = new RTextScrollPane(textArea);

		codeContent.add(scrollPanel);
		contentPane.add(codeContent, codeContentConst);

		JPanel buttonPane = new JPanel(new GridBagLayout());
		buttonPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		GridBagConstraints buttonPaneConst = new GridBagConstraints();
		buttonPaneConst.fill = GridBagConstraints.HORIZONTAL;
		buttonPaneConst.weightx = 1;
		buttonPaneConst.weightx = 0.9;
		buttonPaneConst.anchor = GridBagConstraints.PAGE_END;
		buttonPaneConst.gridx = 0;
		buttonPaneConst.gridy = 3;
		buttonPaneConst.gridwidth = 3;

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					PrintWriter pw = new PrintWriter(exploratoryFile);
					pw.write(textArea.getText());
					pw.flush();
					pw.close();
				} catch (Exception ex) {
					System.out.println("[ERROR] " + ex.getMessage());
				}

				JOptionPane.showMessageDialog(frame, "Java Code: " + selected.appNameProperty().getValue() + " saved successfully.");
			}
		});

		GridBagConstraints saveBtnConst = new GridBagConstraints();
		saveBtnConst.fill = GridBagConstraints.HORIZONTAL;
		saveBtnConst.insets = new Insets(10, 0, 10, 5);
		saveBtnConst.gridx = 2;
		saveBtnConst.gridy = 0;
		saveBtnConst.gridwidth = 1;

		GridBagConstraints closeBtnConst = new GridBagConstraints();
		closeBtnConst.fill = GridBagConstraints.HORIZONTAL;
		closeBtnConst.insets = new Insets(10, 5, 10, 0);
		closeBtnConst.gridx = 3;
		closeBtnConst.gridy = 0;
		closeBtnConst.gridwidth = 1;
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.dispose();

			}
		});
		buttonPane.add(saveBtn, saveBtnConst);
		buttonPane.add(closeBtn, closeBtnConst);

		contentPane.add(buttonPane, buttonPaneConst);
		frame.add(contentPane);


		frame.pack();
		frame.setSize(680, 600);
		frame.setPreferredSize(new Dimension(680, 600));
		frame.setResizable(true);
		frame.setVisible(true);
	}

	public void showExploratoryCodeWindow(SampleAppInfo selected) throws FileNotFoundException {
		File exploratoryFile = new File("./src/sampleapp/"+selected.getJavaFileName()+".java");
		String [] parts = readExploratoryCode(exploratoryFile.getAbsolutePath());
		JDialog frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setModal(true);
		frame.setTitle("Exploratory Code: "+selected.appNameProperty().getValue());
		frame.setLayout(new BorderLayout());

		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBorder(BorderFactory.createLineBorder(java.awt.Color.DARK_GRAY));

		GridBagConstraints codeContentConst = new GridBagConstraints();
		codeContentConst.fill = GridBagConstraints.BOTH;
		codeContentConst.weightx = 0.0;
		codeContentConst.weighty = 0.1;
		codeContentConst.gridwidth = 3;
		codeContentConst.gridx = 0;
		codeContentConst.gridy = 0;

		JPanel codeContent = new JPanel();

		codeContent.setLayout(new BoxLayout(codeContent, BoxLayout.X_AXIS));
		RTextScrollPane scrollPanel ;

		RSyntaxTextArea textArea = new RSyntaxTextArea();
		textArea.setFont(textArea.getFont().deriveFont(14.0f));
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		textArea.setCodeFoldingEnabled(true);
		textArea.setText(parts[1].trim().replace("null", "")+"\n");
		scrollPanel = new RTextScrollPane(textArea);

		codeContent.add(scrollPanel);
		contentPane.add(codeContent, codeContentConst);

		JPanel buttonPane = new JPanel(new GridBagLayout());
		buttonPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		GridBagConstraints buttonPaneConst = new GridBagConstraints();
		buttonPaneConst.fill = GridBagConstraints.HORIZONTAL;
		buttonPaneConst.weightx = 1;
		buttonPaneConst.weightx = 0.9;
		buttonPaneConst.anchor = GridBagConstraints.PAGE_END;
		buttonPaneConst.gridx = 0;
		buttonPaneConst.gridy = 3;
		buttonPaneConst.gridwidth = 3;

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try {
					PrintWriter pw = new PrintWriter(exploratoryFile);
					pw.write( parts[0].trim().replace("null", "")+"\n\t\t"+textArea.getText().replace("\n", "\n\t\t")+"\n\t}\n\n"+parts[2].trim().replace("null", ""));
					pw.flush();
					pw.close();
				} catch (Exception ex) {
					System.out.println("[ERROR] " + ex.getMessage());
				}

				JOptionPane.showMessageDialog(frame, "Exploratory Code: " + selected.appNameProperty().getValue() + " saved successfully.");
			}
		});

		GridBagConstraints saveBtnConst = new GridBagConstraints();
		saveBtnConst.fill = GridBagConstraints.HORIZONTAL;
		saveBtnConst.insets = new Insets(10, 0, 10, 5);
		saveBtnConst.gridx = 2;
		saveBtnConst.gridy = 0;
		saveBtnConst.gridwidth = 1;

		GridBagConstraints closeBtnConst = new GridBagConstraints();
		closeBtnConst.fill = GridBagConstraints.HORIZONTAL;
		closeBtnConst.insets = new Insets(10, 5, 10, 0);
		closeBtnConst.gridx = 3;
		closeBtnConst.gridy = 0;
		closeBtnConst.gridwidth = 1;
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.dispose();

			}
		});
		buttonPane.add(saveBtn, saveBtnConst);
		buttonPane.add(closeBtn, closeBtnConst);

		contentPane.add(buttonPane, buttonPaneConst);
		frame.add(contentPane);


		frame.pack();
		frame.setSize(680, 600);
		frame.setPreferredSize(new Dimension(680, 600));
		frame.setResizable(true);
		frame.setVisible(true);
	}

	public void generateJavaFileFromAppInfo(SampleAppInfo appInfo) {
		try {
//			InputStream in = ClassLoader.getSystemResourceAsStream("template/SampleAppTemplate");
//			StringBuffer sb = new StringBuffer();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				sb.append(line+"\n");
//			}

			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader("./res/template/SampleAppTemplate"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line+"\n");
			}
			reader.close();

			String javaFileName = appInfo.getJavaFileName();

			File file = new File("./src/sampleapp/" + javaFileName + ".java");
			if(!file.exists()) {
				file.createNewFile();
				PrintWriter pw = new PrintWriter(file);
				String temp = sb.toString();
				temp = temp.replaceAll("ClassName", javaFileName);
				pw.write(temp);
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}

	private String readJavaCode(String exploratoryFilePath) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(exploratoryFilePath));
		String lines = "";

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			lines += line + "\n";
		}
		sc.close();
		return lines;
	}

	private String[] readExploratoryCode(String exploratoryFilePath) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(exploratoryFilePath));
		String expLine = "\tpublic void exploratory() {";
		String endLine = "\tpublic void close() {";
		String [] parts = new String[3];
		int currentIndex = 0;

		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.equals(expLine)) {
				parts[currentIndex] += line + "\n";
				currentIndex++;
			} else if(line.equals(endLine)) {
				currentIndex++;
				parts[currentIndex] += line+ "\n";
			} else {
				parts[currentIndex] += line+ "\n";
			}
		}

		sc.close();
		parts[1] = parts[1].trim().substring(0, parts[1].trim().length()-1);
		parts[1] = parts[1].replaceAll("\t", "");

		return parts;
	}


	private boolean isWindows() {
		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

	public void imagePopupWindowShow(String imgPath) {

		File imageFile;
		Image image;
		ImageView imageView;
		BorderPane pane;
		Scene scene;
		Stage stage;

		imageFile = new File(imgPath);
		image = new Image(imageFile.toURI().toString());
		imageView = new ImageView(image);

		// Image will sit in the middle of our popup.
		pane = new BorderPane();
		pane.setCenter(imageView);
		scene = new Scene(pane);

		// Create the actual window and display it.
		stage = new Stage();
		stage.setScene(scene);
		// Without this, the audio won't stop!
		stage.setOnCloseRequest(
				e -> {
					e.consume();
					stage.close();
				}
				);
		stage.showAndWait();
	}

	private List<String> getRootstraps() {
		ArrayList <String> rootstrapsList = new ArrayList<String>();
		ProcessBuilder getRootstraps;
		Process getRootstrapsProcess;
		try {
			if (!isWindows()) {
				getRootstraps = new ProcessBuilder("/bin/sh", "-c",ConfigUtil.getValue(Constants.SDK_PATH)+"/tools/ide/bin/tizen list rootstrap");
			} else {
				getRootstraps = new ProcessBuilder("cmd", "/c", ConfigUtil.getValue(Constants.SDK_PATH)+"/tools/ide/bin/tizen list rootstrap");
			}
			getRootstrapsProcess = getRootstraps.start();
			String line = "";
			BufferedReader msgReader = new BufferedReader(new InputStreamReader(getRootstrapsProcess.getInputStream()));


			while ((line = msgReader.readLine()) != null) {
				if(line.contains(".core")) {
					rootstrapsList.add(new Scanner(line).next());
				}
			}

			if(rootstrapsList.isEmpty()){
				System.out.println("[MESSAGE] No rootstrap found containg with emulator.core or device.core. Please check if SDK installed correctly");
			}

			getRootstrapsProcess.waitFor();
			msgReader.close();
			getRootstrapsProcess.destroy();
		} catch (Exception e) {
			System.out.println("[ERROR] " + e.getMessage());
		}

		return rootstrapsList;
	}

	private static String getLogFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String logFileName =  format.format(date)+"_uiautomator.log";
		return logFileName;
	}

	private void performCompileRun(String mode) {
		String logFileName = getLogFileName();

		new Thread(new Runnable() {
			@Override
			public void run() {
				String currentWorkingDirectory = System.getProperty("user.dir");
				ProcessBuilder compile, run;
				Process compileProcess, runProcess;

				try {
					if (!isWindows()) {
						compile = new ProcessBuilder("/bin/sh", "-c","javac -d "+
								currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
								+ "/AFEngine/lib/*" + ":"+currentWorkingDirectory + "/lib/*:" + currentWorkingDirectory + "/src "+
								currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/sampleapp/*.java");
					} else {
						compile = new ProcessBuilder("cmd", "/c", "javac -d "+
								currentWorkingDirectory + "/bin -cp "+ currentWorkingDirectory
								+ "/AFEngine/lib/*" + ";"+currentWorkingDirectory + "/lib/*;" + currentWorkingDirectory + "/src "+
								currentWorkingDirectory + "/src/apprunner/Main.java "+ currentWorkingDirectory + "/src/sampleapp/*.java");
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
							run = new ProcessBuilder("/bin/sh", "-c",
									"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*:" + currentWorkingDirectory + "/lib/*:"
											+ currentWorkingDirectory + "/bin apprunner.Main sampleapp "+mode + " " + logFileName);
						} else {
							run = new ProcessBuilder("cmd", "/c",
									"java -cp " + currentWorkingDirectory + "/AFEngine/lib/*;" + "lib/*;"
											+ currentWorkingDirectory + "/bin apprunner.Main sampleapp "+mode + " " + logFileName);
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

								if(sm.contains("STATUS") && sm.contains("result") && sm.contains("time")) {

									String result = sm.split("\\[result")[1].split("\\]")[0].split("->")[1].trim();
									String time = sm.split("\\[time")[1].split("\\]")[0].split("->")[1].split("ms")[0].trim();

						    		sm = sm.split("\\[STATUS\\]")[1].trim();
						    		String[] parts = sm.split("\\]");

						    		ArrayList<String> data =new ArrayList<String>();
						    		for (String part : parts) {
						    			data.add(part.replaceAll("\\[", ""));
									}

						    		String appName = data.get(0);
						    		String appType = data.get(1);
						    		String profile = data.get(2);
						    		String requiredVersion = data.get(3);
						    		String tcName = data.get(4);

						    		SampleAppInfo sampleAppInfo = new SampleAppInfo(appName,appType,profile,requiredVersion);

									List<SampleAppInfo> appList = getTableRows();

									for (SampleAppInfo appInfo : appList) {
										if(appInfo.equals(sampleAppInfo)) {
											if(tcName.equals("build")) {
												appInfo.buildProperty().setValue(result);
												appInfo.setTimeBuild(Integer.parseInt(time));
											}else if(tcName.equals("packaging")) {
												appInfo.packageingProperty().setValue(result);
												appInfo.setTimePackage(Integer.parseInt(time));
											}else if(tcName.equals("install")) {
												appInfo.installProperty().setValue(result);
												appInfo.setTimeInstall(Integer.parseInt(time));
											} else if(tcName.equals("launch")) {
												appInfo.launchProperty().setValue(result);
												appInfo.setTimeLaunch(Integer.parseInt(time));
											} else if(tcName.equals("exploratory")) {
												appInfo.exploratoryProperty().setValue(result);
												appInfo.setTimeExporatory(Integer.parseInt(time));
											} else if(tcName.equals("close")) {
												appInfo.closeProperty().setValue(result);
												appInfo.setTimeClose(Integer.parseInt(time));
											} else if(tcName.equals("uninstall")) {
												appInfo.uninstallProperty().setValue(result);
												appInfo.setTimeUninstall(Integer.parseInt(time));
											} else if(tcName.equals("detectCrash")) {
												appInfo.crashProperty().setValue(result);
												appInfo.setTimeCrash(Integer.parseInt(time));
											}
											appInfo.resultProperty().set(appInfo.getAppResult() + "");

										}
									}
								}else if(sm.contains("selected apps execution finished") || sm.contains("Total selected apps")){
									final String resultTitle = sm.split("\\[INFO\\]")[1].trim();
									Platform.runLater(() -> appResultLbl.setText(resultTitle));
								}

								msgSb = new StringBuffer();
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
				rerunBtn.setDisable(false);
				browseBtn.setDisable(false);
				refreshBtn.setDisable(false);
				settingsBtn.setDisable(false);
				runSuspendBtn.setDisable(true);
				exportReportBtn.setDisable(false);
				exploratoryCodeBtn.setDisable(false);
				createAppList.setDisable(false);
				checkBox.setEditable(true);
				selectAllCheck.setDisable(false);

			}

//			private TCResult getAppResult(SampleAppInfo sampleAppInfo) {
//				ArrayList<StringProperty> resultList = new ArrayList<StringProperty>();
//				resultList.add(sampleAppInfo.getBuild());
//				resultList.add(sampleAppInfo.getPackageing());
//				resultList.add(sampleAppInfo.getInstall());
//				resultList.add(sampleAppInfo.getLaunch());
//				resultList.add(sampleAppInfo.getExploratory());
//				resultList.add(sampleAppInfo.getClose());
//				resultList.add(sampleAppInfo.getUninstall());
//				resultList.add(sampleAppInfo.getDetectCrash());
//
//				boolean isPass = false;
//				for (StringProperty stringProperty : resultList) {
//					if(stringProperty.getValue().toString().equals(TCResult.Fail+""))
//						return TCResult.Fail;
//					else if(stringProperty.getValue().toString().equals(TCResult.Pass+""))
//						isPass = true;
//				}
//				if(isPass)
//					return TCResult.Pass;
//				else
//					return TCResult.NT;
//			}
//
//			private int getAppCount() {
//				int tcCount = 0;
//				for(SampleAppInfo sampleappInfo : getTableRows()){
//					if(sampleappInfo.getCheckBox()){
//						tcCount++;
//					}
//				}
//				return tcCount;
//			}
		}).start();
	}

}
