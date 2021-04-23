package com.hive.rpg;

import com.hive.rpg.controllers.AuthenticationController;
import com.hive.rpg.controllers.GameEngine;

public class App {
    public static void main(String[] args) {
        String username = "Player";
        // AuthenticationController authenticationController = new
        // AuthenticationController();
        // username = authenticationController.initialize();
        GameEngine game = new GameEngine(username);
        game.run();
    }

}
