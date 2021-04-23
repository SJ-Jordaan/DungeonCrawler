package com.hive.rpg;

import com.hive.rpg.controllers.AuthenticationController;
import com.hive.rpg.controllers.GameEngine;

public class App 
{
    public static void main( String[] args ) {
//        AuthenticationController authenticationController = new AuthenticationController();
//        authenticationController.initialize();
        GameEngine game = new GameEngine();
        game.run();
    }

}
