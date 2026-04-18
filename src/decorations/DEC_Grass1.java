package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_Grass1 extends InteractiveTile {

    GamePanel gp;

    public DEC_Grass1(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        //this image is 76x111 , I need to adjust the width and height
        down1 = setup("/assets/grass_1",gp.tileSize,gp.tileSize);
        //I need to adjust the solid area
        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);


    }
}
