package uirunner;

import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SampleAppInfo {

	private SimpleStringProperty appType;
	private SimpleStringProperty profile;
	private SimpleStringProperty requiredVersion;
	private SimpleStringProperty appName;
	private SimpleStringProperty result;
	private SimpleStringProperty build;
	private SimpleStringProperty packageing;
	private SimpleStringProperty install;
	private SimpleStringProperty launch;
	private SimpleStringProperty exploratory;
	private SimpleStringProperty close;
	private SimpleStringProperty uninstall;
	private SimpleStringProperty crash;
	private SimpleBooleanProperty checkBox = new SimpleBooleanProperty(true);
	private String fullPath;
	private String projectType;


	private long timeBuild;
	private long timePackage;
	private long timeInstall;
	private long timeLaunch;
	private long timeExporatory;
	private long timeClose;
	private long timeUninstall;
	private long timeCrash;
	private long timeTotal;

	public SampleAppInfo(String appName, String appType, String profile, String requiredVersion) {

		setAppName(appName);
		setAppType(appType);
		setProfile(profile);
		setRequiredVersion(requiredVersion);
		String initalResult = TCResult.NT + "";
		long initialTime = 0;
		setResult(initalResult);
		setBuild(initalResult);
		setPackageing(initalResult);
		setInstall(initalResult);
		setLaunch(initalResult);
		setExploratory(initalResult);
		setClose(initalResult);
		setUninstall(initalResult);
		setCrash(initalResult);


		setTimeBuild(initialTime);
		setTimePackage(initialTime);
		setTimeInstall(initialTime);
		setTimeLaunch(initialTime);
		setTimeExporatory(initialTime);
		setTimeClose(initialTime);
		setTimeUninstall(initialTime);
		setTimeCrash(initialTime);
	}

	public String getProjectType() {
		return projectType;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public SimpleStringProperty appTypeProperty() {
		return appType;
	}

	public SimpleStringProperty profileProperty() {
		return profile;
	}

	public SimpleStringProperty requiredVersionProperty() {
		return requiredVersion;
	}

	public SimpleStringProperty appNameProperty() {
		return appName;
	}

	public SimpleStringProperty resultProperty() {
		return result;
	}

	public SimpleStringProperty buildProperty() {
		return build;
	}

	public SimpleStringProperty packageingProperty() {
		return packageing;
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
        return this.checkBoxProperty().getValue();
    }

    public void setCheckBox(final java.lang.Boolean checked) {
        this.checkBoxProperty().set(checked);
    }

	public void setAppType(String appType) {
		this.appType = new SimpleStringProperty(appType);
	}

	public void setProfile(String profile) {
		this.profile = new SimpleStringProperty(profile);
	}

	public void setRequiredVersion(String requiredVersion) {
		this.requiredVersion = new SimpleStringProperty(requiredVersion);
	}

	public void setAppName(String appName) {
		this.appName = new SimpleStringProperty(appName);
	}

	public void setResult(String result) {
		this.result = new SimpleStringProperty(result);
	}

	public void setBuild(String build) {
		this.build = new SimpleStringProperty(build);
	}

	public void setPackageing(String packageing) {
		this.packageing = new SimpleStringProperty(packageing);
	}

	public void setInstall(String install) {
		this.install = new SimpleStringProperty(install);
	}

	public void setLaunch(String launch) {
		this.launch = new SimpleStringProperty(launch);
	}

	public void setExploratory(String exploratory) {
		this.exploratory = new SimpleStringProperty(exploratory);
	}

	public void setClose(String close) {
		this.close = new SimpleStringProperty(close);
	}

	public void setUninstall(String uninstall) {
		this.uninstall = new SimpleStringProperty(uninstall);
	}

	public void setCrash(String crash) {
		this.crash = new SimpleStringProperty(crash);
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SampleAppInfo)) {
			return false;
		}
		SampleAppInfo target = (SampleAppInfo) obj;
		return (this.appNameProperty().getValue().equals(target.appNameProperty().getValue())) &&
				(this.requiredVersionProperty().getValue().equals(target.requiredVersionProperty().getValue())) &&
				(this.profileProperty().getValue().equals(target.profileProperty().getValue())) &&
				(this.appTypeProperty().getValue().equals(target.appTypeProperty().getValue()));
	}



	public SimpleStringProperty getAppType() {
		return appType;
	}

	public SimpleStringProperty getProfile() {
		return profile;
	}

	public SimpleStringProperty getRequiredVersion() {
		return requiredVersion;
	}

	public SimpleStringProperty getName() {
		return appName;
	}

	public SimpleStringProperty getResult() {
		return result;
	}

	public SimpleStringProperty getBuild() {
		return build;
	}

	public SimpleStringProperty getPackageing() {
		return packageing;
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

	private String getPascalCase(String incase){
		return incase.substring(0,1).toUpperCase() + incase.substring(1).toLowerCase();
	}

	public String getJavaFileName() {
		String fileName = "Sampleapp" + getPascalCase(this.appTypeProperty().getValue()) + getPascalCase(this.profileProperty().getValue()) + this.requiredVersionProperty().getValue() + getPascalCase(this.appName.getValue());
		return fileName.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\.", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("_", "");
	}

	public TCResult getAppResult() {
		ArrayList<StringProperty> propertyList = new ArrayList<StringProperty>();
		propertyList.add(this.buildProperty());
		propertyList.add(this.packageingProperty());
		propertyList.add(this.installProperty());
		propertyList.add(this.launchProperty());
		propertyList.add(this.exploratoryProperty());
		propertyList.add(this.closeProperty());
		propertyList.add(this.uninstallProperty());
		propertyList.add(this.crashProperty());

		boolean isPass = false;
		for (int index = 0; index < propertyList.size(); index++) {
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

	public long getTimeBuild() {
		return timeBuild;
	}

	public void setTimeBuild(long timeBuild) {
		this.timeBuild = timeBuild;
		setTimeTotal();
	}

	public long getTimePackage() {
		return timePackage;
	}

	public void setTimePackage(long timePackage) {
		this.timePackage = timePackage;
		setTimeTotal();
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
		this.timeTotal = getTimeBuild() + getTimePackage() + getTimeInstall() + getTimeLaunch() +
				getTimeExporatory() + getTimeClose() + getTimeUninstall() + getTimeCrash();
	}

}
