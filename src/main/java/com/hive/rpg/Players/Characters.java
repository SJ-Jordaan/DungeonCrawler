package com.hive.rpg.Players;

import java.util.ArrayList;

import com.hive.rpg.Weapons.Weapon;
import com.hive.rpg.models.Entity;
import com.hive.rpg.models.EntityType;

public class Characters extends Entity {
    private Integer Health;
    private Weapon Weapon;

    // #region Getters & Setters
    /**
     * @return String return the Name
     */
    public String getName() {
        return this.name;
    }

    public void ReceiveAttack(int damage){
        Health-=damage;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * @return Integer return the Health
     */
    public Integer getHealth() {
        return Health;
    }

    /**
     * @param Health the Health to set
     */
    public void setHealth(Integer Health) {
        this.Health = Health;
    }

    /**
     * @return ArrayList<Weapon> return the Weapons
     */
    public Weapon getWeapon() {
        return Weapon;
    }

    /**
     * @param Weapon the Weapons to set
     */
    public void setWeapons(Weapon Weapon) {
        this.Weapon = Weapon;
    }

    // #endregion

    public Characters(String Name, Integer Health, Weapon Weapon, EntityType type, int[] coords) {
        super(Name, type, coords);
        this.name = Name;
        this.Health = Health;
        this.Weapon = Weapon;

    }

    public Characters(String Name, Integer Health, EntityType type, int[] coords) {
        super(Name, type, coords);
        this.name = Name;
        this.Health = Health;
    }

}
