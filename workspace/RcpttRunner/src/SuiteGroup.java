import java.util.ArrayList;

public class SuiteGroup {
	String platform;
	String profile;
	ArrayList<TCSuite> suiteList;
	
	public SuiteGroup(String platform, String profile) {
		this.platform = platform;
		this.profile = profile;
	
		this.suiteList = new ArrayList<TCSuite>();
	}
	
	public void addSuite(TCSuite suite) {
		if(!suiteList.contains(suite)) {
			suiteList.add(suite);
		}
	}
	
	public String getPlatform() {
		return platform;
	}

	public String getProfile() {
		return profile;
	}

	public ArrayList<TCSuite> getSuiteList() {
		return suiteList;
	}
	
	public String getSuiteString() {
		String suiteString = "";
		
		for (TCSuite tcSuite : suiteList) {
			suiteString += tcSuite.getName() + ";";
		}
		
		suiteString = suiteString.substring(0, suiteString.length() - 1);
		return suiteString;
	}

	public ArrayList<Testcase> getFailTcList() {
		ArrayList<Testcase> failTcList = new ArrayList<Testcase>();
		
		for (TCSuite suit : suiteList) {
			failTcList.addAll(suit.getFailTcList());
		}
		
		return failTcList;
	}
	
	public TCSuite getSuiteForTc(Testcase tc) {
		for (TCSuite tcSuite : suiteList) {
			if(tcSuite.containsTc(tc)) {
				return tcSuite;
			}
		}
		return null;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof SuiteGroup)) {
			return false;
		}
		SuiteGroup target = (SuiteGroup) obj;
		return this.platform.equals(target.platform) && this.profile.equals(target.profile);
	}	
	
}
