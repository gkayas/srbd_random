import org.junit.*;
import base.TCBase;

public class TestCase extends TCBase
{
	public TestCase()
	{
		super();		
	}
	
	public TestCase(String s)
	{
		super(s);
	}
	
	@Test
	public void testCases() throws Exception

 { 
device.launchApp("org.tizen.gallery");
device.waitForImage(6000, "images.png");
device.tapImage("images.png");
}
}