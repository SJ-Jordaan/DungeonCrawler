package com.hive.rpg.Weapons;

public class Weapon {
    private String Name;
    private String Description;
    private Integer CombatValue;

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
     * @return Integer return the BaseCombatValue
     */
    public Integer getCombatValue() {
        return CombatValue;
    }
    // #endregion

    public Weapon(String Name, String Description, Integer CombatValue) {
        this.Name = Name;
        this.Description = Description;
        this.CombatValue = CombatValue;
    }

    public void Upgrade(Integer CombatValue) {
        this.CombatValue += CombatValue;
    }
}
