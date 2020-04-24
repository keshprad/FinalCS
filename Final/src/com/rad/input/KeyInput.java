package com.rad.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rad.Const;
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

		for (int y = 0; y < Const.WORLD_HEIGHT_IN_TILES; y++) {
			for (int x = 0; x < Const.WORLD_WIDTH_IN_TILES; x++) {
				if (gameWorld.getEntities()[y][x] instanceof Player) {
					System.out.println("IS PLAYER");
					Player player = (Player) gameWorld.getEntities()[y][x];
					if (!player.isAI()) {
						if (key == KeyEvent.VK_W)
							player.setPosition(player.getX(), player.getY() - 1);
						else if (key == KeyEvent.VK_S)
							player.setPosition(player.getX(), player.getY() + 1);
						else if (key == KeyEvent.VK_D)
							player.setPosition(player.getX() + 1, player.getY());
						else if (key == KeyEvent.VK_A)
							player.setPosition(player.getX() - 1, player.getY());
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

}
