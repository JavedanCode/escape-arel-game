package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    public static final String objName = "Red Potion";
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;


        name = objName;
        down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
        type = type_consumable;
        value = 5;
        description = "[Healing Potion]\nA Potion That Gets You\nHigh AF";
        price = 10;
        stackable = true;
        setDialogue();

    }
    public void setDialogue(){
        dialogues[0][0] = "You Just Got High Bero :o";
    }

    public boolean use(Entity entity){
        startDialogue(this,0);
        entity.life += value;
        gp.playSE(2);
        return true;

    }
}
