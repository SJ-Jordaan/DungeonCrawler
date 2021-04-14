package com.hive.rpg.map;

import java.util.HashSet;
import java.util.Set;

import com.hive.rpg.models.Entity;
import com.hive.rpg.models.MapTile;

public class Map {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Entity player;
    public Set<Entity> entities;

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
        return player;
    }
    public void setPlayer(Entity player) {
        this.player = player;
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
    public Map(MapTile[][] tiles, Set<Entity> entities, int maxWidth, int maxHeight) {
        this.entities = new HashSet<>();
        this.entities.addAll(entities);
        this.tiles = tiles;
        this.width = maxWidth;
        this.height = maxHeight;
    }
    /**
    * Additional member functions
    */

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public MapTile getTile(int[] coord) {
        // Check to see if coord are within map boundaries
        if (coord[0] < 0 || 
            coord[0] >= this.width || 
            coord[1] < 0 || 
            coord[1] >= this.height) {

            return null;
        }
        
        return tiles[coord[0]][coord[1]];
    }

    public boolean isPathableTerrain(int [] coord) {
        return (tiles[coord[0]][coord[1]].isPathable());
    }
}
