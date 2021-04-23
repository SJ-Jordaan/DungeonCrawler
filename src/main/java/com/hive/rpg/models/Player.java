package com.hive.rpg.models;

import java.util.ArrayList;

import com.hive.rpg.controllers.*;

public class Player extends Characters implements ICharacterActions {
    public int selected_attack = 0;
    public boolean can_attack = false;

    public Player(String name, Integer Health, Weapon weapon, int[] coords) {
        super(name, Health, weapon, EntityType.Player, coords);
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

    public String[] getAttacks() {
        ArrayList<String> attacks = new ArrayList<>();
        for (int i = 0; i < this.getWeapon().getAttacks().size(); i++) {
            attacks.add(this.getWeapon().getAttacks().get(i).name);
        }
        return attacks.toArray(new String[0]);
    }

    private void switchAttack(int change) {
        int amount = this.getWeapon().getAttacks().size();
        int newAttack = (this.selected_attack + change) % amount;
        this.selected_attack = newAttack < 0 ? amount + newAttack : newAttack;
    }

    public void combat(CombatHandler combatHandler) {
        switch (GameEngine.window.GetCurrentScreen().controller.last.charAt(0)) {
        case 'u':
            switchAttack(-1);
            break;
        case 'd':
            switchAttack(1);
            break;
        case 'l':
            switchAttack(-3);
            break;
        case 'r':
            switchAttack(3);
            break;
        case 'a':
            combatHandler.selectAttack(this.getWeapon().getAttacks().get(this.selected_attack));
            this.can_attack = false;
            break;
        default:
            break;
        }
    }

    public void move(GameScreenController inputHandler, Map map, CombatHandler combatHandler) {
        int[] c = this.getCoord().clone();
        switch (inputHandler.last.charAt(0)) {
        case 'u':
            c[1] -= 1;
            break;
        case 'd':
            c[1] += 1;
            break;
        case 'l':
            c[0] -= 1;
            break;
        case 'r':
            c[0] += 1;
            break;
        default:
            break;
        }
        int[][] surrounding_coords = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
                { 1, 0 } };
        for (int i = 0; i < 8; i++) {
            int[] coords = this.getCoord().clone();
            coords[0] += surrounding_coords[i][0];
            coords[1] += surrounding_coords[i][1];
            Enemy enemy = map.getEnemyAt(coords);
            if (enemy != null) {
                combatHandler.pushEnemy(enemy);
                GameEngine.state = State.Combat;
            }
        }
        if (map.isPathableTerrain(c)) {
            this.setCoord(c);
        }
    }

}
