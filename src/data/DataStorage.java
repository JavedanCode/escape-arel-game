package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    // PLAYER STATS

    int maxLife;
    int life;
    int maxBullet;
    int bullets;


    //PLAYER POSITION AND MAP
    int currentMap;
    int worldX;
    int worldY;
    String direction;

    //MUSIC Index
    int musicIndex;



    // PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentMeleeWeaponSlot;



    //OBJECTS ON THE MAP
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootName;
    boolean[][] mapObjectOpened;

    //Progress
    public boolean universityReached;
    public boolean firstScenePlayed;
    public boolean secondScenePlayed;
    public boolean finalScenePlayed;
    public boolean zombieKilled;
    public boolean stuffTaken;
    public boolean vladDefeated;
    public boolean doorOpened;
    public boolean leftUniversity;
    public boolean reachedBus;

}
