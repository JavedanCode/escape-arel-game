package monster;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Pistol;

public class MON_FirstZombie extends Entity {
    GamePanel gp;

    public static final String monName = "FirstZombie";

    public MON_FirstZombie(GamePanel gp) {
        super(gp);

        this.gp = gp;
        direction = "left";
        name = monName;
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        type = type_monster;
        attack = 2;
        defense = 1;
        exp = 0;
        sleep = true;
        onPath = false;


        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        knockBackPower = 1;



        getImage();
        setDialogue();


    }

    public void setDialogue(){

    }

    public void damageReaction(){

        actionLockCounter = 0;
        onPath = true;

    }

    public void getImage(){

        up1 = setup("/monster/zombie_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/zombie_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/zombie_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/zombie_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/zombie_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/zombie_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/zombie_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/zombie_right_2",gp.tileSize, gp.tileSize);

    }


    public void setAction(){

        if(onPath){

            //Check if it stops chasing
            /*checkStopChase(gp.player,15,100);*/

            checkCollision();
            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));


        }
        else{

            //Check if it should start chasing
            checkStartChase(gp.player,5,100);
            //Get a random direction
            getRandomDirection(120);

        }
        if(!attacking){
            checkAttack(30,gp.tileSize*4,gp.tileSize);
        }
    }

    public void checkDrop(){

        Progress.zombieKilled = true;
        gp.tManager.taskNum = gp.tManager.taskGetYourStuff;
        gp.tManager.checkTask();
        gp.player.startDialogue(gp.player,1);

        gp.stopMusic();
        gp.playMusic(26);

    }
}
