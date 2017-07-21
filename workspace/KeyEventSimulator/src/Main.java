
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class Main {
	public static final int XF86XK_Home = 0x10090001;
	public static void main(String [] arg) throws InterruptedException {
		try {
	        Robot robot = new Robot();
	        
	        Thread.sleep(5000);
	        // Simulate a key press
//	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_A);
	        robot.keyRelease(KeyEvent.VK_A);
//	        robot.keyRelease(KeyEvent.VK_CONTROL);
	       

	} catch (AWTException e) {
	        e.printStackTrace();
	}
		
	}
	
}
