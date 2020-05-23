package com.rad.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import com.rad.Const;
import com.rad.Game;
import com.rad.entity.Block;
import com.rad.entity.Enemy;
import com.rad.entity.Entity;
import com.rad.entity.Item;
import com.rad.entity.Player;
import com.rad.input.KeyInput;
import com.rad.input.MouseInput;

/**
 * Represents the world of the game during game play. Holds the entire list of entities in the game world.
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class GameWorld {

	// A LinkedList was used over an ArrayList for constant time removal of objects, 
	// and the lack of need for accessing a single Entity
	
	/**
	 *  The list of all entities in the world.
	 */
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	
	/**
	 * The list of all players in the world.
	 */
	private LinkedList<Player> players = new LinkedList<Player>();
	
	/**
	 * The list of all enemies in the world.
	 */
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	/**
	 * The list of all items in the world.
	 */
	private LinkedList<Item> items = new LinkedList<Item>();
	
	/**
	 * The list of all blocks in the world.
	 */
	private LinkedList<Block> blocks = new LinkedList<Block>();
	
	/**
	 * The list of all players that ever existed in the world, so includes dead players as well.
	 */
	private LinkedList<Player> allPlayers= new LinkedList<>();
	
	/**
	 * A temporary holder of entities marked to be removed from the world. Necessary to avoid concurrent modifications.
	 */
	private Queue<Entity> deadEntitiesQueue = new LinkedList<Entity>();
	
	/**
	 * A temporary holder of entities marked to be newly added to the world. Necessary to avoid concurrent modifications.
	 */
	private Queue<Entity> newEntityQueue = new LinkedList<Entity>();
	
	/**
	 * Holds the instance of the main game.
	 */
	private Game game;

	/**
	 * The current user controlled player.
	 */
	private Player curPlayer;
	
	/**
	 * The number of spots remaining of players to be a user controlled player.
	 */
	private int numPlayers = Const.NUM_PLAYERS;
	
	/**
	 * The constructor.
	 * @param g the main game the game world was created from.
	 */
	public GameWorld(Game g) {
		
		this.game = g;		
	}

	/**
	 * Updates the game world state. Called Const.FRAMES_PER_SECOND frames per second.
	 * Adds all entities in queue to be added to the world, calls the tick() method for each entity in the world,
	 * removes all entities in queue to be removed from the world.
	 */
	public void tick() {
		checkGameOver();
		for (Entity e : newEntityQueue) {
			entities.add(e);
		}
		newEntityQueue.clear();
		
		for (Entity e : entities) {
			e.tick();
			if (e.isDead()) {
				deadEntitiesQueue.add(e);
			}
		}
		
		for (Entity e : deadEntitiesQueue) {
			removeEntity(e);
		}
		deadEntitiesQueue.clear();
	}

	/**
	 * Returns the list of all players that existed in the world, including currently dead players.
	 * @return the list of all players that existed in the world, including currently dead players.
	 */
	public LinkedList<Player> getAllPlayers() {
		return allPlayers;
	}

	
	/**
	 * Draws the game world to the screen. Called as often as possible.
	 * Draws the background and the entities to the screen.
	 * @param g the graphics the images are drawn on.
	 */
	public void render(Graphics g) {
    	g.drawImage(game.getFloor(), 0, 0, Const.WORLD_WIDTH, Const.WORLD_HEIGHT, 0, 0, Const.WORLD_WIDTH, Const.WORLD_HEIGHT, null);

		for (Entity e : entities) {
			e.render(g);
		}
	}

	/**
	 * Adds the given entity to the game world.
	 * @param e the entity to be added
	 */
	public void addEntity(Entity e) {
		newEntityQueue.add(e);
		
		if (e instanceof Player) {
			players.add((Player)e);
			allPlayers.add((Player) e);
		}
		else if (e instanceof Enemy) {
			enemies.add((Enemy)e);
		}
		else if (e instanceof Block) {
			blocks.add((Block)e);
		}
		else if (e instanceof Item) {
			items.add((Item)e);
		}
	}

	/**
	 * Removes the given entity from the game world.
	 * @param e the entity to be removed
	 */
	private void removeEntity(Entity e) {
		entities.remove(e);
		if (e instanceof Player) {
			players.remove((Player)e);
		}
		else if (e instanceof Enemy) {
			enemies.remove((Enemy)e);
		}
		else if (e instanceof Block) {
			blocks.remove((Block)e);
		}
		else if (e instanceof Item) {
			items.remove((Item)e);
		}
	}
	
	/**
	 * Loads a map from the given path. The map is a text file containing 
	 * ints that represent the ID of an entity to be added in that position.
	 */
	private void loadMap(String path) {
		Scanner scan = null;

		try {
			scan = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int countX = 0;
		int countY = 0;
		while (scan.hasNextInt()) {
			int curr = scan.nextInt();

			if (curr == 0) {
				//addEntity(new Empty(countX, countY));
			} else if (curr / 10 == 0) {
				addEntity(new Block(this, curr, countX, countY));
			} else if (curr / 10 == 1) {
				addEntity(new Enemy(this, curr, countX, countY));
			} else if (curr / 10 == 2) {
				if (numPlayers > 0) {
					curPlayer = new Player(this, curr, countX, countY, false);
					addEntity(curPlayer);
				} else {
					addEntity(new Player(this, curr, countX, countY, true));
				}
				numPlayers--;

			} else if (curr / 10 == 3) {
				addEntity(new Item(this, curr, countX, countY));
			}

			if (countX >= Const.WORLD_WIDTH - Const.TILE_SIZE) {
				countX = 0;
				countY += Const.TILE_SIZE;
			} else {
				countX += Const.TILE_SIZE;
			}

		}
	}

	/**
	 * Sets the game state to GameState.END if there is only one player left or the user controlled player is dead.
	 */
	private void checkGameOver() {
		if (players.size() == 1) {
			game.setGameState(Game.GameState.END);
			return;
		}
		for (Player p : allPlayers) {
			if (!p.isAI() && p.isDead()) {
				game.setGameState(Game.GameState.END);
				return;
			}
		}

	}
	
	/**
	 * Creates a new enemy to be spawned in the given x and y position.
	 * @param x the x coordinate the enemy will be spawned
	 * @param y the y coordinate the enemy will be spawned
	 */
	public void createEnemy(int x, int y) {
		int id;
		if (Math.random() < 0.05) {
			int index = (int)(Math.random() * Const.ID.BOSSES.length);
			id = Const.ID.BOSSES[index];
		}
		else {
			int index = (int)(Math.random() * Const.ID.GMOBS.length);
			id = Const.ID.GMOBS[index];
		}
		addEntity(new Enemy(this, id, x, y));
	}

	/**
	 * Initializes the game world. Clears all lists of entities and loads a new map.
	 */
	public void initialize() {
		entities.clear();
		players.clear();
		blocks.clear();
		enemies.clear();
		items.clear();
		allPlayers.clear();
		deadEntitiesQueue.clear();
		newEntityQueue.clear();
		numPlayers = Const.NUM_PLAYERS;
		
		loadMap(Const.PATHS.MAPS[0]);
	}
	
	/**
	 * Returns the key input handler
	 * @return the key input handler
	 */
	public KeyInput getKeyInput() {
		return this.game.getKeyInput();
	}

	/**
	 * Returns the mouse input handler
	 * @return the mouse input handler
	 */
	public MouseInput getMouseInput() {
		return this.game.getMouseInput();
	}
	
	/**
	 * Returns the list of entities in the game world
	 * @return the list of entities in the game world
	 */
	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * Returns the list of players in the game world
	 * @return the list of players in the game world
	 */
	public LinkedList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Returns the list of enemies in the game world
	 * @return the list of enemies in the game world
	 */
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * Returns the list of blocks in the game world
	 * @return the list of blocks in the game world
	 */
	public LinkedList<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * Returns the list of items in the game world
	 * @return the list of items in the game world
	 */
	public LinkedList<Item> getItems() {
		return items;
	}
	
	/**
	 * Returns the user controlled player
	 * @return the user controlled player
	 */
	public Player getCurPlayer() {
		return curPlayer;
	}
	
	/**
	 * Returns the sprite sheet that contains the sprites for all entities.
	 * @return the sprite sheet
	 */
	public BufferedImage getSpritesheet() {
		return game.getSpritesheet();
	}

}
