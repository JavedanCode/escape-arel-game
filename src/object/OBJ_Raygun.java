package object;

import entity.Entity;
import gun.GUN_Raygun;
import main.GamePanel;

public class OBJ_Raygun extends Entity {
    public static final String objName = "Raygun";

    public OBJ_Raygun(GamePanel gp) {
        super(gp);
        bullet = new GUN_Raygun(gp);
        name = objName;
        down1 = setup("/objects/raygun",gp.tileSize,gp.tileSize);
        attackValue = bullet.attack;
        description = "["+ name +"]\nPew Pew";
        type = type_gun;
        price = 5;
        knockBackPower = 0;
        motion1_duration = 5;
        motion2_duration = 25;
        maxBullet = 1000;
    }
}
