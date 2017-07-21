package device;

import java.io.File;
import java.util.Scanner;

public class SecurityProfile {

	private String  certPath;
	private String  certName;
	private String  keyName;
	private String  keyPWD;
	
	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}
	
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyPWD() {
		return keyPWD;
	}

	public void setKeyPWD(String keyPWD) {
		this.keyPWD = keyPWD;
	}
	
	public SecurityProfile(String certPath, String keyName,String keyPWD) {
		
		if(!isDataCorrect(certPath, keyName, keyPWD)){
			createDefaultCertificate();
		} else{
			setCertificateData(certPath, keyName, keyPWD);
		}
		
		addSecurityProfile();
	}
	
	private void setCertificateData(String certPath, String keyName,String keyPWD) {
		setCertPath(certPath);
		String certName = new File(certPath).getName();
		if(certName.contains(".p12")) 
			certName = certName.split(".p12")[0].trim();
		setCertName(certName);
		setKeyName(keyName);
		setKeyPWD(keyPWD);
	}
	
	boolean isExist(){
		File file = new File(getCertPath());
		if(file.exists() && !file.isDirectory()) 
			return true;
		return false;
	}

	boolean createDefaultCertificate(){
		String currentPath = System.getProperty("user.dir") + "/temp";
		//setCertificateData(ResourceUtils.getSdkDataPath() + "/keystore/author/" + Constants.DEFAULT_CERT_NAME, Constants.DEFAULT_CERT_KEY_NAME, Constants.DEFAULT_CERT_PASSWORD);
		setCertificateData(currentPath + "/" +Constants.DEFAULT_CERT_NAME, Constants.DEFAULT_CERT_KEY_NAME, Constants.DEFAULT_CERT_PASSWORD);
		if(isExist()) { 
		    return true;
		}
		else {
			DeviceUtils.runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen certificate -a " + getKeyName() +" -p " + getKeyPWD() + " -c Bangladesh -s Dhaka -ct Dhaka -o SRBD -u SWEG -n \"Tizen TCT\" -e srbd@example.org -f " + getCertName() + " -- " + currentPath);
			if(isExist()) { 
			    return true;
			}
		}
		return false;
	}
	
	private boolean deleteCertificate(){
		File f = new File(getCertPath());
		if(f.exists() && !f.isDirectory()) { 
		    return f.delete();
		}
		return true;
	}
	
	boolean remove(){
		DeviceUtils.runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen security-profiles remove -n " + getKeyName());
		deleteCertificate();
		if(isExist()) 
			return false;
		return true;
	}
	
	static boolean isDataCorrect(String certPath, String keyName,String keyPWD){
		
		if(certPath == null || keyName == null || keyPWD == null || !new File(certPath).exists())
			return false;
		return true;
			
	}
	
	boolean addSecurityProfile(){
		if(isProfileAdded())
			return true;
		else{
			DeviceUtils.runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen security-profiles add -n " + getKeyName() + " -a " + getCertPath() + " -p " + getKeyPWD());
			if(isProfileAdded())
				return true;
		}
		return false;
	}
	
	void setDefault(){
		DeviceUtils.runCommand(ResourceUtils.getSdkPath() + "/tools/ide/bin/tizen " + "cli-config \"default.profiles.path=" + ResourceUtils.getSdkPath() + "/tools/ide/conf/profiles.xml\"");
	}

	private boolean isProfileAdded() {
		String profilesXmlPath = ResourceUtils.getSdkDataPath() + "/ide/keystore/profiles.xml";
		try 
		{		
			Scanner sc = new Scanner(new File(profilesXmlPath));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				if(line.contains(getCertPath())){
					sc.close();
					return true;
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("[AutomatorCore] " + e.getMessage());
		}
		return false;
	}

}
