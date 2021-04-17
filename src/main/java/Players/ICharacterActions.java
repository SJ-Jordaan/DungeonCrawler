package Players;

import com.hive.rpg.Weapons.Weapon;

public interface ICharacterActions {
    public void Attack(Character Opponent, Character Player, Weapon Weapon);

    public boolean EquipWeapon(Weapon Weapon);

    public boolean DropWeapon(Weapon Weapon);
    // public abstract void Defend(Character Opponent, Character Player, Weapon
    // Weapon);
}
