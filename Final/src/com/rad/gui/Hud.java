package com.rad.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.Game;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

/**
 * Represents the HUD (headless user display). Displays the user's score and current effect on the screen.
 * @author RAD
 * @version 1.0
 */
public class Hud {
	
	/**
	 * The current instance of game.
	 */
	private Game game;
	
	/**
	 * The game world.
	 */
	private GameWorld gameWorld;
	
	/**
	 * The user controlled player's score.
	 */
	private int playerScore = 0;
	
	/**
	 * If the user controlled player has a speed up or eat others power up.
	 */
	private boolean hasSpeedUp = false;
	private boolean hasEatOthers = false;
	
	/**
	 * Used to calculate the dimensions of a string when drawn with the specified font.
	 */
	private FontMetrics metrics;
	
	/**
	 * The constructor.
	 * @param g the instance of game the HUD was created in
	 */
	public Hud(Game g) {
		this.game = g;
		this.gameWorld = g.getGameWorld();
	}

	/**
	 * Updates the HUD's state. Called Const.FRAMES_PER_SECOND frames per second.
	 * Retrieves the user controlled player's score and power up to display the information.
	 */
	public void tick() {

		if (gameWorld.getCurPlayer() != null) {
	        metrics = game.getFontMetrics(game.getPlayingScoreFont());

			playerScore = gameWorld.getCurPlayer().getScore();
			if (gameWorld.getCurPlayer().getEffect() != null) {
				hasSpeedUp = gameWorld.getCurPlayer().getEffect().equals(Effect.SPEED_UP);
				hasEatOthers = gameWorld.getCurPlayer().getEffect().equals(Effect.EAT_OTHER);
			} else {
				hasSpeedUp = false;
				hasEatOthers = false;
			}
		}
		
	}

	/**
	 * Draws the HUD to the screen. Called as often as possible.
	 * Displays the user controlled player's score and a message that indicates the player's current effect.
	 * @param g the graphics the HUD is drawn on
	 */
	public void render(Graphics g) {
		g.setFont(game.getPlayingScoreFont());
    	g.setColor(Color.WHITE);
    	g.drawString(String.valueOf(playerScore), Const.HUD.SCORE_LOC.x, Const.HUD.SCORE_LOC.y);
    	
    	if (hasSpeedUp) {
        	g.drawString("SPEED UP!", Const.HUD.EFFECT_LOC.x - metrics.stringWidth("SPEED UP!"), Const.HUD.EFFECT_LOC.y);
    	} else if (hasEatOthers) {
        	g.drawString("EAT OTHERS!", Const.HUD.EFFECT_LOC.x - metrics.stringWidth("EAT OTHERS!"), Const.HUD.EFFECT_LOC.y);
    	}
	}
	
}
