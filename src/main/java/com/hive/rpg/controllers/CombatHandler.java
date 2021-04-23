package com.hive.rpg.controllers;

import com.hive.rpg.models.*;

import java.util.Stack;

public class CombatHandler {
    private Stack<Enemy> enemyStack;
    public CombatHandler() {
        enemyStack = new Stack<Enemy>();
    }

    public void pushEnemy(Enemy enemy) {
        enemyStack.push(enemy);
    }

    public void selectAttack(Attack attack) {
        getCurrentEnemy().ReceiveAttack(attack.attack);
        if (getCurrentEnemy().getHealth() <= 0) {
            enemyStack.pop();
        }
    }

    public boolean processCombat() {
        if (GameEngine.player.can_attack) {
            GameEngine.player.combat(this);
        } else {
            GameEngine.player.ReceiveAttack(getCurrentEnemy().getWeapon().getAttacks().get(0).attack);
            if (GameEngine.player.getHealth() <= 0) {
                GameEngine.state = State.PlayerDied;
            }
            GameEngine.player.can_attack = true;
        }
        if (enemyStack.empty()) {
            return false;
        }
        return true;

    }

    public Enemy getCurrentEnemy() {
        return enemyStack.peek();
    }
}
