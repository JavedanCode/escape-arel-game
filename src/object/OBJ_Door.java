package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gp;

    public static final String objName = "Door";
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = objName;
        down1 = setup("/assets/doors",gp.tileSize*2, gp.tileSize*3);
        collision = true;

        solidArea.x = 0;
        solidArea.y = gp.tileSize/2;
        solidArea.width = gp.tileSize*2;
        solidArea.height = gp.tileSize*2+gp.tileSize/2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_obstacle;

        setDialogue();
    }
    public void setDialogue(){
        dialogues [0][0] = "Locked";
    }
    public void interact(){
        startDialogue(this,0);
    }
}
