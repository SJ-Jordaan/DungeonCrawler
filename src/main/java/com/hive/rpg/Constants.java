package com.hive.rpg;

public enum Constants {
  MainMenu("MainMenu", 115, 54), BattleWon("BattleWon", 115, 54), NextLevel("NextLevel", 115, 54),
  GameWon("GameWon", 110, 50), Credits("Credits", 110, 50), PlayerDied("PlayerDied", 110, 50),
  Level0("Level0", 110, 50), Level1("Level1", 110, 50), Level2("Level2", 110, 50), Level3("Level3", 110, 50),
  BossFight("BossFight", 110, 50);

  public String filename;
  public int columns;
  public int rows;

  Constants(String filename, int columns, int rows) {
    this.filename = filename;
    this.columns = columns;
    this.rows = rows;
  }
}
