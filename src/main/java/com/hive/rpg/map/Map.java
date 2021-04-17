package com.hive.rpg.map;

import java.util.HashSet;
import java.util.Set;

import com.hive.rpg.InputHandler;
import com.hive.rpg.Player;
import com.hive.rpg.models.Entity;
import com.hive.rpg.models.MapTile;

import javax.security.auth.login.CredentialException;

public class Map {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Player player;
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
    public void setPlayer(Player player) {
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
    public Map(MapTile[][] tiles, Set<Entity> entities, Player player, int maxWidth, int maxHeight) {
        this.entities = new HashSet<>();
        this.entities.addAll(entities);
        this.tiles = tiles;
        this.width = maxWidth;
        this.height = maxHeight;
        this.player = player;
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

    public void update(InputHandler inputHandler) {
        player.move(inputHandler, this);
    }

    public <T extends Entity> T getEntityAt(Class<T> type, int[] coord) {
        if (type == MapTile.class) {
            return type.cast(tiles[coord[0]][coord[1]]);
        } else if (type == Entity.class) {
            Entity e = entities.stream()
            .filter(entity -> entity.getX() == coord[0] && entity.getY() == coord[1])
            .findFirst()
            .orElse(null);
            return type.cast(e);
        }

        return null;
    }

    public boolean isPathableTerrain(int [] coord) {
        if (coord[0] < this.width && coord[0] >= 0) {
            if (coord[1] < this.height && coord[1] >= 0) {
                return (tiles[coord[0]][coord[1]].isPathable());
            }
        }
        return false;
    }
}
