package com.hive.rpg.views;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyListener;

public abstract class BaseScreen extends AsciiPanel {
    /**
     *
     */
    private static final long serialVersionUID = 7885965659003468962L;
    public BaseScreen(int width, int height){
        super(width, height);
    }

    abstract void handleInput();
    abstract KeyListener getController();
}
