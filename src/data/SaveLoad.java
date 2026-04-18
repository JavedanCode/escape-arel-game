package data;

import main.GamePanel;
import main.Sound;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }


    public void save(){

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxBullet = gp.player.maxBullet;
            ds.bullets = gp.player.bullets;

            //PLAYER POSITION AND MAP
            ds.currentMap = gp.currentMap;
            ds.worldX = gp.player.worldX;
            ds.worldY = gp.player.worldY;
            ds.direction = gp.player.direction;


            //MUSIC INDEX
            ds.musicIndex = gp.musicIndex;





            //PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size();i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            //PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentMeleeWeaponSlot = gp.player.getCurrentMeleeWeaponSlot();

            //Progress
            ds.universityReached = Progress.universityReached;
            ds.firstScenePlayed = Progress.firstScenePlayed;
            ds.secondScenePlayed = Progress.secondScenePlayed;
            ds.finalScenePlayed = Progress.finalScenePlayed;
            ds.zombieKilled = Progress.zombieKilled;
            ds.stuffTaken = Progress.stuffTaken;
            ds.vladDefeated = Progress.vladDefeated;
            ds.doorOpened = Progress.doorOpened;
            ds.leftUniversity = Progress.leftUniversity;
            ds.reachedBus = Progress.reachedBus;


            //OBJECTS ON THE MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap;mapNum++){

                for(int i = 0; i < gp.obj[1].length;i++){
                    if(gp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                }else{
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if(gp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
                    }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                }
            }}

            // Write the DataStorage object to the file
            oos.writeObject(ds);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load(){

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the DataStorage object from the file
            DataStorage ds = (DataStorage) ois.readObject();


            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxBullet = ds.maxBullet;
            gp.player.bullets = ds.bullets;


            //PLAYER POSITION AND MAP
            gp.currentMap = ds.currentMap;
            gp.player.worldX = ds.worldX;
            gp.player.worldY = ds.worldY;
            gp.player.direction = ds.direction;



            //PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size();i++){
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            //PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.meleeWeapon = gp.player.inventory.get(ds.currentMeleeWeaponSlot);

            gp.player.getMeleeAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            //Progress
            Progress.universityReached = ds.universityReached;
            Progress.firstScenePlayed = ds.firstScenePlayed;
            Progress.secondScenePlayed = ds.secondScenePlayed;
            Progress.finalScenePlayed = ds.finalScenePlayed;
            Progress.zombieKilled = ds.zombieKilled;
            Progress.stuffTaken = ds.stuffTaken;
            Progress.vladDefeated = ds.vladDefeated;
            Progress.doorOpened = ds.doorOpened;
            Progress.leftUniversity = ds.leftUniversity;
            Progress.reachedBus = ds.reachedBus;
            gp.tManager.checkTask();


            //MUSIC
            gp.playMusic(ds.musicIndex);


            //OBJECTS ON THE MAP
            for(int mapNum = 0; mapNum < gp.maxMap;mapNum++){

                for(int i = 0; i < gp.obj[1].length;i++){

                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i] = null;
                    }else{
                        if (gp.obj[mapNum][i] != null) { // Add null check
                            gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                            gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                            if (ds.mapObjectLootName[mapNum][i] != null) {
                                gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootName[mapNum][i]));
                            }
                            gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                            if (gp.obj[mapNum][i].opened) {
                                gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                            }
                        }
                }

                }
            }


        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}
