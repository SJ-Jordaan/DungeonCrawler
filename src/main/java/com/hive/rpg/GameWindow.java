package com.hive.rpg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    //private final List<BaseScreen> screens = new ArrayList<>();
    //public static int currentScreen = 0;
    private final GameScreen screen;

    GameWindow(int width, int height){
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
