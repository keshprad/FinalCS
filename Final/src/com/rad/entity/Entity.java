package com.rad.entity;

import java.awt.Graphics;

import com.rad.Const;
import com.rad.world.GameWorld;

/**
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public abstract class Entity {

	protected int id;

	protected int x;
	protected int y;
	protected float speed;
		
	public Entity(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		init();
	}
	
	public void init() {
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setPosition(int x, int y) {
		if (x < 0 || x >= Const.WORLD_WIDTH_IN_TILES) return;
		if (y < 0 || y >= Const.WORLD_HEIGHT_IN_TILES) return;
		this.x = x;
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Entity[] getAdjacent() {
		Entity[] adjacents = new Entity[4];
//		adjacents[0] = GameWorld.entities[y][x + 1];
//		adjacents[1] = GameWorld.entities[y - 1][x];
//		adjacents[2] = GameWorld.entities[y][x - 1];
//		adjacents[3] = GameWorld.entities[y + 1][x];
		return adjacents;
	}
	
}
