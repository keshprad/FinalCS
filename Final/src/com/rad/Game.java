package com.rad;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.rad.world.GameWorld;

public class Game extends Canvas implements Runnable {

	private Thread thread;
	private boolean isRunning = false;

	private GameWorld gameWorld;

	public Game() {
		Window window = new Window(this);
		window.init();
		
		this.gameWorld = new GameWorld();

		start();
	}

	// Game Loop
	public void run() {
		final double framesPerNanosecond = Constants.FRAMES_PER_SECOND / 1000000000;
		long lastTime = System.nanoTime();
		double delta = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) * framesPerNanosecond;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
		}

		stop();
	}

	/*
	 * Updates the game. Called 60 times per second.
	 */
	private void tick() {
		gameWorld.tick();
	}

	/*
	 * Renders the game. Called as often as possible.
	 */
	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		// RENDERING GOES HERE
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		
		gameWorld.render(g);

		bufferStrategy.show();
		g.dispose();
	}

	private synchronized void start() {
		if (isRunning) {
			return;
		}

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	private synchronized void stop() {
		if (!isRunning) {
			return;
		}

		try {
			thread.join();
			isRunning = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}