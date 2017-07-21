package clirunner;

public class TestInfo {

	private int level;
	private String tcName;
	private TCResult result;
	private long elapseTime;
	private String preCondition = "";


	public TestInfo(String tcName, TCResult tcResult,long elapseTime) {
		setTcName(tcName);
		setResult(tcResult);
		setElapseTime(elapseTime);
	}

	public long getElapseTime() {
		return elapseTime;
	}

	public void setElapseTime(long elapseTime) {
		this.elapseTime = elapseTime;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public TCResult getResult() {
		return result;
	}

	public void setResult(TCResult result) {
		this.result = result;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

}
