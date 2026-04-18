package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Safe extends Entity {

    GamePanel gp;
    public static final String objName = "Safe";

    private final int code = 1234;

    public OBJ_Safe(GamePanel gp){
        super(gp);
        this.gp = gp;


        type = type_obstacle;
        name = objName;
        image = setup("/puzzles/safe",gp.tileSize, gp.tileSize);
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

    }


    public void interact(){
        gp.ui.drawSafeScreen();
        System.out.println("Safe screen drawn");

    }}

