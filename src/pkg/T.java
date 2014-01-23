package pkg;
import java.awt.Color;



public class T extends Figure{
	Logic mybrain = new Logic();
	public T(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell left = new Cell(center.getX()-center.getSize().getHeight(),center.getY());
		Cell right = new Cell(center.getX()+center.getSize().getHeight(),center.getY());
		Cell bottom = new Cell(center.getX(),center.getY()+center.getSize().getHeight());
		
		myCells[0] = left;
		myCells[1] = right;
		myCells[2] = bottom;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.pink);
	}
}
