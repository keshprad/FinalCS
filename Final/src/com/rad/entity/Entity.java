package com.rad.entity;

import java.awt.Graphics;

import com.rad.Const;
import com.rad.world.GameWorld;

/**
 * Is the base class for objects created in teh map
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public abstract class Entity {
	/**
	 * ID that is specific to each identity
	 */
	protected int id;
	/**
	 * the X coordinate of the object
	 */
	protected int x;
	/**
	 * the Y coordinate of the object
	 */
	protected int y;
	/**
	 *  the speed of the entity
	 */
	protected float speed;

	/**
	 *  constructs a certain enemy and identity
	 * @param id the id that identifies the entitiy
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Entity(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		init();
	}

	/**
	 * the method that identifies the type of entity
	 */
	abstract public void init();

	/**
	 * determines what updates
	 */
	public abstract void tick();

	/**
	 * renders the object
	 * @param g tool used to draw object in the window
	 */
	public abstract void render(Graphics g);

	/**
	 * gets the id
	 * @return indentification of the object
	 */
	public int getId() {
		return id;
	}

	/**
	 * changes the id
	 * @param id the id of the certain object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * returns X
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * returns y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the postion of the object
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void setPosition(int x, int y) {
		if (x < 0 || x >= Const.WORLD_WIDTH_IN_TILES) return;
		if (y < 0 || y >= Const.WORLD_HEIGHT_IN_TILES) return;
		this.x = x;
		this.y = y;
	}

	/**
	 * returns the speed of the entity
	 * @return the speed of entity
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * sets the speed of the entity
	 * @param speed the speed of the entity
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * retunrs the four adjacent objects to entity
	 * @return an array of adjacent elements
	 */
	public Entity[] getAdjacent() {
		Entity[] adjacents = new Entity[4];
//		adjacents[0] = GameWorld.entities[y][x + 1];
//		adjacents[1] = GameWorld.entities[y - 1][x];
//		adjacents[2] = GameWorld.entities[y][x - 1];
//		adjacents[3] = GameWorld.entities[y + 1][x];
		return adjacents;
	}

	/**
	 * NEW
	 * needed for AI for enemy and player
	 * returns the shortest distance between two objects
	 * @param e the object compared to another objectct
	 * @return the distance between two objects
	 */
	public double shortestDistanceBtwn(Entity e)
	{
		return 0; //FIX
	}
	/**
	 * NEW
	 * series of moves that decrease shortestDistanceBtwn of this entity and the other entity
	 */
	public void stepsTOEntity(Entity e)
	{

	}
	
}
