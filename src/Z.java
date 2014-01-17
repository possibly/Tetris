import java.awt.Color;


public class Z extends Figure{
	Logic mybrain = new Logic();
	public Z(){
		Cell [] myCells = new Cell [4];
		Cell center = new Cell(mybrain.START_POSITION_OF_FIGURES.getX(),mybrain.START_POSITION_OF_FIGURES.getY());
		Cell topLeft = new Cell(center.getX()-center.getSize().getWidth(),center.getY());
		Cell bottom = new Cell(center.getX(),center.getY()+center.getSize().getHeight());
		Cell bottomRight = new Cell(center.getX()+center.getSize().getWidth(),bottom.getY());
		
		myCells[0] = topLeft;
		myCells[1] = bottom;
		myCells[2] = bottomRight;
		setCells(myCells);
		
		//setRotatingCell should be done last because otherwise it will get overriden when we call setCells.
		//center gets added to setCells in the setRotatingCell function.
		setRotatingCell(center);
		
		setColor(Color.red);
	}

}
