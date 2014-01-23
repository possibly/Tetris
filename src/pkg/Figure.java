package pkg;
import java.awt.Color;
import java.awt.Rectangle;



public class Figure extends Rectangle {
	Color myColor;
	Cell[] myCells;
	Cell rotatingCell;
	
	public Cell[] getCells(){
		return myCells;
	}
	
	public Cell getRotatingCell(){
		return rotatingCell;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public void setCells(Cell[] toset){
		myCells = toset;
	}
	
	public void setRotatingCell(Cell c){
		rotatingCell = c;
		for(int i = 0; i < myCells.length;i++){ //add rotatingCell to myCells
			if(myCells[i] == null){
				myCells[i] = rotatingCell;
			}
		}
	}
	
	public void setColor(Color c){
		myColor = c;
	}
	//Credit http://gamedev.stackexchange.com/questions/17974/how-to-rotate-blocks-in-tetris for the formula!
	public void rotateRight(){
		for(Cell c:getCells()){
			int x = (int) c.getX() - (int) getRotatingCell().getX();
			int y = (int) c.getY() - (int) getRotatingCell().getY();
			
			int x1 = -y;
			int y1 = x;
			
			int newx = (int)getRotatingCell().getX() + x1;
			int newy = (int)getRotatingCell().getY() + y1;
			c.setBounds(newx,newy,(int)c.getWidth(),(int)c.getHeight());
		}
	}
	
	public void rotateLeft(){
		for(Cell c:getCells()){
			int x = (int) c.getX() - (int) getRotatingCell().getX();
			int y = (int) c.getY() - (int) getRotatingCell().getY();
			
			int x1 = -y;
			int y1 = x;
			
			int newx = (int)getRotatingCell().getX() - x1;
			int newy = (int)getRotatingCell().getY() - y1;
			c.setBounds(newx,newy,(int)c.getWidth(),(int)c.getHeight());
		}
	}
}
