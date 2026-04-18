package buildings;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class BUILD_School extends InteractiveTile {

    GamePanel gp;

    public BUILD_School(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/buildings/School",gp.tileSize*17,gp.tileSize*14);
        destructible = false;
        solidArea = new Rectangle(gp.tileSize/3,gp.tileSize/3,gp.tileSize*17-gp.tileSize/2,gp.tileSize*12-gp.tileSize/3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;





    }
}
