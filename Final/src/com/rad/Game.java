package com.rad;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rad.gui.EndMenu;
import com.rad.gui.Hud;
import com.rad.gui.StartMenu;
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
	private StartMenu startMenu;
	/**
	 * is the game over state
	 */
	private EndMenu endMenu;
	/**
	 * is the window for the game
	 */
	private Window window;
	
	/**
	 * the assets to be used for the game
	 */
	private BufferedImage spritesheet;
	private Font fontPlayingScore;
	private Font fontEndScore;
	private Font fontEndRank;

	/**
	 * is the game state for the games
	 */
	public static enum GameState {
		START, PLAYING, SPECTATING, END;
	}

	/**
	 * is the starting game state/ testing game state
	 */
	private GameState gameState = GameState.START;
	
	/**
     * loads up the window, and allows is to be able to  run
     */
	public Game() {
		window = new Window(this);
		window.init();
	
		startMenu = new StartMenu(this);
		endMenu= new EndMenu(this);
		
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
		else if (gameState== GameState.START) {
			startMenu.tick();
		}
		else if(gameState==GameState.END)
		{
			endMenu.tick();
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
		else if (gameState == GameState.START) {
			startMenu.render(g);
		}
		else if (gameState == GameState.END) {
			endMenu.render(g);
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
		
		loadAssets();
	}

    /**
     * Stops the game loop, along with the thread
      */

	public synchronized void stop() {
		window.closeWindow();
		isRunning = false;
		System.exit(0);
	}
	
	public void restart() {
		gameState = GameState.PLAYING;
		this.gameWorld = new GameWorld(this);
		hud = new Hud(this);
	}
	
	
	/**
	 * Loads all assets required for the game.
	 */
	private void loadAssets() {
		try {
			spritesheet = ImageIO.read(new File(Const.PATHS.SPRITESHEET));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
//			FileInputStream in = new FileInputStream(Const.PATHS.PIXEL_FONT);
//			InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(Const.PATHS.PIXEL_FONT);
			fontPlayingScore = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.HUD.SCORE_FONT_SIZE);
			fontEndScore = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.END_MENU.SCORE_FONT_SIZE);
			fontEndScore = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.END_MENU.RANK_FONT_SIZE);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * returns the sprite sheet
	 * @return the sprite sheet
	 */
	public BufferedImage getSpritesheet() {
		return spritesheet;
	}
	
	/**
	 * returns the font
	 * @return the sprite sheet
	 */
	public Font getPlayingScoreFont() {
		return fontPlayingScore;
	}
	
	/**
	 * returns the font
	 * @return the sprite sheet
	 */
	public Font getEndRankFont() {
		return fontEndRank;
	}
	
	/**
	 * returns the font
	 * @return the sprite sheet
	 */
	public Font getEndScoreFont() {
		return fontEndScore;
	}
	
    /**
     * runs a version of the game
     * @param args arguments
     */
	public static void main(String[] args) {
		new Game();
	}
}