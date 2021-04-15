package com.hive.rpg.map;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hive.rpg.models.*;

public class MapFactory {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Entity player;
    public Set<Entity> entities;

    public MapFactory(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new MapTile[width][height];
        this.entities = new HashSet<Entity>();
    }

    public MapTile createTile(String name, EntityType type, int [] coord) {
        return new MapTile(name, coord, type);
    }

    public MapFactory populate(String name, EntityType type) {
        for (int i = 0; i < this.width; i++) {
            for (int y = 0; y < this.height; y++) {
                int [] coord = {i,y};
                tiles[i][y] = createTile(name, type, coord);
            }
        }
        return this;
    }

    public Map build() {
        return new Map(this.tiles, this.entities, this.width, this.height);
    }

    public MapFactory generateRandomMap(int seed, int startX, int startY, int length) {
        Random randomNumber = new Random(seed);
        int direction;
        int x = startX;
        int y = startY;

        for (int i = 0; i < length; i++) {
            direction = randomNumber.nextInt(4);
            if (direction == 0 && (x+1) < (width)) {
                x += 1;
            } else if (direction == 1 && (x-1) > 0) {
                x -= 1;
            } else if (direction == 2 && (y+1) < (height)) {
                y += 1;
            } else if (direction == 3 && (y-1) > 0) {
                y -= 1;
            }
    
            int[] coord = {x,y};
            tiles[x][y] = createTile("floor", EntityType.Path, coord);
        }
        return this;
    }

    public Entity createLivingEntity(String name, EntityType type, int[] coord) {
        return new Entity(name, type, coord);
    }

    public MapFactory populateMap(int numberOfEntities, EntityType[] types) {
        Random randomNumber = new Random();
        int x;
        int y;
        int entityType;

        for (int i = 0; i < numberOfEntities; i++) {

            do {
                x = randomNumber.nextInt(width);
                y = randomNumber.nextInt(height);
            } while (!tiles[x][y].isPathable());

            entityType = randomNumber.nextInt(types.length);
            int[] coord = {x,y};
            entities.add(createLivingEntity("Entity", types[entityType], coord));
            tiles[x][y] = createTile("Entity", types[entityType], coord);
        }
        return this;
    }
}
