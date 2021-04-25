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
        if (getCurrentEnemy().getHealth() - attack.attack < 0) {
            getCurrentEnemy().ReceiveAttack(getCurrentEnemy().getHealth());
        } else {
            getCurrentEnemy().ReceiveAttack(attack.attack);
        }
        if (getCurrentEnemy().getHealth() == 0) {
            if (!GameEngine.tutorialCompleted) {
                GameEngine.tutorialCompleted = true;
            }
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
            GameEngine.player.can_attack = true;
            return false;
        }
        return true;

    }

    public Enemy getCurrentEnemy() {
        return enemyStack.peek();
    }
}
