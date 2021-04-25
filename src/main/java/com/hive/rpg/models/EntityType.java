package com.hive.rpg.models;

import java.awt.Color;

public enum EntityType {
    Player('@', Color.blue, "Player"), SQL_JOINS('=', Color.red, "JOINS"), Skeleton('S', Color.red, "Skeleton"),
    Hobgoblin('H', Color.yellow, "Hobgoblin"), Javathian('!', Color.magenta, "Javathian"),
    Dragon('!', Color.magenta, "Dragon"), Knight('K', Color.green, "Knight"), Jester('J', Color.pink, "Jester"),
    Pig('P', Color.green, "Pig"), Unicorn('U', Color.green, "Unicorn"), Path('.', Color.white, Color.black, true),
    Wall('#', Color.white, Color.black, false), Lucky('L', Color.magenta, "Lucky"), Tony('T', Color.magenta, "Tony"),
    Rudolph('R', Color.magenta, "Rudolph"), YourMom('O', Color.red, "YourMom"),

    // CombatView Entities
    HP('X', Color.RED), Border('#', Color.ORANGE), HLine('=', Color.ORANGE), VLine('|', Color.ORANGE);

    private boolean pathable;
    private char glyph;
    private Color colour;
    private String type;
    private Color backgroundColour;
    private String filename;

    EntityType(final char glyph, final Color colour) {
        this.setGlyph(glyph);
        this.setColour(colour);
    }

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
