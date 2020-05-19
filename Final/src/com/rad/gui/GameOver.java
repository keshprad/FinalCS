package com.rad.gui;

import com.rad.Game;
import com.rad.world.GameWorld;

import java.awt.*;

public class GameOver {
	private GameWorld gameWorld;
    private Game game;


    public GameOver(Game g) {
        game = g;
        gameWorld = g.getGameWorld();
    }

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


    public void render(Graphics g) {
        render(g);
        //draw the gimp file and buttons
    }
}
