package com.rad.effects;

/**
 * is an enums for classes
 * @author rad
 * @version 1.0
 */
public enum Effect {
	POINT_PLUS(0),
    POINT_PLUS_BIG(0),
    SPEED_UP(10),
    EAT_OTHER(10);
	
	private int duration;
	
	/**
	 * The constructor for an effect.
	 * @param duration the duration of the effect in seconds
	 */
	Effect(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return this.duration; 
	}
}
