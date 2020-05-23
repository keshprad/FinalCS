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
     * Constructor for Enemy class and initializes the enemy
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
     * the ids with bot and mech are considered "bosses" therefore have a higher speed but lower spawn rate
     */
    public void init() {
        switch (id) {
            case Const.ID.GHOUL:
                speed = 4;
                break;
            case Const.ID.GOLEM:
                speed = 4;
                break;
            case Const.ID.GNAT:
                speed = 8;
                break;
            case Const.ID.GUPPY:
                speed = 8;
                break;
            case Const.ID.BOT:
                speed = 16;
                break;
            case Const.ID.MECH:
                speed = 16;
                break;
            default:
                break;
        }
    }

    @Override
    /**
     * Necessary for GUI, but not needed for this class
     * finds the closest player, judged by the distance between the enemy and player
     * then calls chase enemy to chase the closest enemy.
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
     * renders a graphic to represent an Enemy. Uses the pixel images in the spreadsheet to represent
     * each mob by id
     */
    public void render(Graphics g) {
        super.render(g);
    }

    /**
     * moves the enemy to the location given
     * usually called when the location is right next to the enemy
     *
     * @param l the location of where the enemy is going to move to
     */
    public void move(Location l) {
        this.x = l.getX();
        this.y = l.getY();
    }

    /**
     * an A* search to find player and capture it
     *  How an A* algorithim works is that it places its location in a priority que.
     *  This priorityque holds locations and is ordered by the distance in locations from the enemy to a possible point
     *  (not nessecarily the distance, but a hueristic that incorporates distance.)
     *  in where the enemy can move to.
     *  Another data structure is a hashmap that holds all the possible moves that the enemy can move.
     *  Then the enemy goes through the priority que, expanding all the best possible moves by distance.
     *  If there is a location that meets the goal state, then the enemy
     *  moves through the best possible moves to where the player is
     *  If the goal state is not found, the enemy explores through the neighbor locations, adding it to the priority que
     *  until goal state is found.
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
            Location currLocation = priorityQueue.poll();// first iteration of the loop removes first node, rest helps determine
            Rectangle rectangle = new Rectangle(currLocation.getX(), currLocation.getY(), TILE_SIZE, TILE_SIZE);//possible location movement option
            
            if (goalStateFound(rectangle,player,currLocation)) {
            	break;
        	}
            
            //checks every possible move position in the tile in the gameworld with this enemy
            checkPossibleMoveLocations(currLocation, playerLocation,possibleMoveLocationsHashMap,priorityQueue);
        }
    }
    
    /**
     * checks the next possible move state, it uses a hueristic to calculate the best possible move to add to the priority que
     * it uses hypothetical positions that the enemy can go to, then checks the hashmap for the nieghbors
     * and adds it to the que and the map. After that, it adds the best possible position of neighbors
     * to the priority que for the enemy to move to and expand that path.
     * @param currLocation it is the location with the next highest priority location
     * @param playerLocation is the location of the player
     * @param map is the hash map of all possible move locations
     * @param pq priority que of the best locations
     */
    public void checkPossibleMoveLocations(Location currLocation, Location playerLocation,  HashMap<Location, Double> map, PriorityQueue<Location> pq)
    {
        for (Location possiblePostion : possibleMoveLocations(gameWorld, currLocation, this))
        {

            if (possiblePostion != null)
            {

                Location movePoint; // if the location
                if (currLocation.getMovePoint() == null)
                {
                    movePoint = possiblePostion;
                }
                else movePoint = currLocation.getMovePoint();


                double dist = currLocation.getDist() + currLocation.distBetween(possiblePostion);
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
     * tests whether the goal state is found or not, which is determined if the hypothetical position intersects the
     * player.
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
     * gets possible locations for enemy, removes all the non possible locations that the enemy can move to
     * @param gameWorld the world that contains all the possible entities
     * @param enemyLocation the (current or hypothetical )location of the enemy
     * @param enemy the enemy that needs to be moved
     * @return an array of possible locations that the enemy can move to
     */
    public Location[] possibleMoveLocations(GameWorld gameWorld, Location enemyLocation, Enemy enemy) {
        int xl = enemyLocation.getX();
        int yl = enemyLocation.getY();
        if (!enemyLocation.inGrid(new Location(xl, yl))) {
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
}