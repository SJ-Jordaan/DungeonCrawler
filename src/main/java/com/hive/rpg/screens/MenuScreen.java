package com.hive.rpg.screens;

import com.hive.rpg.controllers.MenuScreenController;

import java.awt.event.KeyListener;

public class MenuScreen extends BaseScreen {

    MenuScreenController controller = new MenuScreenController();

    public MenuScreen(int width, int height) {
        super(width, height);
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
        this.controller.handleUserInput();
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        //this.write("Menu");
    }

    @Override
    public KeyListener getController() {
        return this.controller;
    }
}
