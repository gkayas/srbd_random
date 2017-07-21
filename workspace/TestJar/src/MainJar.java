
public class MainJar {
	public static void main(String [] args) throws InterruptedException {
		printMsg(args[0]);		
	}
	
	public static void printMsg(String s) throws InterruptedException {
		
		
		for(int x = 0 ; x < 20; x++) {
			
			if(s.equals("Run")) {
				System.out.println("Run");
				System.err.println("Run error");
			} else if(s.equals("Re-run")){
				System.out.println("Re-run");
			} else if(s.equals("Others")) {
				System.out.println("Others");
			} 
			Thread.sleep(100);
		}
		
	}
}
