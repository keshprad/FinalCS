package com.rad.entity;

import java.awt.Graphics;

/**
 * 
 * This class defines an Empty space. This is important so we can differentiate between null and an empty space
 * 
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Empty extends Entity {

	/**
	 * Constructor for the Empty space Entity
	 * @param x initial x-position for the Empty space
	 * @param y initial y-position for the Empty space
	 */
	public Empty(int x, int y) {
		super(0, x, y);
	}

	@Override
	public void init() {

	}

	@Override
	/**
	 * Necessary for GUI, but not needed for this class
	 */
	public void tick() {
		// do nothing		
	}

	@Override
	/**
	 * renders the graphic that represents an empty block
	 */
	public void render(Graphics g) {
		// do nothing
	}

}
