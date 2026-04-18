package buildings;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class BUILD_University extends Building {


    public BUILD_University(GamePanel gp, int col, int row) {
        super(gp, col, row, "/buildings/University", gp.tileSize * 22, gp.tileSize * 25, new Rectangle(0, 0, gp.tileSize * 22, gp.tileSize * 20));

    }
}
