import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String [] args) throws FileNotFoundException{
		if(args.length < 1){
			System.out.println("More agrs needed");
			return;
		}
		//1 = input, 2 = old name, 3 = output
		File file = new File("OLD_TO_NEW.map");
		HashMap<String , String> map = new HashMap<String, String>();
		Scanner sc = new Scanner(file);
		while(sc.hasNext()) {
			String oldStr = sc.next();
			String newStr = sc.next();
			map.put(oldStr, newStr);
		}
		sc.close();
		
		if(map.containsKey(args[0])) {
			writeOutput( map.get(args[0]));
		}else {
			writeOutput(args[0]);
		}
			
	}
	
	static void writeOutput( String value) throws FileNotFoundException {
		File file = new File("oldToNew.map");
		PrintWriter pw = new PrintWriter(file);
		
		pw.write(value);
		pw.flush();
		pw.close();
	}
}
