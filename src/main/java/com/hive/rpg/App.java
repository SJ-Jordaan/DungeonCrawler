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
    private static int MAP_WIDTH = 30;
    private static int MAP_HEIGHT = 30;
    
    public static void main( String[] args )
    {
        createMap();
        
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coord = {i,y};
                System.out.print(map.getTile(coord).getGlyph());
            }
            System.out.println();
        }
    }
    
    private static void createMap() {
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
        .populate("wall", EntityType.Wall)
        .generateRandomMap(12232, 10, 10, 90000)
        .build();

    }
}
