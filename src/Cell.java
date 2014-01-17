import java.awt.Color;
import java.awt.Rectangle;


public class Cell extends Rectangle {
	Color myColor;
	Logic mybrain = new Logic();
	String state = "";
	
	public Color getColor(){
		return myColor;
	}
	
	public Cell(double d, double e){
		setSize(mybrain.CELL_SIZE);
		setBounds((int)d, (int)e, (int)getWidth(), (int)getHeight());
	}
	
	public void setState(String s){
		state = s;
	}
	
	public String getState(){
		return state;
	}
}
