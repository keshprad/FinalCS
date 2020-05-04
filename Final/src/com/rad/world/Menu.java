package com.rad.world;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rad.Const;
import com.rad.Game;

public class Menu extends MouseAdapter {
    private Game game;
    public int xTitle=0;
    public int yTitle=0;
    public int heightOfBox=20;
    public int widthOfBox=100;
    
    public Menu(Game g) {
		this.game = g;
	}
	
	public void mousePressed(MouseEvent e){
        int mouseXLocation=e.getX();
        int mouseYLocation=e.getY();
        if(mouseIsOverLocation(mouseXLocation, mouseYLocation, xTitle, yTitle, widthOfBox, heightOfBox))
        {
            game.setGameState(Game.StateOfGame.PLAYINGGAME);
        }
    }
    
    public boolean mouseIsOverLocation(int mouseX, int mouseY, int x, int y, int width, int height)
    {
        return ((mouseX>x&&mouseX<x+width)&&(mouseY>y&&mouseY<y+height));
    }
    public void mouseReleased(MouseEvent e)
    {

    }
    public void tick(){
    	if (this.game.getGameState() == Game.StateOfGame.MENU) {
    		//run
    	}
    	else if(this.game.getGameState() == Game.StateOfGame.ENDGAME) {
    		//stop
    	}
    }
    
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(xTitle, yTitle, widthOfBox, heightOfBox);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        
        g2d.setFont(game.loadFont(Const.PATHS.RADLEY_ITALIC, 100));
        g2d.drawString(Const.TITLE, xTitle + 400, yTitle + 400);
      

    }
}
