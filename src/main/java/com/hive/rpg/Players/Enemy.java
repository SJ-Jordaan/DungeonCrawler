package com.hive.rpg.Players;

import java.util.ArrayList;

import com.hive.rpg.Weapons.Weapon;
import com.hive.rpg.models.EntityType;

public class Enemy extends Characters implements ICharacterActions {

    public Enemy(String Name, Integer Health, Weapon Weapon, EntityType type, int[] coords) {
        super(Name, Health, Weapon, type, coords);
    }

    @Override
    public boolean EquipWeapon(Weapon Weapon) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean DropWeapon(Weapon Weapon) {
        // TODO Auto-generated method stub
        return false;
    }
}
