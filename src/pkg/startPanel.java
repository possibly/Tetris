package pkg;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
	
	public void initImages(){
		URL url = getClass().getResource("header.png");
		Image image = new ImageIcon(url).getImage();
		picLabel = new JLabel(new ImageIcon(image));
		picLabel.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public void addStuff(){
		add(picLabel);
		add(new Box(1).createRigidArea(new Dimension(getWidth()/2,getHeight()/4)));
		add(start);
	}
}
