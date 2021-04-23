package com.hive.rpg.models;

import java.util.ArrayList;

public enum WeaponType {
  DBFundamentals("SQL", "Weak but painful");

  public String Name;
  public String Description;
  public ArrayList<Attack> Attacks;

  WeaponType(String name, String description) {
    Name = name;
    Description = description;
    ArrayList<Attack> attacks = new ArrayList<>();
    switch (name) {
    case "SQL":
      attacks.add(new Attack("DELETE", -1));
      attacks.add(new Attack("ORDER", 5));
      attacks.add(new Attack("VIEWS", 10));
      break;

    default:
      attacks.add(new Attack("WHAT", 1));
      break;
    }
    Attacks = attacks;
  }
}
