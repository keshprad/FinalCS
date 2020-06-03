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
 * This class handles the GUI in running the window
 * @author Sources: rishi.pradeep, daniel.lee, akshanth.srivatsa
 */
public class Game extends Canvas implements Runnable {

	/**
     *  The thread the game loop runs on.
     */
	private Thread thread;
    
	/**
     * Represents if the game is running; the game loop is looped through as long as this is true.
     */
	private boolean isRunning = false;

	/**
     * Represents the world that game play occurs.
     */
	private GameWorld gameWorld;
	
	/**
	 * Handles all user mouse interaction.
	 */
	private MouseInput mouse;
	
	/**
	 * Handles all user keyboard interaction.
	 */
	private KeyInput keys;
	
	/**
	 * The HUD (Headless User Display) displayed during game play.
	 */
	private Hud hud;
	
	/**
	 * The start menu.
	 */
	private StartMenu startMenu;
	
	/**
	 * The game over menu.
	 */
	private EndMenu endMenu;
	
	/**
	 * The window our game is drawn on, uses a JFrame.
	 */
	private Window window;
	
	/**
	 * The assets for the game: images and fonts.
	 */
	private BufferedImage spritesheet;
	private BufferedImage emph;
	private BufferedImage floor;
	private Font fontPlayingScore;
	private Font fontEndScore;
	private Font fontEndRank;

	/**
	 * The states the game can be in.
	 */
	public static enum GameState {
		START, PLAYING, SPECTATING, END;
	}

	/**
	 * The state the game is in.
	 */
	private GameState gameState = GameState.START;
	
	/**
	 * The constructor for Game. Creates a new window and starts the thread and game loop.
     */
	public Game() {
		window = new Window(this);
		window.init();
	
		start();
	}

	
	/**
     * Represents the game loop for the game; loops continuously while the game is being played.
     * The tick() method, which updates the game state, is called Const.FRAMES_PER_SECOND frames per second, while the
     * render() method, which draws the screen, is called as often as possible.
     */
	@Override
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
	 * Updates the game state. Called Const.FRAMES_PER_SECOND frames per second.
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
	 * Draws the game to the screen. Called as often as possible.
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
	 * Starts the game. First loads all assets, then initializes fields, and finally starts 
	 * the new thread to run the game on.
	 */
	public synchronized void start() {
		if (isRunning) {
			return;
		}

		loadAssets();
		
		init();

		thread = new Thread(this);
		thread.start();
		isRunning = true;		
	}
	
	/**
     * Stops the game.
     */
	public synchronized void stop() {
		window.closeWindow();
		isRunning = false;
		System.exit(0);
	}
	
	/**
	 * Initializes the fields. Done once when the game is launched. Adds the user input handlers to the frame.
	 */
	private void init() {
		gameWorld = new GameWorld(this);
		hud = new Hud(this);
		
		startMenu = new StartMenu(this);
		endMenu= new EndMenu(this);
		
		mouse = new MouseInput(this);
		this.addMouseListener(mouse);
		keys = new KeyInput(this);
		this.addKeyListener(keys);	
	}

    /**
     * Starts the actual game play. Called when the start button is clicked in the start menu.
     */
	public void startPlaying() {
		gameState = GameState.PLAYING;
		this.gameWorld.initialize();
	}
	
	/**
	 * Loads all the assets required for the game.
	 */
	private void loadAssets() {
		try {
			spritesheet = ImageIO.read(new File(Const.PATHS.SPRITESHEET));
			emph = ImageIO.read(new File(Const.PATHS.EMPH));
			floor = ImageIO.read(new File(Const.PATHS.FLOOR));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fontPlayingScore = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.HUD.SCORE_FONT_SIZE);
			fontEndScore = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.END_MENU.SCORE_FONT_SIZE);
			fontEndRank = Font.createFont(Font.TRUETYPE_FONT, new File(Const.PATHS.PIXEL_FONT)).deriveFont(Const.END_MENU.RANK_FONT_SIZE);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the game world.
	 * @return the game world
	 */
	public GameWorld getGameWorld() {
		return gameWorld;
	}

	/**
	 * Returns the mouse user input handler.
	 * @return the mouse input
	 */
	public MouseInput getMouseInput() {
		return this.mouse;
	}

	/**
	 * Returns the keyboard user input handler.
	 * @return the key input
	 */
	public KeyInput getKeyInput() {
		return this.keys;
	}

	/**
	 * Returns the state of the game.
	 * @return the game state
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Sets the state of the game.
	 * @param gameState returns the value for the game state
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	
	/**
	 * Returns the sprite sheet
	 * @return the sprite sheet
	 */
	public BufferedImage getSpritesheet() {
		return spritesheet;
	}
	
	/**
	 * Returns the emph image
	 * @return the emph image
	 */
	public BufferedImage getEmph() {
		return emph;
	}
	
	/**
	 * Returns the floor image
	 * @return the floor image
	 */
	public BufferedImage getFloor() {
		return floor;
	}
	
	/**
	 * Returns the font for the score to be used on the HUD.
	 * @return the font for the score to be used on the HUD.
	 */
	public Font getPlayingScoreFont() {
		return fontPlayingScore;
	}
	
	/**
	 * Returns the font for the rank to be used on the end menu.
	 * @return the font for the rank to be used on the end menu.
	 */
	public Font getEndRankFont() {
		return fontEndRank;
	}
	
	/**
	 * Returns the font for the score to be used on the end menu.
	 * @return the font for the score to be used on the end menu.
	 */
	public Font getEndScoreFont() {
		return fontEndScore;
	}
	
    /**
     * Launches the game.
     * @param args not used
     */
	public static void main(String[] args) {
		new Game();
	}
}