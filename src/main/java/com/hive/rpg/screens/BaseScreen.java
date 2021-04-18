package com.hive.rpg.screens;

import asciiPanel.AsciiPanel;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class BaseScreen extends AsciiPanel {
    BaseScreen(int width, int height){
        super(width, height);
    }

    public abstract void init();
    public abstract void handleInput();
    public abstract void update();
    public abstract void render();
    public abstract KeyListener getController();
}
