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
            } else if (!GameEngine.luckyDefeated && getCurrentEnemy().getName().equals("Lucky")) {
                GameEngine.luckyDefeated = true;
                GameEngine.player.setHealth(40);
            } else if (!GameEngine.rudolphDefeated && getCurrentEnemy().getName().equals("Rudolph")) {
                GameEngine.rudolphDefeated = true;
                GameEngine.player.setHealth(40);
            } else if (!GameEngine.tonyDefeated && getCurrentEnemy().getName().equals("Tony")) {
                GameEngine.tonyDefeated = true;
                GameEngine.player.setHealth(40);
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
