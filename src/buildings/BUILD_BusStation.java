package buildings;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class BUILD_BusStation extends Building {


    public BUILD_BusStation(GamePanel gp, int col, int row) {
        super(gp, col, row, "/buildings/BusStation", gp.tileSize * 6, gp.tileSize * 4, new Rectangle(0, 0, gp.tileSize * 6, gp.tileSize * 4));

    }
}
