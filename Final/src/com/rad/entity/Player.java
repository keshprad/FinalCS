package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rad.Const;
import com.rad.Location;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

/**
 * the player class
 * 
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
	 * is an array of adjecent blocks
	 */
	private boolean[] adjBlocks = { false, false, false, false };
	/**
	 * is a time out used to see if the player AI is in a corner
	 */
	private int cornerTimeout = 0;
	/**
	 * another system used to keep track of items
	 */
	private int gold=0;//fix later

	/**
	 * Constructor for Player
	 * 
	 * @param id Tells us the type of player
	 * @param x  initial x-position of the player
	 * @param y  initial y-position of the player
	 * 
	 */
	public Player(GameWorld gameWorld, int id, int x, int y, boolean isAI) {
		super(gameWorld, id, x, y);
		this.isAI = isAI;
		this.speed = 8;
	}

	/**
	 * returns a rectangle
	 * @return a new rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x + velX + 4, y + velY + 4, Const.TILE_SIZE - 8, Const.TILE_SIZE - 8);
	}

	/**
	 * type of player
	 */
	@Override
	public void init() {
		switch (id) {
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
		score++;
		if (this.isAI) {
			if (cornerTimeout > 0) {
				// Run CornerHandle Algorithm
				cornerTimeout--;
			}
			
			escapeEnemies();

			x += velX;
			y += velY;
		} else {
			x += velX;
			y += velY;
			useKeyInput();

		}
		super.tick();

		x = clamp(x, 0, Const.WORLD_WIDTH - width);
		y = clamp(y, 0, Const.WORLD_HEIGHT - height);
	}

	/**
	 * what renders during a call of render
	 * 
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	/**
	 * handles a collision with an enemy
	 * @param e is the entity you collide with
	 */
	@Override
	public void handleCollision(Entity e) {
		if (e instanceof Block) {
			if (this.isAI) {
				velX *= -1;
				velY *= -1;
			}
			else {
				velX = 0;
				velY = 0;
			}
//			System.out.println("Shouldn't be passing...");
		}
		if (e instanceof Player && e != this) {
			if (this.isAI) {
				velX *= -1;
				velY *= -1;
			}
			else {
				velX = 0;
				velY = 0;
			}
		}
		if (e instanceof Enemy) {
			isDead = true;
			// IF NOT AI GAMEOVER
			System.out.println("THAT HURTS!");
		}
		if (e instanceof Item) {
			Item i = (Item) e;
			setEffect(i);
//			System.out.println("You got the effect " + i.getEffect());
			System.out.println("YOUR CURRENT SCORE: " + score);
		}

	}

	/**
	 * the keyimputs for a player/user
	 */

	public void useKeyInput() {
		if (!gameWorld.getKeyInput().isLeft() && !gameWorld.getKeyInput().isRight()) {
			velX = 0;
		} else if (gameWorld.getKeyInput().isLeft()) {
			velX = -speed;
			velY = 0;
		} else if (gameWorld.getKeyInput().isRight()) {
			velX = speed;
			velY = 0;
		}

		if (!gameWorld.getKeyInput().isUp() && !gameWorld.getKeyInput().isDown()) {
			velY = 0;
		} else if (gameWorld.getKeyInput().isUp()) {
			velY = -speed;
			velX = 0;
		} else if (gameWorld.getKeyInput().isDown()) {
			velY = speed;
			velX = 0;
		}
	}

	/**
	 * Sets the effect to that of the given item.
	 * 
	 * @param item the item to provide the effect
	 */
	public void setEffect(Item item) {
		this.effect = item.getEffect();
		switch (this.effect) {
		case POINT_PLUS:
			gold += 1;
			this.effect = null;
			break;
		case POINT_PLUS_BIG:
			gold += 10;
			this.effect = null;
			break;
		default:
			break;
		}
	}

	/**
	 * Determines the best of 4 moves(up, right, down, left) that puts the player
	 * farthest away from enemies that are close to it. If there are no close
	 * enemies, the player will not move. This method is meant to be used for
	 * players controlled by a computer.
	 */
	public void escapeEnemies() {
		// If no enemies in proximity, this function should exit and do nothing
		LinkedList<Enemy> closestEnemies = findProximityEnemies(new Location(x, y), gameWorld.getEntities());
		if (closestEnemies.size() == 0) {
			velX = 0;
			velY = 0;
			return;
		}

		// Find if blocks are above, below, on the right, or on the left of this player
		if (this.cornerTimeout == 0) {
			this.adjBlocks = hasAdjBlocks();
		}
		isInCorner();
		
		
		// The index in possibleMoves notes the direction
		// 0 -> North; 1 -> East; 2 -> South; 3 -> West
		// Finds possible moves. To simplify I only consider up, right, down, and left
		float[] possibleMoves = new float[4];
		possibleMoves[0] = findAvgDist(new Location(x, y - speed), closestEnemies); // North
		possibleMoves[1] = findAvgDist(new Location(x + speed, y), closestEnemies); // East
		possibleMoves[2] = findAvgDist(new Location(x, y + speed), closestEnemies); // South
		possibleMoves[3] = findAvgDist(new Location(x - speed, y), closestEnemies); // West

		// Finds the best move from the list of possibleMoves.
		int index = -1;
		float bestMove = 0;
		for (int i = 0; i < possibleMoves.length; i++) {
			if (!adjBlocks[i]) {
				if (possibleMoves[i] > bestMove) {
					index = i;
					bestMove = possibleMoves[i];
				} else if (possibleMoves[i] == bestMove) {
					double rand = Math.random();
					if (rand > 0.5) {
						index = i;
					}
				}
			}
		}

		// The index of the best move determines the direction of the move as noted.
		if (index == 0) {
			// North
			velY = -1 * speed;
			velX = 0;
		} else if (index == 1) {
			// East
			velY = 0;
			velX = speed;
		} else if (index == 2) {
			// South
			velY = speed;
			velX = 0;
		} else if (index == 3) {
			// West
			velY = 0;
			velX = -1 * speed;
		}
	}

	/**
	 * Helps the moveAI method by finding the average distance between a point and 2
	 * closest Enemies
	 * 
	 * @param location       location
	 * @param closestEnemies  closest enemy to this player
	 * @return average of 2 distances between a point (i,j) and its 2 closest
	 *         enemies
	 */
	public float findAvgDist(Location location, LinkedList<Enemy> closestEnemies) {
		float totalD = 0;
		for (Enemy e : closestEnemies) {
			totalD += (float) (location.distBetween(new Location(e.x, e.y)));
		}
		totalD = totalD / closestEnemies.size();
		return totalD;
	}

	/**
	 * Looks through a list of entities and finds the 2 closest Entity of the given
	 * type
	 * 
	 * @param entities a list of entities
	 * @return the closest entity of the given type
	 */
	public LinkedList<Enemy> findProximityEnemies(Location location, LinkedList<Entity> entities) {
		LinkedList<Enemy> closeEnemies = new LinkedList<Enemy>();
		float radius = (float) (Const.WORLD_WIDTH / 2);

		for (Enemy enemy : gameWorld.getEnemies()) {
			if (location.distBetween(new Location(enemy.x, enemy.y)) <= radius) {
				closeEnemies.add((Enemy) enemy);
			}
		}
		return closeEnemies;
	}

	/**
	 * Finds a list of telling if there are adjacent blocks(or game bounds) above,
	 * below, on the left, on the right.
	 * 
	 *
	 * @return a list of booleans
	 */
	public boolean[] hasAdjBlocks()
	{
		// Creating a list of blocks and checks bounds if the blocks are adjacent
		// 0 -> North; 1 -> East; 2 -> South; 3 -> West
		boolean[] adjBlocks = new boolean[4];

		if (y == 0) {
			// game bounds above
			adjBlocks[0] = true;
		}
		if (x == Const.WORLD_WIDTH - width) {
			// game bounds on right
			adjBlocks[1] = true;
		}
		if (y == Const.WORLD_HEIGHT - width) {
			adjBlocks[2] = true;
		}
		if (x == 0) {
			adjBlocks[3] = true;
		}

		for (Block b : gameWorld.getBlocks()) {
			if (b.x > x - b.width && b.x < x + width) {
				if (b.y == y - b.height) {
					// block above
					adjBlocks[0] = true;
				}
				if (b.y == y + height) {
					// block below
					adjBlocks[2] = true;
				}
			}
			if (b.y > y - b.height && b.y < y + height) {
				if (b.x == x + width) {
					// block on the right
					adjBlocks[1] = true;
				}
				if (b.x == x - b.width) {
					// block on the left
					adjBlocks[3] = true;
				}
			}
		}
		return adjBlocks;
	}

	/**
	 * checks if the player is in a corner
	 */
	public void isInCorner() {
		boolean[] adjBlocks = hasAdjBlocks();
		for (int i = 0; i < adjBlocks.length; i++) {
			if (i == adjBlocks.length - 1) {
				if (adjBlocks[i] && adjBlocks[0]) {
					this.cornerTimeout = 30;
					return;
				}
			} else if (adjBlocks[i] && adjBlocks[i + 1]) {
				this.cornerTimeout = 30;
				return;
			}
		}
	}

	/**
	 * returns a gold value
	 * @return returns the value of gold
	 */
	public int getGold()
	{
		return gold;
	}

	/**
	 * Adds the given amount to the player's score
	 * 
	 * @param amount the amount of score to add
	 */
	public void addScore(int amount) {
		this.score += amount;
	}

	/**
	 * the score of the player
	 * 
	 * @return
	 */
	/**
	 * Gets the score of the player
	 * 
	 * @return the player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Gets the current effect the player is afflicted with
	 * 
	 * @return the player's effect
	 */
	public Effect getEffect() {
		return effect;
	}

	/**
	 * Returns if the player is an AI (computer-controlled)
	 * 
	 * @return if the player is an AI
	 */
	public boolean isAI() {
		return isAI;
	}

}
