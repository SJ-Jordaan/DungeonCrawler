package com.hive.rpg.views;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CombatView {

    private String resourcePath = "src/resources/";
    public int width = 220;
    public int height = 50;
    public int playerHealth;
    public int enemyHealth;
    public int maxHealth;
    private char wordlist[][][];
    private  int textHeight = 3;
    private  int menuCount = 3;
    private  int menuHeight = 3;
    private  int xBuffer = 3;
    char[][] playerText;
    char[][] enemyText;
    char[][] enemy;
    char[][] player;
    char[][] arrow;
    int selectedIndex;
    public String[] actions;
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public char[][] letterArr;
    public int width() { return width; }
    public int height() { return height; }

    public CombatView(String playerFile, String enemyFile, String playerName, String enemyName, int pHealth, int eHealth, int index, String[] act)
    {

        playerHealth = pHealth;
        enemyHealth = eHealth;
        actions = act.clone();
        selectedIndex = index;
        player = new char[20][20];
        enemy = new char[60][60];
        letterArr = readFile(resourcePath+"Letters.txt", 6, 171);
        arrow = toText(">");
        playerText = toText(playerName);
        enemyText = toText(enemyName);
        wordlist = new char[menuHeight*menuCount][textHeight][(int)((width)/menuCount - xBuffer)];
        for(int i = 0; i < actions.length; i++)
            wordlist[i] = toText(actions[i]);
        player = readFile(resourcePath+playerFile+".txt", 110, 70);
        enemy = readFile(resourcePath+enemyFile+".txt", 110, 70);
    }

    private char[][] readFile(String filename, int maxcolumns, int maxlines)
    {
        char[][] fileRes = new char[maxlines][maxcolumns];

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int a = 0; a < maxcolumns; a++)
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
        return fileRes;
    }

    public char[][] toText(String sentence)
    {
        char[][] text = new char[textHeight][70];
        int indexCount = 0;
        for(char c : sentence.toCharArray())
        {
            int i = 0;
            if(c >= 65 && c <= 90)
            {
                i = (c-65) * textHeight;
            }
            else if(c >= 97 && c <= 122)
            {
                i = (c-97) * textHeight + 78;
            }
            else if(c == ' ')
            {
                i=53*textHeight;
            }
            else if(c == '>')
            {
                i=52*textHeight;
            }
            int j = 0;
            boolean letterEnd = false;
            do
            {
                text[0][indexCount] = letterArr[i][j];
                text[1][indexCount] = letterArr[i+1][j];
                text[2][indexCount] = letterArr[i+2][j];
                j++;
                indexCount++;
                if(letterArr[i][j] == ' ' && letterArr[i+1][j] == ' ' && letterArr[i+2][j] == ' ')
                    letterEnd = true;
            }while(!letterEnd);
            text[0][indexCount] = ' ';
            text[1][indexCount] = ' ';
            text[2][indexCount] = ' ';
            indexCount++;
        }
        return text;
    }

    public char[][] createCombatView()
    {
        char[][] combatView = new char[width][height];
        maxHealth = 100;
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(y == 0 || y == height - 1)
                {
                    combatView[x][y] = ('#');
                }
                else if(x == 0 || x == width - 1)
                {
                    combatView[x][y] = ('#');
                }
                else if((y == 4 || y == 6) && ((x >= 4 && x <= maxHealth+6)||((x >= width - maxHealth - 7 && x <= width - 5))))
                {
                    combatView[x][y] = ('=');
                }
                else if(y >= 1 && y <= textHeight)
                {
                    if(x>=4 && x < playerText[0].length + 4 && playerText[y-1][x-4] != 0)
                    {
                        combatView[x][y] = (/*TEXT_BLUE + */playerText[y-1][x-4]/* + TEXT_RESET*/);
                    }
                    else if(x>=width - maxHealth - 7 && x < enemyText[0].length + width - maxHealth - 7 && enemyText[y-1][x-113] != 0)
                    {
                        combatView[x][y] = (/*TEXT_GREEN + */enemyText[y-1][x-113]/* + TEXT_RESET*/);
                    }
                    else
                    {
                        combatView[x][y] = (' ');
                    }
                }
                else if(y == 5 && x >= 4 && x <= width - 5)
                {
                    if(x==4 || x == maxHealth+6 || x == width - 5 || x==width - maxHealth - 7)
                        combatView[x][y] = ('|');
                    else if(x<=playerHealth+5)
                        combatView[x][y] = (/*TEXT_RED + */'X'/* + TEXT_RESET*/);
                    else if(x>=width - 6 - enemyHealth)
                    {
                        combatView[x][y] = (/*TEXT_RED +*/ 'X'/* + TEXT_RESET*/);
                    }
                    else
                        combatView[x][y] = (' ');

                }
                else if(y == height - (menuCount*(menuHeight+2)))
                {
                    combatView[x][y] = ('#');
                }
                else if(y > height - 15 && x%(width/menuCount) == 0) //(x == width/menuCount || x == 2*width/menuCount))
                {
                    combatView[x][y] = ('#');
                }
                else if(y > height - 14 && y < height - 2)
                {// && (x < width/3) && x >= 5
                    //char[][] word = toText("Testing");
                    int ygroup = (y - height + 13)/(menuHeight+1);
                    int xgroup = x/(width/menuCount);
                    if(ygroup + xgroup*3  == selectedIndex && x >= xgroup*(width/menuCount)+2 && x <= xgroup*(width/menuCount)+4)
                    {
                        if(arrow[2][y - height + 13-4*ygroup] != 0)
                            combatView[x][y] = (arrow[2][y - height + 13-4*ygroup]);
                        else
                            combatView[x][y] = (' ');
                    }
                    //combatView[x][y] = ( ygroup+menuCount*xgroup);
                    else if(x-5 - xgroup*(width/menuCount)>= 0 && ( y - height + 13-(textHeight+1)*ygroup) < 3 && wordlist[ygroup+menuCount*xgroup][ y - height + 13-4*ygroup][x-5 - xgroup*(width/menuCount)] != 0)
                        combatView[x][y] = (wordlist[ygroup+menuCount*xgroup][ y - height + 13-4*ygroup][x-5 - xgroup*(width/menuCount)]);
                    else
                        combatView[x][y] = (' ');
                }
                else if(x >= width/2 && x < width && y >= 8 && y <= 34)
                {
                    //combatView[x][y] = ln("y: " + (y - 8) + "x: " + (x-60));
                    if( x < enemy[ y - 8].length + width)
                    { combatView[x][y] = (enemy[ y - 8][x-width/2]);
                    }
                    else
                        combatView[x][y] = (' ');
                }
                else if(x >= 0 && x < width/2 && y >= 15 && y <= 34)
                {

                    //combatView[x][y] = ln("y: " + (y - 8) + "x: " + (x-10));
                    if( x < player[ y - 15].length + 100 && player[ y - 15][x] != 0)
                    { combatView[x][y] = (player[ y - 15][x]);
                    }
                    else
                        combatView[x][y] = (' ');
                }
                else
                {
                    combatView[x][y] = (' ');

                }

            }
            combatView[width-1][y] = ('\n');
        }
        return combatView;
    }
}