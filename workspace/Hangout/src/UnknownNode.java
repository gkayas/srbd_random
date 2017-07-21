
public class UnknownNode extends Node {

	
	public UnknownNode(int value, int operation, int posX, int posY) {
		super(value, operation, posX, posY);
		setType(1);
	}



	@Override
	public int getValue() {
	
		return value;
	}

	
}
