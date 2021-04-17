package com.hive.rpg.models;

import java.awt.Color;

public class MapTile extends Entity {

    /**
     * Map specific members
     */
    private boolean pathable = false;
    private Color backgroundColour;
    
    /**
     * Can the player move onto this tile
     */
    public boolean isPathable() {
        return this.pathable;
    }
    /**
     * Get the background colour of the tile
     */
    public Color getBackgroundColor() {
        return this.backgroundColour;
    }

    public MapTile(String name, int[] coord, EntityType type) {
        super(name, type, coord);
        this.backgroundColour = type.getBackgroundColour();
        this.pathable = type.isPathable();
    }
}
