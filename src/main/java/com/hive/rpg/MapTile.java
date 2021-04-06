package com.hive.rpg;

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

    public MapTile(String name, EntityType type, int[] coord, char glyph, Color colour, Color backgroundColour, boolean pathable) {
        super(name, type, coord, glyph, colour);
        this.backgroundColour = backgroundColour;
        this.pathable = pathable;
    }
}
