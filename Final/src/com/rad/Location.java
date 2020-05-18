package com.rad;

import com.rad.world.GameWorld;

import java.awt.*;
import java.util.Objects;

public class Location implements Comparable {
    private Location movePoint = null;
    private double dist;
    private double priority;
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Location(int x, int y, double dist) {
        this.x = x;
        this.y = y;
        //startingPoint=this;
        this.dist = dist;
    }

    public Location(int x, int y, double dist, Location movePoint) {
        this.x = x;
        this.y = y;
        //startingPoint=this;
        this.setMovePoint(movePoint);
        this.dist = dist;
    }

    public Location(int x, int y, double dist, Location movePoint, double priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
        //startingPoint=this;
        this.setMovePoint(movePoint);
        this.dist = dist;
    }
    
    public double distBetween(Location l) {
    	double dx = this.getX() - l.getX();
    	double dy = this.getY() - l.getY();
    	return Math.sqrt((dx * dx) + (dy * dy));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean inGrid(double xl, double yl) {
        return 0 <= xl && xl < Const.WORLD_WIDTH && 0 <= yl && yl < Const.WORLD_HEIGHT;
    }

    public boolean inGrid(Location loc) {
        return inGrid(loc.getX(), loc.getY());
    }

    public Rectangle toRect(Location loc) {
        return new Rectangle(loc.getX(), loc.getY(), Const.TILE_SIZE, Const.TILE_SIZE);
    }


    @Override
    public int compareTo(Object o) {
        Location other = (Location) o;
        return Double.compare(this.getPriority(), other.getPriority());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

	public double getPriority() {
		return priority;
	}

	public Location getMovePoint() {
		return movePoint;
	}

	public void setMovePoint(Location movePoint) {
		this.movePoint = movePoint;
	}

	public double getDist() {
		return dist;
	}
}