package main;

import buildings.BUILD_BusStation;
import buildings.BUILD_School;
import buildings.BUILD_Security;
import buildings.BUILD_University;
import data.Progress;
import decorations.*;

import entity.NPC_Abdu;
import entity.NPC_Tuberk;
import light_source.Light_Lamp_1;
import monster.MON_FirstZombie;
import monster.MON_Vlad;
import monster.MON_Zombie;
import object.*;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        int i = 0;




         mapNum = 1;
         i = 0;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*68;
        gp.obj[mapNum][i].worldY = gp.tileSize*43;
        i++;

        gp.obj[mapNum][i] = new OBJ_ChestWithStuff(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Coin_Bronze(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*51;
        gp.obj[mapNum][i].worldY = gp.tileSize*46;
        i++;





    }

    public void setNPC(){

        int i = 0;
        int mapNum = 0;


        gp.npc[mapNum][i] = new NPC_Tuberk(gp);
        gp.npc[mapNum][i].worldX= gp.tileSize*59;
        gp.npc[mapNum][i].worldY = gp.tileSize*53;


        //Same system as Object, but for NPCs


    }

    public void setMonster(){
        int mapNum = 1;
        int i = 0;
        //Same as Object, but for monsters
        if(!Progress.firstScenePlayed){
        gp.monster[mapNum][i] = new MON_Vlad(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*21;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;


        gp.monster[mapNum][i] = new MON_FirstZombie(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;}
        else if(!Progress.zombieKilled&&!Progress.secondScenePlayed){
            gp.monster[mapNum][i] = new MON_FirstZombie(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*23;
            gp.monster[mapNum][i].worldY = gp.tileSize*32;
            gp.monster[mapNum][i].sleep = false;
            i++;}


        if(Progress.secondScenePlayed&&!Progress.vladDefeated){
            gp.monster[mapNum][i] = new MON_Vlad(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*49;
            gp.monster[mapNum][i].worldY = gp.tileSize*60;
            gp.monster[mapNum][i].sleep = false;
            i++;
        }
    }






    public void setInteractiveTiles(){
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new BUILD_University(gp, 50, 1);i++;
        gp.iTile[mapNum][i] = new BUILD_Security(gp, 65, 40);i++;
        gp.iTile[mapNum][i] = new BUILD_BusStation(gp, 51, 48);i++;

        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,67,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,69,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,71,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,73,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,75,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,77,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,79,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,53,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,51,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,49,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,47,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,45,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,43,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,41,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,39,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,29,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,27,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,25,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,23,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,21,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,19,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,17,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,15,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,13,28);i++;
        gp.iTile[mapNum][i] = new DEC_Tree_2(gp,11,28);i++;

        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,29);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,28);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,27);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,26);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,25);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,24);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,23);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,22);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,21);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,20);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,19);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,18);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,17);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,16);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,15);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,37,14);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,13);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,29);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,28);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,27);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,26);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,25);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,24);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,23);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,22);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,21);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,20);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,19);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,18);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,17);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,16);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,15);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,14);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,31,13);i++;

        gp.iTile[mapNum][i] = new DEC_FoodBooth1(gp,39,14);i++;
        gp.iTile[mapNum][i] = new DEC_FoodBooth1(gp,39,18);i++;
        gp.iTile[mapNum][i] = new DEC_FoodBooth1(gp,39,24);i++;

        gp.iTile[mapNum][i] = new DEC_FoodBooth2(gp,28,14);i++;
        gp.iTile[mapNum][i] = new DEC_FoodBooth2(gp,28,18);i++;
        gp.iTile[mapNum][i] = new DEC_FoodBooth2(gp,28,24);i++;

        gp.iTile[mapNum][i] = new DEC_Fountain(gp,59,31);i++;

        gp.iTile[mapNum][i] = new DEC_Flag(gp,69,17);i++;

        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,70,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,75,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,80,35);i++;

        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,40,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,50,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,45,35);i++;

        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,12,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,17,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,22,35);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_2(gp,27,35);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,11,37);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,81,30);i++;

        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,30,35);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,37,36);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,31,36);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot1(gp,38,35);i++;

        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,57,26);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,57,27);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,56,28);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,55,29);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,64,26);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,64,27);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,65,28);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,66,29);i++;

        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,54,35);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,55,36);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,66,36);i++;
        gp.iTile[mapNum][i] = new DEC_FlowerPot2(gp,67,35);i++;

        gp.iTile[mapNum][i] = new DEC_FootballField(gp,11,55);i++;
        gp.iTile[mapNum][i] = new DEC_GoalLeft(gp,27,57);i++;
        gp.iTile[mapNum][i] = new DEC_GoalRight(gp,11,57);i++;
        gp.iTile[mapNum][i] = new DEC_SittingPlace(gp,13,50);i++;
        gp.iTile[mapNum][i] = new DEC_SittingPlace(gp,18,50);i++;
        gp.iTile[mapNum][i] = new DEC_SittingPlace(gp,23,50);i++;

        gp.iTile[mapNum][i] = new DEC_Flowers_Pink(gp,14,42);i++;
        gp.iTile[mapNum][i] = new DEC_Flowers_Pink(gp,18,44);i++;
        gp.iTile[mapNum][i] = new DEC_Flowers_Pink(gp,22,46);i++;
        gp.iTile[mapNum][i] = new DEC_Flowers_Pink(gp,13,45);i++;
        gp.iTile[mapNum][i] = new DEC_Flowers_Pink(gp,26,43);i++;

        gp.iTile[mapNum][i] = new DEC_Bench_4(gp,28,40);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_4(gp,28,42);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_4(gp,28,47);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_3(gp,11,40);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_3(gp,11,42);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_4(gp,11,47);i++;
        gp.iTile[mapNum][i] = new DEC_Bench_4(gp,11,49);i++;

        gp.iTile[mapNum][i] = new DEC_Rock_1(gp,23,47);i++;
        gp.iTile[mapNum][i] = new DEC_Rock_2(gp,17,45);i++;
        gp.iTile[mapNum][i] = new DEC_Rock_3(gp,22,42);i++;

        gp.iTile[mapNum][i] = new DEC_Car(gp,78,51);i++;
        gp.iTile[mapNum][i] = new DEC_Car(gp,72,51);i++;

        mapNum = 1;
        i = 0;

        gp.iTile[mapNum][i] = new DEC_Reception(gp,45,45);i++;

        gp.iTile[mapNum][i] = new DEC_Pot1(gp,46,48);i++;

        gp.iTile[mapNum][i] = new DEC_Table(gp,33,49);i++;

        gp.iTile[mapNum][i] = new DEC_Couch(gp,33,47);i++;
        gp.iTile[mapNum][i] = new DEC_CouchLeft(gp,37,48);i++;
        gp.iTile[mapNum][i] = new DEC_CouchRight(gp,30,48);i++;

        gp.iTile[mapNum][i] = new DEC_Painting1(gp,32,44);i++;

        gp.iTile[mapNum][i] = new DEC_Painting2(gp,43,44);i++;

        gp.iTile[mapNum][i] = new DEC_Pot2(gp,36,47);i++;
        gp.iTile[mapNum][i] = new DEC_Pot2(gp,32,47);i++;

        gp.iTile[mapNum][i] = new DEC_CoffeeVending(gp,37,32);i++;
        gp.iTile[mapNum][i] = new DEC_ColaVending(gp,39,32);i++;



        gp.iTile[mapNum][i] = new DEC_Locker(gp,41,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,42,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,43,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,44,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,45,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,46,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,47,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,48,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,49,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,50,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,51,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,52,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,53,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,54,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,55,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,56,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,57,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,58,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,59,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,60,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,61,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,62,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,63,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,64,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,65,32);i++;
        gp.iTile[mapNum][i] = new DEC_Locker(gp,66,32);i++;


        gp.iTile[mapNum][i] = new DEC_Files(gp,23,27);i++;

        gp.iTile[mapNum][i] = new DEC_Desk_Right(gp,19,30);i++;

        gp.iTile[mapNum][i] = new DEC_Pot2(gp,17,34);i++;
        gp.iTile[mapNum][i] = new DEC_Pot2(gp,27,28);i++;

        gp.iTile[mapNum][i] = new DEC_Shelf(gp,19,27);i++;

        gp.iTile[mapNum][i] = new DEC_Bookshelf(gp,49,45);i++;

        gp.iTile[mapNum][i] = new DEC_Board(gp,55,47);i++;

        gp.iTile[mapNum][i] = new DEC_NoticeBoard(gp,64,44);i++;

        gp.iTile[mapNum][i] = new DEC_Desk_Front(gp,51,48);i++;

        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,52,52);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,56,52);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,60,52);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,64,52);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,68,52);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,52,56);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,56,56);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,60,56);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,64,56);i++;
        gp.iTile[mapNum][i] = new DEC_StudentDesk(gp,68,56);i++;

        gp.iTile[mapNum][i] = new DEC_Blackboard(gp,53,44);i++;


        gp.iTile[mapNum][i] = new Light_Lamp_1(gp,44,45);i++;

        gp.iTile[mapNum][i] = new DEC_Map(gp,59,44);i++;





    }





}
