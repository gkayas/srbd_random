package refapp;


public class TestInfo {
	
	private int level;
	private String tcName;
	private String preCondition;
	private TCResult result;
	private Boolean isMethodExists;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	public String getPreCondition() {
		return preCondition;
	}
	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}
	public TCResult getResult() {
		return result;
	}
	public void setResult(TCResult result) {
		this.result = result;
	}
	public Boolean isMethodExists() {
		return isMethodExists;
	}
	public void setIsMethodExists(Boolean isMethodExists) {
		this.isMethodExists = isMethodExists;
	}
	

}
