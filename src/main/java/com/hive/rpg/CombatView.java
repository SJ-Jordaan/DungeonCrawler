import java.util.HashSet;
import java.util.Set;
import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;

import javax.lang.model.util.ElementScanner14;

public class CombatView {

    private int width;
    private int height;
    public int playerHealth;
    public int enemyHealth;
    public int maxHealth;
    private char wordlist[][][];
  //public int actions[];
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

    private char[][] readFile(String filename, int maxcolumns, int maxlines)
    {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
                //System.out.print(fileRes[i][a]);
              }
              //System.out.println("");
              i++;
              
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          return fileRes;
    }
    
    public static void main( String[] args )
    {
        char[][] player = new char[20][20];
        char[][] enemy = new char[60][60];
        CombatView cw = new CombatView();
        cw.letterArr = cw.readFile("Letters.txt", 6, 171);
        char[][] playerText = cw.toText("Player");
        char[][] enemyText = cw.toText("Enemy");
        String[] actions = {"rock Attack" , "paper Attack" ,"scissor Attack" ,"rock Spell" ,"paper Spell" ,"scissor Spell" ,"Heal" ,"Defend" , "Cower"};
        cw.wordlist = new char[9][3][70];
        for(int i = 0; i < 9; i++)
            cw.wordlist[i] = cw.toText(actions[i]);
        enemy = cw.readFile("Enemies.txt", 110, 70);
        player = cw.readFile("Players.txt", 110, 70);
        char[][] map = cw.createCombatView(player, enemy, playerText, enemyText);
    }

    public char[][] toText(String sentence)
    {
        char[][] text = new char[3][70];
        int indexCount = 0;
        for(char c : sentence.toCharArray())
        {
            int i = 0;
            if(c >= 65 && c <= 90)
            {
                i = (c-65) * 3;
            }
            else if(c >= 97 && c <= 122)
            {
                i = (c-97) * 3 + 78;
            }
            else if(c == ' ')
            {
                i=53*3;
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

    public char[][] createCombatView(char[][] player, char[][] enemy, char[][] playerText, char[][] enemyText)
    {
        
        
        char[][] combatView = new char[width][height];
        height = 50;
        width = 220;
        playerHealth = 100;
        enemyHealth = 50;
        maxHealth = 100;
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(y == 0 || y == height - 1)
                {
                    System.out.print('#');
                }
                else if(x == 0 || x == width - 1)
                {
                    System.out.print('#');
                }
                else if((y == 4 || y == 6) && ((x >= 4 && x <= maxHealth+6)||((x >= width - maxHealth - 7 && x <= width - 5))))
                {
                    System.out.print('=');
                }
                else if(y >= 1 && y <= 3)
                {
                    if(x>=4 && x < playerText[0].length + 4 && playerText[y-1][x-4] != 0)
                    {
                        System.out.print(TEXT_BLUE + playerText[y-1][x-4] + TEXT_RESET);
                    }
                    else if(x>=width - maxHealth - 7 && x < enemyText[0].length + width - maxHealth - 7 && enemyText[y-1][x-113] != 0)
                    {
                        System.out.print(TEXT_GREEN + enemyText[y-1][x-113] + TEXT_RESET);
                    }
                    else
                    {
                        System.out.print(' ');
                    }
                }
                else if(y == 5 && x >= 4 && x <= width - 5)
                {
                    if(x==4 || x == maxHealth+6 || x == width - 5 || x==width - maxHealth - 7)
                        System.out.print('|');
                    else if(x<=playerHealth+5)
                        System.out.print(TEXT_RED + 'X' + TEXT_RESET);
                    else if(x>=width - 6 - enemyHealth)
                    {
                        System.out.print(TEXT_RED + 'X' + TEXT_RESET);
                    }
                    else
                        System.out.print(' ');

                }
                else if(y == height - 15)
                {
                    System.out.print('#');
                }
                else if(y > height - 15 && (x == width/3 || x == 2*width/3))
                {
                    System.out.print('#');
                    
                }
                else if(y > height - 14 && y < height - 2)
                {// && (x < width/3) && x >= 5
                    //char[][] word = toText("Testing");
                    int ygroup = (y - height + 13)/4;
                    int xgroup = x/(width/3);
                    //System.out.print( y - height + 13-4*ygroup);
                    if(x-5 - xgroup*(width/3)>= 0 && ( y - height + 13-4*ygroup) < 3 && wordlist[ygroup+3*xgroup][ y - height + 13-4*ygroup][x-5 - xgroup*(width/3)] != 0)
                        System.out.print(wordlist[ygroup+3*xgroup][y - height + 13-4*ygroup][x-5 - xgroup*(width/3)]);
                    else
                        System.out.print(' ');
                }
                else if(x >= 140 && x < 220 && y >= 8 && y <= 34)
                {
                    //System.out.println("y: " + (y - 8) + "x: " + (x-60));
                    if( x < enemy[ y - 8].length + 200)
                       { System.out.print(enemy[ y - 8][x-140]);
                        }
                    else
                        System.out.print(' ');
                }
                else if(x >= 40 && x < 110 && y >= 15 && y <= 34)
                {   

                    //System.out.println("y: " + (y - 8) + "x: " + (x-10));
                    if( x < player[ y - 15].length + 100 && player[ y - 15][x-40] != 0)
                       { System.out.print(player[ y - 15][x-40]);
                        }
                    else
                        System.out.print(' ');
                }
                else
                {
                    System.out.print(' ');
                    
                }
            }
            System.out.print('\n');
        }
        return combatView;
    }
}



