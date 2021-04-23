package com.hive.rpg.views;

import com.hive.rpg.controllers.*;
import com.hive.rpg.models.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GameScreen extends BaseScreen {

    private static final long serialVersionUID = -2250594602715438858L;
    public GameScreenController controller = new GameScreenController();
    private CombatView view;

    GameScreen(int width, int height){
        super(width, height);
        view = new CombatView(
                (int)Math.floor((double)width/this.getCharWidth())-2,
                (int)Math.floor((double)height/this.getCharHeight())-2
        );
    }

    public void outputCombat(CombatHandler combatHandler){
        resetScreen();
        Player player = GameEngine.player;

        char[][] output = view.createCombatView(
                player.getName(),
                combatHandler.getCurrentEnemy().getName(),
                player.getHealth(),
                combatHandler.getCurrentEnemy().getHealth(),
                player.selected_attack,
                player.getAttacks()
        );
        for (int i = 0; i < this.getWidth()/this.getCharWidth(); i++) {
            for (int j = 0; j < view.height; j++) {
                this.write(output[i][j], i, j, Color.WHITE);
            }
        }
        this.repaint();
    }

    public void outputMap(int MAP_WIDTH, int MAP_HEIGHT, int UI_WIDTH, int UI_HEIGHT, Map map) {
        resetScreen();
        for (int i = 0; i < MAP_WIDTH; i++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                int[] coord = {i, y};
                MapTile tile = map.getTile(coord);
                this.write(tile.getGlyph(), i, y, tile.getColour(), tile.getBackgroundColor());
            }
        }
        for (Entity e : map.entities) {
            writeEntity(e);
        }
        for (Entity e : map.enemies) {
            writeEntity(e);
        }
        writeEntity(GameEngine.player);
        this.repaint();
    }

    public void resetScreen() {
        for (int i = 0; i < this.getWidth()/this.getCharWidth()+1; i++) {
            for (int j = 0; j < this.getHeight()/this.getCharHeight()+1; j++) {
                this.write(" ", i, j);
            }
        }
    }

    public void displayAsciiArt(String filename) {
        filename = "src/resources/"+filename+".txt";
        int w = 0;
        int i = 0;
        char[][] fileRes = new char[24][87];

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.toCharArray().length > w)
                    w = data.toCharArray().length;
                for(int a = 0; a < 87; a++)
                {
                    if(a >= data.toCharArray().length)
                        fileRes[i][a] = ' ';
                    else
                    {
                        fileRes[i][a] = data.toCharArray()[a];
                    }
                }
                i++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int k = 0; k < 86; k++) {
            for (int j = 0; j < 24; j++) {
                this.write(fileRes[j][k], this.getWidth()/this.getCharWidth()/2 -43+k, this.getHeight()/this.getCharHeight()/2-12+j, Color.WHITE);
            }
        }
    }

    public void outputText(String string) {
        resetScreen();
        this.write(string, this.getWidth()/this.getCharWidth()/2 - string.length()/2, this.getHeight()/this.getCharHeight()/2, Color.WHITE);
        this.repaint();
    }

    public void writeEntity(Entity entity) {
        this.write(entity.getGlyph(), entity.getX(), entity.getY(), entity.getColour());
    }

    @Override
    public GameScreenController getController(){
        return this.controller;
    }

    @Override
    public void handleInput() {
        this.controller.processKeyPress();
    }
}
