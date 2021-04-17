package com.hive.rpg.models;

import java.awt.Color;

public enum EntityType {
    Player('@', Color.blue, "Player"),
    Bull('B', Color.red, "Bull"),
    Skeleton('U', Color.red, "Skeleton"),
    Pig('P', Color.green, "Pig"),
    Path('.', Color.white, Color.black, true),
    Wall('#', Color.white, Color.black, false);

    private boolean pathable;
    private char glyph;
    private Color colour;
    private String type;
    private Color backgroundColour;
    private String filename;

    // Create player entity
    EntityType(final char glyph, final Color colour, String filename) {
        this.setGlyph(glyph);
        this.setColour(colour);
        this.setFilename(filename);
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
