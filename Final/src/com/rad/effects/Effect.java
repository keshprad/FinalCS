package com.rad.effects;

import com.rad.Const;

/**
 * Represents an effect that an entity can get when contacting an item
 * 
 * @author RAD
 * @version 1.0
 */
public enum Effect {
	POINT_PLUS(10),
    POINT_PLUS_BIG(10),
    SPEED_UP((int)(5 * Const.FRAMES_PER_SECOND)),
    EAT_OTHER((int)(5 * Const.FRAMES_PER_SECOND));
	
	/**
	 * represents the duration in ticks that the effect is applied for
	 */
	private int duration;
	
	/**
	 * The constructor for an effect.
	 * @param duration the duration of the effect in ticks
	 */
	Effect(int duration) {
		this.duration = duration;
	}
	
	/**
	 * returns the current duration of the effect
	 * @return the current duration of the effect
	 */
	public int getDuration() {
		return this.duration; 
	}
}
