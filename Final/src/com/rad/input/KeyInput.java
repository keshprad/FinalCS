package com.rad.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	public KeyInput() {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_A) {
			left = true;
		}
		else if (key == KeyEvent.VK_D) {
			right = true;
		}
		else if (key == KeyEvent.VK_W) {
			up = true;
		}
		else if (key == KeyEvent.VK_S) {
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_A) {
			left = false;
		}
		else if (key == KeyEvent.VK_D) {
			right = false;
		}
		else if (key == KeyEvent.VK_W) {
			up = false;
		}
		else if (key == KeyEvent.VK_S) {
			down = false;
		}
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	
	
}
