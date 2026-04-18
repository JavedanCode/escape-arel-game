package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_Flowers_Pink extends InteractiveTile {

    GamePanel gp;

    public DEC_Flowers_Pink(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        //this image is 42x22 , I need to adjust the width and height
        down1 = setup("/assets/flowers_pink",gp.tileSize,gp.tileSize);
        //I need to adjust the solid area
        solidArea = new Rectangle(0,0,0,0);


    }
}
