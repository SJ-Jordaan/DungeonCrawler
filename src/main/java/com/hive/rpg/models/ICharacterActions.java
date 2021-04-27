package com.hive.rpg.models;

public interface ICharacterActions {
    public void ReceiveAttack(int damage);

    public boolean EquipWeapon(Weapon Weapon);

    public boolean DropWeapon(Weapon Weapon);
    // public abstract void Defend(Character Opponent, Character Player, Weapon
    // Weapon);
}
