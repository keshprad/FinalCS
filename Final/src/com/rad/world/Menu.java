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
    	BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Const.PATHS.MENU_BACKGROUND));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	g.drawImage(img, 0, 0, Const.FRAME_WIDTH, Const.FRAME_HEIGHT, 0, 0, img.getWidth(), img.getHeight(), null);

    }
}
