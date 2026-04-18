package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Abdu extends Entity{

    public static final String npcName = "Abdu";

    public NPC_Abdu(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(8,16,40,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;
        name = npcName;


        direction = "down";
        speed = 0;

        dialogueSet = -1;
        getImage();
        setDialogue();
    }

    public void getImage(){

        down1 = setup("/npc/Abdu_Smoking_1",gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Abdu_Smoking_2",gp.tileSize, gp.tileSize);
        down3 = setup("/npc/Abdu_Smoking_3",gp.tileSize, gp.tileSize);
        up1 = setup("/npc/Abdu_Smoking_1",gp.tileSize, gp.tileSize);
        up2 = setup("/npc/Abdu_Smoking_2",gp.tileSize, gp.tileSize);
        up3 = setup("/npc/Abdu_Smoking_3",gp.tileSize, gp.tileSize);
        left1 = setup("/npc/Abdu_Smoking_1",gp.tileSize, gp.tileSize);
        left2 = setup("/npc/Abdu_Smoking_2",gp.tileSize, gp.tileSize);
        left3 = setup("/npc/Abdu_Smoking_3",gp.tileSize, gp.tileSize);
        right1 = setup("/npc/Abdu_Smoking_1",gp.tileSize, gp.tileSize);
        right2 = setup("/npc/Abdu_Smoking_2",gp.tileSize, gp.tileSize);
        right3 = setup("/npc/Abdu_Smoking_3",gp.tileSize, gp.tileSize);



    }

    public void update(){

        if(!sleep){
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
                if(spriteCounter > 60){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 3;
                    }else if(spriteNum == 3){
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

    public void setDialogue(){

        dialogues[0][0] = "Hey man How you doing?";

        dialogues[1][0] = "Just chilling here..";
        dialogues[1][1] = "Damn bro you got gun!!";


    }

    public void setAction(){


    }

    public void speak(){

        facePlayer();
        startDialogue(this,dialogueSet);
        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }
        //onPath = true;

    }
}
