package com.rad;

import com.rad.world.GameWorld;

import java.awt.*;
import java.util.Objects;

/**
 * @author RAD
 * @version is the location general method
 */
public class Location implements Comparable {
    /**
     * is the "move point," a point where the enemy can move to, for the possible location,
     * a calculated point used in enemy for the A* program
     */
    private Location movePoint = null;
    /**
     * is the distance from a location to another location.
     * Used in A* program in finding distances of hypothetical move points for the enemy AI
     */
    private double dist;
    /**
     * is a priority used to calculate the priority of locations in AI
     * it is a calculated hueristic derived from distances of the enemy AI
     */
    private double priority;
    /**
     * is the x coordinate of the location
     */
    private int x;
    /**
     * is the y coordinate of the location
     */
    private int y;

    /**
     * is the (x, y) coordinate constructor for the location
     * @param x x coordinate
     * @param y y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * is teh (x, y), and dist constructor for the location
     * @param x X coordinate
     * @param y Y coordinate
     * @param dist distance from a point
     */
    public Location(int x, int y, double dist) {
        this.x = x;
        this.y = y;
        //startingPoint=this;
        this.dist = dist;
    }

    /**
     *  is the constuctor for X coordinate , Y coordinate, Dist, movepoint for the location
     * @param x X coordinate
     * @param y Y coordinate
     * @param dist distance from a point
     * @param movePoint  move point to move to a point
     */
    public Location(int x, int y, double dist, Location movePoint) {
        this.x = x;
        this.y = y;
        //startingPoint=this;
        this.setMovePoint(movePoint);
        this.dist = dist;
    }

    /**
     * is the constructor for x coordinate, y coordinate, dist, movepoint and priority for the location
     * @param x X point/coordinate
     * @param y Y coordinate
     * @param dist distance from a point
     * @param movePoint is the point to move to
     * @param priority is the priority to move to
     */
    public Location(int x, int y, double dist, Location movePoint, double priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
        //startingPoint=this;
        this.setMovePoint(movePoint);
        this.dist = dist;
    }

    /**
     * distance between 2 locations
     * @param l another location
     * @return the distance between two locations
     */
    public double distBetween(Location l) {
    	double dx = this.getX() - l.getX();
    	double dy = this.getY() - l.getY();
    	return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     *  returns the X coordinate of the location
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *  returns the Y coordinate of the location
     * @return  the Y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * whether a location is in the grid or not
     * @param loc location to be compared to
     * @return checks whether its in the grid or not
     */
    public boolean inGrid(Location loc) {
        return 0 <=loc.x && loc.x < Const.WORLD_WIDTH && 0 <= loc.y && loc.y < Const.WORLD_HEIGHT;
    }
    
    /**
     * changes a location to a rectangle (square) with height and width as the tile size
     * @param loc a given location
     * @return a new rectangle in the location
     */
    public Rectangle toRect(Location loc) {
        return new Rectangle(loc.getX(), loc.getY(), Const.TILE_SIZE, Const.TILE_SIZE);
    }
    
    /**
     * comparator class used for AI used to compare priorities
     * @param o object ot compare priority locations with
     * @return the double values between 2 objects of type location
     */
    @Override
    public int compareTo(Object o) {
        Location other = (Location) o;
        return Double.compare(this.getPriority(), other.getPriority());
    }

    /**
     * an overriden equals method
     * @param o object to be compared to
     * @return compares location in classes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    /**
     * returns hashcode of a certain object
     * @return returns the hascode of a certain object
     */
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * a getter for priority
     * returns the priority(determined by a hueristic)
     * @return the priority
     */
	public double getPriority() {
		return priority;
	}

    /**
     * getter for move point
     * returns movepoint(a hypothetical location point to move to)
     * @return the move point location
     */
	public Location getMovePoint() {
		return movePoint;
	}

    /**
     * setter for movepoint
     * sets the hypothetical location to move to
     * @param movePoint the move point location
     */
	public void setMovePoint(Location movePoint) {
		this.movePoint = movePoint;
	}

    /**
     * getter for distance
     * returns the distance used for location
     * @return the (euclidian) location dist in a double
     */
	public double getDist() {
		return dist;
	}
}