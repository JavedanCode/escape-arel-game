package buildings;

import main.GamePanel;

import java.awt.*;

public class BUILD_Security extends Building {


    public BUILD_Security(GamePanel gp, int col, int row) {
        super(gp, col, row, "/assets/security", gp.tileSize * 4, gp.tileSize * 5, new Rectangle(0, 0, gp.tileSize * 4, gp.tileSize * 5));

    }
}
