package com.hive.rpg.views;

import com.hive.rpg.controllers.*;
import com.hive.rpg.models.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameScreen extends BaseScreen {

    private static final long serialVersionUID = -2250594602715438858L;
    String resourcePath = "src/resources/";
    public GameScreenController controller = new GameScreenController();
    private CombatView view;

    GameScreen(int width, int height) {
        super(width, height);
        view = new CombatView((int) Math.floor((double) width / this.getCharWidth()) - 2,
                (int) Math.floor((double) height / this.getCharHeight()) - 2);
    }

    public void outputCombat(CombatHandler combatHandler) {
        resetScreen();

        char[][] output = view.createCombatView(GameEngine.player.getName(), combatHandler.getCurrentEnemy().getName(),
                GameEngine.player.getHealth(), combatHandler.getCurrentEnemy().getHealth(),
                GameEngine.player.selected_attack, GameEngine.player.getAttacks());
        for (int i = 0; i < this.getWidth() / this.getCharWidth(); i++) {
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
                int[] coord = { i, y };
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
        for (int i = 0; i < this.getWidth() / this.getCharWidth() + 1; i++) {
            for (int j = 0; j < this.getHeight() / this.getCharHeight() + 1; j++) {
                this.write(" ", i, j);
            }
        }
    }

    public void resetPartial(int endY) {
        for (int i = 0; i < this.getWidth() / this.getCharWidth() + 1; i++) {
            for (int j = this.getHeight() / this.getCharHeight(); j >= endY / this.getCharHeight(); j--) {
                this.write(" ", i, j);
            }
        }
    }

    private char[][] readFile(String filename, int maxcolumns, int maxlines) {
        int w = 0;
        int i = 0;
        char[][] fileRes = new char[maxlines][maxcolumns];

        try {
            File myObj = new File(resourcePath + filename + ".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.toCharArray().length > w)
                    w = data.toCharArray().length;
                for (int a = 0; a < maxcolumns; a++) {
                    if (a >= data.toCharArray().length)
                        fileRes[i][a] = ' ';
                    else {
                        fileRes[i][a] = data.toCharArray()[a];
                    }
                }
                i++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileRes;
    }

    public void outputAsciiArt(String filename, int maxcolumns, int maxlines) {
        resetScreen();
        char[][] fileRes = readFile(filename, maxcolumns, maxlines);
        for (int k = 0; k < maxcolumns; k++) {
            for (int j = 0; j < maxlines; j++) {
                this.write(fileRes[j][k], this.getWidth() / this.getCharWidth() / 2 - maxcolumns / 2 + k, j,
                        Color.WHITE);
            }
        }
        this.repaint();
    }

    public void outputAsciiCredits(String filename, int maxcolumns, int maxlines) {
        resetPartial(8 * this.getCharHeight());
        char[][] fileRes = readFile(filename, maxcolumns, maxlines);

        int numberOfCredits = maxlines;
        int lineToStart = 0;
        if (GameEngine.creditSpeed < maxlines) {
            numberOfCredits = GameEngine.creditSpeed;
        }

        if (getHeight() / this.getCharHeight() - GameEngine.creditSpeed + maxlines - 8 < maxlines) {
            GameEngine.creditSpeed--;
        }
        for (int k = 0; k < maxcolumns; k++) {
            for (int j = lineToStart; j < numberOfCredits; j++) {
                this.write(fileRes[j][k], this.getWidth() / this.getCharWidth() / 2 - maxcolumns / 2 + k,
                        this.getHeight() / this.getCharHeight() + j - GameEngine.creditSpeed, Color.WHITE);
            }
        }
        this.repaint();

    }

    public void outputCredits() {

        outputAsciiCredits("CreditList", 85, 15);
    }

    public void writeEntity(Entity entity) {
        this.write(entity.getGlyph(), entity.getX(), entity.getY(), entity.getColour());
    }

    @Override
    public GameScreenController getController() {
        return this.controller;
    }

    @Override
    public void handleInput() {
        this.controller.processKeyPress();
    }
}
