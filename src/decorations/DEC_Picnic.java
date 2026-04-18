package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_Picnic  extends InteractiveTile {

    GamePanel gp;

    public DEC_Picnic(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        //this image is 48x32 , I need to adjust the width and height
        down1 = setup("/assets/picnic",gp.tileSize*2,gp.tileSize);
        //I need to adjust the solid area
        solidArea = new Rectangle(0,0,gp.tileSize*2,gp.tileSize);


    }
}
