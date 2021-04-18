package com.hive.rpg.controllers;

import com.hive.rpg.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuScreenController extends BaseScreenController implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.inputEventQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void processKeyPress(KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_ENTER:
                System.out.println("Transitioning to Game Screen");
                GameWindow.currentScreen = 1;
        }
    }
}
