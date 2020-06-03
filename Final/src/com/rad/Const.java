package com.rad;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

/**
 * This is a class containing constants for all classes
 * @version 1.0
 * @author Rishi Pradeep, Akshanth Srivatsa, Daniel Lee
 */
public class Const {
	
	/**
	 * The title of our game
	 */
    public static final String TITLE = "RAD Game";

    /**
     * The size(in pixels) of the tile system
     */
    public static final int TILE_SIZE = 64;
    
    /**
     * The amount of tiles in the width of the world
     */
    public static final int WORLD_WIDTH_IN_TILES = 12;
    
    /**
     * The amount of tiles in the height of the world
     */
    public static final int WORLD_HEIGHT_IN_TILES = 12;

    /**
     * The amount of frames, the game runs per second
     */
    public static final double FRAMES_PER_SECOND = 15.0;

    /**
     * Margins in the x-direction between game screen and JFrame
     */
    public static final int MARGIN_X = 0;
    
    /**
     * Margins in the y-direction between game screen and JFrame
     */
    public static final int MARGIN_Y = 0;

    /**
     * Width of the frame in pixels
     */
    public static final int FRAME_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES + MARGIN_X;
    
    /**
     * Height of the frame in pixels
     */
    public static final int FRAME_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES + MARGIN_Y;
    
    /**
     * Width of the world in pixels
     */
    public static final int WORLD_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES;
    
    /**
     * Height of the world in pixels
     */
    public static final int WORLD_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES;
    
    /**
     * Number of user-controlled players
     */
    public static final int NUM_PLAYERS = 1;

    /**
     * All constants related to our HUD
     */
    public static final class HUD {
        
    	/**
         * Size of the font for the user's score
         */
    	public static final float SCORE_FONT_SIZE = 48f;
        
    	/**
    	 * Holds the location where the current effect should be printed
    	 */
        public static final Point EFFECT_LOC = new Point(752, 752);
        
        /**
         * Holds the location where the score should be printed
         */
        public static final Point SCORE_LOC = new Point(16, 752);
    }
    
    /**
     * All constants related to our Start Menu
     */
    public static final class START_MENU {
	    
    	/**
    	 * Holds a rectangle for the place where our start button would go
    	 */
    	public static final Rectangle START_BTN = new Rectangle(270, 470, 240, 100);
	    
	    /**
	     * Holds a rectangle for the place where our exit button would go
	     */
	    public static final Rectangle EXIT_BTN = new Rectangle(300, 590, 180, 100);
    }
    
    /**
     * All constants related to our End Menu
     */
    public static final class END_MENU {
       
    	/**
    	 * Holds the size of the font for displaying the score on the end menu
    	 */
    	public static final float SCORE_FONT_SIZE = 128f;
    	
    	/**
    	 * Holds the size of the font for displaying the rank on the end menu
    	 */
        public static final float RANK_FONT_SIZE = 80f;

        /**
         * Holds a Rectangle for the place where the return to home button is located
         */
    	public static final Rectangle RETURN_BTN = new Rectangle(400, 630, 340, 95);
    	
    	/**
    	 * Holds the y-coordinate of where the score is printed on the End Menu
    	 */
    	public static final int SCORE_Y = 408;
    	
    	/**
    	 * Holds the location where the rank is drawn on the End Menu
    	 */
    	public static final Point RANK_LOC = new Point(486, 556);
    }
    
    /**
     * ID constants for each entity in our game
     */
    public static final class ID {
    	/* Blocks 1-9*/
    	/* Players 20-29*/
    	/* Items 30-39*/
        
    	/* Blocks 1-9*/
    	/**
    	 * Holds an integer as the ID for an Empty spot
    	 */
    	public static final int EMPTY = 0;

    	/**
    	 * Holds an integer as the ID for a Wall
    	 */
        public static final int WALL = 1;
        
        /**
         * Holds an integer as the ID for a Bush
         */
        public static final int BUSH = 2;

        /**
         * Holds an integer as the ID for a Ghoul
         */
        public static final int GHOUL = 10;
        
        /**
         * Holds an integer as the ID for a Golem
         */
        public static final int GOLEM = 11;
		        
