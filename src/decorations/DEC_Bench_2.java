package decorations;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class DEC_Bench_2 extends InteractiveTile {

    GamePanel gp;

    public DEC_Bench_2(GamePanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;


        down1 = setup("/assets/bench_2",gp.tileSize*2,gp.tileSize);

        solidArea = new Rectangle(0,0,gp.tileSize*2,gp.tileSize);



    }
}
