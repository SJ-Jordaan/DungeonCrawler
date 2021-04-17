package com.hive.rpg.controllers;

import com.hive.rpg.models.*;

public class CombatResult {

    private Character Attacker;
    private Character Defender;
    private Weapon AttackerWeapon;
    private Weapon DefenderWeapon;
    private String BattleDetails;
    private String Result;

    // #region Getters & Setters
    /**
     * @return Character return the Attacker
     */
    public Character getAttacker() {
        return Attacker;
    }

    /**
     * @param Attacker the Attacker to set
     */
    public void setAttacker(Character Attacker) {
        this.Attacker = Attacker;
    }

    /**
     * @return Character return the Defender
     */
    public Character getDefender() {
        return Defender;
    }

    /**
     * @param Defender the Defender to set
     */
    public void setDefender(Character Defender) {
        this.Defender = Defender;
    }

    /**
     * @return Weapon return the AttackerWeapon
     */
    public Weapon getAttackerWeapon() {
        return AttackerWeapon;
    }

    /**
     * @param AttackerWeapon the AttackerWeapon to set
     */
    public void setAttackerWeapon(Weapon AttackerWeapon) {
        this.AttackerWeapon = AttackerWeapon;
    }

    /**
     * @return Weapon return the DefenderWeapon
     */
    public Weapon getDefenderWeapon() {
        return DefenderWeapon;
    }

    /**
     * @param DefenderWeapon the DefenderWeapon to set
     */
    public void setDefenderWeapon(Weapon DefenderWeapon) {
        this.DefenderWeapon = DefenderWeapon;
    }

    /**
     * @return String return the BattleDetails
     */
    public String getBattleDetails() {
        return BattleDetails;
    }

    /**
     * @param BattleDetails the BattleDetails to set
     */
    public void setBattleDetails(String BattleDetails) {
        this.BattleDetails = BattleDetails;
    }

    /**
     * @return String return the Result
     */
    public String getResult() {
        return Result;
    }

    /**
     * @param Result the Result to set
     */
    public void setResult(String Result) {
        this.Result = Result;
    }

    // #endregion

    public CombatResult(Character Attacker, Character Defender, Weapon AttackerWeapon, Weapon DefenderWeapon) {
        this.Attacker = Attacker;
        this.Defender = Defender;
        this.AttackerWeapon = AttackerWeapon;
        this.DefenderWeapon = DefenderWeapon;
    }

    public void setBattleDeatils() {

    }

    public void setBattleResult() {

    }

}
