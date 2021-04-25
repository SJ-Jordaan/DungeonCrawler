package com.hive.rpg.models;

import java.util.ArrayList;

public enum WeaponType {
  TutorialWeapon("Stick", "Yikes"), DBFundamentals("SQL", "Weak but painful"), Java("Java", "Noice"),
  CSharp("CSharp", "Ew"), LuckySlayer("Lucky", "Yes it does");

  public String Name;
  public String Description;
  public ArrayList<Attack> Attacks;

  WeaponType(String name, String description) {
    Name = name;
    Description = description;
    ArrayList<Attack> attacks = new ArrayList<>();
    switch (name) {
    case "Stick":
      attacks.add(new Attack("STAB", 3));
      attacks.add(new Attack("SLASH", 4));
      attacks.add(new Attack("THROW", 5));
      attacks.add(new Attack("SCREAM", 0));
      attacks.add(new Attack("COMPLIMENT", 0));
      attacks.add(new Attack("PAN FRY", 10));
      break;
    case "SQL":
      attacks.add(new Attack("DELETE ALL", -1));
      attacks.add(new Attack("ORDER BY", 5));
      attacks.add(new Attack("GROUP BY", 5));
      attacks.add(new Attack("USE VIEWS", 10));
      attacks.add(new Attack("MAKE ERD", 3));
      attacks.add(new Attack("USE REDDIT", 0));
      break;
    case "Java":
      attacks.add(new Attack("USE MAVEN", 5));
      attacks.add(new Attack("OR CRY", 0));
      attacks.add(new Attack("AND CRY", 0));
      attacks.add(new Attack("DEBUG", 10));
      attacks.add(new Attack("OR CRY", 0));
      attacks.add(new Attack("AND CRY", 0));
      break;
    case "CSharp":
      attacks.add(new Attack("NO IDEA", 0));
      attacks.add(new Attack("GET BORED", 5));
      attacks.add(new Attack("SLEEP", 5));
      attacks.add(new Attack("GIVE UP", 100));
      break;
    case "Lucky":
      attacks.add(new Attack("IT DEPENDS", 1000));
      attacks.add(new Attack("OR DOES IT", -10));
      break;
    default:
      attacks.add(new Attack("WHAT", 1));
      break;
    }
    Attacks = attacks;
  }
}
