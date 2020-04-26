package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;

/**
 * items that the player can consume
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Item extends Entity {

	/**
	 * the effects that each item can give
	 */
	private Effect effect = null;
	
	/**
	 * constructs an empty item
	 * @param id the type of item
	 * @param x the X coordinate of item
	 * @param y the Y coordinate of item
	 */
	public Item(int id, int x, int y) {
		super(id, x, y);
		effect = Effect.POINT_PLUS;
	}

	/**
	 * the type of item
	 */
	@Override
	public void init() {
		switch (getId()) {
			case Const.ID.CHIP:
				effect = Effect.POINT_PLUS;
				break;
			default:
				effect = Effect.POINT_PLUS;
				break;
		}
	}

	/**
	 * What runs during call of item
	 */
	@Override
	public void tick() {
		
	}

	/**
	 * what is drawn with an item
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, Const.TILE_SIZE, Const.TILE_SIZE);
	}
	
	@Override
	public void collidedWith(Entity e) {
		if (e instanceof Player) {
			isDead = true;
		}
	}

	/**
	 * returns the effects of an item
	 * @return the effect of an item
	 */
	public Effect getEffect() {
		return this.effect;
	}

}
