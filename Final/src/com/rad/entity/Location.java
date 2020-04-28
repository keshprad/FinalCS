package com.rad.entity;

import com.rad.Const;
import com.rad.world.GameWorld;

import java.awt.*;
import java.util.Objects;

public class Location implements Comparable {
    public Location movePoint = null;
    double dist;
    double priority;
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
        this.movePoint = movePoint;
        this.dist = dist;
    }

    public Location(int x, int y, double dist, Location movePoint, double priority) {
        this.x = x;
        this.y = y;
        this.priority = priority;
        //startingPoint=this;
        this.movePoint = movePoint;
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

    public Location[] possibleMoveLocations(GameWorld gameWorld, Location enemyLocation, Enemy enemy) {
        int xl = enemyLocation.getX();
        int yl = enemyLocation.getY();
        if (!inGrid(xl, yl)) {
            return new Location[]{};
        }

        Location[] possibleLocations = new Location[4];
        possibleLocations[0] = new Location(xl + Const.TILE_SIZE/Const.ENEMEY_AI_SLOW_DOWN_RATE, yl, 0, movePoint);
        possibleLocations[1] = new Location(xl - Const.TILE_SIZE/Const.ENEMEY_AI_SLOW_DOWN_RATE, yl, 0, movePoint);
        possibleLocations[2] = new Location(xl, yl + Const.TILE_SIZE/Const.ENEMEY_AI_SLOW_DOWN_RATE, 0, movePoint);
        possibleLocations[3] = new Location(xl, yl - Const.TILE_SIZE/Const.ENEMEY_AI_SLOW_DOWN_RATE, 0, movePoint);

        for (int i = 0; i < possibleLocations.length; i++) {
            if (!this.inGrid(possibleLocations[i])) {
                possibleLocations[i] = null;
            }
        }
        
		for (Block b : gameWorld.getBlocks()) {
			for (int i = 0; i < possibleLocations.length; i++) {
				if (possibleLocations[i] != null && b.getBounds().intersects(this.toRect(possibleLocations[i]))) {
					possibleLocations[i] = null;
				}
			}
		}
		return possibleLocations;
	}


    @Override
    public int compareTo(Object o) {
        Location other = (Location) o;
        return Double.compare(this.priority, other.priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}