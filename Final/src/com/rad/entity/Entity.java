package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.rad.Const;
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

    protected int velX = 0;
    protected int velY = 0;

    protected int width = Const.TILE_SIZE;
    protected int height = Const.TILE_SIZE;

    protected boolean isDead;

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
    	g.drawImage(gameWorld.getSpritesheet(), x, y, x + width, y + width, 1, 1, 1 + 12, 1 + 12, null);
    }
    
    /**
     * @param e
     */
    public void handleCollision(Entity e) {
    	//doNothing
    }

    public Location getLocation()
    {
        return new Location(this.x, this.y);
    }
    protected int clamp(int i, int min, int max) {
        if (i < min) return min;
        if (i > max) return max;
        return i;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + velX, y + velY, Const.TILE_SIZE, Const.TILE_SIZE);
    }

    /*protected void invClamp(int left, int right, int top, int bottom) {
	if (this.x > left - this.width && this.x < left - this.width + (speed + 1)) {
		this.x = left - this.width;
	}
	else if (this.x < right && this.x > left + this.width - (speed + 1)) {
		this.x = right;
	}	
	else if (this.y > top - this.height && this.y < top - this.height + (speed + 1)) {
		this.y = top - this.height;
	}
	else if (this.y < bottom && this.y > top + this.height - (speed + 1)) {
		this.y = bottom;
	}*/

    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    public boolean isDead() {
        return isDead;
    }
}
