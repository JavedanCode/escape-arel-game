package buildings;

import main.GamePanel;
import tile_interactive.InteractiveTile;

import java.awt.*;

public class Building extends InteractiveTile {
    GamePanel gp;

    public Building(GamePanel gp, int col, int row, String imagePath, int width, int height, Rectangle solidArea) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        this.solidArea = solidArea;

        // Load and scale the image
        down1 = setup(imagePath, width, height);
        destructible = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (inCamera()) {
            g2.drawImage(down1, screenX, screenY, null);
        }
    }

    @Override
    public boolean inCamera() {
        return worldX + gp.tileSize * 50 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 50 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
    }


}