package com.hive.rpg.Weapons;

public class WeaponAttribute {
    private AttributeClassifications Classification;
    private Integer CombatValue;
    private AttributeClassifications Affinity;

    // #region Getters & Setters
    /**
     * @return AttributeClassifications return the Classification
     */
    public AttributeClassifications getClassification() {
        return Classification;
    }

    /**
     * @param Classification the Classification to set
     */
    private void setClassification(AttributeClassifications Classification) {
        this.Classification = Classification;
    }

    /**
     * @return Integer return the CombatValue
     */
    public Integer getCombatValue() {
        return CombatValue;
    }

    /**
     * @param CombatValue the CombatValue to set
     */
    private void setCombatValue(Integer CombatValue) {
        this.CombatValue = CombatValue;
    }

    /**
     * @return AttributeClassifications return the Affinity
     */
    public AttributeClassifications getAffinity() {
        return Affinity;
    }

    /**
     * @param Affinity the Affinity to set
     */
    private void setAffinity(AttributeClassifications Affinity) {
        this.Affinity = Affinity;
    }

    // #endregion

    public WeaponAttribute(AttributeClassifications Classification, Integer CombatValue,
            AttributeClassifications Affinity) {
        this.Classification = Classification;
        this.CombatValue = CombatValue;
        this.Affinity = Affinity;
    }
}
