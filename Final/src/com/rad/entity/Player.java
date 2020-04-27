package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

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
	 * Constructor for Player
	 * @param id Tells us the type of player
	 * @param x initial x-position of the player
	 * @param y initial y-position of the player

	 */
	public Player(GameWorld gameWorld, int id, int x, int y, boolean isAI) {
		super(gameWorld, id, x, y);
		this.isAI = isAI;
		this.speed = 5;
	}

	/**
	 * type of player
	 */
	@Override
	public void init() {
		switch (getId()) {
			case Const.ID.BRAD:
				color = Color.WHITE;
				break;
			case Const.ID.FULK:
				color = Color.LIGHT_GRAY;
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
		x += velX;
		y += velY;
		
		if (!gameWorld.getKeyInput().isLeft() && !gameWorld.getKeyInput().isRight()) { 
			velX = 0; 
		} else if (gameWorld.getKeyInput().isLeft()) {
			velX = -speed;
		} else if (gameWorld.getKeyInput().isRight()) {
			velX = speed;
		}
		
		if (!gameWorld.getKeyInput().isUp() && !gameWorld.getKeyInput().isDown()) { 
			velY = 0; 
		} else if (gameWorld.getKeyInput().isUp()) {
			velY = -speed;
		} else if (gameWorld.getKeyInput().isDown()) {
			velY = speed;
		}
		
		super.tick();
		
		x = clamp(x, 0, Const.WORLD_WIDTH - width);
		y = clamp(y, 0, Const.WORLD_HEIGHT - height);
	}

	/**
	 * what renders during a call of render
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	
	@Override
	public void handleCollision(Entity e) {
		
		if (e instanceof Block) {
			velX = 0;
			velY = 0;
			System.out.println("Shouldn't be passing...");
		}
		if (e instanceof Enemy) {
			isDead = true;
			System.out.println("THAT HURTS!");
		}
		if (e instanceof Item) {
			Item i = (Item) e;
			setEffect(i);
			System.out.println("You got the effect " + i.getEffect());
			System.out.println("YOUR CURRENT SCORE: " + score);
		}	
		
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
	 * the score of the player
	 * @return
	 */
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
