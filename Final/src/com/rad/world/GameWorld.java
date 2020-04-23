package com.rad.world;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import com.rad.Constants;
import com.rad.assets.Assets;
import com.rad.entity.Block;
import com.rad.entity.Enemy;
import com.rad.entity.Entity;
import com.rad.entity.Item;
import com.rad.entity.Player;

public class GameWorld {

	private LinkedList<Entity> entities = new LinkedList<Entity>();

	public GameWorld() {
		loadMap(Assets.MAP0);
	}
	
	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public void printMap() {
		int[][] tilemap = new int[Constants.WORLD_WIDTH_IN_TILES][Constants.WORLD_HEIGHT_IN_TILES];
		
		
		for (int i = 0; i < Constants.WORLD_WIDTH_IN_TILES; i++) {
    		for (int j = 0; j < Constants.WORLD_HEIGHT_IN_TILES; j++) {
    			System.out.print("[Entity: " + tilemap[i][j] + "] ");
    		}
    		System.out.print("\n\n");
    	}
	}
	
	private void loadMap(String path) {
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(path));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int countX = 0;
		int countY = 0;
		while (scan.hasNextInt()) {
			int curr = scan.nextInt();
			
			if (curr == 0) {
				//do nothing
			}
			else if (curr/10 == 0) {
				addEntity(new Block(curr, countX, countY));
			}
			else if (curr/10 == 1) {
				addEntity(new Enemy(curr, countX, countY));
			}
			else if (curr/10 == 2) {
				addEntity(new Player(curr, countX, countY));
			}
			else if (curr/10 == 3) {
				addEntity(new Item(curr, countX, countY));
			}
			
	    	if (countX >= Constants.WORLD_WIDTH_IN_TILES - 1) {
	    		countX = 0;
	    		countY++;
	    	}
	    	else {
	    		countX++;
	    	}


		}
	}

}
