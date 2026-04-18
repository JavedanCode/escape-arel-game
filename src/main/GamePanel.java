package main;

import Task.Task;
import Task.TaskManager;
import ai.PathFinder;
import buildings.Building;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import light_source.Light_Source;
import tile_interactive.InteractiveTile;
import tiles.Map;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize*scale; // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol; //960 pixels
    public final int screenHeight = tileSize*maxScreenRow; //576 pixels

    //WORLD SETTINGS
    public  int maxWorldCol ;
    public int maxWorldRow ;
    public final int maxMap = 10;
    public int currentMap = 0;
    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //AREA STATE
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int classes= 51;


    //FPS
    int FPS = 60;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler  keyH = new KeyHandler(this);
    public MouseListener mouseL = new MouseListener(this);
    public EventHandler eHandler = new EventHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public PathFinder pFinder = new PathFinder(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public Task tManager = new Task(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    SaveLoad saveLoad = new SaveLoad(this);
    Map map = new Map(this);
    Sound music = new Sound();
    Sound se = new Sound();
    Config config = new Config(this);
    Thread gameThread;
    public int musicIndex = 0;



    //ENTITY AND OBJECT
    public  Player player = new Player(this,keyH,mouseL);
    public Entity[][] obj = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monster = new Entity[maxMap][80];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][250];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    public Entity[][] projectileList = new Entity[maxMap][20];



    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;

    //OTHERS
    public boolean bossBattleOn = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //I had to add a key listener to be able to use the key handler and now if I want to use a MouseInputListener I have to add a mouse listener
        this.addMouseListener(mouseL);
        this.addMouseMotionListener(mouseL);
        this.setFocusable(true);
    }
    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount =0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            timer+= (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >= 1){

                update();
                drawToTempScreen();
                drawToScreen();
                delta--;
                drawCount++;

            }
            if(timer >= 1000000000){
                //System.out.println("FPS: " + drawCount); //DISPLAY FPS
                drawCount= 0;
                timer =0;
            }
        }
    }
    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();

        aSetter.setInteractiveTiles();


        eManager.setup();
        playMusic(26);
        stopMusic();

        gameState = titleState;

        currentArea = outside;

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if(fullScreenOn){
            setFullScreen();
        }

    }
    public void changeArea(){

        /*if(nextArea != currentArea){
            stopMusic();
            if(nextArea == outside){
                playMusic(26);
            }
            if(nextArea == classes){
                playMusic(26);
            }
        }*/
        currentArea = nextArea;
        aSetter.setMonster();
        aSetter.setNPC();
    }
    public void resetGame(boolean restart){


        currentArea = outside;

        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPosition();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
       // aSetter.setMonster();
        aSetter.setInteractiveTiles();
        aSetter.setObject();
        if(restart){
            player.setDefaultValues();
            eManager.lighting.resetDay();
        }

    }
    public void setFullScreen(){

        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET SCREEN WIDTH AND SCREEN HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }
    public void update(){

        //LIGHTS


        if(gameState == playState){
        //PLAYER
            player.update();

        //NPC
            for(int i = 0 ; i < npc[1].length; ++i){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            //INTERACTIVE TILES
            for(int i = 0 ; i < iTile[1].length; ++i){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

        //MONSTER
            for(int i = 0 ; i < monster[1].length; ++i){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying){

                        monster[currentMap][i].update();
                    }if(!monster[currentMap][i].alive){
                        monster[currentMap][i].checkDrop();
                    monster[currentMap][i] = null;
                }}
            }
        }if(gameState == pauseState){
            //nothing
        }
        //PROJECTILE
        for(int i = 0 ; i < projectileList[1].length; ++i){
            if(projectileList[currentMap][i] != null){
                if(projectileList[currentMap][i].alive){
                    projectileList[currentMap][i].update();
                }if(!projectileList[currentMap][i].alive){
                    projectileList[currentMap][i] = null;
                }
            }
        }



        for(int i = 0 ; i < particleList.size(); ++i){
            if(particleList.get(i) != null){
                if(particleList.get(i).alive){
                    particleList.get(i).update();
                }if(!particleList.get(i).alive){
                    particleList.remove(i);
                }
            }
        }
        eManager.update();

    }
    public void drawToTempScreen(){

        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugText){
            drawStart = System.nanoTime();
        }

        //TItLE SCREEN
        if(gameState == titleState){

            ui.draw(g2);

        }
        // MAP SCREEN
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }
        else{
            //TILE
            tileM.draw(g2);

            //INTERACTIVE TILES
            for(int i = 0 ; i < iTile[1].length ; i++){
                if(iTile[currentMap][i] != null){
                   entityList.add(iTile[currentMap][i]);
                }
            }


            // ADD ENTITIES TO THE LIST
            entityList.add(player);
            for(int i = 0; i < npc[1].length; ++i){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i = 0; i < obj[1].length; ++i){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i = 0; i < monster[1].length; ++i){
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }

            for(int i = 0; i < projectileList[1].length; ++i){
                if(projectileList[currentMap][i] != null){
                    entityList.add(projectileList[currentMap][i]);
                }
            }

            for(int i = 0; i < particleList.size(); ++i){
                if(particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); ++i){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();



            //ENVIRONMENT
            eManager.draw(g2);

            //MINI MAP
            map.drawMiniMap(g2);

            //TASK
            tManager.drawTaskWindow(g2);

            // CUTSCENE
            csManager.draw(g2);

            //UI
            ui.draw(g2);


        }
        //DEBUG
        if(keyH.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            String godMode ="";
            if(keyH.godMode){
                godMode = "ON";
            }else{
                godMode = "OFF";
            }

            g2.drawString("WorldX: " + player.worldX,x,y); y+=lineHeight;
            g2.drawString("WorldY: " + player.worldY,x,y); y+=lineHeight;
            g2.drawString("Col: " + (player.worldX+player.solidArea.x)/tileSize,x,y); y+=lineHeight;
            g2.drawString("Row: " + (player.worldY+player.solidArea.y)/tileSize,x,y); y+=lineHeight;
            g2.drawString("Draw Time: " + passed, x, y); y+=lineHeight;
            g2.drawString("God Mode:" + godMode,x,y); y+=lineHeight;


        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    public void playMusic(int i){
        musicIndex = i;
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void stopSE(){
        se.stop();
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();

    }
    public void removeTempEntity(){

        for(int mapNum = 0; mapNum < maxMap; ++mapNum){
            for(int i = 0; i < obj[1].length; ++i){
                if(obj[mapNum][i] != null&&obj[mapNum][i].temp){
                        obj[mapNum][i] = null;
                }
            }
        }
    }

}
