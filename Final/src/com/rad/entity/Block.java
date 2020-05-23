package com.rad.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rad.world.GameWorld;

/**
 * Represents a static block. Blocks the movement of entities.
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Block extends Entity {

	/**
	 * The constructor.
	 * 
	 * @param id The ID of the block. 1 -> Wall, 2 -> Bush
	 * @param x  The x coordinate of the block.
	 * @param y  The y coordinate of the block.
	 */
	public Block(GameWorld gameWorld, int id, int x, int y) {
		super(gameWorld, id, x, y);
	}

	@Override
	/**
	 * Initializes the block. Empty for now.
	 */
	public void init() {

	}

	@Override
	/**
	 * Updates the block's state. Called Const.FRAMES_PER_SECOND frames per second.
	 * Empty for now.
	 */
	public void tick() {
		super.tick();
	}

	@Override
	/**
	 * Draws the block to the screen. Called as often as possible. Empty for now.
	 * 
	 * @param g the graphics the images are drawn on.
	 */
	public void render(Graphics g) {
		super.render(g);
	}

}
