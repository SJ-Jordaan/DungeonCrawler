package com.hive.rpg;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class GameScreenController implements KeyListener {
    Hero hero;
    private final Queue<InputEvent> inputQueue;
    GameScreenController(Hero p){
        inputQueue = new LinkedList<InputEvent>();
        this.hero = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.inputQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Polls the input queue for a key event, process the event
     * and clears the input queue to avoid processing of the
     * same key event in multiple loop iterations
     */
    public void handleUserInput(){

        InputEvent event = this.inputQueue.poll();
        if (event instanceof KeyEvent) {
            this.processKeyPress((KeyEvent)event);
            System.out.println("Handling user input called");
            this.inputQueue.clear();
        }
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
                System.out.println("Quiting the game");
                GameWindow.currentScreen = -1;
        }
    }

    Hero getPlayer(){
        return this.hero;
    }
}
