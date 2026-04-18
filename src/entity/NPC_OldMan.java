package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
//This NPC was in an earlier version of the game.
public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(8,16,40,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;


        direction = "down";
        speed = 1;

        dialogueSet = -1;
        getImage();
        setDialogue();
    }

    public void getImage(){
        up1 = setup("/npc/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2",gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogues[0][0] = "Hello mother fu..\nI mean Hello adventurer!";
        dialogues[0][1] = "I used to break dance in\nthe olympics\n:)";
        dialogues[0][2] = "I met this Turkish Dad \nin the olympics and bro just shot the \nbullseye with one hand in his pocket\n:o";
        dialogues[0][3] = "A dude beat the shit out of a women in\nthe olympics and they gave him a medal..\nthat shit was sick!";


        dialogues[1][0] = "If you're tired, then sleep..";
        dialogues[1][1] = "The monsters reappear when you drink water.";
        dialogues[1][2] = "In any case, it is what it is.";


        dialogues[2][0] = "I wonder where the merchant came from.";

    }

    public void setAction(){

        if(onPath){

            /*int goalCol = 12;
            int goalRow = 9;*/
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol,goalRow);

        }else{
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
