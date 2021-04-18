package com.hive.rpg.controllers;

import com.hive.rpg.GameWindow;
import com.hive.rpg.Hero;
import com.hive.rpg.Movable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreenController extends BaseScreenController implements KeyListener {
    Hero hero;

    public GameScreenController(Hero p){
        this.hero = p;
    }

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


    public void processKeyPress(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_A:
                this.hero.move(Movable.Direction.LEFT);
                System.out.println("Moving Left");
                break;
            case KeyEvent.VK_D:
                this.hero.move(Movable.Direction.RIGHT);
                System.out.println("Moving Right");
                break;
            case KeyEvent.VK_W:
                this.hero.move(Movable.Direction.UP);
                break;
            case KeyEvent.VK_S:
                this.hero.move(Movable.Direction.DOWN);
                break;
            case KeyEvent.VK_ESCAPE:
                GameWindow.currentScreen = -1;
            case KeyEvent.VK_BACK_SPACE:
                GameWindow.currentScreen = 0;
        }
    }
}
