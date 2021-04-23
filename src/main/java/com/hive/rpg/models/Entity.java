package com.hive.rpg.models;

import java.awt.Color;

public class Entity {

    /**
     * Entity specific members
     */
    protected String name;
    /**
     * Map related members
     */
    protected int[] coord;
    protected char glyph;
    protected Color colour;
    protected String type;

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

    public void setCoord(int[] coord) {
        this.coord = coord;
    }

    /**
     * Get entity map symbol
     */
    public char getGlyph() {
        return this.glyph;
    }

    /**
     * Get entities colour
     */
    public Color getColour() {
        return this.colour;
    }

    public Entity(EntityType type, int[] coord) {
        this.coord = coord;
        this.name = type.getFilename();
        this.type = type.getType();
        this.glyph = type.getGlyph();
        this.colour = type.getColour();
    }

    public Entity(String name, EntityType type, int[] coord) {
        this.coord = coord;
        this.name = name;
        this.type = type.getType();
        this.glyph = type.getGlyph();
        this.colour = type.getColour();
    }

    public String ToString() {
        return this.name;
    }
}
