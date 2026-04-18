package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bullets extends Entity {

    GamePanel gp;
    public static final String objName = "Ammo";
    public OBJ_Bullets(GamePanel gp) {


        super(gp);
        this.gp = gp;


        name = objName;
        image = setup("/objects/bullets",gp.tileSize,gp.tileSize);

        type = type_pickupOnly;
        value = 1;
        down1 = image = setup("/objects/bullets",gp.tileSize,gp.tileSize);
        price = 10;




    }

    public boolean use(Entity entity) {

        gp.playSE(2);
        entity.bullets += value*10;
        return true;

    }
}
