package com.rad;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * GUI to create the window
 *@author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Window {
	/**
	 * instance of game
	 */
	private Game game;

	/**
	 * passes a version of game into the window
	 * @param game runs the game
	 */
	public Window(Game game) {
		this.game = game;
	}

	/**
	 * creates the  window
	 */
	public void init() {
		JFrame frame = new JFrame(Const.TITLE);
		
		frame.setPreferredSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));
		frame.setMinimumSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));
		frame.setMaximumSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the game if closed
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // Opens in the center of the screen
		
		frame.add(game);
		
		frame.setVisible(true);
	}
	
}
