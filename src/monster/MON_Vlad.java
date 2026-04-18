package monster;

import data.Progress;
import entity.Entity;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Math;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MON_Vlad extends Entity {
    GamePanel gp;

    public static final String monName = "Vlad";

    public MON_Vlad(GamePanel gp) {
        super(gp);

        this.gp = gp;

        direction = "right";

        name = monName;
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 80;
        life = maxLife;
        type = type_monster;
        attack = 3;
        defense = 0;

        knockBackPower = 7;
        boss = true;
        sleep = true;
        shieldOn = false;
        shieldLife = 3;
        onPath = false;
        gun = new OBJ_Math(gp);


        solidArea.x = 4;
        solidArea.y = 18;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        motion1_duration = 25;
        motion2_duration = 50;

        hasVoiceLine = true;
        soundPlayed = new boolean[4][2]; // 0 = creation, 1 = noise, 2 = check noise, 3 = evil laugh

        getImage();
        getAttackImage();
        setDialogue();




    }

    public void damageReaction(){

        actionLockCounter = 0;

    }

    @Override
    public void playDialogue(int dialogueIndex,int dialogueSet){

        if(!soundPlayed[dialogueIndex][dialogueSet]){

            if(dialogueSet == 0){
            switch(dialogueIndex){
                case 0: gp.playSE(31); break;
                case 1: gp.playSE(21); break;
                case 2: gp.playSE(34); break;
                case 3: gp.playSE(33); break;

                default:
                    break;
            }}else if(dialogueSet == 1){
                switch(dialogueIndex){
                    case 0: gp.playSE(32); break;
                    case 1: gp.playSE(35); break;

                    default:
                        break;
                }
            }
            soundPlayed[dialogueIndex][dialogueSet] = true;
        }

    }

    public void setDialogue(){

        dialogues[0][0] = "OH BRAVO!, What a creation!";
        dialogues[0][1] = "noise...";
        dialogues[0][2] = "Go check that noise my student.. ";
        dialogues[0][3] = "Evil-Laugh*";

        dialogues[1][0]= "You Killed my creation!!";
        dialogues[1][1] = "I will divide you by zero!!";

    }

    public void getImage(){



        if(!inRage) {
            up1 = setup("/vlad/vlad_up_1", gp.tileSize, gp.tileSize);
            up2 = setup("/vlad/vlad_up_2", gp.tileSize, gp.tileSize);
            up3 = setup("/vlad/vlad_up_3", gp.tileSize, gp.tileSize);
            down1 = setup("/vlad/vlad_down_1", gp.tileSize , gp.tileSize );
            down2 = setup("/vlad/vlad_down_2", gp.tileSize , gp.tileSize) ;
            down3 = setup("/vlad/vlad_down_3", gp.tileSize, gp.tileSize);
            left1 = setup("/vlad/vlad_left_1", gp.tileSize, gp.tileSize );
            left2 = setup("/vlad/vlad_left_2", gp.tileSize , gp.tileSize );
            left3 = setup("/vlad/vlad_left_3", gp.tileSize , gp.tileSize );
            right1 = setup("/vlad/vlad_right_1", gp.tileSize , gp.tileSize );
            right2 = setup("/vlad/vlad_right_2", gp.tileSize , gp.tileSize );
            right3 = setup("/vlad/vlad_right_3", gp.tileSize , gp.tileSize );



        }
        else{

            up1 = setup("/vlad/vlad_up_1", gp.tileSize, gp.tileSize );
            up2 = setup("/vlad/vlad_up_2", gp.tileSize , gp.tileSize );
            up3 = setup("/vlad/vlad_up_3", gp.tileSize , gp.tileSize );
            down1 = setup("/vlad/vlad_down_1", gp.tileSize , gp.tileSize );
            down2 = setup("/vlad/vlad_down_2", gp.tileSize , gp.tileSize );
            down3 = setup("/vlad/vlad_down_3", gp.tileSize , gp.tileSize );
            left1 = setup("/vlad/vlad_left_1", gp.tileSize, gp.tileSize );
            left2 = setup("/vlad/vlad_left_2", gp.tileSize , gp.tileSize );
            left3 = setup("/vlad/vlad_left_3", gp.tileSize , gp.tileSize );
            right1 = setup("/vlad/vlad_right_1", gp.tileSize , gp.tileSize );
            right2 = setup("/vlad/vlad_right_2", gp.tileSize , gp.tileSize );
            right3 = setup("/vlad/vlad_right_3", gp.tileSize , gp.tileSize );
        }

    }
    public void getAttackImage(){
        int i = 5;

        if(!inRage) {
            attackUp1 = setup("/vlad/vlad_up_1", gp.tileSize, gp.tileSize);
            attackUp2 = setup("/vlad/vlad_up_2", gp.tileSize, gp.tileSize);
            attackUp3 = setup("/vlad/vlad_up_3", gp.tileSize, gp.tileSize);
            attackDown1 = setup("/vlad/vlad_down_1", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/vlad/vlad_down_2", gp.tileSize, gp.tileSize);
            attackDown3 = setup("/vlad/vlad_down_3", gp.tileSize, gp.tileSize);
            attackLeft1 = setup("/vlad/vlad_left_1", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/vlad/vlad_left_2", gp.tileSize, gp.tileSize);
            attackLeft3 = setup("/vlad/vlad_left_3", gp.tileSize, gp.tileSize);
            attackRight1 = setup("/vlad/vlad_right_1", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/vlad/vlad_right_2", gp.tileSize, gp.tileSize);
            attackRight3 = setup("/vlad/vlad_right_3", gp.tileSize, gp.tileSize);

        }
        else{
            attackUp1 = setup("/vlad/vlad_up_1", gp.tileSize, gp.tileSize);
            attackUp2 = setup("/vlad/vlad_up_2", gp.tileSize, gp.tileSize);
            attackUp3 = setup("/vlad/vlad_up_3", gp.tileSize, gp.tileSize);
            attackDown1 = setup("/vlad/vlad_down_1", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/vlad/vlad_down_2", gp.tileSize, gp.tileSize);
            attackDown3 = setup("/vlad/vlad_down_3", gp.tileSize, gp.tileSize);
            attackLeft1 = setup("/vlad/vlad_left_1", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/vlad/vlad_left_2", gp.tileSize, gp.tileSize);
            attackLeft3 = setup("/vlad/vlad_left_3", gp.tileSize, gp.tileSize);
            attackRight1 = setup("/vlad/vlad_right_1", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/vlad/vlad_right_2", gp.tileSize, gp.tileSize);
            attackRight3 = setup("/vlad/vlad_right_3", gp.tileSize, gp.tileSize);

        }
    }


    public void update(){

        if(!sleep){

            gp.ui.drawShield();
            if(knockBack){

                checkCollision();

                if(collisionOn){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
                else{
                    switch (knockBackDirection){
                        case "up"   : worldY-=speed; break;
                        case "down" : worldY+=speed; break;
                        case "left" : worldX-=speed; break;
                        case "right": worldX+=speed; break;
                    }
                }
                knockBackCounter++;
                if(knockBackCounter == 10){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }

            }
            else if(attacking){
                attacking();
            }else{
                setAction();
                checkCollision();

                //IF COLLISION IS FALSE PLAYER CAN'T MOVE
                if(!collisionOn){
                    switch (direction){
                        case "up"   : worldY-=speed; break;
                        case "down" : worldY+=speed; break;
                        case "left" : worldX-=speed; break;
                        case "right": worldX+=speed; break;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 20){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 3;
                    }
                    else if(spriteNum == 3){
                        spriteNum = 4;
                    }
                    else if(spriteNum == 4){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if(invincible){
                invincibleCounter++ ;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter=0;
                }
            }
            if(projectileCoolDown < 30){
                projectileCoolDown++;
            }
            if(offBalance){
                offBalanceCounter++;
                if(offBalanceCounter > 60){
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }



    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (inCamera()){

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {

                case "up":
                    if(!attacking){
                        if(spriteNum == 1){image = up2; }
                        if(spriteNum == 2){image = up3;}
                        if(spriteNum == 3){image = up2; }
                        if(spriteNum == 4){image = up3;}
                    }
                    if(attacking){
                        tempScreenY = getScreenY() - up1.getHeight();
                        if(spriteNum == 1){image = attackUp1;}
                        if(spriteNum == 2){image = attackUp2;}
                    }
                    break;
                case "down":
                    if(!attacking){
                        if(spriteNum == 1){ image = down2;}
                        if(spriteNum == 2){ image = down3;}
                        if(spriteNum == 3){ image = down2;}
                        if(spriteNum == 4){ image = down3;}}
                    if(attacking){
                        if(spriteNum == 1){image = attackDown1;}
                        if(spriteNum == 2){image = attackDown2;}
                    }break;
                case "left" :
                    if(!attacking){
                        if(spriteNum == 1){ image = left1; }
                        if(spriteNum == 2){ image = left2; }
                        if(spriteNum == 3){ image = left1; }
                        if(spriteNum == 4){ image = left3;}
                    }
                    if(attacking){
                        tempScreenX = getScreenX() - left1.getWidth();
                        if(spriteNum == 1){image = attackLeft1;}
                        if(spriteNum == 2){image = attackLeft2;}
                    }break;
                case "right" :
                    if(!attacking){
                        if(spriteNum == 1){ image = right1;}
                        if(spriteNum == 2){image = right2; }
                        if(spriteNum == 3){ image = right1;}
                        if(spriteNum == 4){image = right3; }}

                    if(attacking){

                        if(spriteNum == 1){image = attackRight1;}
                        if(spriteNum == 2){image = attackRight2;}
                    }break;
            }




            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);
            }
            if(dying){
                dyingAnimation(g2);
            }


            g2.drawImage(image,tempScreenX,tempScreenY,null);
            changeAlpha(g2,1f);
        }
    }

    public void setAction(){

        if(!inRage&&life < maxLife/2){
            inRage = true;
            shieldOn = true;
        }

        if(onPath){

            /*//Check if it stops chasing
            checkStopChase(gp.player,15,100);*/

            //search the direction to go
            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));

            checkShoot(50,30);


        }
        else{

            //Check if it should start chasing
            checkStartChase(gp.player,5,100);
            //Get a random direction
            getRandomDirection(120);

        }
    }


    public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.vladDefeated = true;
        gp.tManager.taskNum = gp.tManager.taskLeaveUni;
        gp.tManager.checkTask();

        for(int i = 0; i < gp.obj[1].length; i++){
            Entity tempObj = gp.obj[gp.currentMap][i];
            if(tempObj != null && tempObj.name.equals(OBJ_Door.objName)){
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
            }
        }

        gp.eHandler.checkEvent();
        //Restore the music
        gp.stopMusic();
        gp.playMusic(26);



    }
}
