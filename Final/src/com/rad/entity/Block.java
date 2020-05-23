package com.rad.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rad.world.GameWorld;

/**
 * 
 * This class defines a Block. This includes walls, bushes, etc.
 * 
 * id = 1 -> Wall
 * id = 2 -> Bush
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Block extends Entity {

	/**
	 * Constructor for Block
	 * @param id Tells us the type of block
	 * @param x initial x-position of the block
	 * @param y initial y-position of the block
	 */
	public Block(GameWorld gameWorld, int id, int x, int y) {
		super(gameWorld, id, x, y);
	}

	@Override
	/**
	 * changes the color of the block
	 */
	public void init() {
		
	}

	@Override
	/**
	 * inherits the entity tick
	 */
	public void tick() {
		super.tick();
	}

	@Override
	/**
	 * renders a graphic to represent a Block. Either a wall, bush...
	 */
	public void render(Graphics g) {
		super.render(g);
	}
	
}
