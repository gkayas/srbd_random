package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockInfo {
	private SimpleStringProperty comName;
	private SimpleStringProperty lastPrice;
	private SimpleStringProperty changeInBdt ;
	private SimpleStringProperty changeInPer;
	private SimpleBooleanProperty checkBox = new SimpleBooleanProperty(false);
	
	public StockInfo (String com, String price, String taka, String perCent){ 
		comName = new SimpleStringProperty(com);
		lastPrice = new SimpleStringProperty(price);
		changeInBdt = new SimpleStringProperty(taka);
		changeInPer = new SimpleStringProperty(perCent);
	}
	
	public SimpleStringProperty comNameProperty() {
		return comName;
	}
	
	public SimpleStringProperty lastPriceProperty() {
		return lastPrice;
	}
	
	public SimpleStringProperty changeInBdtProperty() {
		return changeInBdt;
	}
	
	public SimpleStringProperty changeInPerProperty() {
		return changeInPer;
	}

	public SimpleBooleanProperty checkBoxProperty() {
        return this.checkBox;
    }
	
	public void setComName(SimpleStringProperty type) {
		this.comName = type;
	}
	
	public void setLastPrice(SimpleStringProperty type) {
		this.lastPrice = type;
	}
	
	public void setChangeInBdt(SimpleStringProperty type) {
		this.changeInBdt = type;
	}
	
	public void setChangeInPer(SimpleStringProperty type) {
		this.changeInPer = type;
	}
	
	public void setCheckBox(final java.lang.Boolean checked) {
        this.checkBoxProperty().set(checked);
    }	
	
	public String getComName() {
		return comName.getValue();
	}
	
	public void setSelected(boolean selected) {
		checkBox = new SimpleBooleanProperty(selected);
	}
	
	public boolean isSelected() {
		return checkBox.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StockInfo)) return false;
		
		return ((StockInfo)obj).getComName().equals(this.getComName());
	}
	
	@Override
	public String toString() {
		return this.comName.getValue() +" "+ this.lastPrice.getValue() +" "+ this.changeInBdt.getValue() +" "+ this.changeInPer.getValue();
	}
}
