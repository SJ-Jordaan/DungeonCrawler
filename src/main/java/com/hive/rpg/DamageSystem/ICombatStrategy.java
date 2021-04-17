package com.hive.rpg.DamageSystem;

import com.hive.rpg.Weapons.Weapon;

public interface ICombatStrategy {

    public CombatResult DoCombat(CombatResult Result);
    // public Integer DamageSustained(Weapon PlayerWeapon);
}
