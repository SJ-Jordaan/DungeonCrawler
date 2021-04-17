package com.hive.rpg;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyListener;

public abstract class BaseScreen extends AsciiPanel {
    BaseScreen(int width, int height){
        super(width, height);
    }

    abstract void init();
    abstract void handleInput();
    abstract void update();
    abstract void render();
    abstract KeyListener getController();
}
