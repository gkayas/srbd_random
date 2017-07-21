package clirunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigReader {

	private HashMap<String, String> devInfo = new HashMap<String, String>();

	public ConfigReader() throws IOException
	{
		read(Constant.UIATOOL_CONFIG_FILE);
		read(Constant.DEVICE_CONFIG_FILE);
	}

	public void read(String path) {
		try{
			Scanner sc = new Scanner(new File(path));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String tokens[] = line.split("=");
				if(tokens.length == 1){
					devInfo.put(tokens[0].trim(), "");
				}else{
					devInfo.put(tokens[0].trim(), tokens[1].trim());
				}
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(path + " file not found");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Wrong formated Config File.");
			System.exit(0);
		}
	}

	public String getReportpath() {
		return devInfo.get(Constant.UIATOOL_CONFIG_TAG_REPORT_PATH) + "";
	}

	public String getSdkPath() {
		return devInfo.get(Constant.SDK_PATH) + "";
	}

}
