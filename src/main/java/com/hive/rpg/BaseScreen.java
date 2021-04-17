package com.hive.rpg;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyListener;

public abstract class BaseScreen extends AsciiPanel {
    BaseScreen(int width, int height){
        super(width, height);
    }

    abstract void handleInput();
    abstract KeyListener getController();
}
