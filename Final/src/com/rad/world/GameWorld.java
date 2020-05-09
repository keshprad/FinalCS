package com.rad.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.rad.Const;
import com.rad.entity.Block;
import com.rad.entity.Enemy;
import com.rad.entity.Entity;
import com.rad.entity.Item;
import com.rad.entity.Player;
import com.rad.input.KeyInput;

/**
 * This class holds the map where the map loads up, and where the window resets every secons
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class GameWorld {

	// Holds all Entities in the map. A LinkedList was used over an ArrayList for
	// constant time removal of objects, and the lack of need for accessing a single
	// specific Entity
	private LinkedList<Entity> entities = new LinkedList<Entity>();
	private LinkedList<Player> players = new LinkedList<Player>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<Block> blocks = new LinkedList<Block>();
	private LinkedList<Entity> deadEntities = new LinkedList<Entity>();
	
	/**
	 * the spritesheet to use for all sprites in the game
	 */
	private BufferedImage spritesheet;
	
	/**
	 * used to handle user key input
	 */
	private KeyInput keyInput;
	
	/**
	 * the number of players in a game
	 */
	private int numPlayers = 0; // Changeable later
	
	/**
	 * calls loadMap which reads a user generated map to create a map in the window
	 */
	public GameWorld() {
		loadMap(Const.PATHS.MAP2);
		loadSpritesheet(Const.PATHS.SPRITESHEET);
		keyInput = new KeyInput();
	}

	/**
	 * iterates through the map, reloading it every second
	 */
	public void tick() {
		for (Entity e : entities) {
			e.tick();
			if (e.isDead()) {
				deadEntities.add(e);
			}
		}
		for (Entity e : deadEntities) {
			removeEntity(e);
		}
		deadEntities.clear();
	}
	
	/**
	 * Draws the classes in the window
	 * @param g input required to draw
	 */
	public void render(Graphics g) {
		// Drawing the background
    	g.drawImage(getSpritesheet(), 0, 0, Const.WORLD_WIDTH, Const.WORLD_HEIGHT, 0, 0, Const.TILE_SIZE, Const.TILE_SIZE, null);

		for (Entity e : entities) {
			e.render(g);
		}
	}

	/**
	 * given the map, it places the entity in that location
	 * this is done in the start of the program
	 * @param e
	 */
	public void addEntity(Entity e) {
		entities.add(e);
		if (e instanceof Player) {
			players.add((Player)e);
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
	 * where to remove the entity
	 * @param e removes this entity in the mape
	 */
	public void removeEntity(Entity e) {
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
	 * Loads the sprite sheet
	 */
	private void loadSpritesheet(String path) {
		try {
			spritesheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads a map from the given path (path meaning the numbers printed in folder res)
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
				addEntity(new Player(this, curr, countX, countY, numPlayers <= 0));
				numPlayers--;
			} else if (curr / 10 == 3) {
				addEntity(new Item(this, curr, countX, countY));
			}

			if (countX >= Const.WORLD_WIDTH_IN_TILES - 1) {
				countX = 0;
				countY++;
			} else {
				countX++;
			}

		}
	}
	
	/**
	 * Returns the key input
	 * @return the key input
	 */
	public KeyInput getKeyInput() {
		return keyInput;
	}
	
	/**
	 * returns the entity map
	 * @return the entire map of entities
	 */
	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * returns the player
	 * @return list of players in map
	 */
	public LinkedList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * returns the enemies
	 * @return list of enemies in map
	 */
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * returns the blocks
	 * @return list of blocks in map
	 */
	public LinkedList<Block> getBlocks() {
		return blocks;
	}
	
	/**
	 * returns the items
	 * @return list of items in map
	 */
	public LinkedList<Item> getItems() {
		return items;
	}
	
	/**
	 * returns the sprite sheet
	 * @return the sprite sheet
	 */
	public BufferedImage getSpritesheet() {
		return spritesheet;
	}
}
