package com.rad.gui;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rad.Const;
import com.rad.Game;
import com.rad.input.MouseInput;

/**
 * @author RAD
 * @version 1.0
 * is the menu state of the
 */
public class StartMenu {
	/**
	 * The current instance of game
	 */
    private Game game;

	/**
	 * Constructs a Start Menu
	 * Sets the field game
	 * 
	 * @param g the game that is being played
	 */
    public StartMenu(Game g) {
		this.game = g;
	}

	/**
	 * Updates the game state with a specific amount of frames per second
	 */
	public void tick(){
    	if (game.getMouseInput().isStartMenuStartButtonClicked()) {
    		game.startPlaying();
    	}
    	if (game.getMouseInput().isStartMenuExitButtonClicked()) {
    		game.stop();
    	}
    }

	/**
	 * Renders the buttons, text, and background of the start menu
	 * 
	 * @param g is the graphics card
	 */
	public void render(Graphics g)
    {

    	BufferedImage imgBG = null;
    	BufferedImage imgStart = null;
    	BufferedImage imgExit = null;
		try {
			imgBG = ImageIO.read(new File(Const.PATHS.START_MENU_BACKGROUND));
			imgStart = ImageIO.read(new File(Const.PATHS.START_MENU_START_BTN));
			imgExit = ImageIO.read(new File (Const.PATHS.START_MENU_EXIT_BTN));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	g.drawImage(imgBG, 0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 0, 0, imgBG.getWidth(), imgBG.getHeight(), null);
    	g.drawImage(imgStart, Const.START_MENU.START_BTN.x, Const.START_MENU.START_BTN.y, Const.START_MENU.START_BTN.x + imgStart.getWidth(), Const.START_MENU.START_BTN.y + imgStart.getHeight(), 0, 0, imgStart.getWidth(), imgStart.getHeight(), null);
    	g.drawImage(imgExit, Const.START_MENU.EXIT_BTN.x, Const.START_MENU.EXIT_BTN.y, Const.START_MENU.EXIT_BTN.x + imgExit.getWidth(), Const.START_MENU.EXIT_BTN.y + imgExit.getHeight(), 0, 0, imgExit.getWidth(), imgExit.getHeight(), null);
    	
    }
}
