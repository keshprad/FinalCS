package com.rad.gui;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.GameWorld;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author RAD
 * @version 1.0
 * is the game over state
 */
public class EndMenu {
	
    /**
     * is the gameworld of this game, holding all the entities
     */
    private GameWorld gameWorld;
    
    /**
     * is the game we are currently in
     */
    private Game game;

    /**
     * is the constructor of the game over state
     * @param g the game user is playing
     */
    public EndMenu(Game g) {
        game = g;
        gameWorld = g.getGameWorld();
    }

    /**
     * is the tick method for the game over state
     */
    public void tick() { 
    	if (game.getMouseInput().isEndMenuReturnButtonClicked()) {
    		game.setGameState(Game.GameState.START);
    	}
    }

    /**
     * renders the graphics game
     * @param g is the graphics of the game
     */
    public void render(Graphics g) {
    	BufferedImage imgBG = null;
    	BufferedImage imgReturn = null;
		try {
			imgBG = ImageIO.read(new File(Const.PATHS.END_MENU_BACKGROUND));
			imgReturn = ImageIO.read(new File(Const.PATHS.END_MENU_RETURN_BTN));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	g.drawImage(imgBG, 0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 0, 0, imgBG.getWidth(), imgBG.getHeight(), null);
    	g.drawImage(imgReturn, 0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 0, 0, imgBG.getWidth(), imgBG.getHeight(), null);
    
    	g.setFont(game.getEndScoreFont());
      	g.setColor(Color.WHITE);
      	g.drawString(gameWorld.getCurPlayer().getScore() + "", Const.WORLD_WIDTH / 2, Const.WORLD_HEIGHT / 2);
    }
}
