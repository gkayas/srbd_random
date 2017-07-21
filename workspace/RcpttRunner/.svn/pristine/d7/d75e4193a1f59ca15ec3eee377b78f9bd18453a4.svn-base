import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;

public class TCSuite /*implements Comparable<TCSuite>*/{
	private String name;
	private String platform;
	private String profile;
	private ArrayList<Testcase> tcList;
	private ArrayList<Testcase> failTcList;
	
	public TCSuite() {
		tcList = new ArrayList<Testcase>();
		failTcList = new ArrayList<Testcase>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Testcase> getTcList() {
		return tcList;
	}

	public void addTc(Testcase tc) {
		tcList.add(tc);
	}

	public void addToFailList(Testcase failTc) {
		failTcList.add(failTc);
	}
	
	public ArrayList<Testcase> getFailTcList() {
		return failTcList;
	}

	public boolean containsTc(Testcase testCase) {
		return tcList.contains(testCase);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TCSuite)) {
			return false;
		} 
		TCSuite suite = (TCSuite) obj;
		return (this.name.equals(suite.name)); 
	}

}
