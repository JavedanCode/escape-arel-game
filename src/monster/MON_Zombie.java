package monster;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Key;

public class MON_Zombie extends Entity {
    GamePanel gp;

    public static final String monName = "Zombie";

    public MON_Zombie(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        type = type_monster;
        attack = 1;
        defense = 1;

        onPath = true;


        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        knockBackPower = 1;



        getImage();
        setDialogue();
        getAttackImage();


    }

    public void setDialogue(){

        dialogues[0][0]= "ARGGG!!!";
        dialogues[0][1] = "I NEED TO GET OUT OF HERE!!";
    }

    public void damageReaction(){

        actionLockCounter = 0;

        onPath = true;
    }

    public void getImage(){

        up1 = setup("/monster/zombie_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/zombie_up_2",gp.tileSize, gp.tileSize);
        up3 = setup("/monster/zombie_up_1",gp.tileSize, gp.tileSize);
        up4 = setup("/monster/zombie_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/zombie_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/zombie_down_2",gp.tileSize, gp.tileSize);
        down3 = setup("/monster/zombie_down_1",gp.tileSize, gp.tileSize);
        down4 = setup("/monster/zombie_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/zombie_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/zombie_left_2",gp.tileSize, gp.tileSize);
        left3 = setup("/monster/zombie_left_1",gp.tileSize, gp.tileSize);
        left4 = setup("/monster/zombie_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/zombie_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/zombie_right_2",gp.tileSize, gp.tileSize);
        right3 = setup("/monster/zombie_right_1",gp.tileSize, gp.tileSize);
        right4 = setup("/monster/zombie_right_2",gp.tileSize, gp.tileSize);


    }

    public void getAttackImage(){

        attackUp1 = setup("/monster/zombie_up_1",gp.tileSize, gp.tileSize);
        attackUp2 = setup("/monster/zombie_up_1",gp.tileSize, gp.tileSize);
        attackDown1 = setup("/monster/zombie_down_1",gp.tileSize, gp.tileSize);
        attackDown2 = setup("/monster/zombie_down_1",gp.tileSize, gp.tileSize);
        attackLeft1 = setup("/monster/zombie_left_1",gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/monster/zombie_left_1",gp.tileSize, gp.tileSize);
        attackRight1 = setup("/monster/zombie_right_1",gp.tileSize, gp.tileSize);
        attackRight2 = setup("/monster/zombie_right_1",gp.tileSize, gp.tileSize);

    }


    public void setAction(){

        if(onPath){

            /*//Check if it stops chasing
            checkStopChase(gp.player,15,100);*/

            //search the direction to go
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

}

