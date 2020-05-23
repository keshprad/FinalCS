package com.rad.effects;

/**
 * is an enums for classes
 * @author rad
 * @version 1.0
 */
public enum Effect {
	POINT_PLUS(10),
    POINT_PLUS_BIG(10),
    SPEED_UP(5 * 60),
    EAT_OTHER(5 * 60);
	
	private int duration;
	
	/**
	 * The constructor for an effect.
	 * @param duration the duration of the effect in ticks
	 */
	Effect(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return this.duration; 
	}
}