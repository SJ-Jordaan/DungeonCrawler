package com.hive.rpg.models;

public class Enemy extends Characters implements ICharacterActions {

    public Enemy(Integer Health, Weapon Weapon, EntityType type, int[] coords) {
        super(Health, Weapon, type, coords);
    }

    @Override
    public boolean EquipWeapon(Weapon Weapon) {
        return false;
    }

    @Override
    public boolean DropWeapon(Weapon Weapon) {
        return false;
    }
}
