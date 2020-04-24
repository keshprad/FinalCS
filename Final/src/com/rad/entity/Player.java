package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;

/**
<<<<<<< HEAD
 * the player class
=======
 * 
 * Represents a player.
 * 
 * id = 1 -> Wall
 * id = 2 -> Bush
 *
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
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
<<<<<<< HEAD

	/**
	 * the player class
	 * @param id the id that determines type of player
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param isAI whether its a player or not
=======
	
	/**
	 * Constructor for Block
	 * @param id Tells us the type of player
	 * @param x initial x-position of the player
	 * @param y initial y-position of the player
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
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
<<<<<<< HEAD

	/**
	 *  sets the effect of this player
	 * @param item the item that gave this effect
	 */
=======
	
        /**
         * Sets the effect to that of the given item.
         * @param item the item to provide the effect
         */
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
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
<<<<<<< HEAD

	/**
	 * adds to the score
	 * @param amount the amount added to the score
	 */
=======
	
        /**
         * Adds the given amount to the player's score
         * @param amount the amount of score to add
         */
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
	public void addScore(int amount) {
		this.score += amount;
	}

<<<<<<< HEAD
	/**
	 * the score of the player
	 * @return
	 */
=======
        /**
         * Gets the score of the player
         * @return the player's score
         */
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
	public int getScore() {
		return score;
	}

<<<<<<< HEAD
	/**
	 *  the effect gotten
	 * @return
	 */

=======
        /**
         * Gets the current effect the player is afflicted with
         * @return the player's effect
         */
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
	public Effect getEffect() {
		return effect;
	}

<<<<<<< HEAD
	/**
	 * whether its an AI or not
	 * @return whether its an AI or not
	 */
=======
        /**
         * Returns if the player is an AI (computer-controlled)
         * @return if the player is an AI
         */
>>>>>>> 617ab467f3a0d53ddeb25e351c34cb087d393015
	public boolean isAI() {
		return isAI;
	}
	
}
