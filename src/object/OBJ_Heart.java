package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    GamePanel gp;
    public static final String objName = "Heart";
    public OBJ_Heart(GamePanel gp){
        super(gp);

        this.gp = gp;

        name = objName;
        image = setup("/objects/heart_full",gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half",gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank",gp.tileSize, gp.tileSize);
        down1 = setup("/objects/heart_full",gp.tileSize, gp.tileSize);

        type = type_pickupOnly;
        value = 2;


        collision = true;
    }


    public void setDialogue(){

        dialogues[0][0] = "OMG..";
        dialogues[0][1] = "Professor Vlad...";
        dialogues[0][2] = "I'm sorry...";
        dialogues[0][3] = "I Won't miss your notes though...";

    }

    public boolean use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("+life");
        entity.life += value;
        return true;

    }
}
