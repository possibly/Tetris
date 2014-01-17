import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Logic {
	//NOTE: I believe blocks may fall through each other sometimes because the panel class executes
	//its own move() while play() executes its own move(). Hard to reproduce.
	final Dimension CELL_SIZE = new Dimension(20,20);
	final Dimension PANEL_SIZE = new Dimension(400,600);
	final Dimension GRID_SIZE = new Dimension(401,601);
	final Dimension STARTPANEL_SIZE = new Dimension(400,600);
	final Dimension SETUPPANEL_SIZE = new Dimension (400,600);
	final Point START_POSITION_OF_FIGURES = new Point(PANEL_SIZE.width/2,PANEL_SIZE.height-PANEL_SIZE.height);
	final int SPEED_MS = 100; //how often play() is called, ins miliseconds
	final int MOVE_TICK = 20; //how far to move, in pixels
	Grid g;
	tetrisPanel panel;
	boolean youLose = false;
	
	
	public void create(){ //this is necessary so I can have access to the variables without re-creating grid or panel
		g = new Grid();
		panel = new tetrisPanel();
		panel.setBrain(this);
	}
	public tetrisPanel getPanel(){
		return panel;
	}
	
	public Dimension getPanelSize(){
		return PANEL_SIZE;
	}
	
	public void play(){
		//create activeFigure if there isint one
		if(g.getActiveFigure() == null){
			createActiveFigure();
		}
		
		//display all figures
		//NOTE: This is not an clean way to draw figures
		displayFigures();
		
		//check if aF can move
		if(canMove(g.getActiveFigure(), "down")){
			//move aF
			move(g.getActiveFigure(),"down");
		}else if(!canMove(g.getActiveFigure(), "down")){ //if figure stops moving
			if((int)g.getActiveFigure().getRotatingCell().getY() <= 0){
				youLose();
				return;
			}
			//set activeFigure to null so we can spawn a new one.
			g.activeFigure = null;
			//check if there is a full line that can clear.
			g.set(GRID_SIZE.getWidth(),GRID_SIZE.getHeight()); //reset the grid
			for(Figure poop:g.getFigures()){ //add all figures to a 2d array
				g.addFigureCellsToGrid(poop);
			}
			ArrayList<Integer> fullRows = g.getFullRows();
			if(fullRows != null){
				g.smashFullRows(fullRows);
				displayFigures();
				//move all non-active figures if they can be moved
				for(Figure f:g.getFigures()){
					if(canMove(f,"down")){
						move(f,"down");
					}
				}
				displayFigures();
			}			
		}
	}
	
	public void createActiveFigure(){
		//First, create get a random figure and create it
		Random generator = new Random();
		ArrayList<String> types = new ArrayList<String>();
		types.add("i");
		types.add("j");
		types.add("l");
		types.add("o");
		types.add("s");
		types.add("t");
		types.add("z");
		
		int i = generator.nextInt(types.size());
		String typeOfFigure = types.get(i);
		
		Figure f = new Figure();
		if (typeOfFigure.equals("i")){
			f = new I();
		}
		if (typeOfFigure.equals("j")){
			f = new J();
		}
		else if (typeOfFigure.equals("l")){
			f = new L();
		}
		else if (typeOfFigure.equals("o")){
			f = new O();
		}
		else if (typeOfFigure.equals("s")){
			f = new S();
		}
		else if (typeOfFigure.equals("t")){
			f = new T();
		}
		else if (typeOfFigure.equals("z")){
			f = new Z();
		}
		
		//Second, add this figure to the grid so we can remember it.
		g.addFigure(f);
	}
	
	public boolean canMove(Figure figureToCheck,String direction){
		//first, lets pretend we move the figure.
		move(figureToCheck,direction);
	
		for(Cell c:figureToCheck.getCells()){
			if(c.getState().equals("dead")){
				break;
			}
			//1) collide with the edges of the panel
			if((int)c.getX() < 0){ //xleft of the panel
				if(direction == "left"){
					unmove(figureToCheck,direction);
					return false; 
				}
				if(direction == "up"){
					unmove(figureToCheck,direction);
					return false;
				}
			}
			else if((int)c.getX() > PANEL_SIZE.getWidth()){ //xright of the panel
				if(direction == "right"){
					unmove(figureToCheck,direction);
					return false;
				}
				if(direction == "up"){
					unmove(figureToCheck,direction);
					return false;
				}
			}
			
			//2) collide with another figure OR go outside of bounds by rotateRight
			//Now, lets go through every other figure
			for(Figure f:g.getFigures()){
				if(f != figureToCheck){ //make sure you dont collide with yourself!
					//if any of there cells intersect (thanks, Rectangle class!) with our figures cells, return false.
					for(Cell c2:f.getCells()){
						if(c.intersects(c2)){
							unmove(figureToCheck,direction); //stop pretending and return to previous position.
							return false;
						}
					}
				}
			}
			
			if((int)c.getY() > PANEL_SIZE.getHeight()){ //ybottom of the panel
				unmove(figureToCheck,direction);
				return false;
			}
		}
		//even if this figures cells dont intersect, it is not canMove's job to actually move the figure!
		unmove(figureToCheck,direction);
		return true;
	}
	
	public void move(Figure figureToMove, String direction){
		if(direction.equals("down")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX(),(int)c.getY()+MOVE_TICK,(int)c.getWidth(),(int)c.getHeight());
			}
		}
		else if (direction.equals("left")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX()-MOVE_TICK,(int)c.getY(),(int)c.getWidth(),(int)c.getHeight());
			}
		}
		else if (direction.equals("up")){
			figureToMove.rotateRight();
		}
		else if (direction.equals("right")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX()+MOVE_TICK,(int)c.getY(),(int)c.getWidth(),(int)c.getHeight());
			}
		}
	}
	
	public void unmove(Figure figureToMove, String direction){
		if(direction.equals("down")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX(),(int)c.getY()-MOVE_TICK,(int)c.getWidth(),(int)c.getHeight());
			}
		}
		else if (direction.equals("left")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX()+MOVE_TICK,(int)c.getY(),(int)c.getWidth(),(int)c.getHeight());
			}
		}
		else if (direction.equals("up")){
			figureToMove.rotateLeft();
		}
		else if (direction.equals("right")){
			for(Cell c:figureToMove.getCells()){
				c.setBounds((int)c.getX()-MOVE_TICK,(int)c.getY(),(int)c.getWidth(),(int)c.getHeight());
			}
		}
	}
	
	public void youLose(){
		Runner.t.stop();
		g.turnGrey();
		panel.setLabelToDraw("You Lose.");
		displayFigures();
		
	}
	//methods for panels
	public void displayFigures(){
		ArrayList<Figure> figuresToDraw = new ArrayList<Figure>();
		for(Figure f:g.getFigures()){
			figuresToDraw.add(f);
		}
		panel.setFiguresToDraw(figuresToDraw);
		panel.repaint();
	}
}
