package pkg;
import java.awt.Color;



public class S extends Figure{
	Logic mybrain = new Logic();
	public S(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell topRight = new Cell(center.getX()+center.getSize().getWidth(),center.getY());
		Cell bottom = new Cell(center.getX(),center.getY()+center.getSize().getHeight());
		Cell bottomLeft = new Cell(center.getX()-center.getSize().getWidth(),bottom.getY());
		
		myCells[0] = topRight;
		myCells[1] = bottom;
		myCells[2] = bottomLeft;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.green);
	}
}
