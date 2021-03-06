package pkg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Runner {
	final static Logic brain = new Logic();
	//static JFrame frameTetris;
	static JFrame startFrame;
	static JPanel startPanel;
	static Timer t;
	
	public static void main(String[] args) {
		brain.create();
		
		init();

		startFrame.add(startPanel);
		
		startFrame.pack();
		startFrame.setVisible(true);
	}
	public static void init(){	
		startFrame = new JFrame("Welcome to Tyler's Tetris");
		startFrame.setDefaultCloseOperation(startFrame.EXIT_ON_CLOSE);
		startFrame.setMinimumSize(brain.STARTPANEL_SIZE);
		startFrame.setLayout(new BorderLayout());
		
		startPanel = new startPanel(brain.STARTPANEL_SIZE);
	}
	
	public static void startGame(){
		brain.setTetrisFrameVisible();
		t = new Timer(brain.SPEED_MS, new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent e) {
				brain.play();
		 	}
		});
		t.start();

	}

}
