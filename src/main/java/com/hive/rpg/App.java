package com.hive.rpg;

import com.hive.rpg.map.Map;
import com.hive.rpg.map.*;
import com.hive.rpg.models.*;
/**
 * Hello world!
 *
 */
public class App 
{
    private static Map map;
    private static int MAP_WIDTH = 500;
    private static int MAP_HEIGHT = 500;
    
    public static void main( String[] args )
    {
        // Testing stuff for outputting generated map
        // createMap();
        
        // for (int i = 0; i < MAP_WIDTH; i++) {
        //     for (int y = 0; y < MAP_HEIGHT; y++) {
        //         int[] coord = {i,y};
        //         System.out.print(map.getTile(coord).getGlyph());
        //     }
        //     System.out.println();
        // }
        
        boolean isRunning;
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        Screen screen;
        screen = new Screen(MAP_WIDTH,MAP_HEIGHT);
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
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
        .populate("wall", EntityType.Wall)
        .generateRandomMap(12232, 10, 10, 9000)
        .build();
    }
}
