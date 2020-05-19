package com.rad.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rad.Game;

/**
 * @author RAD
 * @version 1.0
 * is the key listner for our code, used in players
 */
public class KeyInput implements KeyListener {
    /**
     * is the up movement
     */
    private boolean up = false;
    /**
     * down movement
     */
    private boolean down = false;
    /**
     * left movement
     */
    private boolean left = false;
    /**
     * right movement
     */
    private boolean right = false;
    /**
     * the game user is playing in
     */
    private Game game;

    /**
     * is the key input of the location
     * @param g the game users are playing in
     */
    public KeyInput(Game g) {
        this.game = g;
    }

    /**
     * the key to be typed
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /**
     *  the key to be released
     * @param e
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
     * is the key to be released
     * @param e the key pressed
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
     * if movement is up
     * @return if movement is up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * if movement is down
     * @return if movement is down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * if movement is left
     * @return  if movement is left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     *  if movement is right
     * @return  if movement is left
     */
    public boolean isRight() {
        return right;
    }


}
