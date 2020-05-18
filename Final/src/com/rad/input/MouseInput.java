package com.rad.input;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	
	private boolean MenuStartButtonClicked = false;
	private boolean MenuExitButtonClicked = false;
	private Game game;
	
	public MouseInput(Game g) {
		this.game = g;
	}
	
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		MenuStartButtonClicked = false;
		MenuExitButtonClicked = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isMenuStartButtonClicked() {
		return MenuStartButtonClicked;
	}

	public boolean isMenuExitButtonClicked() {
		return MenuExitButtonClicked;
	}

}
