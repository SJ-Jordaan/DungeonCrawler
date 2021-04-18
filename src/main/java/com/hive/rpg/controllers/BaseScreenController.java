package com.hive.rpg.controllers;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

public abstract class BaseScreenController{
    protected final Queue<InputEvent> inputEventQueue = new LinkedList<>();

    /**
     * Polls the input queue for a key event, process the event
     * and clears the input queue to avoid processing of the
     * same key event in multiple loop iterations
     */
    public void handleUserInput(){
        InputEvent event = this.inputEventQueue.poll();
        if (event instanceof KeyEvent) {
            this.processKeyPress((KeyEvent)event);
            System.out.println("Handling user input called");
            this.inputEventQueue.clear();
        }
    }

    public abstract void processKeyPress(KeyEvent event);
}
