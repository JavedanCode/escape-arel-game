package main;

import data.Progress;
import entity.Entity;
import entity.PlayerDummy;
import monster.MON_FirstZombie;
import monster.MON_Vlad;
import object.OBJ_Door;
import object.OBJ_Heart;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    public final int firstScene = 1;
    public final int secondScene = 2;
    public final int finalScene = 3;

    int counter = 0;
    float alpha = 0f;
    int y;
    boolean soundPlayed = false;


    //Scene Number
    public final int NA = 0;


    public CutsceneManager(GamePanel gp){
        this.gp = gp;

    }
    public void draw(Graphics2D g2){
        this.g2 = g2;


        switch(sceneNum){
            case firstScene : vlad_scene_first(); break;
            case secondScene : vlad_scene_second(); break;
            case finalScene : final_scene(); break;

        }
    }

    public void vlad_scene_first(){



        if(scenePhase == 0){
            for(int i = 0 ; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }
        if(scenePhase == 1){
            gp.player.worldY -= 5;
            if(gp.player.worldY < gp.tileSize*33){
                scenePhase++;

            }
        }
        if(scenePhase == 2){
            gp.player.worldX -= 5;
            if(gp.player.worldX < gp.tileSize*24){
                scenePhase++;

            }
        }
        if(scenePhase == 3){
            for(int i = 0; i < gp.monster[1].length; i++){
                Entity monster = gp.monster[gp.currentMap][i];
                if(monster != null && monster.name.equals(MON_Vlad.monName)){
                    gp.ui.npc = monster;
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 4){
            gp.ui.drawDialogueScreen();





        }
        if(scenePhase == 5){

            for(int i = 0; i < gp.monster[1].length; i++){
                Entity monster = gp.monster[gp.currentMap][i];
                if(monster != null && monster.name.equals(MON_Vlad.monName)){

                    gp.monster[gp.currentMap][i] = null;
                    break;
                }
            }

            for(int i = 0; i < gp.monster[1].length; i++){
                Entity monster = gp.monster[gp.currentMap][i];
                if(monster != null && monster.name.equals(MON_FirstZombie.monName)){
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.monster[gp.currentMap][i].onPath = true;
                    gp.monster[gp.currentMap][i].direction = "down";
                    break;
                }
            }

            //search for the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                Entity dummy = gp.npc[gp.currentMap][i];
                if(dummy != null && dummy.name.equals(PlayerDummy.npcName)){
                    gp.player.worldX = dummy.worldX;
                    gp.player.worldY = dummy.worldY;
                    gp.player.direction = dummy.direction;

                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;

            //Reset sceneNum and scenePhase
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            gp.tManager.taskNum = gp.tManager.taskKillTheZombie;
            Progress.firstScenePlayed = true;
            gp.tManager.checkTask();



        }
        gp.eHandler.checkEvent();

        /*//Change the music
        gp.stopMusic();
        gp.playMusic(27);*/


    }

    public void vlad_scene_second(){



        if(scenePhase == 0){
            for(int i = 0 ; i < gp.npc[1].length; i++){

                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = "down";

                    break;
                }
            }

            for(int i = 0; i < gp.obj[1].length;i++){

                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*68;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*43;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            for(int i = 0; i < gp.monster[1].length;i++){

                if(gp.monster[gp.currentMap][i] == null){
                    gp.monster[gp.currentMap][i] = new MON_Vlad(gp);
                    gp.monster[gp.currentMap][i].worldX = gp.tileSize*49;
                    gp.monster[gp.currentMap][i].worldY = gp.tileSize*60;
                    gp.monster[gp.currentMap][i].dialogueSet = 1;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    gp.monster[gp.currentMap][i].sleep = false;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }
        if(scenePhase == 1){
            gp.player.worldY += 5;
            if(gp.player.worldY > gp.tileSize*60){
                scenePhase++;

            }
        }
        if(scenePhase == 2){
            gp.player.worldX -= 5;
            if(gp.player.worldX < gp.tileSize*51){
                scenePhase++;

            }
        }

        if(scenePhase == 3){
            gp.ui.drawDialogueScreen();



        }
        if(scenePhase == 4){


            //search for the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                Entity dummy = gp.npc[gp.currentMap][i];
                if(dummy != null && dummy.name.equals(PlayerDummy.npcName)){
                    gp.player.worldX = dummy.worldX;
                    gp.player.worldY = dummy.worldY;
                    gp.player.direction = dummy.direction;

                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            for(int i = 0; i < gp.monster[1].length; i++){
                Entity monster = gp.monster[gp.currentMap][i];
                if(monster != null && monster.name.equals(MON_Vlad.monName)){
                    monster.sleep = false;
                    monster.onPath = true;
                    break;
                }
            }
            gp.player.drawing = true;

            //Reset sceneNum and scenePhase
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            gp.tManager.taskNum = gp.tManager.taskKillVlad;
            Progress.secondScenePlayed = true;
            gp.tManager.checkTask();
            gp.eHandler.checkEvent();


        }

       /* //Change the music
        gp.stopMusic();
        gp.playMusic(27);*/

    }

    public void final_scene(){

        if(scenePhase == 0){
            gp.stopMusic();
            gp.playSE(29);
            scenePhase++;
        }

        if(scenePhase == 1){
            if(counterReached(200)){
                scenePhase++;
            }

        }
        if(scenePhase == 2){
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;

            }
            drawBlackBackground(alpha);

            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 3){
            drawBlackBackground(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;

            }

            String text = "Thank you for playing my demo!\n\n" +
                    "I hope you enjoyed it!\n\n" +
                    "Please look forward to the full game!\n\n"+
                    "Hello Professor :D\n\n";
            drawString(alpha,38f,200,text,70);
            if(counterReached(600)){
                gp.playMusic(26);
                scenePhase++;
            }
        }if(scenePhase == 4){

            drawBlackBackground(1f);

            drawString(1f,120,gp.screenHeight/2,"THE END",40);

            if(counterReached(480)){

                scenePhase++;
            }

        }
        if(scenePhase == 5){


            gp.gameState = gp.titleState;
            gp.resetGame(true);
            sceneNum = NA;
            scenePhase = 0;
        }

        gp.eHandler.checkEvent();
    }



    //for counting down to the next scene
    public boolean counterReached(int target){

        boolean counterReached = false;

        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line : text.split("\n")){
            int x = gp.ui.getXforCenterText(line);
            g2.drawString(line,x,y);
            y+= lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));


    }




}



