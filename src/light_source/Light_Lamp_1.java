package light_source;

import main.GamePanel;

import java.awt.*;

public class Light_Lamp_1 extends Light_Source{

    public Light_Lamp_1(GamePanel gp, int col, int row){

        super(gp,col,row);

        down1 = setup("/assets/lamp_1",gp.tileSize,gp.tileSize*2);

        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize*2);

        lightRadius = 500;


    }
}
