package com.rad.input;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * is the mouse input of the code
 * @author RAD
 * @version 1.0
 */
public class MouseInput implements MouseListener {
	/**
	 * if the mouse left click is clicked
	 */
	private boolean MenuStartButtonClicked = false;
	/**
	 * if the right mouse button is clicked
	 */
	private boolean MenuExitButtonClicked = false;
	/**
	 * is the game users are in
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
        if (game.getGameState() == Game.GameState.MENU) {
	        MenuStartButtonClicked = Const.MENU.START_BTN.contains(e.getX(), e.getY());
	        MenuExitButtonClicked = Const.MENU.EXIT_BTN.contains(e.getX(), e.getY());
        }
        else if (game.getGameState() == Game.GameState.PLAYING) {
        	//do nothing for now
        }
        else if (game.getGameState() == Game.GameState.GAMEOVER) {
        	//do nothing for now

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
		MenuStartButtonClicked = false;
		MenuExitButtonClicked = false;
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
	public boolean isMenuStartButtonClicked() {
		return MenuStartButtonClicked;
	}

	/**
	 * checks if the menu exit button is clicked
	 * @return if the menu exit button is clicked
	 */
	public boolean isMenuExitButtonClicked() {
		return MenuExitButtonClicked;
	}

}
