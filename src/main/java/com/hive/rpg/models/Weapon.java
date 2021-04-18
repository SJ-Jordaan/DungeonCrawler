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
     * @param Name the Name to set
     */
    private void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return String return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    private void setDescription(String Description) {
        this.Description = Description;
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
        // TODO: Replace with better generic weapon instantiation
        this.Name = "Spaghetti-Code";
        this.Description = "Weak but effective";
        this.Attacks = new ArrayList<Attack>();
        this.Attacks.add(new Attack("This game", 1));
        this.Attacks.add(new Attack("Sucks...", 5));
        this.Attacks.add(new Attack("really bad", 10));
        this.Attacks.add(new Attack("like really really", 1));
        this.Attacks.add(new Attack("baaaaaaad", 1));
    }
}
