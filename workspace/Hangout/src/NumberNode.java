
public class NumberNode extends Node{
	
	
	public NumberNode(int value, int operation, int posX, int posY) {
		super(value, operation,  posX,  posY);
		setType(0);
	}


	@Override
	public int getValue() {

		return value;
	}
	

}
