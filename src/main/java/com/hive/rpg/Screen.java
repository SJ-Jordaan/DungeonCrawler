package com.hive.rpg;

import javax.swing.*;
import asciiPanel.AsciiPanel;
import com.hive.rpg.InputHandler;
import com.hive.rpg.Players.Player;
import com.hive.rpg.combatView.CombatView;
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

    public int pHealth = 100;

    public void outputCombat(CombatHandler combatHandler, Player player) {
        CombatView view = new CombatView("Player", "Enemy", "player", combatHandler.getCurrentEnemy().getName(),
                player.getHealth(), combatHandler.getCurrentEnemy().getHealth(), player.selected_attack, player.getAttacks());
        char[][] output = view.createCombatView();
        for (int i = 0; i < view.width; i++) {
            for (int j = 0; j < view.height; j++) {
                this.terminal.write(output[i][j], i, j);
            }
        }
        this.terminal.repaint();
    }

    public void outputMap(int MAP_WIDTH, int MAP_HEIGHT, Map map) {
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coord = {i, y};
                MapTile tile = map.getTile(coord);
                this.terminal.write(tile.getGlyph(), i, y, tile.getColour(), tile.getBackgroundColor());
            }
        }
        for (Entity e : map.entities) {
            writeEntity(e);
        }
        for (Entity e : map.enemies) {
            writeEntity(e);
        }
        writeEntity(map.player);
        this.terminal.repaint();
    }

    public void writeEntity(Entity entity) {
        this.terminal.write(entity.getGlyph(), entity.getX(), entity.getY(), entity.getColour());
    }

    public void clearScreen() {
        this.terminal.clear();
    }
}
