package com.rad.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rad.Const;
import com.rad.entity.Entity;
import com.rad.entity.Player;
import com.rad.world.GameWorld;

public class KeyInput implements KeyListener {

	private GameWorld gameWorld;

	public KeyInput(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key==KeyEvent.VK_ESCAPE) System.exit(1);
		
		for (Entity entity : gameWorld.getEntities()) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				if (!player.isAI()) {
					if (key == KeyEvent.VK_W)
						player.setVelY(-player.getSpeed());
					else if (key == KeyEvent.VK_S)
						player.setVelY(player.getSpeed());
					else if (key == KeyEvent.VK_D)
						player.setVelX(player.getSpeed());
					else if (key == KeyEvent.VK_A)
						player.setVelX(-player.getSpeed());
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (Entity entity : gameWorld.getEntities()) {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				if (!player.isAI()) {
					if (key == KeyEvent.VK_W)
						player.setVelY(0);
					else if (key == KeyEvent.VK_S)
						player.setVelY(0);
					else if (key == KeyEvent.VK_D)
						player.setVelX(0);
					else if (key == KeyEvent.VK_A)
						player.setVelX(0);
				}
			}
		}
	}

}
