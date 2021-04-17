package com.hive.rpg.models;

public class Enemy extends Characters implements ICharacterActions {

    public Enemy(String Name, Integer Health, Weapon Weapon, EntityType type, int[] coords) {
        super(Name, Health, Weapon, type, coords);
    }

    @Override
    public boolean EquipWeapon(Weapon Weapon) {
        // TODO: Add pick up weapon logic here
        return false;
    }

    @Override
    public boolean DropWeapon(Weapon Weapon) {
        // TODO: Add drop weapon logic here
        return false;
    }
}
