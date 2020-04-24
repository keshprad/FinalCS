package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;

public class Enemy extends Entity {

	public Enemy(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x * Const.TILE_SIZE, y * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE);
	}

}