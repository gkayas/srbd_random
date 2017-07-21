package clirunner;

import java.util.ArrayList;

public class AppInfo {

	private String appName;
	private String profile;
	private String appType;
	private String rootstrap;
	private String binaryPath;
	private TCResult appResult;
	private long appElapseTime;
	private String javaFileName;
	private String requiredVersion;
	private ArrayList<TestInfo> tcList;

	public AppInfo() {
		this.tcList = new ArrayList<TestInfo>();
	}

	public void setTCResult(String tcName, TCResult result) {
		TestInfo tcInfo = getTestInfo(tcName);
		tcInfo.setResult(result);
		this.appResult = calculateAppResult();
	}

	public void setTCTime(String tcName, long elaspseTime) {
		TestInfo tcInfo = getTestInfo(tcName);
		tcInfo.setElapseTime(elaspseTime);
		this.appElapseTime = calculateAppElapseTime();
	}

	public TestInfo getTestInfo(String tcName) {
		TestInfo tcInfo = null;
		for (TestInfo testInfo : tcList) {
			if(testInfo.getTcName().equals(tcName)) {
				tcInfo = testInfo;
			}
		}
		return tcInfo;
	}

	private TCResult calculateAppResult() {
		boolean isPass = false;
		for (TestInfo testInfo : tcList) {
			if(testInfo.getResult().equals(TCResult.Fail)){
				return TCResult.Fail;
			}else if(testInfo.getResult().equals(TCResult.Pass)) {
				isPass = true;
			}
		}
		if(isPass)
			return TCResult.Pass;
		else
			return TCResult.Fail;
	}

	private long calculateAppElapseTime() {
		long time = 0;
		for (TestInfo testInfo : tcList) {
			time += testInfo.getElapseTime();
		}
		return time;
	}

	public TCResult getAppResult() {
		return appResult;
	}

	public long getAppElapseTime() {
		return appElapseTime;
	}

	public ArrayList<TestInfo> getTcList() {
		return tcList;
	}
	public void setTcList(ArrayList<TestInfo> tcList) {
		this.tcList = tcList;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getJavaFileName() {
		return javaFileName;
	}

	public void setJavaFileName(String javaFileName) {
		this.javaFileName = javaFileName;
	}

	public void setBinaryPath(String binaryPath) {
		this.binaryPath = binaryPath;
	}

	public String getBinaryPath() {
		return binaryPath;
	}

	public String getRootstrap() {
		return rootstrap;
	}

	public void setRootstrap(String rootstrap) {
		this.rootstrap = rootstrap;
	}

	public String getRequiredVersion() {
		return requiredVersion;
	}

	public void setRequiredVersion(String requiredVersion) {
		this.requiredVersion = requiredVersion;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AppInfo)) {
			return false;
		}
		AppInfo target = (AppInfo) obj;
		return this.getAppName().equals(target.getAppName());
	}
}
