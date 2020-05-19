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
     * is the movepoint for the possible location, a calculated point in enemy
     */
    private Location movePoint = null;
    /**
     * is the distance from a point to another
     */
    private double dist;
    /**
     * is a priority used to calculate the priority of locations in AI
     */
    private double priority;
    /**
     * is the x coordinate
     */
    private int x;
    /**
     * is the y coordinate
     */
    private int y;

    /**
     * is the x,y constructor
     * @param x x coordinate
     * @param y y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * is teh x,y,dist constructor
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
     *  is the constuctor for X, Y , Dist, moivepoint
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
     * is the constructor for x,y,dist, movepoint 'n priority
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
     * @return the distance
     */
    public double distBetween(Location l) {
    	double dx = this.getX() - l.getX();
    	double dy = this.getY() - l.getY();
    	return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     *  the X coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *  the Y coordinate
     * @return  the Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * checks if a value is in the grid/world
     * @param xl the x coordinate
     * @param yl the y coordinate
     * @return whether a value is in the grid or not
     */
    public boolean inGrid(double xl, double yl) {
        return 0 <= xl && xl < Const.WORLD_WIDTH && 0 <= yl && yl < Const.WORLD_HEIGHT;
    }

    /**
     * whether a location is in the grid or not
     * @param loc location to be compared to
     * @return checks whether its in the grid or not
     */
    public boolean inGrid(Location loc) {
        return inGrid(loc.getX(), loc.getY());
    }

    /**
     * changes a location to a rectangle
     * @param loc a given location
     * @return a new location
     */
    public Rectangle toRect(Location loc) {
        return new Rectangle(loc.getX(), loc.getY(), Const.TILE_SIZE, Const.TILE_SIZE);
    }

    /**
     * comparator class used for AI
     * @param o object ot compare priority locations with
     * @return the double values btwn 2 objects of type location
     */
    @Override
    public int compareTo(Object o) {
        Location other = (Location) o;
        return Double.compare(this.getPriority(), other.getPriority());
    }

    /**
     * overriden equals method
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
     * getter for priority
     * @return the priority
     */
	public double getPriority() {
		return priority;
	}

    /**
     * getter for move point
     * @return the move point
     */
	public Location getMovePoint() {
		return movePoint;
	}

    /**
     * setter for movepoint
     * @param movePoint the move point location
     */
	public void setMovePoint(Location movePoint) {
		this.movePoint = movePoint;
	}

    /**
     * getter for location
     * @return the location dist in a double
     */
	public double getDist() {
		return dist;
	}
}