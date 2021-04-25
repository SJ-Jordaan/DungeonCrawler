package com.hive.rpg.controllers;

import com.hive.rpg.views.*;
import com.hive.rpg.models.*;

import java.awt.*;

public class GameEngine {
    public static String username;
    public static Map map;
    public static Player player;
    public static GameWindow window;
    public static CombatHandler combatHandler;
    public static State state;
    private static int UI_WIDTH = 1200;
    private static int UI_HEIGHT = 800;
    private static int MAP_WIDTH;
    private static int MAP_HEIGHT;
    private static int level = 1;
    public static int timer = 0;
    public static int creditSpeed = 0;
    private static int framesPerSecond = 60;
    private static int timePerLoop = 1000000000 / framesPerSecond;
    private static boolean isRunning;

    public GameEngine(String username) {
        GameEngine.username = username;
    }

    public void setup() {
        state = State.MainMenu;
        window = new GameWindow(UI_WIDTH, UI_HEIGHT);
        UI_HEIGHT -= window.getInsets().top;
        UI_WIDTH -= window.getInsets().left;
        combatHandler = new CombatHandler();
        MAP_WIDTH = (int) Math.floor((double) UI_WIDTH / window.GetCurrentScreen().getCharWidth());
        MAP_HEIGHT = (int) Math.floor((double) UI_HEIGHT / window.GetCurrentScreen().getCharHeight());
        isRunning = true;

    }

    public void run() {
        setup();

        while (isRunning) {
            long startTime = System.nanoTime();
            window.GetCurrentScreen().handleInput();
            switch (state) {
            case MainMenu: {
                window.GetCurrentScreen().outputAsciiArt("MainMenu", 115, 54);
                if (window.GetCurrentScreen().getController().last.equals("attack")) {
                    int[] coords = { 0, 0 };
                    GameEngine.player = new Player(GameEngine.username, 40, new Weapon(WeaponType.DBFundamentals),
                            coords);
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case Moving: {
                updateMap();
                GameEngine.player.move(window.GetCurrentScreen().controller, map, combatHandler);
                window.GetCurrentScreen().outputMap(MAP_WIDTH, MAP_HEIGHT, UI_WIDTH, UI_HEIGHT, map);
                if (GameEngine.player.getX() >= MAP_WIDTH - 15 && GameEngine.player.getY() >= MAP_HEIGHT - 10) {
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
            case BattleWon: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt("BattleWon", 115, 54);
                timer += 1;
                if (timer > 60 * 2) {
                    timer = 0;
                    state = State.Moving;
                }
                break;
            }
            case NextLevel: {
                if (level == 4) {
                    state = State.WonGame;
                    continue;
                }
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt("NextLevel", 115, 54);
                timer += 1;
                if (timer > 60 * 2) {
                    timer = 0;
                    level += 1;
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case WonGame: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt("GameWon", 110, 50);
                timer += 1;
                if (timer > 60 * 4) {
                    timer = 0;
                    state = State.Credits;
                }
                break;
            }
            case Credits: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt("Credits", 110, 50);
                window.GetCurrentScreen().outputCredits();
                timer++;
                if (timer % 10 == 0) {
                    creditSpeed++;
                }
                if (timer > 60 * 20) {
                    timer = 0;
                    state = State.MainMenu;
                }
                break;
            }
            case PlayerDied: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt("PlayerDied", 110, 50);
                timer += 1;
                if (timer > 60 * 2) {
                    timer = 0;
                    state = State.MainMenu;
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

        EntityType[] types = { EntityType.SQL_JOINS,
                // EntityType.Skeleton,
                // EntityType.Unicorn,
                // EntityType.Pig,
                // EntityType.Hobgoblin,
                // EntityType.Javathian,
                // EntityType.Dragon,
                // EntityType.Jester,
                // EntityType.Knight
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                .generateRandomMap(level, 10, 10, MAP_WIDTH * MAP_HEIGHT * 5)
                .carveOutRoom(MAP_WIDTH - 15, MAP_HEIGHT - 10, 15, 10, Color.GRAY).populateMap(35, types).placePlayer()
                .build();
    }

    // private static void createTutorialMap() {

    // EntityType[] tutorialEnemy = {
    // EntityType.Pig
    // };
    // map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
    // .populate("wall", EntityType.Wall)
    // .carveOutRoom(1, 1, MAP_WIDTH-2, MAP_HEIGHT-2)
    // .populateMap(25, tutorialEnemy)
    // .build();
    // }
}
