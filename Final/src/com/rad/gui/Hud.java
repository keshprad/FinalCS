package com.rad.gui;

import java.awt.Graphics;

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
		
	}

	/**
	 * render method for hud
	 * @param g graphs for hud
	 */
	public void render(Graphics g) {
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(20, 20, 600, 60);
	}
	
}
