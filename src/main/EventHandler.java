package main;


import data.Progress;
import entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    boolean musicPlaying = false;
    boolean vladKilled = false;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {


            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }


        }

        setDialogue();


    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "Oh shit where am I!!";
        eventMaster.dialogues[1][0] = "Get healed bro ;)";
        eventMaster.dialogues[2][0] = "you fell like an idiot!";
        eventMaster.dialogues[3][0] = "You Killed my creation!!";
        eventMaster.dialogues[3][1] = "I will divide you by zero!!";
        eventMaster.dialogues[4][0] = "OMG..";
        eventMaster.dialogues[4][1] = "Professor Vlad...";
        eventMaster.dialogues[4][2] = "I'm sorry...";
        eventMaster.dialogues[4][3] = "I Won't miss your notes though...";
    }

    public void checkEvent() {

        //Check if the player character is more than one tile away from the event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize+gp.tileSize/2) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {


            if (gp.player.getCol() <= 61 && gp.player.getRow() == 21 && gp.player.getCol() >= 60 && gp.currentMap == 0) {

                //Set the previous event X and Y so this condition doesn't get called back to back
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

                canTouchEvent = false;


                //If they have never reached the University and have a light, set the task to get your stuff and let them inside
                if ( gp.player.currentLight != null&&gp.tManager.taskNum != gp.tManager.taskEscapeToBus) {

                    changeMap(gp.classes, 1, 41, 59);

                    Progress.universityReached = true;
                    gp.tManager.taskNum = gp.tManager.taskGetYourStuff;
                    gp.tManager.checkTask();

                }
                //If player's task is to leave don't let them go to the university
                else if (gp.tManager.taskNum == gp.tManager.taskEscapeToBus) {
                    gp.player.startDialogue(gp.player, 2);
                }

                //If player doesn't have a light, start dialogue
                if (gp.player.currentLight == null) {
                    gp.player.startDialogue(gp.player, 0);

                }
            }//Go to the University


            else if (gp.player.getCol() <= 41 && gp.player.getRow() == 61 && gp.player.getCol() >= 40 && gp.currentMap == 1) {


                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

                canTouchEvent = false;



                if (Progress.vladDefeated) {
                    finalScene();
                }else{changeMap(gp.outside, 0, 60, 22);}




            }



            else if (gp.player.getCol() <= 41 && gp.player.getRow() == 46 && gp.player.getCol() >= 40 && gp.currentMap == 1) {

                //Set the previous event X and Y so this condition doesn't get called back to back
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

                canTouchEvent = false;

                first_scene();
            }


            else if (gp.player.getCol() <= 69 && gp.player.getRow() == 46 && gp.player.getCol() >= 68 && gp.currentMap == 1) {


                if (Progress.stuffTaken) {
                    //Set the previous event X and Y so this condition doesn't get called back to back
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;

                    canTouchEvent = false;

                    second_scene();
                }
            }

            else if (gp.player.getCol() <= 63 && gp.player.getRow() == 48 && gp.player.getCol() >= 58 && gp.currentMap == 0&&gp.tManager.taskNum == gp.tManager.taskEscapeToBus) {

                //Set the previous event X and Y so this condition doesn't get called back to back
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

                canTouchEvent = false;


            }

            if(Progress.vladDefeated&&!vladKilled){
                gp.gameState = gp.dialogueState;
                eventMaster.startDialogue(eventMaster,4);
                vladKilled = true;

            }


            if( Progress.firstScenePlayed&&!Progress.zombieKilled&&!musicPlaying){
                gp.stopMusic();
                gp.playMusic(28);
                musicPlaying = true;
            }

            if(Progress.zombieKilled&&!Progress.secondScenePlayed&&musicPlaying){

                musicPlaying = false;
            }
            if( Progress.secondScenePlayed&&!Progress.vladDefeated&&!musicPlaying){
                gp.stopMusic();
                gp.playMusic(27);
                musicPlaying = true;
            }



        }
    }

    public void finalScene(){

            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.finalScene;
            Progress.finalScenePlayed = true;

    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            entity.speak();

        }
    }

    public void second_scene() {
        if (!Progress.secondScenePlayed) {
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.secondScene;
        }
    }

    public void changeMap(int area, int map, int col, int row) {

        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;



        canTouchEvent = false;
        gp.playSE(13);

    }

    public void first_scene() {
        if (!Progress.firstScenePlayed) {
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.firstScene;
        }
    }




    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }

        return hit;
    }


}
