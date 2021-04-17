package com.hive.rpg;

/**
 * The Hero class models a BBD graduate, the hero who embarks on
 * a journey to master different level ups in the BBD Graduate Programme
 */
public class Hero extends GameCharacter implements Movable {
    static final int SPEED = 1;
    private int healthPoints = 5;

    public Hero(char symbol){
        super(symbol);
    }

    public Hero(char symbol, int xPos, int yPos){
        super(symbol, xPos, yPos);
    }

    @Override
    public void move(Direction direction) {
        switch (direction){
            case UP:
                setY(this.getY() - SPEED);
                break;
            case DOWN:
                setY(this.getY() + SPEED);
                break;
            case LEFT:
                setX(this.getX() - SPEED);
                break;
            case RIGHT:
                setX(this.getX() + SPEED);
                break;
        }
    }

    public void decreaseLifePoints(){
        this.healthPoints -= 1;
    }

    public boolean isAlive(){
        return this.healthPoints >= 0;
    }
}
