import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class tetrisPanel extends JPanel implements KeyListener{
	ArrayList<Figure> figuresToDraw = new ArrayList<Figure>();
	String labelToDraw;
	Logic mybrain;
	Font f = new Font("TimesRoman", Font.PLAIN, 40);
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Figure f:figuresToDraw){
			for(Cell c:f.getCells()){
				g.setColor(f.getColor());
				g.fillRect((int)c.getX(), (int)c.getY(), (int)c.getWidth(), (int)c.getHeight());
			}
		}
		
		if(labelToDraw != null){
			g.setColor(Color.red);
			g.setFont(f); 
			g.drawString(labelToDraw, mybrain.getPanelSize().width/4, mybrain.getPanelSize().height/4);
		}
	}	
	
	public void setFiguresToDraw(ArrayList<Figure> l){
		figuresToDraw = l;
	}
	
	public void setLabelToDraw(String t){
		labelToDraw = t;
	}
	
	public void setBrain(Logic l){ //needed because of shitty keylistener
		mybrain = l;
	}
	
	@Override
	//Does keyListener need to be on panel because I want it in logic :(
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 37){ //left arrow
			if(mybrain.canMove(mybrain.g.getActiveFigure(), "left")){
				mybrain.move(mybrain.g.getActiveFigure(), "left");
			}
		}
		else if (arg0.getKeyCode() == 39){ //right arrow
			if(mybrain.canMove(mybrain.g.getActiveFigure(), "right")){
				mybrain.move(mybrain.g.getActiveFigure(), "right");
			}
		}
		else if (arg0.getKeyCode() == 40){ //down arrow
			if(mybrain.canMove(mybrain.g.getActiveFigure(), "down")){
				mybrain.move(mybrain.g.getActiveFigure(), "down");
			}
		}
		else if (arg0.getKeyCode() == 38){ //up arrow
			if(mybrain.canMove(mybrain.g.getActiveFigure(), "up")){
				mybrain.move(mybrain.g.getActiveFigure(), "up");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public tetrisPanel(){
		addKeyListener(this);
		setBackground(Color.white);
	}
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
