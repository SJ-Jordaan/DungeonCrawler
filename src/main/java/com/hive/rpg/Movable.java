package com.hive.rpg;

public interface Movable {
    enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    void move(Direction direction);
}
