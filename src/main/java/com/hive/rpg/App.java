package com.hive.rpg;
import com.hive.rpg.map.*;
import com.hive.rpg.models.*;

import com.hive.rpg.Players.Player;

/**
 * Hello world!
 *
 */

public class App 
{
    private static Map map;
    private static int MAP_WIDTH = 54;
    private static int MAP_HEIGHT = 29;
    private static int UI_WIDTH = 500;
    private static int UI_HEIGHT = 500;
    
    public static void main( String[] args ) {
        // Testing stuff for outputting generated map
        // createMap();
        // createTutorialMap();
        GameEngine game = new GameEngine();
        game.run();
    }

}
