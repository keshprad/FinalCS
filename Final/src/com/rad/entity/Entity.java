package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
    private int id;
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
    	g.setColor(color);
		g.fillRect(x, y, width, height);
    }
    
    /**
     * @param e
     */
    public void handleCollision(Entity e) {
   
    }


    protected int clamp(int i, int min, int max) {
        if (i < min) return min;
        if (i > max) return max;
        return i;
    }

//    protected void invClamp(int left, int right, int top, int bottom) {
//        if (this.x > left - this.width && this.x < left - this.width + velX) {
//            this.x = left - this.width;
//        } else if (this.x < right && this.x > left + this.width - velX) {
//            this.x = right;
//        } else if (this.y > top - this.height && this.y < top - this.height + velY) {
//            this.y = top - this.height;
//        } else if (this.y < bottom && this.y > top + this.height - velY) {
//            this.y = bottom;
//        }
//    }

    public Rectangle getBounds() {
        return new Rectangle(x + velX, y + velY, Const.TILE_SIZE, Const.TILE_SIZE);
    }

    public boolean overlaps(Rectangle r) {
        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }

    public boolean doesCollide(Entity e) {
        return this.overlaps(e.getBounds());
    }

    /**
     * gets the id
     *
     * @return id of the object
     */
    public int getId() {
        return id;
    }

    /**
     * changes the id
     *
     * @param id the id of the certain object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns X
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * returns y coordinate
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * returns the speed of the entity
     *
     * @return the speed of entity
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * sets the speed of the entity
     *
     * @param speed the speed of the entity
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
