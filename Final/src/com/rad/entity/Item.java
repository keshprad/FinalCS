package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

/**
 * items that the player can consume
 * 
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Item extends Entity {

	/**
	 * the effects that each item can give
	 */
	private Effect effect = null;

	/**
	 * constructs an empty item
	 * 
	 * @param id the type of item
	 * @param x  the X coordinate of item
	 * @param y  the Y coordinate of item
	 */
	public Item(GameWorld gameWorld, int id, int x, int y) {
		super(gameWorld, id, x, y);
		effect = Effect.POINT_PLUS;
	}

	/**
	 * the type of item
	 */
	@Override
	public void init() {
		switch (id) {
		case Const.ID.CHIP:
			color = Color.YELLOW;
			effect = Effect.POINT_PLUS;
			break;
		default:
			color = Color.YELLOW;
			effect = Effect.POINT_PLUS;
			break;
		}
	}

	/**
	 * What runs during call of item
	 */
	@Override
	public void tick() {
		super.tick();
	}

	/**
	 * what is drawn with an item
	 * 
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	/**
	 * handles collisions in terms of the death of a player
	 * @param e is the entity you collide with
	 */
	@Override
	public void handleCollision(Entity e) {

		if (e instanceof Player) {
			isDead = true;
		}
	}

	/**
	 * returns the effects of an item
	 * 
	 * @return the effect of an item
	 */
	public Effect getEffect() {
		return this.effect;
	}

}
