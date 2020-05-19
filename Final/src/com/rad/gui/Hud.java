package com.rad.gui;

import java.awt.Graphics;

import com.rad.Game;
import com.rad.world.GameWorld;

public class Hud {
	
	private Game game;
	private GameWorld gameWorld;
	
	public Hud(Game g) {
		this.game = g;
		this.gameWorld = g.getGameWorld();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(20, 20, 600, 60);
	}
	
}
