package com.rad.gui;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.rad.Const;
import com.rad.Game;

/**
 * GUI to create the window
 *@author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 * @version 1.0
 */
public class Window {
	/**
	 * is an instance of game used to implement the GUI
	 */
	private Game game;
	/**
	 * is the frame which holds the game itself
	 */
	private JFrame frame;

	/**
	 * passes a version of game into the window and creates a jframe frame with a title
	 * @param game initializes the game to the private variable game
	 */
	public Window(Game game) {
		this.game = game;
		frame = new JFrame(Const.TITLE);
	}

	/**
	 * creates the  window with determined size, doesn't allow it to move or resize, and adds the game.
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
