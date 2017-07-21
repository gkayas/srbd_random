
public class Testcase implements Comparable<Testcase>{

	private String suiteName;
	private String name;
	private String status;
	private String msg;
	private String module;
	private String platform;
	private String profile;
	private String tcmID;
	
	private boolean isIncomplete = false;


	public Testcase() {
		this.suiteName = "";
		this.name = "";
		this.status = "";
		this.msg = "";
	}
		
	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getTcmID() {
		return tcmID;
	}

	public void setTcmID(String tcmID) {
		this.tcmID = tcmID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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
	
	public boolean isIncomplete() {
		return isIncomplete;
	}

	public void setIncomplete(boolean isIncomplete) {
		this.isIncomplete = isIncomplete;
	}


	public void copy(Testcase target) {
		this.setMsg(target.getMsg());
//		this.setName(target.getName());
		this.setStatus(target.getStatus());
		//		this.setTimeRerun(this.getTimeRerun() + target.getTimeRun());
		//		this.rerunCount++;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Testcase)) {
			return false;
		}
		Testcase obj = (Testcase) o;
		return this.name.equals(obj.name);

	}

	@Override
	public int compareTo(Testcase o) {

		return this.suiteName.compareTo(o.suiteName);
	}

	//	public void setTimeRun(String text) {
	//		setTimeRun(Double.parseDouble(text));
	//		
	//	}
	//
	//	public void setTimeRerun(String text) {
	//		setTimeRerun(Double.parseDouble(text));
	//		
	//	}
	//
	//	public void setRerunCount(String text) {
	//		setRerunCount(Integer.parseInt(text));
	//		
	//	}

}
