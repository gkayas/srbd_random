package refapp;

public class ResultUtils {
	// TODO: This Class will Make the result and write to XLS File by XlsUtil
	// Class. All Pass Fail Info Will be Found From utils.DeviceInfo Class

	public static void showOutput(String result){
		if(result.indexOf("PASS") > 0)
			System.out.println(result);	
		else
			System.err.println(result);
		
	}
}
