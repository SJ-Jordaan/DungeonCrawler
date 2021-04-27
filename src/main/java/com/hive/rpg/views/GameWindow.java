package com.hive.rpg.views;

import javax.swing.*;

public class GameWindow extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -2770730696299861402L;
    //private final List<BaseScreen> screens = new ArrayList<>();
    //public static int currentScreen = 0;
    private final GameScreen screen;

    public GameWindow(int width, int height){
        this.setSize(width, height);
        this.setTitle("Dungeon Crawler");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        screen = new GameScreen(width, height);
        this.add(screen);
        this.addKeyListener(screen.controller);
        this.setVisible(true);
    }

    public GameScreen GetCurrentScreen() {
        return screen;
    }

    public void clearScreen() {
        screen.clear();
    }
}
