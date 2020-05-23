package com.rad.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.rad.Const;
import com.rad.Location;
import com.rad.effects.Effect;
import com.rad.world.GameWorld;

/**
 * Represents both user controlled players and AI controlled players
 * 
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Player extends Entity {
	
	/**
	 * The score of each player
	 */
	private int score;

	/**
	 * The effect that the player has
	 */
	private Effect effect = null;

	/**
	 * Whether or not a player is an AI or not
	 */
	private boolean isAI;

	/**
	 * An array of blocks adjacent to this player
	 * Only used for AI players
	 */
	private boolean[] adjBlocks = { false, false, false, false };

	/**
	 * A timer(measured in ticks) used to control the player AI when it is close to a corner
	 */
	private int cornerTimeout = 0;
	
	/**
	 * A timer(measured in ticks) for the time an effect lasts for
	 */
	private int effectTimer = 0;
	
	/**
	 * Whether or not a player has the effect to and can eat other players
	 */
	private boolean hasEatOthers = false;
		
	/**
	 * Constructs a player object
	 * 
	 * @param id Tells us the type of player
	 * @param x  initial x-position of the player
	 * @param y  initial y-position of the player
	 * 
	 */
	public Player(GameWorld gameWorld, int id, int x, int y, boolean isAI) {
		super(gameWorld, id, x, y);
		this.isAI = isAI;
		this.speed = Const.PLAYER.SPEED;
	}

	/**
	 * Initializes the type of player
	 */
	@Override
	public void init() {
		switch (id) {
			case Const.ID.BRAD:
				break;
			case Const.ID.FULK:
				break;
			default:
				break;
		}
	}

	/**
	 * Updates the game state with a constant amount of frames per second
	 * This handles movement based on whether the player is an AI or not
	 */
	@Override
	public void tick() {
		score++; //players awarded 1 point for every tick alive
			
		handleEffect();
		
		if (this.isAI) {
			if (cornerTimeout > 0) {
				//Run CornerHandle Algorithm
				cornerTimeout--;
			}
			escapeEnemies();
			x += velX;
			y += velY;
		} 
		else {
			x += velX;
			y += velY;
			useKeyInput();
		}
		
		super.tick();
		x = clamp(x, 0, Const.WORLD_WIDTH - width);
		y = clamp(y, 0, Const.WORLD_HEIGHT - height);
	}

	/**
	 * Renders the player's graphic
	 * @param g tool used to draw object in the window
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	/**
	 * Handles collisions with other entities
	 * @param e is the entity you collide with
	 */
	@Override
	public void handleCollision(Entity e) {		
		if (e instanceof Block) {
			if (!isAI) {
				velX = 0;
				velY = 0;
			}
		}
		if (e instanceof Player) {
			if (((Player) e).hasEatOthers()) {
				isDead = true;
			}
			
			if (this.hasEatOthers()) {
				score += Const.EFFECTS.POINT_PLUS_BIG;
			}
		}
		if (e instanceof Enemy) {
			isDead = true;
			gameWorld.createEnemy(this.x, this.y);
		}
		if (e instanceof Item) {
			Item i = (Item) e;
			this.effect = i.getEffect();
			effectTimer = i.getEffect().getDuration();
		}

	}

	/**
	 * Takes in the Key input for a user-controlled player
	 */
	private void useKeyInput() {
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
	 * Handles the effect and decreases the timer for the effect, if there is one
	 */
	private void handleEffect() {
		if (effectTimer == 0 && this.effect != null) {
			switch (this.effect) {
				case SPEED_UP:
					speed = Const.PLAYER.SPEED;
					break;
				case EAT_OTHER:
					setEatOthers(false);
					break;
				default:
					break;
			}
			this.effect = null;
		}
		
		if (this.getEffect() != null && this.effectTimer > 0) {
			effectTimer--;
			switch (this.getEffect()) {
				case POINT_PLUS:
					score += Const.EFFECTS.POINT_PLUS;
					break;
				case POINT_PLUS_BIG:
					score += Const.EFFECTS.POINT_PLUS_BIG;
					break;
				case SPEED_UP:
					clearPowers();
					if (this.x % Const.PLAYER.BOOSTED_SPEED == 0 && this.y % Const.PLAYER.BOOSTED_SPEED == 0) {
						speed = Const.PLAYER.BOOSTED_SPEED;
					}
					break;
				case EAT_OTHER:
					clearPowers();
					setEatOthers(true);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * Clears anything that changed in the player because of an effect
	 */
	private void clearPowers() {
		setEatOthers(false);
		this.speed = Const.PLAYER.SPEED;
	}
	
	/**
	 * Determines the best of 4 moves(up, right, down, left) that puts the player
	 * farthest away from enemies that are close to it. If there are no close
	 * enemies, the player will not move. This method is meant to be used for
	 * players controlled by a computer.
	 */
	private void escapeEnemies() {
		//Adding randomness to Player AI's move
		if (Math.random() < 0.05) {
			velX = 0;
			velY = 0;
			return;
		}
		
		// If no enemies in proximity, this function should exit and do nothing
		LinkedList<Entity> dangerousEntities = findProximityEnemies(new Location(x, y));
		if (dangerousEntities.size() == 0) {
			velX = 0;
			velY = 0;
			return;
		}

		
		// Find if blocks are above, below, on the right, or on the left of this player
		if (this.cornerTimeout == 0) {
			this.adjBlocks = hasAdjBlocks();
		}
		isInCorner(); //Checks if player is currently in a corner and sets cornerTimeout accordingly
		
		
		// Finds the direction of the best possible move
		// Index: 0 -> North; 1 -> East; 2 -> South; 3 -> West
		int index = bestDirection(dangerousEntities);

		
		//The index of the best move determines the direction of the move as noted.
		//This switch just sets the velocity in the correct direction according to the value of index
		switch(index) {
			case 0:
				// North
				velY = -1 * speed;
				velX = 0;
				break;
			case 1:
				// East
				velY = 0;
				velX = speed;
				break;
			case 2:
				// South
				velY = speed;
				velX = 0;
				break;
			case 3:
				// West
				velY = 0;
				velX = -1 * speed;
				break;	
		}
	}
	
	/**
	 * Helps the escapeEnemy method by determining the best direction to move
	 * First finds a list of all possible move locations
	 * Next, determines its validity and which direction is best
	 * @param dangerousEntities a list of all enemies within a radius.
	 * @return an index that represents the best direction for player to move
	 */
	private int bestDirection(LinkedList<Entity> dangerousEntities) {
		// The index in possibleMoves notes the direction
		// Index: 0 -> North; 1 -> East; 2 -> South; 3 -> West
		// Finds possible moves. To simplify I only consider up, right, down, and left
		float[] possibleMoves = {
			findAvgDist(new Location(x, y - speed), dangerousEntities), 
			findAvgDist(new Location(x + speed, y), dangerousEntities), 
			findAvgDist(new Location(x, y + speed), dangerousEntities), 
			findAvgDist(new Location(x - speed, y), dangerousEntities)
		};

		// Finds the best move in terms of an index from the list of possibleMoves.
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
		return index;
	}
	
	/**
	 * Helps the escapeEnemy method by finding the average distance between a point and 2
	 * closest Enemies
	 * 
	 * @param location the possible move location for the player
	 * @param dangerousEntities; closest fatal entities to this player
	 * @return average of 2 distances between a point (i,j) and its 2 closest
	 *         enemies
	 */
	private float findAvgDist(Location location, LinkedList<Entity> dangerousEntities) {
		float totalD = 0;
		for (Entity e : dangerousEntities) {
			totalD += (float) (location.distBetween(new Location(e.x, e.y)));
		}
		totalD = totalD / dangerousEntities.size();
		return totalD;
	}

	/**
	 * Looks through a list of entities and finds all enemies and fatal players within a certain radius
	 * 
	 * @param entities a list of entities
	 * @return the closest entity of the given type
	 */
	private LinkedList<Entity> findProximityEnemies(Location location) {
		LinkedList<Entity> dangerousEntities = new LinkedList<Entity>();
		float radius = (float) (2*Const.WORLD_WIDTH / 3);

		for (Enemy e : gameWorld.getEnemies()) {
			if (location.distBetween(new Location(e.x, e.y)) <= radius) {
				dangerousEntities.add((Enemy) e);
			}
		}
		for (Player p : gameWorld.getPlayers()) {		
			if (p.hasEatOthers() && p != this && location.distBetween(new Location(p.x, p.y)) <= radius) {
				dangerousEntities.add(p);
			}
		}
		return dangerousEntities;
	}

	/**
	 * Creates a list telling whether there are adjacent blocks(or game bounds) above,
	 * below, on the left, on the right.
	 *
	 * @return a list of booleans
	 */
	private boolean[] hasAdjBlocks()
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
	 * Checks if the player is in a corner
	 * If in a corner, it creates a corner timeout
	 */
	private void isInCorner() {
		boolean[] adjBlocks = hasAdjBlocks();
		for (int i = 0; i < adjBlocks.length; i++) {
			if (i == adjBlocks.length - 1) {
				if (adjBlocks[i] && adjBlocks[0]) {
					this.cornerTimeout = 8;
					return;
				}
			} else if (adjBlocks[i] && adjBlocks[i + 1]) {
				this.cornerTimeout = 8;
				return;
			}
		}
	}
	
	/**
	 * Handles when the player has the effect eatOthers
	 * @param hasEatOthers
	 */
	public void setEatOthers(boolean hasEatOthers) {
		this.hasEatOthers = hasEatOthers;
		if (hasEatOthers) {
			this.id = Const.ID.FULK;
		} else {
			this.id = Const.ID.BRAD;
		}
	}
	
	public boolean hasEatOthers() {
		return this.hasEatOthers;
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
	 * Returns whether of not the player is an AI (computer-controlled)
	 * 
	 * @return whether or not the player is an AI
	 */
	public boolean isAI() {
		return isAI;
	}

}
