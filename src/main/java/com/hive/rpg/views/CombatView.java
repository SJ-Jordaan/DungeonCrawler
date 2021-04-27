package com.hive.rpg.views;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;

import com.hive.rpg.models.Entity;
import com.hive.rpg.models.EntityType;

import java.io.FileNotFoundException;

public class CombatView {

    private String resourcePath = "src/resources/";
    public int width;
    public int height;
    public int playerHealth;
    public int enemyHealth;
    public int maxHealth;
    private Entity wordlist[][][];
    private int textHeight = 3;
    private int menuCount = 3;
    private int menuHeight = 3;
    private int enemyPaddingLeft = 0;
    private int enemyPaddingTop = 0;
    private int playerPaddingLeft = 0;
    private int playerPaddingTop = 0;
    private int playerM;
    private int enemyM;
    private Entity[][] playerText;
    private Entity[][] enemyText;
    private Entity[][] enemy;
    private Entity[][] player;
    private Entity[][] arrow;
    private int selectedIndex;
    public String[] actions;

    public Entity[][] letterArr;

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public CombatView(int fieldWidth, int fieldHeight) {
        width = fieldWidth;
        height = fieldHeight;
    }

    // index: 0 - player, 1 - enemy, 3 - other
    private Entity[][] readFile(String filename, int maxcolumns, int maxlines, int index) {
        int w = 0;
        int i = 0;
        Entity[][] fileRes = new Entity[maxlines][maxcolumns];

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.toCharArray().length > w)
                    w = data.toCharArray().length;
                for (int a = 0; a < maxcolumns; a++) {
                    if (a >= data.toCharArray().length)
                        fileRes[i][a] = new Entity(' ', Color.WHITE);
                    else {
                        fileRes[i][a] = new Entity(data.toCharArray()[a], Color.WHITE);
                    }
                }
                i++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (index == 0) {
            playerPaddingLeft = (width / 2 - w) / 2;
            playerPaddingTop = (height - 16 - i + 1);
        } else if (index == 1) {
            enemyPaddingLeft = (width / 2 - w) / 2;
            enemyPaddingTop = (height - 16 - i + 1);
        }

