package main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        new Main().setIcon();


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn ){
            window.setUndecorated(true);

        }

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Set the invisible cursor
        setCustomCursor(window);

        centerMouseCursor(window);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public void setIcon(){

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player_standing/standing_down.png"));
        window.setIconImage(icon.getImage());

    }

    private static void setCustomCursor(JFrame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Ensure the path is correct and the file exists
        ImageIcon url = new ImageIcon(Main.class.getClassLoader().getResource("Cursor/Arel_Cursor.png"));

        Image image = url.getImage();
        Point hotspot = new Point(0, 0);
        Cursor customCursor = toolkit.createCustomCursor(image, hotspot, "CustomCursor");
        frame.setCursor(customCursor);
    }

    private static void centerMouseCursor(JFrame frame) {
        try {
            Robot robot = new Robot();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int centerX = screenSize.width / 2;
            int centerY = screenSize.height / 2;
            robot.mouseMove(centerX, centerY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    

}

