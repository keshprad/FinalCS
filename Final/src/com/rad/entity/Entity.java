package com.rad.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rad.Const;
import com.rad.Location;
import com.rad.world.GameWorld;

/**
 * The base class of all entities to be added to the game world. Inherited by Block, Enemy, Item, Player.
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public abstract class Entity {
	
	/**
	 * The game world the entity is in.
	 */
	protected GameWorld gameWorld;
	
    /**
     * The ID indicating the type of entity it is.
     */
    protected int id;
    
    /**
     * The X coordinate of the entity in pixels.
     */
    protected int x;
    
    /**
     * The Y coordinate of the entity in pixels.
     */
    protected int y;
    
    /**
     * The speed of the entity in pixels per tick.
     */
    protected int speed = 0;
    
    /**
     * The velocity of the entity in the x direction in pixels per tick.
     */
    protected int velX = 0;
    
    /**
     * The velocity of the entity in the y direction in pixels per tick.
     */
    protected int velY = 0;
    
    /**
     * The width of the entity
     */
    protected int width = Const.TILE_SIZE;
    
    /**
     * The height of the entity
     */
    protected int height = Const.TILE_SIZE;
    
    /**
     * Represents if the entity is marked for removal from the game world.
     */
    protected boolean isDead;
    
    /**
     * The constructor. Calls the init() method.
     *
     * @param id the id that identifies what type of entity the entity is
     * @param x  the x coordinate of the entity in pixels
     * @param y  the y coordinate of the entity in pixels
     */
    public Entity(GameWorld gameWorld, int id, int x, int y) {
        this.gameWorld = gameWorld;
    	this.id = id;
        this.x = x;
        this.y = y;
        this.isDead = false;
        init();
    }

    /**
     * Used to initialize any fields for the entity specific to each certain subclass of entity.
     */
    public abstract void init();

    /**
	 * Updates the entity's state. Called Const.FRAMES_PER_SECOND frames per second.
	 * Calls handleCollision(Entity e) if this entity is colliding with another entity.
	 */
    public void tick() {
    	for (Entity e : gameWorld.getEntities()) {
			if (e != this && this.getBounds().intersects(e.getBounds())) {
				handleCollision(e);
			}
		}
    	
    }

    /**
	 * Draws the game world to the screen. Called as often as possible.
	 * Draws the entity to the screen. The entity's sprite is determined by its ID;
	 * its position on the game world sprite sheet is calculated by the ID.
	 * @param g the graphics the images are drawn on.
	 */
    public void render(Graphics g) {
    	g.drawImage(gameWorld.getSpritesheet(), x, y, x + width, y + width, id % 10 * Const.TILE_SIZE, id / 10 * Const.TILE_SIZE, id % 10 * Const.TILE_SIZE + Const.TILE_SIZE, id / 10 * Const.TILE_SIZE + Const.TILE_SIZE, null);
    }
    
    /**
     * Handles a collision with another entity. To be overridden by subclasses.
     * @param e the entity this entity collided with
     */
    public void handleCollision(Entity e) {

    }

    /**
     * Returns the location of the entity.
     * @return the location of the entity.
     */
    public Location getLocation()
    {
        return new Location(this.x, this.y);
    }

    /**
     * Clamps the given int between the given min and max. If i is less than min, min is returned;
     * if i is greater than max, max is returned.
     * @param i the integer to be clamped
     * @param min the minimum the integer is not to be less than
     * @param max the maximum the integer is not to be greater than
     * @return
     */
    protected int clamp(int i, int min, int max) {
        if (i < min) return min;
        if (i > max) return max;
        return i;
    }

    /**
     * Returns if the entity is dead
     * @return if the entity is dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Returns the bounds of the entity used for collision detection. The entity's velocity is 
     * added to the x and y to prevent the entities from being stuck.
     * @return the bounds of the entity
     */
    public Rectangle getBounds() {
        return new Rectangle(x + velX, y + velY, height, width);
    }

    @Override
    /**
     * Returns the entity's name.
     * @return the entity's name
     */
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
