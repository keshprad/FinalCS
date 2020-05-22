package com.rad.gui;

import com.rad.Const;
import com.rad.Game;
import com.rad.world.GameWorld;

import java.awt.*;

/**
 * @author RAD
 * @version 1.0
 * is the game over state
 */
public class GameOver {
	
    /**
     * is the gameworld of this game, holding all the entities
     */
    private GameWorld gameWorld;
    
    /**
     * is the game we are currently in
     */
    private Game game;

    /**
     * is the constructor of the game over state
     * @param g the game user is playing
     */
    public GameOver(Game g) {
        game = g;
        gameWorld = g.getGameWorld();
    }

    /**
     * is the tick method for the game over state
     */
    public void tick() { 
    	//run
        //now that I realize it dead players can be private, but idk where that variable went
        // game.getScoreOfDeadPlayers(); //now that i realize it, i could have made these methods return a value, but idk where the methods are
        // game.getRANKOfDeadPlayers(); //now that i realize it, i could have made these methods return a value, but idk where the methods are
        //this.score=game.SCORE;
        //this.rank=game.RANK;
        if (gameWorld.getMouseInput().isMenuStartButtonClicked())//idk where the button is cuz of the gimp file exportation, so figure out during the meeting
        {
           // gameWorld.setGameState(Game.GameState.MENU);
        }


    }

    /**
     * renders the graphics game
     * @param g is the graphics of the game
     */
    public void render(Graphics g) {
        g.setFont(game.getEndScoreFont());
    	g.setColor(Color.WHITE);
    	g.drawString("HI", Const.WORLD_WIDTH / 2, Const.WORLD_HEIGHT / 2);
    	game.getEndRankFont();
    }
}
