package com.rad.world;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

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
	public LinkedList<Entity> entities = new LinkedList<Entity>();	
	private LinkedList<Entity> deadEntities = new LinkedList<Entity>();
	
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
	}

	/**
	 * where to remove the entity
	 * @param e removes this entity in the mape
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * returns the entity map
	 * @return the entire map of entities
	 */
	public LinkedList<Entity> getEntities() {
		return entities;
	}


	/**
	 * Prints the map in a grid format for debugging
	 */
	public void printMap() {
		Entity[][] tilemap = new Entity[Const.WORLD_WIDTH_IN_TILES][Const.WORLD_HEIGHT_IN_TILES];

		for (Entity e : entities) {
			tilemap[e.getY() / Const.TILE_SIZE][e.getX() / Const.TILE_SIZE] = e;
		}
		
		for (int i = 0; i < Const.WORLD_WIDTH_IN_TILES; i++) {
			for (int j = 0; j < Const.WORLD_HEIGHT_IN_TILES; j++) {
				System.out.print("[Entity: " + tilemap[i][j] + "] ");
			}
			System.out.print("\n\n");
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

}
