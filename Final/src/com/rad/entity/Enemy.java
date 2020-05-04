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
			speed = 3;
			break;
		case Const.ID.IMP_ZOMBIE:
			color = Color.ORANGE;
			speed = 4;
			break;
		default:
			break;
    	}
    }

    @Override
    /**
     * Necessary for GUI, but not needed for this class
     */
    public void tick()
    {	
    	Player p=null;
        double d=Double.MIN_VALUE;

        for (Entity e : gameWorld.getEntities())
        {
            if (e instanceof Player) {
                // TODO find closest Player
                Location playerLocation=new Location(e.x,e.y);
                if (this.getLocation().distBetween(playerLocation)>d)
               {
                   p=(Player)e;
                }

                }
        }
        if (p != null) {
        	this.chasePlayers(p);
        }
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
        // creates a priority queue of possible nodes that the enemy can go to
        //(o1, o2) -> Double.compare(o1.priority, o2.priority) basically tells the PriorityQ to compare through the compare method
        //the higher up the node is on the priorityQ the more favorable the location is for the enemy
        //

        HashMap<Location, Double> map = new HashMap<>();
        Location startingLoc = new Location(this.x, this.y, 0, null, 0);
        pq.add(startingLoc);// adds the position you are in
        Location playerLocation = new Location(player.x, player.y, 0);
        map.put(startingLoc, 0.0);//puts the player into the map
        while (!pq.isEmpty()) {
            Location pt = pq.poll();// first iteration of the loop removes first node, rest helps determine
            Rectangle rectangle = new Rectangle(pt.getX(), pt.getY(), TILE_SIZE, TILE_SIZE);
            if (rectangle.intersects(player.getBounds())) //if the enemy caught the player
            {
                if (pt.movePoint != null)
                {
                    move(pt.movePoint);
                }
                break;
            }
            //checks every possible move position in the tile in the gameworld with this enemy
            for (Location possiblePostion : possibleMoveLocations(gameWorld, pt, this))
            {
                if (possiblePostion != null) {
                    Location movePoint = pt.movePoint;// if the location
                    if (movePoint == null) {
                        movePoint = possiblePostion;
                    }
                    double dist = pt.dist + pt.distBetween(possiblePostion);
                    double priority = possiblePostion.distBetween(playerLocation) + dist; //hueristic to determine the priority of the A* algorithim which is
                    //basically travel distance  so far + distance to Location
                    Location neighbor = new Location(possiblePostion.getX(), possiblePostion.getY(), dist, movePoint, priority); //highest priority should be the nieghbor to the enemy

                    if (!map.containsKey(neighbor)) // if the map of all possible tiles does not contain this neighbor nessary for not including repeats
                    {
                        map.put(possiblePostion, priority);
                        pq.add(neighbor);
                    } else if (map.get(possiblePostion) > priority) //if the possible postition has a higher priority than whats given,
                        // where all of them would be put on the first run, put in map of all possible positions
                    {
                        map.put(possiblePostion, priority);
                        pq.remove(neighbor);
                        pq.add(neighbor);
                    }
                }
            }
        } 
    }
    
    /**
     * gets possible locations for enemy
     * @param gameWorld
     * @param enemyLocation
     * @param enemy
     * @return
     */
    public Location[] possibleMoveLocations(GameWorld gameWorld, Location enemyLocation, Enemy enemy) {
        int xl = enemyLocation.getX();
        int yl = enemyLocation.getY();
        if (!enemyLocation.inGrid(xl, yl)) {
            return new Location[]{};
        }

        Location[] possibleLocations = new Location[4];
        possibleLocations[0] = new Location(xl + this.speed, yl, 0, enemyLocation.movePoint);
        possibleLocations[1] = new Location(xl - this.speed, yl, 0, enemyLocation.movePoint);
        possibleLocations[2] = new Location(xl, yl + this.speed, 0, enemyLocation.movePoint);
        possibleLocations[3] = new Location(xl, yl - this.speed, 0, enemyLocation.movePoint);

        for (int i = 0; i < possibleLocations.length; i++) {
            if (!enemyLocation.inGrid(possibleLocations[i])) {
                possibleLocations[i] = null;
            }
        }
        
		for (Block b : gameWorld.getBlocks()) {
			for (int i = 0; i < possibleLocations.length; i++) {
				if (possibleLocations[i] != null && b.getBounds().intersects(enemyLocation.toRect(possibleLocations[i]))) {
					possibleLocations[i] = null;
				}
			}
		}
		return possibleLocations;
	}
    
}
