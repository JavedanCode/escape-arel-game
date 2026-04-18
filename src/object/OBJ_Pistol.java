package object;

import entity.Entity;
import gun.GUN_Pistol;
import gun.GUN_Raygun;
import main.GamePanel;

public class OBJ_Pistol extends Entity {
    public static final String objName = "Pistol";

    public OBJ_Pistol(GamePanel gp) {
        super(gp);
        bullet = new GUN_Pistol(gp);
        name = objName;
        down1 = setup("/objects/pistol",gp.tileSize,gp.tileSize);
        attackValue = bullet.attack;
        description = "A Pistol...";
        type = type_gun;
        price = 5;
        knockBackPower = 0;
        motion1_duration = 5;
        motion2_duration = 25;
        maxBullet = 30;
    }
}
