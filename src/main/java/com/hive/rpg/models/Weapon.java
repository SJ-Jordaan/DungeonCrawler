package com.hive.rpg.models;

import java.util.ArrayList;

public class Weapon {
    private String Name;
    private String Description;
    private ArrayList<Attack> Attacks;

    // #region Getters & Setters
    /**
     * @return String return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @return String return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @return ArrayList<Attack> return the Attacks
     */
    public ArrayList<Attack> getAttacks() {
        return Attacks;
    }

    /**
     * @param Attacks the Attacks to set
     */
    public void setAttacks(ArrayList<Attack> Attacks) {
        this.Attacks = Attacks;
    }

    public Weapon(String Name, String Description, ArrayList<Attack> attacks) {
        this.Name = Name;
        this.Description = Description;
        this.Attacks = attacks;
    }

    public Weapon() {

    }

    public Weapon(WeaponType type) {
        this.Name = type.Name;
        this.Description = type.Description;
        this.Attacks = type.Attacks;
    }
}
