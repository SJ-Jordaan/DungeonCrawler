package com.hive.rpg;

public class GameCharacter {
    private int xPos;
    private int yPos;
    private final char symbol;

    GameCharacter(char symbol){
        this.symbol = symbol;
    }

    GameCharacter(char symbol, int xPos, int yPos){
        this.symbol = symbol;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setX(int xPos){
        this.xPos = xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getX(){
        return this.xPos;
    }

    public int getY(){
        return this.yPos;
    }

    public char getSymbol(){
        return this.symbol;
    }
}
