package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Tuberk extends Entity{
    public static final String npcName = "Tuberk";

    public NPC_Tuberk(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(8,16,40,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;
        name = npcName;


        speed = 1;

        dialogueSet = -1;
        getImage();
        setDialogue();
    }

    public void getImage(){

        //there are 8 images for each direction, the directory for it is tuberk/tuberk_[direction]_[1-8].png
        down1 = setup("/npc/tuberk_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/npc/tuberk_down_2",gp.tileSize, gp.tileSize);
        down3 = setup("/npc/tuberk_down_3",gp.tileSize, gp.tileSize);
        down4 = setup("/npc/tuberk_down_4",gp.tileSize, gp.tileSize);
        down5 = setup("/npc/tuberk_down_5",gp.tileSize, gp.tileSize);
        down6 = setup("/npc/tuberk_down_6",gp.tileSize, gp.tileSize);
        down7 = setup("/npc/tuberk_down_7",gp.tileSize, gp.tileSize);
        down8 = setup("/npc/tuberk_down_8",gp.tileSize, gp.tileSize);

        left1 = setup("/npc/tuberk_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/npc/tuberk_left_2",gp.tileSize, gp.tileSize);
        left3 = setup("/npc/tuberk_left_3",gp.tileSize, gp.tileSize);
        left4 = setup("/npc/tuberk_left_4",gp.tileSize, gp.tileSize);
        left5 = setup("/npc/tuberk_left_5",gp.tileSize, gp.tileSize);
        left6 = setup("/npc/tuberk_left_6",gp.tileSize, gp.tileSize);
        left7 = setup("/npc/tuberk_left_7",gp.tileSize, gp.tileSize);
        left8 = setup("/npc/tuberk_left_8",gp.tileSize, gp.tileSize);

        right1 = setup("/npc/tuberk_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/npc/tuberk_right_2",gp.tileSize, gp.tileSize);
        right3 = setup("/npc/tuberk_right_3",gp.tileSize, gp.tileSize);
        right4 = setup("/npc/tuberk_right_4",gp.tileSize, gp.tileSize);
        right5 = setup("/npc/tuberk_right_5",gp.tileSize, gp.tileSize);
        right6 = setup("/npc/tuberk_right_6",gp.tileSize, gp.tileSize);
        right7 = setup("/npc/tuberk_right_7",gp.tileSize, gp.tileSize);
        right8 = setup("/npc/tuberk_right_8",gp.tileSize, gp.tileSize);

        up1 = setup("/npc/tuberk_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/npc/tuberk_up_2",gp.tileSize, gp.tileSize);
        up3 = setup("/npc/tuberk_up_3",gp.tileSize, gp.tileSize);
        up4 = setup("/npc/tuberk_up_4",gp.tileSize, gp.tileSize);
        up5 = setup("/npc/tuberk_up_5",gp.tileSize, gp.tileSize);
        up6 = setup("/npc/tuberk_up_6",gp.tileSize, gp.tileSize);
        up7 = setup("/npc/tuberk_up_7",gp.tileSize, gp.tileSize);
        up8 = setup("/npc/tuberk_up_8",gp.tileSize, gp.tileSize);




    }
    @Override
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
            if(spriteCounter > 10){
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
                    spriteNum = 5;
                }
                else if(spriteNum == 5){
                    spriteNum = 6;
                }
                else if(spriteNum == 6){
                    spriteNum = 7;
                }
                else if(spriteNum == 7){
                    spriteNum = 8;
                }
                else if(spriteNum == 8){
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
        actionLockCounter ++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100

            if(i <=25){
                direction = "up";

            }
            if(i > 25 && i <= 50){
                direction ="down";

            }
            if(i > 50 && i <= 75){
                direction = "left";

            }
            if(i > 75 && i <= 100 ){
                direction = "right";
            }

            actionLockCounter =0;
        }

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
