package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gp){
        super(gp);

        name = objName;
        down1 = setup("/objects/axe",gp.tileSize,gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+ name +"]\nCut shit down with it";
        type = type_melee;
        price = 15;
        knockBackPower = 10;
        motion1_duration = 15;
        motion2_duration = 35;

    }
}
