package com.hive.rpg;

import com.hive.rpg.map.Map;
import com.hive.rpg.models.Entity;
import com.hive.rpg.models.EntityType;

public class Player extends Entity {
    public Player(int[] coords) {
        super("player", EntityType.Player, coords);
    }

    public void move(InputHandler inputHandler, Map map) {
        int[] c = this.getCoord().clone();
        switch (inputHandler.last.charAt(0)) {
            case 'u': c[1] -= 1; break;
            case 'd': c[1] += 1; break;
            case 'l': c[0] -= 1; break;
            case 'r': c[0] += 1; break;
            default: break;
        }
        if (map.isPathableTerrain(c)) {
            this.setCoord(c);
        }
    }
}
