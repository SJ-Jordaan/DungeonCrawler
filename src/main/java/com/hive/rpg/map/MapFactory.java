package com.hive.rpg.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hive.rpg.InputHandler;
import com.hive.rpg.Players.Enemy;
import com.hive.rpg.Players.Player;
import com.hive.rpg.Weapons.Attack;
import com.hive.rpg.Weapons.Weapon;
import com.hive.rpg.models.*;

public class MapFactory {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Player player;
    public Set<Entity> entities;
    public Set<Enemy> enemies;

    public MapFactory(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new MapTile[width][height];
        this.entities = new HashSet<Entity>();
        this.enemies = new HashSet<Enemy>();
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

    public MapFactory placePlayer() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.tiles[i][j].isPathable()) {
                    int [] coord = {i,j};
                    player = new Player(100, new Weapon(), coord);
                    //this.tiles[i][j] = createTile("player", EntityType.Player, coord);
                    return this;
                }
            }
        }
        return this;
    }

    public Map build() {
        return new Map(this.tiles, this.entities, this.enemies, this.player, this.width, this.height);
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

    public MapFactory carveOutRoom(int bottomX, int bottomY, int width, int height) {
        for (int x = bottomX; x < (bottomX + width); x++) {
            for (int y = bottomY; y < (bottomY + height); y++) {
                int[] coord = {x,y};
                tiles[x][y] = createTile("Room floor", EntityType.Path, coord);
            }
        }
        return this;
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
            ArrayList<Attack> attacks = new ArrayList<Attack>();
            attacks.add(new Attack("SpaghettiPowaaa", 4));
            Weapon weapon = new Weapon("Hands", "You finna throw down??!!?!?!?!", attacks);
            Enemy enemy = new Enemy("Jason Bourne", 100, weapon, EntityType.Bat, coord);
            enemies.add(enemy);
            //entities.add(createLivingEntity("Entity", types[entityType], coord));
            //tiles[x][y] = createTile("Entity", types[entityType], coord);
        }
        return this;
    }
}
