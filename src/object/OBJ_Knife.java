package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Knife extends Entity {
    public static final String objName = "Knife";
    public OBJ_Knife(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/objects/knife",gp.tileSize,gp.tileSize);
        attackValue = 1;
        description = name ;
        attackArea.width = 40;
        attackArea.height = 40;
        type = type_melee;
        price = 5;
        knockBackPower = 1;
        motion1_duration = 5;
        motion2_duration = 20;
    }
}
