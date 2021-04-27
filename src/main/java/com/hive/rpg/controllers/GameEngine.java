package com.hive.rpg.controllers;

import com.hive.rpg.views.*;

import com.hive.rpg.Constants;
import com.hive.rpg.models.*;

import java.awt.*;

public class GameEngine {
    public static String username;
    public static Map map;
    public static Player player;
    public static GameWindow window;
    public static CombatHandler combatHandler;
    public static State state;
    public static boolean tutorialCompleted = false;
    public static boolean bossFight = false;
    public static boolean luckyDefeated = false;
    public static boolean rudolphDefeated = false;
    public static boolean tonyDefeated = false;
    private static int UI_WIDTH = 1200;
    private static int UI_HEIGHT = 800;
    private static int MAP_WIDTH;
    private static int MAP_HEIGHT;
    private static int level = 0;
    public static int timer = 0;
    public static int creditSpeed = 0;
    private static int framesPerSecond = 60;
    private static int timePerLoop = 1000000000 / framesPerSecond;
    private static int loadingTimes = framesPerSecond * 5;
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
        int[] coords = { 0, 0 };
        GameEngine.player = new Player(username, 40, new Weapon(WeaponType.TutorialWeapon), coords);

    }

    public void run() {
        setup();

        while (isRunning) {
            long startTime = System.nanoTime();
            window.GetCurrentScreen().handleInput();
            switch (state) {
            case MainMenu: {
                window.GetCurrentScreen().outputAsciiArt(Constants.MainMenu.filename, Constants.MainMenu.columns,
                        Constants.MainMenu.rows);

                if (window.GetCurrentScreen().getController().last.equals("attack")) {
                    createMap();
                    state = State.Tutorial;
                }
                break;
            }
            case Tutorial: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt(Constants.Level0.filename, Constants.Level0.columns,
                            Constants.Level0.rows);
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    state = State.Moving;
                }
                break;
            }
            case Moving: {
                updateMap();
                GameEngine.player.move(window.GetCurrentScreen().controller, map, combatHandler);
                window.GetCurrentScreen().outputMap(MAP_WIDTH, MAP_HEIGHT, UI_WIDTH, UI_HEIGHT, map);

                if (tutorialCompleted && level == 0) {
                    state = State.Level1;
                } else if (GameEngine.player.getX() >= MAP_WIDTH - 15 && GameEngine.player.getY() >= MAP_HEIGHT - 10
                        && !bossFight && tutorialCompleted) {
                    switch (level) {
                    case 1:
                        state = State.Level2;
                        break;
                    case 2:
                        state = State.Level3;
                        break;
                    case 3:
                        state = State.BossFight;
                        break;
                    default:
                        state = State.WonGame;
                        break;
                    }
                }
                if (luckyDefeated && rudolphDefeated && tonyDefeated) {
                    state = State.WonGame;
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
                    window.GetCurrentScreen().outputAsciiArt(Constants.BattleWon.filename, Constants.BattleWon.columns,
                            Constants.BattleWon.rows);
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    state = State.Moving;
                }
                break;
            }
            case Level1: {
                if (timer == 0) {
                    window.GetCurrentScreen().outputAsciiArt(Constants.Level1.filename, Constants.Level1.columns,
                            Constants.Level1.rows);
                }
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    level += 1;
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case Level2: {
                if (timer == 0) {
                    window.GetCurrentScreen().outputAsciiArt(Constants.Level2.filename, Constants.Level2.columns,
                            Constants.Level2.rows);
                }
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    level += 1;
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case Level3: {
                if (timer == 0) {
                    window.GetCurrentScreen().outputAsciiArt(Constants.Level3.filename, Constants.Level3.columns,
                            Constants.Level3.rows);
                }
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    level += 1;
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case BossFight: {
                if (timer == 0) {
                    window.GetCurrentScreen().outputAsciiArt(Constants.BossFight.filename, Constants.BossFight.columns,
                            Constants.BossFight.rows);
                }
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    level += 1;
                    createMap();
                    state = State.Moving;
                }
                break;
            }
            case WonGame: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt(Constants.GameWon.filename, Constants.GameWon.columns,
                            Constants.GameWon.rows);
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    state = State.Credits;
                }
                break;
            }
            case Credits: {
                if (timer == 0)
                    window.GetCurrentScreen().outputAsciiArt(Constants.Credits.filename, Constants.Credits.columns,
                            Constants.Credits.rows);
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
                    window.GetCurrentScreen().outputAsciiArt(Constants.PlayerDied.filename,
                            Constants.PlayerDied.columns, Constants.PlayerDied.rows);
                timer += 1;
                if (timer > loadingTimes) {
                    timer = 0;
                    tonyDefeated = false;
                    luckyDefeated = false;
                    rudolphDefeated = false;
                    tutorialCompleted = false;
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
        switch (level) {
        case 0:
            EntityType[] tutorialEnemy = { EntityType.YourMom };
            GameEngine.player.setWeapons(new Weapon(WeaponType.TutorialWeapon));
            map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                    .carveOutRoom(1, 1, MAP_WIDTH - 2, MAP_HEIGHT - 2, Color.BLACK).populateMap(1, tutorialEnemy)
                    .placePlayer().build();
            break;
        case 1:
            GameEngine.player.setHealth(40);
            GameEngine.player.setWeapons(new Weapon(WeaponType.DBFundamentals));
            EntityType[] Level1Enemies = { EntityType.SQL_JOINS };
            map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                    .generateRandomMap(level, 10, 10, MAP_WIDTH * MAP_HEIGHT * 5)
                    .carveOutRoom(MAP_WIDTH - 15, MAP_HEIGHT - 10, 15, 10, Color.GRAY).populateMap(20, Level1Enemies)
                    .placePlayer().build();
            break;
        case 2:
            GameEngine.player.setHealth(40);
            EntityType[] Level2Enemies = { EntityType.Javathian };
            GameEngine.player.setWeapons(new Weapon(WeaponType.Java));
            map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                    .generateRandomMap(level, 10, 10, MAP_WIDTH * MAP_HEIGHT * 5)
                    .carveOutRoom(MAP_WIDTH - 15, MAP_HEIGHT - 10, 15, 10, Color.GRAY).populateMap(30, Level2Enemies)
                    .placePlayer().build();
            break;
        case 3:
            GameEngine.player.setHealth(40);
            EntityType[] Level3Enemies = { EntityType.Knight, EntityType.Dragon, EntityType.Pig };
            GameEngine.player.setWeapons(new Weapon(WeaponType.CSharp));
            map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                    .generateRandomMap(level, 10, 10, MAP_WIDTH * MAP_HEIGHT * 5)
                    .carveOutRoom(MAP_WIDTH - 15, MAP_HEIGHT - 10, 15, 10, Color.GRAY).populateMap(40, Level3Enemies)
                    .placePlayer().build();
            break;
        case 4:
            GameEngine.player.setHealth(40);
            EntityType[] bosses = { EntityType.Lucky, EntityType.Tony, EntityType.Rudolph };
            map = new MapFactory(MAP_WIDTH, MAP_HEIGHT).populate("wall", EntityType.Wall)
                    .carveOutRoom(1, 1, MAP_WIDTH - 2, MAP_HEIGHT - 2, Color.BLACK).populateMapWithBosses(bosses)
                    .placePlayer().build();
            break;
        default:
            break;
        }
    }
}
