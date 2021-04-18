package com.hive.rpg.screens;

import com.hive.rpg.controllers.GameScreenController;
import com.hive.rpg.Hero;
import com.hive.rpg.map.Map;
import com.hive.rpg.map.MapFactory;
import com.hive.rpg.models.EntityType;

import java.awt.event.KeyListener;

public class GameScreen extends BaseScreen {

    private final int MAP_WIDTH = 90;
    private final int MAP_HEIGHT = 43;
    Hero p = new Hero('@', 0, 0);
    GameScreenController controller = new GameScreenController(p);
    Map map;

    public GameScreen(int width, int height){
        super(width, height);
        this.map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
                .populate("wall", EntityType.Wall)
                .generateRandomMap(1092, 10, 10, 35000)
                .build();
    }

    @Override
    public void init(){
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coordinate = {x,y};
                this.write(map.getTile(coordinate).getGlyph(), x, y);
            }
        }
    }

    @Override
    public KeyListener getController(){
        return this.controller;
    }

    @Override
    public void handleInput() {
        this.controller.handleUserInput();
    }

    @Override
    public void update() {
        this.setCursorX(p.getX());
        this.setCursorY(p.getY());
    }

    @Override
    public void render() {
        this.write(p.getSymbol());
    }
}
