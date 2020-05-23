package com.rad.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.Game;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

/**
 * is the HUD
 * @author RAD
 * @version 1.0
 */
public class Hud {
	/**
	 * the game users are playing in
	 */
	private Game game;
	/**
	 * is the world where all entities exist in
	 */
	private GameWorld gameWorld;
	
	private int playerScore = 0;
	
	private boolean hasSpeedUp = false;
	private boolean hasEatOthers = false;
	
	private FontMetrics metrics;
	
	/**
	 * is the constructor for the HUD display
	 * @param g the game it is in
	 */
	public Hud(Game g) {
		this.game = g;
		this.gameWorld = g.getGameWorld();
	}

	/**
	 * tick method for HUD
	 */
	public void tick() {

		if (gameWorld.getCurPlayer() != null) {
	        metrics = game.getFontMetrics(game.getPlayingScoreFont());

			playerScore = gameWorld.getCurPlayer().getScore();
			if (gameWorld.getCurPlayer().getEffect() != null) {
				hasSpeedUp = gameWorld.getCurPlayer().getEffect().equals(Effect.SPEED_UP);
				hasEatOthers = gameWorld.getCurPlayer().getEffect().equals(Effect.EAT_OTHER);
			}
		}
		
	}

	/**
	 * render method for HUD
	 * @param g graphs for HUD
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
