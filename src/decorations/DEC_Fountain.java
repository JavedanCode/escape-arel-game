package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_Fountain  extends InteractiveTile {

    GamePanel gp;

    public DEC_Fountain(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        //this image is 42x22 , I need to adjust the width and height
        down1 = setup("/assets/fountain",gp.tileSize*4,gp.tileSize*4);
        //I need to adjust the solid area
        solidArea = new Rectangle(0,gp.tileSize/2,gp.tileSize*4,gp.tileSize*3+gp.tileSize/2);
        solidAreaDefaultY = solidArea.y;


    }
}
