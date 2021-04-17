package Players;

import java.util.ArrayList;

import com.hive.rpg.Weapons.Weapon;

public class Player extends Characters implements ICharacterActions {

    public Player(String Name, Integer Health, ArrayList<Weapon> Weapons) {
        super(Name, Health, Weapons);
    }

    @Override
    public void Attack(Character Opponent, Character Player, Weapon Weapon) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean EquipWeapon(Weapon Weapon) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean DropWeapon(Weapon Weapon) {
        // TODO Auto-generated method stub
        return false;
    }

}
