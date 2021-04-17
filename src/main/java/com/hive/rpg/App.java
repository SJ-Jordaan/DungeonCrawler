package com.hive.rpg;

import com.hive.rpg.map.*;
import com.hive.rpg.models.*;
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
    
    public static void main( String[] args )
    {
        // Testing stuff for outputting generated map
        // createMap();
        createTutorialMap();
        

        boolean isRunning;
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        Screen screen;
        screen = new Screen(UI_WIDTH,UI_HEIGHT);
        screen.outputMap(MAP_WIDTH, MAP_HEIGHT, map);
        isRunning = true;

        while(isRunning) {
            long startTime = System.nanoTime();

            screen.inputHandler.processInput(); //60 fps input processing (actual game can be a manual game loop)

            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }
    
    private static void createMap() {

        EntityType[] types = {
            EntityType.Bat,
            EntityType.Farmer
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
        .populate("wall", EntityType.Wall)
        .generateRandomMap(1, 10, 10, 2500)
        .populateMap(5, types)
        .build();
    }

    private static void createTutorialMap() {

        EntityType[] tutorialEnemy = {
            EntityType.Pig
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
        .populate("wall", EntityType.Wall)
        .carveOutRoom(1, 1, MAP_WIDTH-2, MAP_HEIGHT-2)
        .populateMap(25, tutorialEnemy)
        .build();
    }
}
