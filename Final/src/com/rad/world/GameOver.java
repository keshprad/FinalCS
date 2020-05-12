package com.rad.world;

import com.rad.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class GameOver extends Menu
{
    Game game;

    public GameOver(Game g) {

        super(g);
    }

    @Override
    public void tick() {
        if (this.game.getGameState() == Game.GameState.GAMEOVER)
        {
            //run

        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
