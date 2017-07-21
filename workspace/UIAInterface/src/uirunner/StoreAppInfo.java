package uirunner;

import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StoreAppInfo {

	private SimpleStringProperty appName;
	private SimpleStringProperty result;
	private SimpleStringProperty install;
	private SimpleStringProperty launch;
	private SimpleStringProperty exploratory;
	private SimpleStringProperty close;
	private SimpleStringProperty uninstall;
	private SimpleStringProperty crash;
	private SimpleBooleanProperty checkBox = new SimpleBooleanProperty(true);
	private String absolutePackagePath;
	private String javaFileName;

	private long timeInstall;
	private long timeLaunch;
	private long timeExporatory;
	private long timeClose;
	private long timeUninstall;
	private long timeCrash;
	private long timeTotal;

//	private ArrayList<TestInfo> tcList;
//	private String appName;
//
//	public void setTCResult(String tcName, TCResult result) {
//		getTestInfo(tcName).setResult(result);
//	}
//
//	public String getTCResult(String tcName) {
//		TestInfo testInfo = getTestInfo(tcName);
//		if(testInfo != null)
//			return testInfo.getResult().toString();
//		return "NR";
//	}
//
//	private TestInfo getTestInfo(String testInfoType) {
//		TestInfo installTest = null;
//		for (TestInfo testInfo : tcList) {
//			if(testInfo.getTcName().equals(testInfoType)) {
//				installTest = testInfo;
//			}
//		}
//		return installTest;
//	}

	public StoreAppInfo() {
//		this.tcList = new ArrayList<TestInfo>();
		this.install = new SimpleStringProperty();
		this.exploratory = new SimpleStringProperty();
		this.uninstall = new SimpleStringProperty();
	}

	public StoreAppInfo(String name) {
		this.appName = new SimpleStringProperty(name);
		this.result = new SimpleStringProperty("NT");
		this.install = new SimpleStringProperty("NT");
		this.launch = new SimpleStringProperty("NT");
		this.exploratory = new SimpleStringProperty("NT");
		this.close = new SimpleStringProperty("NT");
		this.uninstall = new SimpleStringProperty("NT");
		this.crash = new SimpleStringProperty("NT");
	}

//	public AppInfo (ArrayList<TestInfo> testCaseList, String name) {
//
//		this.tcList = testCaseList;
//		this.appName = name;
//		this.name = new SimpleStringProperty(name);
//		this.binary = new SimpleStringProperty(binaryName);
//		this.install = new SimpleStringProperty();
//		this.exploratory = new SimpleStringProperty();
//		this.uninstall = new SimpleStringProperty();
//	}
//
//	public ArrayList<TestInfo> getTcList() {
//		return tcList;
//	}
//	public void setTcList(ArrayList<TestInfo> tcList) {
//		this.tcList = tcList;
//	}
//	public String getAppName() {
//		return appName;
//	}
//	public void setAppName(String appName) {
//		this.appName = appName;
//	}
//	public String getBinary() {
//		return binaryName;
//	}
//
//	public void setBinaryName(String binaryName) {
//		this.binaryName = binaryName;
//	}

	public SimpleStringProperty appNameProperty() {
		return appName;
	}

	public SimpleStringProperty resultProperty() {
		return result;
	}

	public SimpleStringProperty installProperty() {
		return install;
	}

	public SimpleStringProperty launchProperty() {

		return launch;
	}

	public SimpleStringProperty exploratoryProperty() {
		return exploratory;
	}

	public SimpleStringProperty closeProperty() {

		return close;
	}

	public SimpleStringProperty uninstallProperty() {

		return uninstall;
	}

	public SimpleStringProperty crashProperty() {

		return crash;
	}


    public SimpleBooleanProperty checkBoxProperty() {
        return this.checkBox;
    }

    public java.lang.Boolean getCheckBox() {
        return this.checkBoxProperty().get();
    }

    public void setCheckBox(final java.lang.Boolean checked) {
        this.checkBoxProperty().set(checked);
    }

	public void setAppName(SimpleStringProperty name) {
		this.appName = name;
	}

	public void setResult(SimpleStringProperty result) {
		this.result = result;
	}

	public void setInstall(SimpleStringProperty name) {
		this.install = name;
	}

	public void setLaunch(SimpleStringProperty launch) {
		this.launch = launch;
	}

	public void setExploratory(SimpleStringProperty name) {
		this.exploratory = name;
	}

	public void setClose(SimpleStringProperty name) {
		this.close = name;
	}

	public void setUninstall(SimpleStringProperty name) {
		this.uninstall = name;
	}

	public void setCrash(SimpleStringProperty name) {
		this.crash = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StoreAppInfo)) {
			return false;
		}
		StoreAppInfo target = (StoreAppInfo) obj;

		return this.getName().getValue().equals(target.getName().getValue());
	}


	public SimpleStringProperty getName() {
		return appName;
	}

	public SimpleStringProperty getResult() {
		return result;
	}

	public SimpleStringProperty getInstall() {
		return install;
	}

	public SimpleStringProperty getExploratory() {
		return exploratory;
	}

	public SimpleStringProperty getUninstall() {
		return uninstall;
	}

	public StringProperty getLaunch() {
		return launch;
	}

	public StringProperty getClose() {
		return close;
	}

	public StringProperty getDetectCrash() {
		return crash;
	}

	public void setJavaFileName(String javaFileName) {
		this.javaFileName = javaFileName;
	}

	public String getJavaFileName() {
		return this.javaFileName;
	}

	private String getRelativePath(String path, String base) {
		String relative = "";
    	File baseFile = new File(base);
    	while(true){
    		if(path.contains(baseFile.getAbsolutePath())){
    			relative = relative + new File(baseFile.getAbsolutePath()).toURI().relativize(new File(path).toURI()).getPath();
    			break;
    		}
    		baseFile = baseFile.getParentFile();
    		relative = "../" + relative;
    	}
		return relative;
	}

	public String getRelativePackagePath() {
		return getRelativePath(getAbsolutePackagePath(), System.getProperty("user.dir"));
	}

	public String getAbsolutePackagePath() {
		return absolutePackagePath;
	}

	public void setFullPath(String absolutePackagePath) {
		this.absolutePackagePath = absolutePackagePath;
	}

	public TCResult getAppResult() {
		ArrayList<StringProperty> propertyList = new ArrayList<StringProperty>();
		propertyList.add(this.getName());
		propertyList.add(this.getInstall());
		propertyList.add(this.getLaunch());
		propertyList.add(this.getExploratory());
		propertyList.add(this.getClose());
		propertyList.add(this.getUninstall());
		propertyList.add(this.getDetectCrash());

		boolean isPass = false;
		for (int index = 1; index < propertyList.size(); index++) {
			if(propertyList.get(index).getValue().toString().equals(TCResult.Fail+""))
				return TCResult.Fail;
			else if(propertyList.get(index).getValue().toString().equals(TCResult.Pass+""))
				isPass = true;
		}
		if(isPass)
			return TCResult.Pass;
		else
			return TCResult.NT;
	}

	public long getTimeInstall() {
		return timeInstall;
	}

	public void setTimeInstall(long timeInstall) {
		this.timeInstall = timeInstall;
		setTimeTotal();
	}

	public long getTimeLaunch() {
		return timeLaunch;
	}

	public void setTimeLaunch(long timeLaunch) {
		this.timeLaunch = timeLaunch;
		setTimeTotal();
	}

	public long getTimeExporatory() {
		return timeExporatory;
	}

	public void setTimeExporatory(long timeExporatory) {
		this.timeExporatory = timeExporatory;
		setTimeTotal();
	}

	public long getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(long timeClose) {
		this.timeClose = timeClose;
		setTimeTotal();
	}

	public long getTimeUninstall() {
		return timeUninstall;
	}

	public void setTimeUninstall(long timeUninstall) {
		this.timeUninstall = timeUninstall;
		setTimeTotal();
	}

	public long getTimeCrash() {
		return timeCrash;
	}

	public void setTimeCrash(long timeCrash) {
		this.timeCrash = timeCrash;
		setTimeTotal();
	}

	public long getTimeTotal() {
		return timeTotal;
	}

	public void setTimeTotal() {
		this.timeTotal = getTimeInstall() + getTimeLaunch() +
				getTimeExporatory() + getTimeClose() + getTimeUninstall() + getTimeCrash();
	}

}
