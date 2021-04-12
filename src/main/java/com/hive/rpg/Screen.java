package com.hive.rpg;

import javax.swing.*;
import asciiPanel.AsciiPanel;
import com.hive.rpg.InputHandler;

public class Screen extends JFrame {
    public InputHandler inputHandler;
    public Screen(int width, int height) {
        inputHandler = new InputHandler();
        super.addKeyListener(inputHandler);
        AsciiPanel terminal = new AsciiPanel(width, height);
        super.add(terminal);
        super.setSize(width,height);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
