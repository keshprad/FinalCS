package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;

/**
 * 
 * This class defines an Enemy. This includes zombies and other enemies.
 * 
 * id = 10 -> ZOMBIE
 * id = 11 -> IMP_ZOMBIE
 * 
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Enemy extends Entity {

	/**
	 * Constructor for Enemy class
	 * @param id Tells us the type of Enemy
	 * @param x initial x-position of the block
	 * @param y initial y-position of the block
	 */
	public Enemy(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	public void init()
    {

	}

	@Override
	/**
	 * Necessary for GUI, but not needed for this class
	 */
	public void tick() {

	}

	@Override
	/**
	 * renders a graphic to represent an Enemy. Either a zombie...
	 */
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, Const.TILE_SIZE, Const.TILE_SIZE);
	}
	
	@Override
	public void collidedWith(Entity e) {
		
	}
    

}