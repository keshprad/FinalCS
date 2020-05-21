package com.rad.entity;

import java.awt.*;
import java.util.*;

import com.rad.Const;
import com.rad.Location;
import com.rad.world.GameWorld;

import static com.rad.Const.TILE_SIZE;

/**
 * This class defines an Enemy. This includes zombies and other enemies.
 * <p>
 * id = 10 -> GHOUL
 * id = 11 -> GOLEM
 * id = 12 -> GNAT
 * id = 13 -> GUPPY
 * id = 14 -> BOT
 * id = 15 -> MECH
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
    /**
     * checks the id for which type of enemy it is, each enemy has its own speed (determined by a design choice)
     */
    public void init() {
        switch (id) {
            case Const.ID.GHOUL:
                speed = 6;
                break;
            case Const.ID.GOLEM:
                speed = 4;
                break;
            case Const.ID.GNAT:
                speed = 8;
                break;
            case Const.ID.GUPPY:
                speed = 4;
                break;
            case Const.ID.BOT:
                speed = 4;
                break;
            case Const.ID.MECH:
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
    public void tick() {
        Player p = null;
        double d = Double.MAX_VALUE;

        for (Entity e : gameWorld.getEntities()) {
            if (e instanceof Player) {
                // TODO find closest Player
                Location playerLocation = new Location(e.x, e.y);
                if (this.getLocation().distBetween(playerLocation) < d) {
                    p = (Player) e;
                    d = this.getLocation().distBetween(playerLocation);
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
     *
     * @param l
     */
    public void move(Location l) {
        this.x = l.getX();
        this.y = l.getY();
    }

    /**
     * A* search to find player
     *
     * @param player
     */
    public void chasePlayers(Player player) {
        //Space it out more
        //Add comments describing the whole process
        //Implementing speed(the field)

        PriorityQueue<Location> priorityQueue = new PriorityQueue<Location>((o1, o2) -> Double.compare(o1.getPriority(), o2.getPriority()));
        // creates a priority queue of possible nodes that the enemy can go to
        //(o1, o2) -> Double.compare(o1.priority, o2.priority) basically tells the PriorityQ to compare through the compare method
        //the higher up the node is on the priorityQ the more favorable the location is for the enemy
        //
        
        HashMap<Location, Double> possibleMoveLocationsHashMap = new HashMap<>();
        Location startingLoc = this.getLocation();
        priorityQueue.add(startingLoc);// adds the position you are in
        Location playerLocation = player.getLocation();
        possibleMoveLocationsHashMap.put(startingLoc, 0.0);//puts the player into the map
        
        while (!priorityQueue.isEmpty()) {
            Location highestPriorityPossibleLocation = priorityQueue.poll();// first iteration of the loop removes first node, rest helps determine
            Rectangle rectangle = new Rectangle(highestPriorityPossibleLocation.getX(), highestPriorityPossibleLocation.getY(), TILE_SIZE, TILE_SIZE);//possible location movement option
            
            if (goalStateFound(rectangle,player,highestPriorityPossibleLocation)) { 
            	System.out.println("Code reaches here!");
            	break;
        	}
            
            //checks every possible move position in the tile in the gameworld with this enemy
            checkPossibleMoveLocations(highestPriorityPossibleLocation, playerLocation,possibleMoveLocationsHashMap,priorityQueue);
        }
    }

    /**
     * checks the next possible move state
     * @param highestPriorityPossibleLocation it is the location with the next highest priority location
     * @param playerLocation is the location of the player
     * @param map is the hash mape of all possible move lcoations
     * @param pq priority que of locations
     */
    public void checkPossibleMoveLocations(Location highestPriorityPossibleLocation, Location playerLocation,  HashMap<Location, Double> map, PriorityQueue<Location> pq)
    {
        for (Location possiblePostion : possibleMoveLocations(gameWorld, highestPriorityPossibleLocation, this))
        {

            if (possiblePostion != null)
            {

                Location movePoint; // if the location
                if (highestPriorityPossibleLocation.getMovePoint() == null)
                {
                    movePoint = possiblePostion;
                }
                else movePoint = highestPriorityPossibleLocation.getMovePoint();


                double dist = highestPriorityPossibleLocation.getDist() + highestPriorityPossibleLocation.distBetween(possiblePostion);
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

    /**
     * tests whether the goal state is found or not
     * @param rectangle is the rectangle of the hypothetical position the enemy is in
     * @param player is the player you are trying to capture
     * @param location is the location you are moving to
     * @return if you found the goal state
     */
    public boolean goalStateFound(Rectangle rectangle, Player player, Location location)
    {
        if (rectangle.intersects(player.getBounds())&&(location.getMovePoint() != null)) //if the enemy caught the player
        {
            move(location.getMovePoint());
            return true;
        }
        return false;
    }
    /**
     * gets possible locations for enemy
     *
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
//        if (this.id == Const.ID.GNAT) {
//            possibleLocations[0] = new Location(xl + this.speed, yl, 0, enemyLocation.getMovePoint());
//            possibleLocations[1] = new Location(xl - this.speed, yl, 0, enemyLocation.getMovePoint());
//            possibleLocations[2] = new Location(xl, yl + this.speed, 0, enemyLocation.getMovePoint());
//            possibleLocations[3] = new Location(xl, yl - this.speed, 0, enemyLocation.getMovePoint());
//        } else {
            possibleLocations[0] = new Location(xl + this.speed, yl, 0, enemyLocation.getMovePoint());
            possibleLocations[1] = new Location(xl - this.speed, yl, 0, enemyLocation.getMovePoint());
            possibleLocations[2] = new Location(xl, yl + this.speed, 0, enemyLocation.getMovePoint());
            possibleLocations[3] = new Location(xl, yl - this.speed, 0, enemyLocation.getMovePoint());
        //}

        for (int i = 0; i < possibleLocations.length; i++) {
            if (!enemyLocation.inGrid(possibleLocations[i])) {
                possibleLocations[i] = null;
            }
            for (Block b : gameWorld.getBlocks()) {
            	if (possibleLocations[i] != null && b.getBounds().intersects(enemyLocation.toRect(possibleLocations[i]))) {
                    possibleLocations[i] = null;
                }
            }
        }

        return possibleLocations;
    }
    
//    @Override
//    public void handleCollision(Entity e) {
//		if (e instanceof Block) {
//			velX *= -1;
//			velY *= -1;
//		}
//		if (e instanceof Player) {
//			e.isDead = true;
//		}
//
//	}
}