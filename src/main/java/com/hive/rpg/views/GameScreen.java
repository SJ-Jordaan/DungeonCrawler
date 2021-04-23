package com.hive.rpg.views;

import com.hive.rpg.controllers.*;
import com.hive.rpg.models.*;

import java.awt.*;
import java.awt.event.KeyListener;

public class GameScreen extends BaseScreen {

    private static final long serialVersionUID = -2250594602715438858L;
    public GameScreenController controller = new GameScreenController();

    GameScreen(int width, int height){
        super(width, height);
    }

    public void outputCombat(CombatHandler combatHandler){
        resetScreen();
        Player player = GameEngine.player;
        CombatView view = new CombatView(
            player.getName(),
            combatHandler.getCurrentEnemy().getName(),
            player.getName(),
            combatHandler.getCurrentEnemy().getName(),
            player.getHealth(),
            combatHandler.getCurrentEnemy().getHealth(),
            player.selected_attack,
            player.getAttacks()
        );
        char[][] output = view.createCombatView();
        for (int i = 0; i < view.width; i++) {
            for (int j = 0; j < view.height; j++) {
                this.write(output[i][j], i, j);
            }
        }
        this.repaint();
    }

    public void outputMap(int MAP_WIDTH, int MAP_HEIGHT, int UI_WIDTH, int UI_HEIGHT, Map map) {
        resetScreen();
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coord = {i, y};
                MapTile tile = map.getTile(coord);
                this.write(tile.getGlyph(), i, y, tile.getColour(), tile.getBackgroundColor());
            }
        }
        for (Entity e : map.entities) {
            writeEntity(e);
        }
        for (Entity e : map.enemies) {
            writeEntity(e);
        }
        writeEntity(GameEngine.player);
        this.repaint();
    }

    public void resetScreen() {
        for (int i = 0; i < this.getWidth()/this.getCharWidth()+1; i++) {
            for (int j = 0; j < this.getHeight()/this.getCharHeight()+1; j++) {
                this.write(" ", i, j);
            }
        }
    }

    public void outputText(String string) {
        resetScreen();
        this.write(string, this.getWidth()/this.getCharWidth()/2 - string.length()/2, this.getHeight()/this.getCharHeight()/2, Color.WHITE);
        this.repaint();
    }

    public void writeEntity(Entity entity) {
        this.write(entity.getGlyph(), entity.getX(), entity.getY(), entity.getColour());
    }

    @Override
    public KeyListener getController(){
        return this.controller;
    }

    @Override
    public void handleInput() {
        this.controller.processKeyPress();
    }
}
