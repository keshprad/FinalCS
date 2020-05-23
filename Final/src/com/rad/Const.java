package com.rad;

import java.awt.Rectangle;

public class Const {
    // TODO: Change title and frame size
    //test
    public static final String TITLE = "RAD Game";

    public static final int TILE_SIZE = 64;
    public static final int WORLD_WIDTH_IN_TILES = 12;
    public static final int WORLD_HEIGHT_IN_TILES = 12;

    public static final double FRAMES_PER_SECOND = 30.0;

    public static final int MARGIN_X = 0;
    public static final int MARGIN_Y = 0;

    public static final int FRAME_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES + MARGIN_X;
    public static final int FRAME_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES + MARGIN_Y;
    
    public static final int HUD_WIDTH = 0;
    public static final int HUD_HEIGHT = 0;
    
    public static final int WORLD_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES;
    public static final int WORLD_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES;

    public static final class HUD {
        public static final float SCORE_FONT_SIZE = 48f;
    }
    
    public static final class START_MENU {
	    //Menu Start
    	public static final Rectangle START_BTN = new Rectangle(270, 470, 240, 100);
	    
	    //Menu End
	    public static final Rectangle EXIT_BTN = new Rectangle(300, 590, 180, 100);
    }
    
    public static final class END_MENU {
        public static final float SCORE_FONT_SIZE = 128f;
        public static final float RANK_FONT_SIZE = 128f;

      //Menu Start
    	public static final Rectangle RETURN_BTN = new Rectangle(400, 630, 340, 95);
    }
    
    public static final class ID {
        public static final int EMPTY = 0;

        /* Blocks 1-9*/
        public static final int WALL = 1;
        public static final int BUSH = 2;

        /* Enemies 10-19*/
        public static final int GHOUL = 10;
        public static final int GOLEM = 11;
        public static final int GNAT = 12;
        public static final int GUPPY = 13;
        public static final int BOT = 14;
        public static final int MECH = 15;

        /* Players 20-29*/
        public static final int BRAD = 20;
        public static final int FULK = 21;

        /* Items 30-39*/
        public static final int CHIP = 30;
        public static final int COOKIE = 31;
        public static final int FLASH = 32;
        public static final int CAKE = 33;

    }

    public static class PATHS {
        
        public static final String[] MAPS = {
        		"res/Maps/map0",
        		"res/Maps/map1",
        		"res/Maps/map2",
        		"res/Maps/map3",
        		"res/Maps/map4",
        		"res/Maps/map5"		
        };
        
        public static final String RADLEY_ITALIC = "res/Fonts/Radley-Italic.ttf";
        public static final String RADLEY_REGULAR = "res/Fonts/Radley-Regular.ttf";
        public static final String PIXEL_FONT = "res/Fonts/FFFFORWA.TTF";
        
        public static final String SPRITESHEET = "res/Sprites/spritesheet.png";
        
        
        public static final String START_MENU_BACKGROUND = "res/GUI/StartMenu/start_menu_background.png";
        public static final String START_MENU_START_BTN = "res/GUI/StartMenu/start_menu_start_button.png";
        public static final String START_MENU_EXIT_BTN = "res/GUI/StartMenu/start_menu_exit_button.png";
        
        public static final String END_MENU_BACKGROUND = "res/GUI/EndMenu/end_menu_background.png";
        public static final String END_MENU_RETURN_BTN = "res/GUI/EndMenu/end_menu_return.png";        
    }
    
    public static class EFFECTS {
    	public static final int POINT_PLUS = 600;
    	public static final int POINT_PLUS_BIG = 2500;
    }
    
    public static class PLAYER {
    	public static final int SPEED = 8;
    }
}