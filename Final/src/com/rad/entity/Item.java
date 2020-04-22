package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Constants;

public class Item extends Entity {

	public Item(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
	}

}
