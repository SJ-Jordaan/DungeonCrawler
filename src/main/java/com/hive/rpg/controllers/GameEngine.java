package com.hive.rpg.controllers;

import com.hive.rpg.views.*;
import com.hive.rpg.models.*;

import java.awt.*;

public class GameEngine {
    public static Map map;
    public static Player player;
    public static GameWindow window;
    private static int UI_WIDTH = 1200;
    private static int UI_HEIGHT = 800;
    private static int MAP_WIDTH;
    private static int MAP_HEIGHT;
    private static int level = 1;

    public static State state = State.Moving;

    public GameEngine() {

    }

    public void run(){
        boolean isRunning;
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        window = new GameWindow(UI_WIDTH,UI_HEIGHT);
        UI_HEIGHT-=window.getInsets().top;
        UI_WIDTH-=window.getInsets().left;
        CombatHandler combatHandler = new CombatHandler();
        MAP_WIDTH = (int)Math.floor((double)UI_WIDTH/window.GetCurrentScreen().getCharWidth());
        MAP_HEIGHT = (int)Math.floor((double)UI_HEIGHT/window.GetCurrentScreen().getCharHeight());
        state = State.WonGame;
        float timer = 0;
        isRunning = true;
            while(isRunning) {
            long startTime = System.nanoTime();
            window.GetCurrentScreen().handleInput();
            switch (state) {
                case Moving: {
                    updateMap();
                    player.move(window.GetCurrentScreen().controller, map, combatHandler);
                    window.GetCurrentScreen().outputMap(MAP_WIDTH, MAP_HEIGHT, UI_WIDTH, UI_HEIGHT, map);
                    if (player.getX() > MAP_WIDTH-15 && player.getY() > MAP_HEIGHT-10) {
                        state = State.NextLevel;
                    }
                    break;
                }
                case Combat: {
                    if (combatHandler.processCombat()) {
                        window.GetCurrentScreen().outputCombat(combatHandler);
                    } else {
                        state = State.BattleWon;
                    }
                    break;
                }
                case NextLevel: {
                    if (level == 4) {state = State.WonGame; continue;}
                    if (timer == 0) window.GetCurrentScreen().outputText("Next Level...");
                    timer += 1;
                    if (timer > 60 * 2) {
                        timer = 0;
                        level+=1;
                        createMap();
                        state = State.Moving;
                    }
                    break;
                }
                case WonGame: {
                    if (timer == 0) window.GetCurrentScreen().displayAsciiArt("achievement");
                    timer += 1;
                    if (timer > 60 * 4) {
                        timer = 0;
                        level+=1;
                        createMap();
                        state = State.MainMenu;
                    }
                    break;
                }
                case MainMenu: {
                    window.GetCurrentScreen().outputText("Main Menu: Press space to start...");
                    if (window.GetCurrentScreen().getController().last.equals("attack")) {
                        int[] coords = {0,0};
                        GameEngine.player = new Player(40, new Weapon(), coords);
                        state = State.Moving;
                        createMap();
                    }
                    break;
                }
                case PlayerDied: {
                    if (timer == 0) window.GetCurrentScreen().outputText("Player died...Game Over");
                    timer += 1;
                    if (timer > 60 * 2) {
                        timer = 0;
                        //generate new map
                        state = State.MainMenu;
                    }
                    break;
                }
                case BattleWon: {
                    if (timer == 0) window.GetCurrentScreen().outputText("Battle Won...");
                    timer += 1;
                    if (timer > 60 * 2) {
                        timer = 0;
                        //generate new map
                        state = State.Moving;
                    }
                    break;
                }
                default: {
                    break;
                }
            }
            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }

    public void updateMap() {
        map.clearBodies();
        map.moveEnemies();
    }

    private static void createMap() {

        EntityType[] types = {
                EntityType.Bull,
                EntityType.Skeleton,
                EntityType.Unicorn,
                EntityType.Pig,
                EntityType.Hobgoblin,
                EntityType.Javathian,
                EntityType.Dragon,
                EntityType.Jester,
                EntityType.Knight
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
                .populate("wall", EntityType.Wall)
                .generateRandomMap(level, 10, 10, MAP_WIDTH*MAP_HEIGHT*5)
                .carveOutRoom(MAP_WIDTH-15, MAP_HEIGHT-10, 15, 10, Color.GRAY)
                .populateMap(35, types)
                .placePlayer()
                .build();
    }

    // private static void createTutorialMap() {

    //     EntityType[] tutorialEnemy = {
    //             EntityType.Pig
    //     };
    //     map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
    //             .populate("wall", EntityType.Wall)
    //             .carveOutRoom(1, 1, MAP_WIDTH-2, MAP_HEIGHT-2)
    //             .populateMap(25, tutorialEnemy)
    //             .build();
    // }
}