        return fileRes;
    }

    public Entity[][] toText(String sentence) {
        char[][] text = new char[textHeight][70];
        int indexCount = 0;
        for (char c : sentence.toCharArray()) {
            int i = 0;
            if (c >= 65 && c <= 90) {
                i = (c - 65) * textHeight;
            } else if (c >= 97 && c <= 122) {
                i = (c - 97) * textHeight + 78;
            } else if (c == ' ') {
                i = 53 * textHeight;
            } else if (c == '>') {
                i = 52 * textHeight;
            }
            int j = 0;
            boolean letterEnd = false;
            do {
                text[0][indexCount] = letterArr[i][j].getGlyph();
                text[1][indexCount] = letterArr[i + 1][j].getGlyph();
                text[2][indexCount] = letterArr[i + 2][j].getGlyph();
                if (letterArr[i][j].getGlyph() == ' ' && letterArr[i + 1][j].getGlyph() == ' '
                        && letterArr[i + 2][j].getGlyph() == ' ')
                    letterEnd = true;
                else if (j == 5)
                    letterEnd = true;
                else {
                    j++;
                    indexCount++;
                }
            } while (!letterEnd);
            // text[0][indexCount] = new Entity(' ', Color.WHITE);
            // text[1][indexCount] = new Entity(' ', Color.WHITE);
            // text[2][indexCount] = new Entity(' ', Color.WHITE);
            text[0][indexCount] = (' ');
            text[1][indexCount] = (' ');
            text[2][indexCount] = (' ');
            indexCount++;
        }

        Entity[][] temp = new Entity[textHeight][70];

        for (int i = 0; i < textHeight; i++) {
            for (int j = 0; j < 70; j++) {
                temp[i][j] = new Entity(text[i][j], Color.WHITE);
            }
        }

        return temp;
    }

    public Entity[][] createCombatView(String playerName, String enemyName, int pHealth, int eHealth, int index,
            String[] act, int playerMove, int enemyMove) {
        playerM = playerMove;
        enemyM = enemyMove;
        maxHealth = 40;
        playerHealth = pHealth;
        enemyHealth = eHealth;
        actions = act.clone();
        selectedIndex = index;
        letterArr = readFile(resourcePath + "Letters.txt", 6, 163, 2).clone();
        arrow = toText(">");
        playerText = toText(playerName);
        enemyText = toText(enemyName);
        wordlist = new Entity[menuHeight * menuCount][textHeight][(int) ((width) / menuCount)];
        for (int i = 0; i < actions.length; i++)
            wordlist[i] = toText(actions[i]);
        player = readFile(resourcePath + "Player.txt", 100, 50, 0);
        enemy = readFile(resourcePath + enemyName + ".txt", 110, 50, 1);

        return printCW();
    }

    public Entity[][] createCombatView(String playerName, String enemyName, int pHealth, int eHealth, int index,
            String[] act) {
        return createCombatView(playerName, enemyName, pHealth, eHealth, index, act, 0, 0);
    }

    public Entity[][] printCW() {
        Entity[][] combatView = new Entity[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height - 1) {
                    combatView[x][y] = new Entity(EntityType.Border);
                } else if (x == 0 || x == width - 1) {
                    combatView[x][y] = new Entity(EntityType.Border);
                } else if ((y == 4 || y == 6)
                        && ((x >= 4 && x <= maxHealth + 6) || ((x >= width - maxHealth - 7 && x <= width - 5)))) {
                    combatView[x][y] = new Entity(EntityType.HLine);
                } else if (y >= 1 && y <= textHeight) {
                    if (x >= 4 && x < playerText[0].length + 4 && playerText[y - 1][x - 4].getGlyph() != 0) {
                        combatView[x][y] = (/* TEXT_BLUE + */playerText[y - 1][x - 4]/* + TEXT_RESET */);
                    } else if (x >= width - maxHealth - 7 && x < enemyText[0].length + width - maxHealth - 7
                            && enemyText[y - 1][x - (width - maxHealth - 7)].getGlyph() != 0) {
                        combatView[x][y] = (/* TEXT_GREEN + */enemyText[y - 1][x
                                - (width - maxHealth - 7)]/* + TEXT_RESET */);
                    } else {
                        combatView[x][y] = new Entity(' ', Color.WHITE);
                    }
                } else if (y == 5 && x >= 4 && x <= width - 5) {
                    if (x == 4 || x == maxHealth + 6 || x == width - 5 || x == width - maxHealth - 7)
                        combatView[x][y] = new Entity(EntityType.VLine);
                    else if (x <= playerHealth + 5)
                        combatView[x][y] = new Entity(EntityType.HP);
                    else if (x >= width - 6 - enemyHealth) {
                        combatView[x][y] = new Entity(EntityType.HP);
                    } else
                        combatView[x][y] = new Entity(' ', Color.WHITE);

                } else if (y == height - (menuCount * (menuHeight + 2))) {
                    combatView[x][y] = new Entity(EntityType.Border);
                } else if (y >= height - 15 && x % (width / menuCount) == 0) // (x == width/menuCount || x ==
                                                                             // 2*width/menuCount))
                {
                    combatView[x][y] = new Entity(EntityType.Border);
                } else if (y > height - 14 && y < height - 2) {// && (x < width/3) && x >= 5
                                                               // char[][] word = toText("Testing");
                    int ygroup = (y - height + 13) / (menuHeight + 1);
                    int xgroup = x / (width / menuCount);
                    if (ygroup + xgroup * 3 == selectedIndex && x >= xgroup * (width / menuCount) + 2
                            && x <= xgroup * (width / menuCount) + 4 && y - height + 13 - 4 * ygroup < 3) {
                        if (arrow[y - height + 13 - 4 * ygroup][x - xgroup * (width / menuCount) - 2].getGlyph() != 0)
                            combatView[x][y] = (arrow[y - height + 13 - 4 * ygroup][x - xgroup * (width / menuCount)
                                    - 2]);
                        else
                            combatView[x][y] = new Entity(' ', Color.WHITE);
                    }
                    // combatView[x][y] = ( ygroup+menuCount*xgroup);
                    else if (x - 5 - xgroup * (width / menuCount) >= 0
                            && (y - height + 13 - (textHeight + 1) * ygroup) < 3
                            && wordlist[ygroup + menuCount * xgroup][y - height + 13 - 4 * ygroup][x - 5
                                    - xgroup * (width / menuCount)] != null)
                        combatView[x][y] = (wordlist[ygroup + menuCount * xgroup][y - height + 13 - 4 * ygroup][x - 5
                                - xgroup * (width / menuCount)]);
                    else
                        combatView[x][y] = new Entity(' ', Color.WHITE);
                } else if (x >= width / 2 && x < width && y >= enemyPaddingTop && y <= 34) {

                    if (x < enemy[y - enemyPaddingTop].length + width && x > width / 2 + enemyPaddingLeft + enemyM) {
                        combatView[x][y] = (enemy[y - enemyPaddingTop][x - width / 2 - enemyPaddingLeft - enemyM]);
                    } else
                        combatView[x][y] = new Entity(' ', Color.WHITE);
                } else if (x >= playerPaddingLeft + playerM && x < width / 2 && y >= playerPaddingTop && y <= 34) {
                    if (x < player[y - playerPaddingTop].length
                            && player[y - playerPaddingTop][x] != null) {
                        combatView[x][y] = (player[y - playerPaddingTop][x - playerPaddingLeft - playerM]);
                    } else
                        combatView[x][y] = new Entity(' ', Color.WHITE);
                } else {
                    combatView[x][y] = new Entity(' ', Color.WHITE);

                }

            }
            combatView[width - 1][y] = new Entity(' ', Color.WHITE);
        }
        return combatView;
    }

}