package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
    public static final String objName = "Lantern";
    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        name = objName;
        type = type_light;
        value = 5;
        description = "A Lantern";
        price = 30;
        down1  = setup("/objects/lantern",gp.tileSize,gp.tileSize);
        lightRadius = 400;

    }

}
