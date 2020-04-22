package com.rad;

public class Constants {

	// TODO: Change title and frame size
	//test
	public static final String TITLE = "Game";

	public static int TILE_SIZE = 60;
	public static int WORLD_WIDTH_IN_TILES = 10;
	public static int WORLD_HEIGHT_IN_TILES = 10;

	public static double FRAMES_PER_SECOND = 60.0;

	public static int FRAME_WIDTH = 600;
	public static int FRAME_HEIGHT = 600;

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

}
