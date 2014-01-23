package pkg;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;



public class Grid {
	Cell[][] theGrid;
	ArrayList<Figure> theFigures = new ArrayList<Figure>();
	Figure activeFigure;
	Logic mybrain = new Logic();
	
	public Grid(){
	}
	
	public void addFigure(Figure f){
		//any figure we add is going to be the figure were going to want to control,
		//so lets make it easy to reference this figure.
		setActiveFigure(f);
		
		//We also want to add this figure to a list of figures so we can easily access 
		//the figures that are on screen.
		addToFigureList(f);
	}
	
	public ArrayList<Figure> getFigures(){
		return theFigures;
	}
	
	public Figure getActiveFigure(){
		if(activeFigure != null){
			return activeFigure;
		}
		return null;
	}
	
	public void addFigureCellsToGrid(Figure f){
		//adds this figures cells to theGrid
		Cell[] figuresCells = f.getCells();
		for(int i = 0; i < figuresCells.length; i++){
			if(figuresCells[i].getState().equals("dead")){
				break;
			}
			theGrid[(int)figuresCells[i].getX()][(int)figuresCells[i].getY()] = figuresCells[i];
		}
	}
	
	public void setActiveFigure(Figure f){
		activeFigure = f;
	}
	
	public void addToFigureList(Figure f){
		theFigures.add(f);
	}
	
	public void set(double w, double h){
		theGrid = new Cell[(int)w][(int)h];
	}
	
	public ArrayList<Integer> getFullRows(){
		//NOTE: Cells are added in increments of CELL_SIZE to theGrid!
		int widthOfGrid = (int)mybrain.PANEL_SIZE.getWidth()-30;
		int widthOfCell = (int)mybrain.CELL_SIZE.getWidth();
		int numCellsInRow = 0;
		ArrayList<Integer> fullRows = new ArrayList<Integer>();
		for(int y = 0; y < (int)mybrain.GRID_SIZE.height;y++){
			for(int x = 0; x < theGrid.length;x++){
				if(theGrid[x][y] != null){
					numCellsInRow++;
				}
			}
			if(widthOfGrid+widthOfCell == widthOfCell*numCellsInRow){
				fullRows.add(y);
			}
			numCellsInRow = 0;
		}
		return fullRows;
	}
	
	public void smashFullRows(ArrayList<Integer> fullRows){
		for(Integer i:fullRows){
			for(Figure f:getFigures()){
				for(Cell c:f.getCells()){
					if((int)c.getY() == (int)i){
						c.setBounds(-100,-100,0,0);
						c.setState("dead");
						for(Cell d:f.getCells()){
							c.setState("dead");
						}
					}
				}
			}
		}
	}
	
	public void turnGrey(){
		for(Figure f:getFigures()){
			f.setColor(Color.GRAY);
		}
	}
}
