package com.rad.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.rad.Const;
import com.rad.effects.Effect;

public class Item extends Entity {

	private Effect effect = null;
	
	public Item(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	public void init() {
		switch (id) {
			case Const.ID.CHIP:
				
		}
	}
	
	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x * Const.TILE_SIZE, y * Const.TILE_SIZE, Const.TILE_SIZE, Const.TILE_SIZE);
	}

	public Effect getEffect() {
		return effect;
	}

}
