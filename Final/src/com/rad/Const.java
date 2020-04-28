package com.rad;

public class Const {
    // TODO: Change title and frame size
    //test
    public static final String TITLE = "Game";

    public static final int ENEMEY_AI_SLOW_DOWN_RATE = 5;
    public static final double ENEMEY_AI_PROB_OF_MOVE_PER_TICK = 0.5;


    public static final int TILE_SIZE = 60;
    public static final int WORLD_WIDTH_IN_TILES = 12;
    public static final int WORLD_HEIGHT_IN_TILES = 12;

    public static final double FRAMES_PER_SECOND = 60.0;

    public static final int MARGIN_X = 0;
    public static final int MARGIN_Y = 22;

    public static final int FRAME_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES + MARGIN_X;
    public static final int FRAME_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES + MARGIN_Y;

    public static final int WORLD_WIDTH = TILE_SIZE * WORLD_WIDTH_IN_TILES;
    public static final int WORLD_HEIGHT = TILE_SIZE * WORLD_HEIGHT_IN_TILES;

    public static final class ID {
        public static final int EMPTY = 0;

        /* Blocks 1-9*/
        public static final int WALL = 1;
        public static final int BUSH = 2;

        /* Enemies 10-19*/
        public static final int ZOMBIE = 10;
        public static final int IMP_ZOMBIE = 11;

        /* Players 20-29*/
        public static final int BRAD = 20;
        public static final int FULK = 21;

        /* Items 30-39*/
        public static final int CHIP = 30;
        public static final int COOKIE = 31;
        public static final int FLASH = 32;
        public static final int CAKE = 33;

    }

    public class PATHS {

        public static final String MAP0 = "res/map0";
        public static final String MAP1 = "res/map1";
        public static final String MAP2 = "res/map2";
        public static final String MAP3 = "res/map3";
        public static final String MAP4 = "res/map4";
        public static final String MAP5 = "res/map5";

    }
}