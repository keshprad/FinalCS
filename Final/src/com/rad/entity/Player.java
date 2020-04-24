package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;

/**
 * 
 * Represents a player.
 * 
 * id = 1 -> Wall
 * id = 2 -> Bush
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Player extends Entity {

	private int score;
	private Effect effect = null;
	private boolean isAI;
	
	/**
	 * Constructor for Block
	 * @param id Tells us the type of player
	 * @param x initial x-position of the player
	 * @param y initial y-position of the player
	 */
	public Player(int id, int x, int y, boolean isAI) {
		super(id, x, y);
		this.isAI = isAI;
	}
	
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
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x * Const.TILE_SIZE, y * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE);
	}
	
        /**
         * Sets the effect to that of the given item.
         * @param item the item to provide the effect
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
         * Adds the given amount to the player's score
         * @param amount the amount of score to add
         */
	public void addScore(int amount) {
		this.score += amount;
	}

        /**
         * Gets the score of the player
         * @return the player's score
         */
	public int getScore() {
		return score;
	}

        /**
         * Gets the current effect the player is afflicted with
         * @return the player's effect
         */
	public Effect getEffect() {
		return effect;
	}

        /**
         * Returns if the player is an AI (computer-controlled)
         * @return if the player is an AI
         */
	public boolean isAI() {
		return isAI;
	}
	
}
