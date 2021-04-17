package com.hive.rpg;

import com.hive.rpg.controllers.GameEngine;

public class App 
{
    public static void main( String[] args ) {
        GameEngine game = new GameEngine();
        game.run();
    }

}
