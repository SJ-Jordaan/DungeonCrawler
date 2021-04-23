package com.hive.rpg;

import asciiPanel.AsciiPanel;
import com.hive.rpg.Players.Player;
import com.hive.rpg.map.Map;
import com.hive.rpg.map.MapFactory;
import com.hive.rpg.models.EntityType;
import com.hive.rpg.models.State;

public class GameEngine {
    public static Map map;
    public static Player player;
    public static GameWindow window;
    public static SoundManager soundManager;
    private static int UI_WIDTH = 1200;
    private static int UI_HEIGHT = 800;
    private static int MAP_WIDTH;
    private static int MAP_HEIGHT;
    private static int level = 1;

    public static State state = State.Moving;

    GameEngine() {

    }

    public void run(){
        boolean isRunning;
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        window = new GameWindow(UI_WIDTH,UI_HEIGHT);
        UI_HEIGHT-=window.getInsets().top;
        UI_WIDTH-=window.getInsets().left;
        CombatHandler combatHandler = new CombatHandler();
        MAP_WIDTH = UI_WIDTH/window.GetCurrentScreen().getCharWidth();
        //UI_WIDTH = MAP_WIDTH*window.GetCurrentScreen().getCharWidth();
        MAP_HEIGHT = UI_HEIGHT/window.GetCurrentScreen().getCharHeight();
        //UI_HEIGHT = MAP_HEIGHT*window.GetCurrentScreen().getCharHeight();
        //window.setSize(UI_WIDTH, UI_HEIGHT);
        //window.setResizable(false);
        createMap();
        soundManager = new SoundManager();
        isRunning = true;
            while(isRunning) {
            long startTime = System.nanoTime();
            window.GetCurrentScreen().handleInput();
            map.clearBodies();
            if (state == State.Moving) {
                player.move(window.GetCurrentScreen().controller, map, combatHandler);
                window.GetCurrentScreen().outputMap(MAP_WIDTH, MAP_HEIGHT, map);
            } else if (state == State.Combat) {
                if (combatHandler.processCombat()) {
                    window.GetCurrentScreen().outputCombat(combatHandler);
                } else {
                    state = State.Moving;
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

    private static void createMap() {

        EntityType[] types = {
                EntityType.Bat,
                EntityType.Farmer
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
                .populate("wall", EntityType.Wall)
                .generateRandomMap(2, 10, 10, MAP_WIDTH*MAP_HEIGHT*(4-level))
                .populateMap(5, types)
                .placePlayer()
                .build();
    }

    private static void createTutorialMap() {

        EntityType[] tutorialEnemy = {
                EntityType.Pig
        };
        map = new MapFactory(MAP_WIDTH, MAP_HEIGHT)
                .populate("wall", EntityType.Wall)
                .carveOutRoom(1, 1, MAP_WIDTH-2, MAP_HEIGHT-2)
                .populateMap(25, tutorialEnemy)
                .build();
    }
}
