package Players;

import java.util.ArrayList;

import com.hive.rpg.Weapons.Weapon;

public class Characters {
    private String Name;
    private Integer Health;
    private ArrayList<Weapon> Weapons;

    // #region Getters & Setters
    /**
     * @return String return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return Integer return the Health
     */
    public Integer getHealth() {
        return Health;
    }

    /**
     * @param Health the Health to set
     */
    public void setHealth(Integer Health) {
        this.Health = Health;
    }

    /**
     * @return ArrayList<Weapon> return the Weapons
     */
    public ArrayList<Weapon> getWeapons() {
        return Weapons;
    }

    /**
     * @param Weapons the Weapons to set
     */
    public void setWeapons(ArrayList<Weapon> Weapons) {
        this.Weapons = Weapons;
    }

    // #endregion

    public Characters(String Name, Integer Health, ArrayList<Weapon> Weapons) {
        this.Name = Name;
        this.Health = Health;
        this.Weapons = Weapons;
    }

}
