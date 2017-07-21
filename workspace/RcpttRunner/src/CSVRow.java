
public class CSVRow {
	private String tcmID;
	private String tcID;
	private String module;
	private String suite;
	private String platform;
	private String profile;
	
	public String rowName(boolean flag) {
		return flag?module:platform;
	}
	
	public String getTcmID() {
		return tcmID;
	}
	public void setTcmID(String tcmID) {
		this.tcmID = tcmID;
	}
	public String getTcID() {
		return tcID;
	}
	public void setTcID(String tcID) {
		this.tcID = tcID;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
