package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;

/**
 * the player class
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Player extends Entity {
	/**
	 * the score of each player
	 */
	private int score;
	/**
	 * the effect that the player has
	 */
	private Effect effect = null;
	/**
	 * if it is an AI or not
	 */
	private boolean isAI;

	/**
	 * the player class
	 * @param id the id that determines type of player
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param isAI whether its a player or not
	 */
	public Player(int id, int x, int y, boolean isAI) {
		super(id, x, y);
		this.isAI = isAI;
	}

	/**
	 * type of player
	 */
	@Override
	public void init() {
		switch (id) {
			case Const.ID.BRAD:
				//
				break;
			case Const.ID.FULK:
				//
				break;
			default:
				break;
		}
	}

	/**
	 * what runs during a call of player
	 */
	@Override
	public void tick() {
		
	}

	/**
	 * what renders during a call of render
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x * Const.TILE_SIZE, y * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE);
	}

	/**
	 *  sets the effect of this player
	 * @param item the item that gave this effect
	 */
	public void setEffect(Item item) {
		this.effect = item.getEffect();
		switch (this.effect) {
			case POINT_PLUS:
				score += 1;
				this.effect = null;
				break;
			case POINT_PLUS_BIG:
				score += 10;
				this.effect = null;
				break;
			default:
				break;
		}
	}

	/**
	 * adds to the score
	 * @param amount the amount added to the score
	 */
	public void addScore(int amount) {
		this.score += amount;
	}

	/**
	 * the score of the player
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 *  the effect gotten
	 * @return
	 */

	public Effect getEffect() {
		return effect;
	}

	/**
	 * whether its an AI or not
	 * @return whether its an AI or not
	 */
	public boolean isAI() {
		return isAI;
	}
	
}