package com.hive.rpg.Weapons;

public class Weapon implements IWeapon {
    private String Name;
    private String Description;
    private WeaponType Type;
    private WeaponAttribute Attribute;
    private Integer WeaponTypeCombatValue;

    private final Integer BaseCombatValue;

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
     * @return WeaponType return the Type
     */
    public WeaponType getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    private void setType(WeaponType Type) {
        this.Type = Type;
    }

    /**
     * @return WeaponAttribute return the Attribute
     */
    public WeaponAttribute getAttribute() {
        return Attribute;
    }

    /**
     * @param Attribute the Attribute to set
     */
    private void setAttribute(WeaponAttribute Attribute) {
        this.Attribute = Attribute;
    }

    /**
     * @return Integer return the WeaponTypeCombatValue
     */

    /**
     * @param WeaponTypeCombatValue the WeaponTypeCombatValue to set
     */
    private void setWeaponTypeCombatValue(Integer WeaponTypeCombatValue) {
        this.WeaponTypeCombatValue = WeaponTypeCombatValue;
    }
    // #endregion

    public Weapon(String Name, String Description, WeaponType Type, WeaponAttribute Attribute,
            Integer WeaponTypeCombatValue, Integer BaseCombatValue) {
        this.Name = Name;
        this.Description = Description;
        this.Type = Type;
        this.Attribute = Attribute;
        this.WeaponTypeCombatValue = WeaponTypeCombatValue;
        this.BaseCombatValue = BaseCombatValue;
    }

    @Override
    public Integer getCombatValue() {
        Integer FinalCombatValue = this.BaseCombatValue + WeaponTypeCombatValue;
        return FinalCombatValue;
    }

    @Override
    public void Upgrade(Integer CombatValue) {
        this.WeaponTypeCombatValue += CombatValue;
    }

}
