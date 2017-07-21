package fxviewer;

public class SampleApp {
	
	private String appName;
	private String appType;
	private String profile;
	private String requiredVersion;
	
	public SampleApp(String appName,String appType, String profile, String requiredVersion){
		setAppType(appType);
		setProfile(profile);
		setAppName(appName);
		setRequiredVersion(requiredVersion);
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRequiredVersion() {
		return requiredVersion;
	}

	public void setRequiredVersion(String requiredVersion) {
		this.requiredVersion = requiredVersion;
	}
	
}