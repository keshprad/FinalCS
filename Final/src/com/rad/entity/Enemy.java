package com.rad.entity;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.rad.Const;
import com.rad.world.GameWorld;

import static com.rad.Const.ENEMEY_AI_SLOW_DOWN_RATE;
import static com.rad.Const.TILE_SIZE;

/**
 * This class defines an Enemy. This includes zombies and other enemies.
 * <p>
 * id = 10 -> ZOMBIE
 * id = 11 -> IMP_ZOMBIE
 *
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Enemy extends Entity {

	/**
     * Constructor for Enemy class
     *
     * @param id Tells us the type of Enemy
     * @param x  initial x-position of the block
     * @param y  initial y-position of the block
     */
    public Enemy(GameWorld gameWorld, int id, int x, int y) {
        super(gameWorld, id, x, y);
    }
    
    @Override
    public void init() {
    	switch (id) {
		case Const.ID.ZOMBIE:
			color = Color.RED;
			speed = 4;
			break;
		case Const.ID.IMP_ZOMBIE:
			color = Color.ORANGE;
			speed = 5;
			break;
		default:
			break;
    	}
    }

    @Override
    /**
     * Necessary for GUI, but not needed for this class
     */
    public void tick() {

        for (Entity e : gameWorld.getEntities()) {
            if (e instanceof Player) {
                // TODO find closest Player
                if (Math.random() < Const.ENEMEY_AI_PROB_OF_MOVE_PER_TICK) {
                    this.chasePlayers((Player) e);
                }
            }
        }
        //x += velX;
        //y += velY;
        super.tick();
    }

    @Override
    /**
     * renders a graphic to represent an Enemy. Either a zombie...
     */
    public void render(Graphics g) {
        super.render(g);
    }
    
    /**
     * move
     * @param l
     */
    public void move(Location l) {
        this.x = l.getX();
        this.y = l.getY();
    }

    /**
     * A* search to find player
     * @param player
     */
    public void chasePlayers(Player player) {
    	//Space it out more
    	//Add comments describing the whole process
    	//Implementing speed(the field)
    	
        PriorityQueue<Location> pq = new PriorityQueue<Location>((o1, o2) -> Double.compare(o1.priority, o2.priority));
        // creates a priority queue of possible points that the person can go to

        HashMap<Location, Double> map = new HashMap<>();
        Location startingLoc = new Location(this.x, this.y, 0, null, 0);
        pq.add(startingLoc);// adds the position you are in
        Location playerLocation = new Location(player.x, player.y, 0);
        map.put(startingLoc, 0.0);
        while (!pq.isEmpty()) {
            Location pt = pq.poll();
            Rectangle rectangle = new Rectangle(pt.getX(), pt.getY(), TILE_SIZE, TILE_SIZE);
            if (rectangle.intersects(player.getBounds())) //this line needs to have getbounds collision
            {
                if (pt.movePoint != null) {
                    move(pt.movePoint);
                }
                break;
            }
            for (Location possiblePostion : pt.possibleMoveLocations(gameWorld, pt, this)) {
                if (possiblePostion != null) {
                    Location movePoint = pt.movePoint;
                    if (movePoint == null) {
                        movePoint = possiblePostion;
                    }
                    double dist = pt.dist + pt.distBetween(possiblePostion);
                    double priority = possiblePostion.distBetween(playerLocation) + dist;
                    Location neighbor = new Location(possiblePostion.getX(), possiblePostion.getY(), dist, movePoint, priority);

                    if (!map.containsKey(neighbor)) {
                        map.put(possiblePostion, priority);
                        pq.add(neighbor);
                    } else if (map.get(possiblePostion) > priority) {
                        map.put(possiblePostion, priority);
                        pq.remove(neighbor);
                        pq.add(neighbor);

                    }

                }
            }

        }
    }


}
