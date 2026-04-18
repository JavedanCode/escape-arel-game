package entity;

import main.GamePanel;
import object.*;

import java.awt.*;
import java.util.Random;

public class NPC_Merchant extends  Entity{


    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";

        solidArea = new Rectangle(8,16,40,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_npc;


        getImage();
        setDialogue();
        setItem();



    }

    public void getImage(){
        up1 = setup("/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        up2 = setup("/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        down1 = setup("/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        left2 = setup("/npc/merchant_down_2",gp.tileSize, gp.tileSize);
        right1 = setup("/npc/merchant_down_1",gp.tileSize, gp.tileSize);
        right2 = setup("/npc/merchant_down_2",gp.tileSize, gp.tileSize);

    }

    public void setDialogue(){

        dialogues[0][0] = "What are you buyin?";
        dialogues[1][0] = "Hehehe Thank you";
        dialogues[2][0] = "Not Enough Cash Stranger";
        dialogues[3][0] = "Your Inventory is Full";
        dialogues[4][0] = "You can't sell equipped items";


    }
    public void speak(){

        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

    }

    public void setItem(){

        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe(gp));



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






}
