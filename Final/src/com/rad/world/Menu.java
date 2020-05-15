package com.rad.world;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rad.Const;
import com.rad.Game;
import com.rad.input.MouseInput;

public class Menu extends MouseAdapter {
    private Game game;
    public int xTitle=250;
    public int yTitle=150;
    public int heightOfBox=50;
    public int widthOfBox=250;

    public Menu(){}
    public Menu(Game g) {
		this.game = g;
	}
	
	public void mousePressed(MouseEvent e){
        int mouseXLocation=e.getX();
        int mouseYLocation=e.getY();
        if(mouseIsOverLocation(mouseXLocation, mouseYLocation, xTitle, yTitle, widthOfBox, heightOfBox))
        {
            game.setGameState(Game.GameState.PLAYING);
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
    	if (this.game.getGameState() == Game.GameState.PLAYING)
    	{   
    		//run
           // mousePressed(new MouseInput());
    	}

    }
    
    public void render(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.fillRect(xTitle, yTitle, widthOfBox, heightOfBox);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        
        g2d.setFont(game.loadFont(Const.PATHS.RADLEY_ITALIC, 200));
        g2d.drawString(Const.TITLE, xTitle , yTitle );


    }
}
