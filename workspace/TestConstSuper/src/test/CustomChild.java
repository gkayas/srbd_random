package test;

public class CustomChild extends Custom{

	private String cmsg;
	public CustomChild(String a, String b) {
		super(a);
		cmsg = b;
	}
	public String toString() {
		
		return "I am custom child: "+ cmsg;
	}
}
