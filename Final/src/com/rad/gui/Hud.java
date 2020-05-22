package com.rad.gui;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.GameWorld;

/**
 * is the hud
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
	
	private int playerScore;

	/**
	 * is the constructor for the hud display
	 * @param g the game it is in
	 */
	public Hud(Game g) {
		this.game = g;
		this.gameWorld = g.getGameWorld();
	}

	/**
	 * tick method for hud
	 */
	public void tick() {
		playerScore = gameWorld.getCurPlayer().getScore();
	}

	/**
	 * render method for hud
	 * @param g graphs for hud
	 */
	public void render(Graphics g) {
		g.setFont(game.getPlayingScoreFont());
    	g.setColor(Color.WHITE);
    	g.drawString(playerScore + "", 0 + 16, Const.WORLD_HEIGHT - 16);
	}
	
}
