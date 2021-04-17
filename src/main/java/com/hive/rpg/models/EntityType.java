package com.hive.rpg.models;

import java.awt.Color;

public enum EntityType {
    Player('@', Color.green),
    Bat('B', Color.red, "Enemy"),
    Farmer('F', Color.blue, "Ally"),
    Pig('P', Color.green, "Neutral"),
    Sword('^', Color.green),
    Path('.', Color.white, Color.black, true),
    Wall('#', Color.white, Color.black, false);

    private boolean pathable;
    private char glyph;
    private Color colour;
    private String type;
    private Color backgroundColour;

    // Create player entity
    EntityType(final char glyph, final Color colour) {
        this.setGlyph(glyph);
        this.setColour(colour);
    }

    // Create interactable entities
    EntityType(final char glyph, final Color colour, final String type) {
        this.setGlyph(glyph);
        this.setColour(colour);
        this.setType(type);
    }
    
    // Create maptile entities
    EntityType(final char glyph, final Color colour, final Color backgroundColour, final boolean pathable) {
        this.setGlyph(glyph);
        this.setColour(colour);
        this.setBackgroundColour(this.backgroundColour);
        this.setPathable(pathable);
    }

    public Color getBackgroundColour() {
        return backgroundColour;
    }


    public void setBackgroundColour(Color backgroundColour) {
        this.backgroundColour = backgroundColour;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Color getColour() {
        return colour;
    }


    public void setColour(Color colour) {
        this.colour = colour;
    }


    public char getGlyph() {
        return glyph;
    }


    public void setGlyph(char glyph) {
        this.glyph = glyph;
    }


    public boolean isPathable() {
        return pathable;
    }


    public void setPathable(boolean pathable) {
        this.pathable = pathable;
    }


}
