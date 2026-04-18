package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {

    GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = objName;
        type = type_consumable;
        value = 1;
        description = "Sleep\nto skip the night";
        price = 50;
        down1  = setup("/objects/tent",gp.tileSize,gp.tileSize);

    }

    public boolean use(Entity entity) {

        gp.playSE(14);
        gp.gameState = gp.sleepState;
        gp.player.life = gp.player.maxLife;
        gp.player.bullets = gp.player.maxBullet;
        gp.player.getSleepingImage(down1);
        return true;

    }
}
