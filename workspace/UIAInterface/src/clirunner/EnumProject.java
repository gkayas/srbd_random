package clirunner;

public enum EnumProject {
	STOREAPP("storeapp"),
	SAMPLEAPP("sampleapp");
	
	private String value;
	
	private EnumProject(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
