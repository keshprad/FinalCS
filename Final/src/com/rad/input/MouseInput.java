package com.rad.input;

import com.rad.Const;
import com.rad.Game;
import com.rad.gui.StartMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * is the mouse input of the code
 * @author RAD
 * @version 1.0
 */
public class MouseInput implements MouseListener {
	/**
	 * boolean is true if start button has been clicked.
	 */
	private boolean startMenuStartButtonClicked = false;

	/**
	 * boolean is true if exit button has been clicked.
	 */
	private boolean startMenuExitButtonClicked = false;

	private boolean endMenuReturnButtonClicked = false;
	
	/**
	 * This represents the game
	 */
	private Game game;

	/**
	 * is the mouse input of the game
	 * @param g
	 */
	public MouseInput(Game g) {
		this.game = g;
	}

	/**
	 * is if the mouse button pressed
	 * @param e is the mouse event pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {        
        if (game.getGameState() == Game.GameState.START) {
	        startMenuStartButtonClicked = Const.START_MENU.START_BTN.contains(e.getX(), e.getY());
	        startMenuExitButtonClicked = Const.START_MENU.EXIT_BTN.contains(e.getX(), e.getY());
        }
        else if (game.getGameState() == Game.GameState.PLAYING) {
        	//do nothing for now
        }
        else if (game.getGameState() == Game.GameState.END) {
        	endMenuReturnButtonClicked = Const.END_MENU.RETURN_BTN.contains(e.getX(), e.getY());
        }
    }

	/**
	 * auto generated code for the mouse event if its clicked
	 * @param e button pressed and location
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * is the mouse button released
	 * @param e is the mouse button released
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		startMenuStartButtonClicked = false;
		startMenuExitButtonClicked = false;
		endMenuReturnButtonClicked = false;
	}

	/**
	 * is the mouse button entered
	 * @param e mouse event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * mouse exited movement
	 * @param e mouse event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * checks if the menu start button is clicked
	 * @return  if the menu start button is clicked
	 */
	public boolean isStartMenuStartButtonClicked() {
		return startMenuStartButtonClicked;
	}

	/**
	 * checks if the menu exit button is clicked
	 * @return if the menu exit button is clicked
	 */
	public boolean isStartMenuExitButtonClicked() {
		return startMenuExitButtonClicked;
	}
	
	/**
	 * checks if the menu start button is clicked
	 * @return  if the menu start button is clicked
	 */
	public boolean isEndMenuReturnButtonClicked() {
		return endMenuReturnButtonClicked;
	}

}
