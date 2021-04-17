package com.hive.rpg;

import javax.swing.*;

import asciiPanel.AsciiPanel;
import com.hive.rpg.InputHandler;
import com.hive.rpg.map.Map;
import com.hive.rpg.models.Entity;
import com.hive.rpg.models.MapTile;

import java.util.Set;

public class Screen extends JFrame {

    public InputHandler inputHandler;
    private AsciiPanel terminal;

    public Screen(int width, int height) {
        inputHandler = new InputHandler();
        super.addKeyListener(inputHandler);
        this.terminal = new AsciiPanel(width, height);
        super.add(terminal);
        super.setSize(width,height);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void outputMap(int MAP_WIDTH, int MAP_HEIGHT, Map map) {
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coord = {i, y};
                MapTile tile = map.getTile(coord);
                this.terminal.write(tile.getGlyph(), i, y, tile.getColour(), tile.getBackgroundColor());
            }
        }
        for (Entity e : map.getEntities()) {
            writeEntity(e);
        }
        writeEntity(map.player);
        this.terminal.repaint();
    }

    public void writeEntity(Entity entity) {
        this.terminal.write(entity.getGlyph(), entity.getX(), entity.getY(), entity.getColour());
    }
}
