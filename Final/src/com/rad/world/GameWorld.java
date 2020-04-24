package com.rad.world;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.rad.Const;
import com.rad.entity.Block;
import com.rad.entity.Empty;
import com.rad.entity.Enemy;
import com.rad.entity.Entity;
import com.rad.entity.Item;
import com.rad.entity.Player;

public class GameWorld {

	// Holds all Entities in the map. A LinkedList was used over an ArrayList for
	// constant time removal of objects, and the lack of need for accessing a single
	// specific Entity
	//private LinkedList<Entity> entities = new LinkedList<Entity>();
	
	private int numPlayers = 1; // Changeable later
	
	private Entity[][] entities = new Entity[Const.WORLD_WIDTH_IN_TILES][Const.WORLD_HEIGHT_IN_TILES];

	public GameWorld() {
		loadMap(Const.PATHS.MAP2);
	}

	public void tick() {
		for (int y = 0; y < entities.length; y++) {
			for (int x = 0; x < entities[0].length; x++) {
				if (entities[y][x] != null) entities[y][x].tick();
			}
		}
	}

	public void render(Graphics g) {
		for (int y = 0; y < entities.length; y++) {
			for (int x = 0; x < entities[0].length; x++) {
				if (entities[y][x] != null) entities[y][x].render(g);
			}
		}
	}

	public void addEntity(Entity e) {
		entities[(int)e.getY()][(int)e.getX()] = e;
	}

	public void removeEntity(Entity e) {
		entities[(int)e.getY()][(int)e.getX()] = null;
	}
	
	public Entity[][] getEntities() {
		return entities;
	}
	
//	public boolean attemptMoveEntity(Entity entity, int newX, int newY) {
//		Entity e = entities[newY][newY];
//		if (e instanceof Empty) {
//			int oldX = (int) entity.getX();
//			int oldY = (int) entity.getY();
//			entities[newY][newX] = entity;
//			entities[oldY][oldX] = e;
//			return true;
//		}
//		else if (e instanceof Item) {
//			int oldX = (int) entity.getX();
//			int oldY = (int) entity.getY();
//			entities[newY][newX] = entity;
//			addEntity(entity);
//			addEntity(new Empty(oldX, oldY));
//			return true;
//		}
//		return true;
//	}

	// Prints the map in a grid format
	public void printMap() {
		int[][] tilemap = new int[Const.WORLD_WIDTH_IN_TILES][Const.WORLD_HEIGHT_IN_TILES];

		for (int i = 0; i < Const.WORLD_WIDTH_IN_TILES; i++) {
			for (int j = 0; j < Const.WORLD_HEIGHT_IN_TILES; j++) {
				System.out.print("[Entity: " + tilemap[i][j] + "] ");
			}
			System.out.print("\n\n");
		}
	}

	// Loads a map from the given path
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
				addEntity(new Empty(countX, countY));
			} else if (curr / 10 == 0) {
				addEntity(new Block(curr, countX, countY));
			} else if (curr / 10 == 1) {
				addEntity(new Enemy(curr, countX, countY));
			} else if (curr / 10 == 2) {
				addEntity(new Player(curr, countX, countY, numPlayers <= 0));
				numPlayers--;
			} else if (curr / 10 == 3) {
				addEntity(new Item(curr, countX, countY));
			}

			if (countX >= Const.WORLD_WIDTH_IN_TILES - 1) {
				countX = 0;
				countY++;
			} else {
				countX++;
			}

		}
	}

}
