package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.rad.Const;
import com.rad.Location;
import com.rad.world.GameWorld;

/**
 * Is the base class for objects created in the map
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public abstract class Entity {
	
	/**
	 * The game world the entity is in
	 */
	protected GameWorld gameWorld;
	
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
     * the speed of the entity
     */
    protected int speed = 0;
    /**
     * velocity of the entity
     */
    protected int velX = 0;
    /**
     * velocity of y value in entitiy
     */
    protected int velY = 0;
    /**
     * width of the entity
     */
    protected int width = Const.TILE_SIZE;
    /**
     * height of the entity
     */
    protected int height = Const.TILE_SIZE;
    /**
     * checks if the entity is dead
     */
    protected boolean isDead;
    /**
     * Color of the enemy
     */
    protected Color color;
    
    /**
     * constructs a certain enemy and identity
     *
     * @param id the id that identifies the entity
     * @param x  the x coordinate
     * @param y  the y coordinate
     */
    public Entity(GameWorld gameWorld, int id, int x, int y) {
        this.gameWorld = gameWorld;
    	this.id = id;
        this.x = x * Const.TILE_SIZE;
        this.y = y * Const.TILE_SIZE;
        this.isDead = false;
        init();
    }

    /**
     * the method that identifies the type of entity
     */
    public abstract void init();

    /**
     * determines what updates
     */
    public void tick() {
    	for (Entity e : gameWorld.getEntities()) {
			if (this.getBounds().intersects(e.getBounds())) {
				handleCollision(e);
			}
		}
    	
    }


    /**
     * renders the object
     *
     * @param g tool used to draw object in the window
     */
    public void render(Graphics g) {
    	g.drawImage(gameWorld.getSpritesheet(), x, y, x + width, y + width, id % 10 * Const.TILE_SIZE, id / 10 * Const.TILE_SIZE, id % 10 * Const.TILE_SIZE + Const.TILE_SIZE, id / 10 * Const.TILE_SIZE + Const.TILE_SIZE, null);
    }
    
    /**
     * @param e is the entity you collide with
     * handles the collisions for to an entity
     */
    public void handleCollision(Entity e) {
    	//doNothing
    }

    /**
     * gets the location of this entity
     * @return
     */
    public Location getLocation()
    {
        return new Location(this.x, this.y);
    }

    /**
     * clamps the min and max values to preset values, used in player bounds
     * @param i value to be clamped
     * @param min min possible value
     * @param max max possible value
     * @return clamped value
     */
    protected int clamp(int i, int min, int max) {
        if (i < min) return min;
        if (i > max) return max;
        return i;
    }


    /**
     * checks if the entity is dead
     * @return if the entity is dead
     */
    public boolean isDead() {
        return isDead;
    }
    /**
     * gets the bounds of the rectangle
     * @return a new rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x + velX, y + velY, Const.TILE_SIZE, Const.TILE_SIZE);
    }

    /**
     * returns a simple name of the entity
     * @return the entity's name
     */
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
