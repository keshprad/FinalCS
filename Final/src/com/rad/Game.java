package com.rad;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.rad.gui.Hud;
import com.rad.gui.Window;
import com.rad.input.KeyInput;
import com.rad.input.MouseInput;
import com.rad.world.GameWorld;
import com.rad.world.Menu;

/**
 * This class handles the gui in running the window
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Game extends Canvas implements Runnable {
    /**
     *  the threads that this runs when the class is run
     */
	private Thread thread;
    /**
     * checks if it is running
     */
	private boolean isRunning = false;
    /**
     * the class that contains the game world
     */
	private GameWorld gameWorld;
	
	private Hud hud;
	
	private MouseInput mouseInput=new MouseInput();
	
	private Menu menu;
	
	public static enum StateOfGame {
		MENU, PLAYINGGAME, ENDGAME;
	}
	
	private StateOfGame gameState=StateOfGame.MENU;
	/**
     * loads up the window, and allows is to be able to  run
     */
	public Game() {
		Window window = new Window(this);
		window.init();
		
		this.gameWorld = new GameWorld();
		this.addKeyListener(gameWorld.getKeyInput());
		hud = new Hud(gameWorld);
		menu = new Menu(this);

		start();
	}

	// Game Loop
	/**
     * runs the java method, and renders it every some times per second
     */
	public void run() {
		this.requestFocus();
		final double framesPerNanosecond = Const.FRAMES_PER_SECOND / 1000000000;
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

	/**
	 * Updates the game. Called 60 times per second.
	 */
	private void tick() {
		if (gameState == StateOfGame.PLAYINGGAME) {
			gameWorld.tick();
			hud.tick();
		}
		else {
			menu.tick();
		}
	}

	/**
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
		g.fillRect(0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT);
		
		if (gameState == StateOfGame.PLAYINGGAME) {
			gameWorld.render(g);
			hud.render(g);
		}
		else {
			menu.render(g);
		}
		
		bufferStrategy.show();
		g.dispose();
	}

    /**
     *     Starts the game loop, along with the thread
      */

	private synchronized void start() {
		if (isRunning) {
			return;
		}

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

    /**
     * Stops the game loop, along with the thread
      */

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

	public Font loadFont(String filePath, int size) {
		Font f = null;
		try {
			InputStream in = new FileInputStream(filePath);
			f = Font.createFont(Font.TRUETYPE_FONT, in);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.deriveFont(size);
	}
	
    /**
     * runs a version of the game
     * @param args arguments
     */
	public static void main(String[] args) {
		new Game();
	}

    public StateOfGame getGameState() {
		return gameState;
	}

	public void setGameState(StateOfGame gameState) {
		this.gameState = gameState;
	}
}