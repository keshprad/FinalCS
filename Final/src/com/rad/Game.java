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
import java.util.LinkedList;

import com.rad.entity.Entity;
import com.rad.entity.Player;
import com.rad.gui.GameOver;
import com.rad.gui.Hud;
import com.rad.gui.Menu;
import com.rad.gui.Window;
import com.rad.input.KeyInput;
import com.rad.input.MouseInput;
import com.rad.world.GameWorld;

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
	/**
	 * is the mouse input to be put in
	 */
	private MouseInput mouse;
	/**
	 * is the key input to be put in
	 */
	private KeyInput keys;
	/**
	 * hud for the player
	 */
	private Hud hud;
	/**
	 * is the menu for the game
	 */
	private Menu menu;
	/**
	 * is the game ovr state
	 */
	private GameOver gameOver;
	/**
	 * is the window for the game
	 */
	private Window window;

	/**
	 * is the game state for the games
	 */
	public static enum GameState {
		MENU, PLAYING, SPECTATING, GAMEOVER;
	}

	/**
	 * is the starting game state/ testing game state
	 */
	private GameState gameState = GameState.MENU;
	
	/**
     * loads up the window, and allows is to be able to  run
     */
	public Game() {
		window = new Window(this);
		window.init();
		
		this.gameWorld = new GameWorld(this);
		
		hud = new Hud(this);
		menu = new Menu(this);
		gameOver= new GameOver(this);
		
		mouse = new MouseInput(this);
		this.addMouseListener(mouse);
		keys = new KeyInput(this);
		this.addKeyListener(keys);
		
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
		if (gameState == GameState.PLAYING) {
			gameWorld.tick();
			hud.tick();
		}
		else if (gameState == GameState.SPECTATING) {
			gameWorld.tick();
			hud.tick();
		}
		else if (gameState== GameState.MENU) {
			menu.tick();
		}
		else if(gameState==GameState.GAMEOVER)
		{
			gameOver.tick();
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
		
		if (gameState == GameState.PLAYING) {
			gameWorld.render(g);
			hud.render(g);
		}
		else if (gameState == GameState.MENU) {
			menu.render(g);
		}
		else if (gameState == GameState.GAMEOVER) {
			gameOver.render(g);
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

	public synchronized void stop() {
		window.closeWindow();
		isRunning = false;
		System.exit(0);
	}

	/**
	 * is the font to be loaded in
	 * @param filePath is the file path to load the font
	 * @param size is the size of the font
	 * @return the font
	 */
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
	 * getter for gameworld
	 * @return the game world
	 */
	public GameWorld getGameWorld() {
		return gameWorld;
	}

	/**
	 * is the getter for mouse input
	 * @return the mouse input
	 */
	public MouseInput getMouseInput() {
		return this.mouse;
	}

	/**
	 * is the mouse input
	 * @return is the keys for the input
	 */
	public KeyInput getKeyInput() {
		return this.keys;
	}

	/**
	 * the getter for the game state
	 * @return the game state
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * setter for the game state
	 * @param gameState returns the value for the game state
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
    /**
     * runs a version of the game
     * @param args arguments
     */
	public static void main(String[] args) {
		new Game();
	}
}