package object;

import data.Progress;
import entity.Entity;
import main.GamePanel;

public class OBJ_ChestWithStuff  extends Entity {

    GamePanel gp;
    public static final String objName = "Chest";

    public OBJ_ChestWithStuff(GamePanel gp){
        super(gp);
        this.gp = gp;


        type = type_obstacle;
        name = objName;
        image = setup("/objects/chest",gp.tileSize, gp.tileSize);
        image2 =setup("/objects/chest_opened",gp.tileSize, gp.tileSize);
        down1 = image; //down1 is the default image
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


    }

    public void setLoot(Entity loot){
        this.loot = loot;

        setDialogue();
    }

    public void setDialogue(){

        dialogues[0][0] = "You found your stuff!";
        dialogues[2][0] = "You already took your stuff.";
    }


    public void interact(){

        if(!opened){
            gp.playSE(3);

            startDialogue(this,0);
            down1 = image2;
            opened = true;
            Progress.stuffTaken = true;
            gp.tManager.taskNum = gp.tManager.taskLeaveUni;
            gp.tManager.checkTask();
        }
        else{
            startDialogue(this,2);
        }
    }
}
