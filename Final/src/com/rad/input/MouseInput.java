package com.rad.input;

import com.rad.Const;
import com.rad.Game;
import com.rad.gui.StartMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The mouse input handler. Used to handle mouse clicks on menus.
 * @author RAD
 * @version 1.0
 */
public class MouseInput implements MouseListener {
	
	/**
	 * If the start menu's start button is clicked.
	 */
	private boolean startMenuStartButtonClicked = false;

	/**
	 * If the start menu's exit button is clicked.
	 */
	private boolean startMenuExitButtonClicked = false;

	/**
	 * If the end menu's return button is clicked.
	 */
	private boolean endMenuReturnButtonClicked = false;
	
	/**
	 * The instance of the main game the mouse input handler was created.
	 */
	private Game game;

	/**
	 * The constructor.
	 * @param game the instance of the game the mouse input handler was created
	 */
	public MouseInput(Game game) {
		this.game = game;
	}


	@Override
	/**
	 * Called whenever the user clicks with the mouse. Sets the appropriate booleans to true
	 * if the appropriate regions were clicked on.
	 * @param e the mouse event
	 */
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

	@Override
	/**
	 * Called whenever the user releases a click with the mouse. Sets all booleans to false.
	 * @param e the mouse event
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		startMenuStartButtonClicked = false;
		startMenuExitButtonClicked = false;
		endMenuReturnButtonClicked = false;
	}

	/**
	 * Returns if the start menu start button is clicked
	 * @return if the start menu start button is clicked
	 */
	public boolean isStartMenuStartButtonClicked() {
		return startMenuStartButtonClicked;
	}

	/**
	 * Returns if the start menu exit button is clicked
	 * @return if the start menu exit button is clicked
	 */
	public boolean isStartMenuExitButtonClicked() {
		return startMenuExitButtonClicked;
	}
	
	/**
	 * Returns if the end menu return button is clicked
	 * @return if the end menu return button is clicked
	 */
	public boolean isEndMenuReturnButtonClicked() {
		return endMenuReturnButtonClicked;
	}


	@Override
	/**
	 * 
	 * @param e the mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	/**
	 * 
	 * @param e the mouse event
	 */
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	/**
	 * Not used.
	 * @param e the mouse event
	 */
	public void mouseExited(MouseEvent e) {
		
	}

}
