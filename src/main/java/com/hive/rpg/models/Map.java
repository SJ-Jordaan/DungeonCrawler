package com.hive.rpg.models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hive.rpg.controllers.*;

public class Map {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Set<Entity> entities;
    public Set<Enemy> enemies;

    /**
     * Auto-generated getters and setters
     */
    public MapTile[][] getTiles() {
        return tiles;
    }

    public void setTiles(MapTile[][] tiles) {
        this.tiles = tiles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Entity getPlayer() {
        return (Entity) GameEngine.player;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    /**
     * Constructors
     */
    public Map(MapTile[][] tiles, Set<Entity> entities, Set<Enemy> enemies, int maxWidth, int maxHeight) {
        this.entities = new HashSet<>();
        this.entities.addAll(entities);
        this.tiles = tiles;
        this.width = maxWidth;
        this.height = maxHeight;
        this.enemies = enemies;
    }

    /**
     * Additional member functions
     */

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public MapTile getTile(int[] coord) {
        // Check to see if coord are within map boundaries
        if (coord[0] < 0 || coord[0] >= this.width || coord[1] < 0 || coord[1] >= this.height) {

            return null;
        }

        return tiles[coord[0]][coord[1]];
    }

    public void clearBodies() {
        enemies.removeIf(enemy -> enemy.getHealth() <= 0);
    }

    public void moveEnemies() {
        Random rnd = new Random();
        int moveChance = rnd.nextInt(100);

        if (moveChance > 98) {
            enemies.stream().forEach(enemy -> {
                int rndNr = rnd.nextInt(3);

                int[] coord = enemy.getCoord().clone();

                if (rndNr == 0) {
                    coord[0] = enemy.getX() + 1;
                } else if (rndNr == 1) {
                    coord[0] = enemy.getX() - 1;
                } else if (rndNr == 2) {
                    coord[1] = enemy.getY() + 1;
                } else if (rndNr == 3) {
                    coord[1] = enemy.getY() - 1;
                }
                if (coord[0] < width && coord[0] > 0 && coord[1] < height && coord[1] > 0) {
                    if (getTile(coord).isPathable()) {
                        enemy.setCoord(coord);
                    }
                }
            });
        }
    }

    public Entity getEntityAt(int[] coord) {
        Entity e = entities.stream().filter(entity -> entity.getX() == coord[0] && entity.getY() == coord[1])
                .findFirst().orElse(null);
        return e;
    }

    public Enemy getEnemyAt(int[] coord) {
        Enemy e = enemies.stream().filter(entity -> entity.getX() == coord[0] && entity.getY() == coord[1]).findFirst()
                .orElse(null);
        return e;
    }

    public boolean isPathableTerrain(int[] coord) {
        if (coord[0] < this.width && coord[0] >= 0) {
            if (coord[1] < this.height && coord[1] >= 0) {
                return (tiles[coord[0]][coord[1]].isPathable());
            }
        }
        return false;
    }
}
