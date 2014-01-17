import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class startPanel extends JPanel{
	JButton start;
	JButton setup;
	BufferedImage banner;
	JLabel picLabel;
	
	public startPanel(Dimension size){
		init(size);
		initButtons();
		initImages();
		addStuff();
	}
	
	public void init(Dimension size){
		setSize(size);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		setBackground(Color.black);
	}
	
	public void initButtons(){
		start = new JButton("Start");
		start.setAlignmentX(CENTER_ALIGNMENT);
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Runner.startGame();
				Runner.startFrame.setVisible(false);
			}
			
		});
	}
	
	//thanks http://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe
	public void initImages(){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("/home/tyler/simpleworkspace/tylerBrothers_reduxTetris/src/header.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Nope");
		}
		picLabel = new JLabel(new ImageIcon(image));
		picLabel.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public void addStuff(){
		add(picLabel);
		add(new Box(1).createRigidArea(new Dimension(getWidth()/2,getHeight()/4)));
		add(start);
	}
}
