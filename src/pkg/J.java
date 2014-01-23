package pkg;
import java.awt.Color;



public class J extends Figure{
	Logic mybrain = new Logic();
	public J(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell centerDown = new Cell(center.getX(),center.getY()-center.getSize().getWidth());
		Cell centerDownDown = new Cell(center.getX(),center.getY()-center.getSize().getWidth()*2);
		Cell left = new Cell(center.getX()-center.getSize().getWidth(),center.getY());
		
		myCells[0] = centerDown;
		myCells[1] = centerDownDown;
		myCells[2] = left;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.blue);
	}
}
