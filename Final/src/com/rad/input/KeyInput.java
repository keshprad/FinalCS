package com.rad.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rad.Game;

/**
 * @author RAD
 * @version 1.0
 * is the key listener for our code, used in players
 */
public class KeyInput implements KeyListener {
    /**
     * is the up movement used in player
     */
    private boolean up = false;
    /**
     * down movement used in player
     */
    private boolean down = false;
    /**
     * left movement used in player
     */
    private boolean left = false;
    /**
     * right movement used in player
     */
    private boolean right = false;
    /**
     * the game user is playing in
     */
    private Game game;

    /**
     * is the constructor of key input, takes the key input for the game, where the game is initialized.
     * @param g the game users are playing in
     */
    public KeyInput(Game g) {
        this.game = g;
    }

    /**
     * the key to be typed
     * @param e the button (key event ) pressed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /**
     *  the key to be released, checks to move keyboard buttons WASD to move up, left, down, right respectively
     * @param e the button (key event ) pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            left = true;
        } else if (key == KeyEvent.VK_D) {
            right = true;
        } else if (key == KeyEvent.VK_W) {
            up = true;
        } else if (key == KeyEvent.VK_S) {
            down = true;
        }

    }

    /**
     * is the key to be released,checks to not move keyboard buttons WASD to not move up, left, down, right respectively
     * @param e the key released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            left = false;
        } else if (key == KeyEvent.VK_D) {
            right = false;
        } else if (key == KeyEvent.VK_W) {
            up = false;
        } else if (key == KeyEvent.VK_S) {
            down = false;
        }
    }

    /**
     * returns if movement is up
     * @return if movement is up
     */
    public boolean isUp() {
        return up;
    }

    /**
     *  returns if movement is down
     * @return if movement is down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * returns if movement is left
     * @return  if movement is left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     *  returns if movement is right
     * @return  if movement is left
     */
    public boolean isRight() {
        return right;
    }


}
