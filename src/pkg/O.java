package pkg;
import java.awt.Color;



public class O extends Figure{
	Logic mybrain = new Logic();
	public O(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell farLeft = new Cell(center.getX()+center.getSize().getWidth(), center.getY());
		Cell bottomLeft = new Cell(farLeft.getX(),center.getY()+center.getSize().getHeight());
		Cell bottomRight = new Cell(center.getX(),bottomLeft.getY());
		
		myCells[0] = farLeft;
		myCells[1] = bottomLeft;
		myCells[2] = bottomRight;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.yellow);
	}
}
