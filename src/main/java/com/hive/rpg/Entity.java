package com.hive.rpg;

import java.awt.Color;

public class Entity {

    /**
     * Entity specific members
     */
    protected String name;
    protected EntityType type;
    /**
     * Map related members
     */
    protected int[] coord;
    protected char glyph;
    protected Color colour;

    /**
     * Get x coordinate
     */
    public int getX() {
        return this.coord[0];
    }
    /**
     * Get y coordinate
     */
    public int getY() {
        return this.coord[1];
    }
    /**
     * Get coordinates
     */
    public int[] getCoord() {
        return this.coord;
    }
    /**
     * Get entity map symbol
     */
    public char getGlyph() {
        return this.glyph;
    }
    /**
     * Get the type of entity
     */
    public EntityType getType() {
        return this.type;
    }
    /**
     * Get entities colour
     */
    public Color getColour() {
        return this.colour;
    }

    public Entity(String name, EntityType type, int[] coord, char glyph, Color colour) {
        this.name = name;
        this.type = type;
        this.glyph = glyph;
        this.coord = coord;
        this.colour = colour;
    }
}
