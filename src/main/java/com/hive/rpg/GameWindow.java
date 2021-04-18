package com.hive.rpg;

import com.hive.rpg.screens.BaseScreen;
import com.hive.rpg.screens.GameScreen;
import com.hive.rpg.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {

    private final List<BaseScreen> screens = new ArrayList<>();
    public static int currentScreen = 0;
    private boolean isRunning = true;

    GameWindow(int width, int height){
        this.setSize(width, height);
        this.setTitle("Dungeon Crawler");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BaseScreen gameScreen = new GameScreen(width, height);
        MenuScreen menuScreen = new MenuScreen(width, height);
        gameScreen.init();
        this.screens.add(menuScreen);
        this.screens.add(gameScreen);
        this.setVisible(true);
    }

    public void run(){
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        while(isRunning) {
            long startTime = System.nanoTime();
            System.out.println(currentScreen);
            if(currentScreen == -1){
                //Close the game window (if escape is pressed)
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else{
                this.revalidate();
                this.addKeyListener(screens.get(currentScreen).getController());
                screens.get(currentScreen).handleInput();
                screens.get(currentScreen).update();
                screens.get(currentScreen).render();
                this.setContentPane(screens.get(currentScreen));
                this.repaint();
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
}