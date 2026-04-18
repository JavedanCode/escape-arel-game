package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_FootballField extends InteractiveTile {

    GamePanel gp;

    public DEC_FootballField(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        //this image is 76x111 , I need to adjust the width and height
        down1 = setup("/assets/football_field",gp.tileSize*18,gp.tileSize*11);
        //I need to adjust the solid area
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*18;
        solidArea.height = gp.tileSize*11;


    }
}