		/**
		 * Holds an integer as the ID for a Gnat
		 */
        public static final int GNAT = 12;
        
        /**
         * Holds an integer as the ID for a Guppy
         */
        public static final int GUPPY = 13;
        
        /**
         * Holds an integer as the ID for a Bot
         */
        public static final int BOT = 14;
        
        /**
         * Holds an integer as the ID for a Mech
         */
        public static final int MECH = 15;
        
        /**
         * Holds a list of integer IDs for mobs
         */
        public static final int[] GMOBS = {GHOUL, GOLEM, GNAT, GUPPY};
        
        /**
         * Holds a list of integer IDs for Bosses
         */
        public static final int[] BOSSES = {BOT, MECH};

        /* Players 20-29*/
        /**
         * Holds an integer as the ID for a Player Brad
         */
        public static final int BRAD = 20;
        
        /**
         * Holds an integer as the ID for a Player Fulk
         */
        public static final int FULK = 21;

        /* Items 30-39*/
        /**
         * Holds an integer as the ID for a Chip item
         */
        public static final int CHIP = 30;
        
        /**
         * Holds an integer as the ID for a Cookie item
         */
        public static final int COOKIE = 31;
        
        /**
         * Holds an integer as the ID for a Flash item
         */
        public static final int FLASH = 32;
        
        /**
         * Holds an integer as the ID for a Cake item
         */
        public static final int CAKE = 33;
    }

    public static class PATHS {
     
    	/**
    	 * A list of Paths(represented as strings) to all maps
    	 */
        public static final String[] MAPS = {
        		"res/Maps/map0",
        		"res/Maps/map1",
        		"res/Maps/map2",	
        };
        
        /**
         * The Path(represented as a string) to the FFFFORWA font
         */
        public static final String PIXEL_FONT = "res/Fonts/FFFFORWA.TTF";
        
        
        /**
         * The Path(represented as a string) to the emphasis image
         */
        public static final String EMPH = "res/GUI/Playing/emph.png";

        /**
         * The Path(represented as a string) to the spritesheet
         */
        public static final String SPRITESHEET = "res/GUI/Playing/spritesheet.png";
        
        /**
         * The Path(represented as a string) to the floor image
         */
        public static final String FLOOR = "res/GUI/Playing/floor.png";
        
        /**
         * The Path(represented as a string) to the background of the start menu
         */
        public static final String START_MENU_BACKGROUND = "res/GUI/StartMenu/start_menu_background.png";
        
        /**
         * The Path(represented as a string) to the start button image
         */
        public static final String START_MENU_START_BTN = "res/GUI/StartMenu/start_menu_start_button.png";
        
        /**
         * The Path(represented as a string) to the exit button image
         */
        public static final String START_MENU_EXIT_BTN = "res/GUI/StartMenu/start_menu_exit_button.png";
        
        /**
         * The Path(represented as a string) to the background of the end menu
         */
        public static final String END_MENU_BACKGROUND = "res/GUI/EndMenu/end_menu_background.png";
        
        /**
         * The Path(represented as a string) to the return button image
         */
        public static final String END_MENU_RETURN_BTN = "res/GUI/EndMenu/end_menu_return.png";        
    }
    
    /**
     * All constants related to effects class
     */
    public static class EFFECTS {
    	
    	/**
    	 * Amount of points added to a player's score when an item with the effect "POINT_PLUS" is picked up
    	 */
    	public static final int POINT_PLUS = 150;
    	
    	/**
    	 * Amount of points added to a player's score when an item with the effect "POINT_PLUS_BIG" is picked up
    	 */
    	public static final int POINT_PLUS_BIG = 300;
    	
    	/**
    	 * Factor by which a player's speed increases when an item with the effect "SPEED_UP" is picked up
    	 */
    	public static final int SPEED_UP = 2;
    }
    
    /**
     * All constants related to player class
     */
    public static class PLAYER {
    	
    	/**
    	 * The speed of the player when no effects are applied
    	 */
    	public static final int SPEED = 16;
    	
    	/**
    	 * The speed of the player when the effect "SPEED_UP" is applied with a factor of Effects.SPEED_UP
    	 */
    	public static final int BOOSTED_SPEED = SPEED * EFFECTS.SPEED_UP;
    }
}