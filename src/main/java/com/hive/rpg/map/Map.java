package com.hive.rpg.map;

import java.util.HashSet;
import java.util.Set;

import com.hive.rpg.CombatHandler;
import com.hive.rpg.InputHandler;
import com.hive.rpg.Players.Characters;
import com.hive.rpg.Players.Enemy;
import com.hive.rpg.Players.Player;
import com.hive.rpg.models.Entity;
import com.hive.rpg.models.MapTile;
import com.hive.rpg.models.State;

public class Map {

    private MapTile[][] tiles;
    private int width;
    private int height;
    public Player player;
    public Set<Entity> entities;
    public State state = State.Moving;

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
    public Set<Enemy> enemies;
    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    /** 
    * Constructors 
    */
    public Map(MapTile[][] tiles, Set<Entity> entities, Set<Enemy> enemies, Player player, int maxWidth, int maxHeight) {
        this.entities = new HashSet<>();
        this.entities.addAll(entities);
        this.tiles = tiles;
        this.width = maxWidth;
        this.height = maxHeight;
        this.player = player;
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
        if (coord[0] < 0 || 
            coord[0] >= this.width || 
            coord[1] < 0 || 
            coord[1] >= this.height) {

            return null;
        }
        
        return tiles[coord[0]][coord[1]];
    }

    public void update(InputHandler inputHandler, CombatHandler combatHandler) {
        player.move(inputHandler, this, combatHandler);
    }

    public void clearBodies() {
        enemies.removeIf(enemy -> enemy.getHealth() <= 0);
    }

    public Entity getEntityAt(int[] coord) {
        Entity e = entities.stream()
                    .filter(entity -> entity.getX() == coord[0] && entity.getY() == coord[1])
                    .findFirst()
                    .orElse(null);
        return e;
    }

    public Enemy getEnemyAt(int[] coord) {
        Enemy e = enemies.stream()
                    .filter(entity -> entity.getX() == coord[0] && entity.getY() == coord[1])
                    .findFirst()
                    .orElse(null);
        return e;
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
