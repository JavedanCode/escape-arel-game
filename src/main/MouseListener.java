package main;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MouseListener implements MouseInputListener {

    GamePanel gp;
    public boolean shootPressed = false;
    public boolean aimPressed = false;
    public double mouseX, mouseY;
    public double targetX, targetY;
    private long lastClickTime = 0;

    public MouseListener(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) { //this is for when the mouse is clicked for example when the mouse is pressed and released like the left click


        long clickTime = System.currentTimeMillis();

        if (gp.gameState == gp.characterState) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            handleInventoryClick(mouseX, mouseY);

            if (clickTime - lastClickTime < 500) { // Double-click detected
                handleInventoryDoubleClick(mouseX, mouseY);
            }
            lastClickTime = clickTime;
        }

    }

    public void playState(int code){

        if (code == MouseEvent.BUTTON1 && aimPressed) {
            shootPressed = true;
        }
        else if (code == MouseEvent.BUTTON3) {
            aimPressed = true;
            targetX = mouseX; // Capture the reticle's dot position
            targetY = mouseY;
        }
    }

    public void characterState( MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        handleInventoryClick(mouseX, mouseY);

    }

    private void handleInventoryDoubleClick(int mouseX, int mouseY) {
        int slotWidth = gp.tileSize + 2;
        int slotHeight = gp.tileSize + 2;
        int inventoryStartX = 183;
        int inventoryStartY = 134;


        if(gp.fullScreenOn){
            slotWidth = 82;
            slotHeight = 82;
            inventoryStartX = 291;
            inventoryStartY = 201;
        }


        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                int slotX = inventoryStartX + col * slotWidth;
                int slotY = inventoryStartY + row * slotHeight;


                if (mouseX >= slotX && mouseX < slotX + slotWidth &&
                        mouseY >= slotY && mouseY < slotY + slotHeight) {
                    if (row == gp.ui.playerSlotRow && col == gp.ui.playerSlotCol) {

                        performDoubleClickAction();
                    }
                    return;
                }
            }
        }
    }
    private void performDoubleClickAction() {

        gp.player.selectItems();

    }
    private void handleInventoryClick(int mouseX, int mouseY) {
        int slotWidth = gp.tileSize + 2;
        int slotHeight = gp.tileSize + 2;
        int inventoryStartX = 183;
        int inventoryStartY = 134;
        System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);

        if(gp.fullScreenOn){
            slotWidth = 82;
            slotHeight = 82;
            inventoryStartX = 291;
            inventoryStartY = 201;
        }

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                int slotX = inventoryStartX + col * slotWidth;
                int slotY = inventoryStartY + row * slotHeight;



                if (mouseX >= slotX && mouseX < slotX + slotWidth &&
                        mouseY >= slotY && mouseY < slotY + slotHeight) {
                    gp.ui.playerSlotRow = row;
                    gp.ui.playerSlotCol = col;
                    gp.playSE(9);
                    return;
                }
            }
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

        int code  = e.getButton();

        if(gp.gameState == gp.playState ){
            playState(code);

        }else if(gp.gameState == gp.characterState){
            characterState(e);
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            shootPressed = false;
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            aimPressed = false;

        }

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) { //this is for when the mouse enters the window
        // TODO Auto-generated method stub


    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        adjustCursor(e);


    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

        adjustCursor(e);

    }

    private void adjustCursor(MouseEvent e) {
        int screenWidth = gp.screenWidth2;
        int screenHeight = gp.screenHeight2;
        int originalWidth = gp.screenWidth;
        int originalHeight = gp.screenHeight;

        double scaleX = (double) screenWidth / originalWidth;
        double scaleY = (double) screenHeight / originalHeight;

        if (gp.fullScreenOn) {
            mouseX = e.getX() / scaleX;
            mouseY = e.getY() / scaleY;
        } else {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        targetX = mouseX;
        targetY = mouseY;


    }
}
