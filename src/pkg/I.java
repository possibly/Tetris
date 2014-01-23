package pkg;
import java.awt.Color;



public class I extends Figure{
	Logic mybrain = new Logic();
	public I(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell farLeft = new Cell(center.getX()-center.getSize().getWidth(),center.getY());
		Cell right = new Cell(center.getX()+center.getSize().getWidth(),center.getY());
		Cell farRight = new Cell(center.getX()+2*center.getSize().getWidth(),center.getY());
		
		myCells[0] = farLeft;
		myCells[1] = right;
		myCells[2] = farRight;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.cyan);
	}
}
