package com.hive.rpg;

import java.awt.event.KeyListener;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class InputHandler implements KeyListener {
    private final Queue<InputEvent> inputQueue;
    private final Map<Integer, String> keyMap;
    public String last = "0";

    public InputHandler() {
        inputQueue = new LinkedList<InputEvent>();
        keyMap = new HashMap<Integer, String>();
        keyMap.put(KeyEvent.VK_A, "left"); //map functions from player controller to keys
        keyMap.put(KeyEvent.VK_D, "right");
        keyMap.put(KeyEvent.VK_W, "up");
        keyMap.put(KeyEvent.VK_S, "down");
        keyMap.put(KeyEvent.VK_SPACE, "attack");
        keyMap.put(KeyEvent.VK_ENTER, "attack");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void processInput() {
        InputEvent event = inputQueue.poll();
        if (event instanceof KeyEvent) {
            KeyEvent keyPress = (KeyEvent)event;
            String out = keyMap.get(keyPress.getKeyCode());
            if (out != null) {
                last = out;
                return;
            }
        }
        last = "0";
    }
}
