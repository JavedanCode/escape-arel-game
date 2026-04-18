package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

    public static final String npcName = "Dummy";

    public PlayerDummy(GamePanel gp){
        super(gp);

        name = npcName;

        setDialogue();

        getImage();
    }

    public void setDialogue(){


    }

    public void getImage(){

        up1    = setup("/player_standing/standing_up",gp.tileSize, gp.tileSize);
        up2    = setup("/player_standing/standing_up",gp.tileSize, gp.tileSize);
        down1  = setup("/player_standing/standing_down",gp.tileSize, gp.tileSize);
        down2  = setup("/player_standing/standing_down",gp.tileSize, gp.tileSize);
        left1  = setup("/player_standing/standing_left",gp.tileSize, gp.tileSize);
        left2  = setup("/player_standing/standing_left",gp.tileSize, gp.tileSize);
        right1 = setup("/player_standing/standing_right",gp.tileSize, gp.tileSize);
        right2 = setup("/player_standing/standing_right",gp.tileSize, gp.tileSize);



    }
}
