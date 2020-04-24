package com.rad.entity;

import java.awt.Graphics;

public class Empty extends Entity {

	public Empty(int x, int y) {
		super(0, x, y);
	}

	@Override
	public void tick() {
		// do nothing		
	}

	@Override
	public void render(Graphics g) {
		// do nothing
	}

}
