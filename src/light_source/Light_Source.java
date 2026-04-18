package light_source;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class Light_Source extends InteractiveTile {

    GamePanel gp;



    public Light_Source(GamePanel gp, int col, int row){
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        this.lightRadius = 200;
        lightSource = true;

        destructible = false;
    }




}
