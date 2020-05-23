package com.rad.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.GameWorld;

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
    
    private String score = "";
    private String rank = "";
    
	private FontMetrics metrics;


    /**
     * is the constructor of the game over state
     * @param g the game user is playing
     */
    public EndMenu(Game g) {
        game = g;
        gameWorld = g.getGameWorld();
        metrics = g.getFontMetrics(game.getEndScoreFont());
    }

    /**
     * is the tick method for the game over state
     */
    public void tick() {
        score = gameWorld.getCurPlayer().getScore() + "";
        rank = calcRank();
        
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
      	g.drawString(score, Const.WORLD_WIDTH / 2 - metrics.stringWidth(score) / 2, Const.END_MENU.SCORE_Y);
      	
      	g.setFont(game.getEndRankFont());
      	g.setColor(Color.YELLOW);
      	g.drawString(rank, Const.END_MENU.RANK_LOC.x, Const.END_MENU.RANK_LOC.y);
    }
    
    private String calcRank() {
    	switch (gameWorld.getPlayers().size() + 1) {
    		case 1:
    			return "1st!";
    		case 2:
    			return "2nd!";
    		case 3:
    			return "3rd!";
    		default:
    			return String.valueOf(gameWorld.getPlayers().size() + 1) + "th!";
    	}
    }
}
