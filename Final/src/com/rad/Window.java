package com.rad;

import javax.swing.JFrame;

public class Window {
	
	private Game game;
	
	public Window(Game game) {
		this.game = game;
	}
	
	public void init() {
		JFrame frame = new JFrame(Constants.TITLE);
		
		frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the game if closed
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // Opens in the center of the screen
		
		frame.add(game);
		
		frame.setVisible(true);
	}
	
}
