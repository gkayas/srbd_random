package device;

public class DeviceInfo{
	private String deviceID;
	private String deviceName;
	private String deviceType;
	
	DeviceInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDeviceID() {
		return deviceID;		
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Override
	public String toString(){
		return deviceID +"\t"+ deviceName +"\t"+ deviceType;
	}
}