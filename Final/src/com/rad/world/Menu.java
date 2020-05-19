package com.rad.world;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rad.Const;
import com.rad.Game;
import com.rad.input.MouseInput;

public class Menu {
    private Game game;

    public Menu(Game g) {
		this.game = g;
	}
    
    public void tick(){
    	if (game.getMouseInput().isMenuStartButtonClicked()) {
    		game.setGameState(Game.GameState.PLAYING);
    	}
    	if (game.getMouseInput().isMenuExitButtonClicked()) {
    		game.stop();
    	}
    }
    
    public void render(Graphics g)
    {

    	BufferedImage imgBG = null;
    	BufferedImage imgStart = null;
    	BufferedImage imgExit = null;
		try {
			imgBG = ImageIO.read(new File(Const.PATHS.MENU_BACKGROUND));
			imgStart = ImageIO.read(new File(Const.PATHS.MENU_START_BTN_TXT));
			imgExit = ImageIO.read(new File (Const.PATHS.MENU_EXIT_BTN_TXT));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	g.drawImage(imgBG, 0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 0, 0, imgBG.getWidth(), imgBG.getHeight(), null);
    	g.drawImage(imgStart, Const.MENU.START_BTN.x, Const.MENU.START_BTN.y, Const.MENU.START_BTN.x + imgStart.getWidth(), Const.MENU.START_BTN.y + imgStart.getHeight(), 0, 0, imgStart.getWidth(), imgStart.getHeight(), null);
    	g.drawImage(imgExit, Const.MENU.EXIT_BTN.x, Const.MENU.EXIT_BTN.y, Const.MENU.EXIT_BTN.x + imgExit.getWidth(), Const.MENU.EXIT_BTN.y + imgExit.getHeight(), 0, 0, imgExit.getWidth(), imgExit.getHeight(), null);
    	
    }
}
