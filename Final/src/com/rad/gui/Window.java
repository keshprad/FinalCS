package com.rad.gui;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.rad.Const;
import com.rad.Game;

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
	 * is the Jframe frame of our game
	 */
	private JFrame frame;

	/**
	 * passes a version of game into the window
	 * @param game runs the game
	 */
	public Window(Game game) {
		this.game = game;
		frame = new JFrame(Const.TITLE);
	}

	/**
	 * creates the  window
	 */
	public void init() {
		frame.setPreferredSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));
		frame.setMinimumSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));
		frame.setMaximumSize(new Dimension(Const.FRAME_WIDTH, Const.FRAME_HEIGHT));
		frame.setUndecorated(true); // remove title bar
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the game if closed
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // Opens in the center of the screen
		frame.add(game);
		
		frame.setVisible(true);
	}

	/**
	 * closes this window
	 */
	public void closeWindow() {
		frame.setVisible(false);
		frame.dispose();
	}
	
}
