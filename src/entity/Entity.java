package entity;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Pistol;
import object.OBJ_Raygun;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,
            down1,down2,down3,down4,down5,down6,down7,down8,
            left1,left2,left3,left4,left5,left6,left7,left8,
            right1,right2,right3,right4,right5,right6,right7,right8;
    public BufferedImage standUp,standDown,standLeft,standRight;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1, attackLeft2,attackRight1,attackRight2,guardUp,guardDown,guardLeft,guardRight;
    public BufferedImage attackUp3,attackDown3,attackLeft3,attackRight3;
    public BufferedImage meleeUp1,meleeUp2,meleeDown1,meleeDown2,meleeLeft1,meleeLeft2,meleeRight1,meleeRight2;
    public BufferedImage image ,image2,image3;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String[][] dialogues = new String[20][20];
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public Entity attacker;
    public Entity linkedEntity;
    public boolean temp = false;
    public boolean hasVoiceLine = false;
    public boolean[][] soundPlayed;


    //STATE
    public int worldX;
    public int worldY;
    public String direction = "down";
    public int dialogueIndex = 0;
    public int dialogueSet = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public int spriteNum = 1;
    public boolean attacking = false;
    public boolean shooting = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;
    private boolean projectileShot = false;


    //COUNTER
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;
    public int spriteCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int projectileCoolDown = 0;
    public int knockBackCounter = 0;
    public int guardCounter = 0;
    public int  offBalanceCounter = 0;


    //CHARACTER ATTRIBUTES
    public String name;
    public int maxLife;
    public int life;
    public int defaultSpeed;
    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int meleeAttack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int maxBullet;
    public int bullets;
    public Entity currentLight;
    public Entity currentWeapon;
    public Entity meleeWeapon;
    public Entity currentShield;
    public Gun gun;
    public int motion1_duration;
    public int motion2_duration;
    public boolean boss = false;
    public Gun bullet;
    public boolean shieldOn = false;


    //ITEM ATTRIBUTES
    public int value;
    public int attackValue;
    public String description = "";
    public int useCost;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int price;
    public int knockBackPower;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    public int shieldLife = 0;
    public boolean lightSource = false;

    //TYPE
    public int type ;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_consumable = 3;
    public final int type_pickupOnly = 4;
    public final int type_obstacle = 5;
    public final int type_light = 6;
    public final int type_gun = 7;
    public final int type_melee = 8;


    public Entity(GamePanel gp){
        this.gp = gp;
    }



    // Game Loop
    public void update(){

        if(!sleep){
            if(knockBack){

                checkCollision();

                if(collisionOn){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
                else{
                    switch (knockBackDirection){
                        case "up"   : worldY-=speed; break;
                        case "down" : worldY+=speed; break;
                        case "left" : worldX-=speed; break;
                        case "right": worldX+=speed; break;
                    }
                }
                knockBackCounter++;
                if(knockBackCounter == 10){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }

            }
            else if(attacking){
                attacking();
            }else{
                setAction();
                checkCollision();

                //IF COLLISION IS FALSE PLAYER CAN'T MOVE
                if(!collisionOn){
                    switch (direction){
                        case "up"   : worldY-=speed; break;
                        case "down" : worldY+=speed; break;
                        case "left" : worldX-=speed; break;
                        case "right": worldX+=speed; break;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 24){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if(invincible){
                invincibleCounter++ ;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter=0;
                }
            }
            if(projectileCoolDown < 30){
                projectileCoolDown++;
            }
            if(offBalance){
                offBalanceCounter++;
                if(offBalanceCounter > 60){
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }



    }


    public void playDialogue(int dialogueIndex, int dialogueSet){}

    public boolean inCamera(){
        boolean inCamera = worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
        return inCamera;
        }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (inCamera()){

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {

                case "up":
                    if(!attacking){
                        if(spriteNum == 1){image = up1; }
                        if(spriteNum == 2){image = up2;}
                        if(spriteNum == 3){image = up3;}
                        if(spriteNum == 4){image = up4;}
                        if(spriteNum == 5){image = up5;}
                        if(spriteNum == 6){image = up6;}
                        if(spriteNum == 7){image = up7;}
                        if(spriteNum == 8){image = up8;}
                    }
                    if(attacking){
                        tempScreenY = getScreenY() - up1.getHeight();
                        if(spriteNum == 1){image = attackUp1;}
                        if(spriteNum == 2){image = attackUp2;}
                    }
                    break;
                case "down":
                    if(!attacking){
                        if(spriteNum == 1){ image = down1;}
                        if(spriteNum == 2){ image = down2;}
                        if(spriteNum == 3){ image = down3;}
                        if(spriteNum == 4){ image = down4;}
                        if(spriteNum == 5){ image = down5;}
                        if(spriteNum == 6){ image = down6;}
                        if(spriteNum == 7){ image = down7;}
                        if(spriteNum == 8){ image = down8; }
                    }
                    if(attacking){
                        if(spriteNum == 1){image = attackDown1;}
                        if(spriteNum == 2){image = attackDown2;}
                    }break;
                case "left" :
                    if(!attacking){
                        if(spriteNum == 1){ image = left1; }
                        if(spriteNum == 2){ image = left2; }
                        if(spriteNum == 3){ image = left3; }
                        if(spriteNum == 4){ image = left4; }
                        if(spriteNum == 5){ image = left5; }
                        if(spriteNum == 6){ image = left6; }
                        if(spriteNum == 7){ image = left7; }
                        if(spriteNum == 8){ image = left8;}
                    }
                    if(attacking){
                        tempScreenX = getScreenX() - left1.getWidth();
                        if(spriteNum == 1){image = attackLeft1;}
                        if(spriteNum == 2){image = attackLeft2;}
                    }break;
                case "right" :
                    if(!attacking){
                        if(spriteNum == 1){ image = right1;}
                        if(spriteNum == 2){image = right2; }
                        if(spriteNum == 3){ image = right3; }
                        if(spriteNum == 4){ image = right4; }
                        if(spriteNum == 5){ image = right5; }
                        if(spriteNum == 6){ image = right6; }
                        if(spriteNum == 7){ image = right7; }
                        if(spriteNum == 8){ image = right8;}
                    }

                    if(attacking){

                        if(spriteNum == 1){image = attackRight1;}
                        if(spriteNum == 2){image = attackRight2;}
                    }break;
            }




            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);
            }
            if(dying){
                dyingAnimation(g2);
            }


            g2.drawImage(image,tempScreenX,tempScreenY,null);
            changeAlpha(g2,1f);}

    }
    public void changeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }


    // Setup Images
    public BufferedImage setup(String imagePath,int width,int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaledImage(image,width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }


    // Set Chest Loot
    public void setLoot(Entity loot){

    }

    // Reset Counters
    public void resetCounter(){
        invincibleCounter = 0;
        actionLockCounter = 0;
        spriteCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        projectileCoolDown = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    // Entity Actions and Reactions
    public void setAction(){

    }
    public void damageReaction(){
    }
    public void speak(){}
    public void facePlayer(){
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void startDialogue(Entity entity, int setNum){

        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;

    }
    public void checkDrop(){

    }
    public void checkStopChase(Entity target,int distance,int rate){
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = false;
            }
        }
    }
    public void checkStartChase(Entity target,int distance,int rate){
        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if(i < 50){
                onPath = true;
            }
        }
    }
    public void getRandomDirection(int interval){

        actionLockCounter ++;

        if(actionLockCounter > interval){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){ direction = "up"; }
            if(i > 25 && i <= 50){ direction ="down"; }
            if(i > 50 && i <= 75){ direction = "left"; }
            if(i > 75 && i <= 100){ direction = "right"; }
            actionLockCounter =0;
        }
    }
    public void checkShoot(int rate,int shotInterval){
       int i = new Random().nextInt(rate);


        if(i == 1 && !gun.alive && projectileCoolDown == shotInterval){


            gun.set(worldX,worldY,direction,true,this);

            //CHECK VACANCY
            for(int ii = 0; ii < gp.projectileList[1].length; ii++){
                if(gp.projectileList[gp.currentMap][ii] == null){
                    gp.projectileList[gp.currentMap][ii] = gun;
                    break;
                }
            }
            projectileCoolDown = 0;

        }}
    public void interact(){

    }
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj[1].length;++i){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }

    }
    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= motion1_duration){
            spriteNum = 1;

        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration){
            spriteNum = 2;

            //SAVE THE CURRENT WORLD POSITION
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //ADJUST PLAYER'S WORLD POSITION TO THEIR ATTACK AREA TEMPORARILY

                switch (direction) {
                    case "up":
                        worldY -= attackArea.height;
                        break;
                    case "down":
                        worldY += attackArea.height;
                        break;
                    case "left":
                        worldX -= attackArea.width;
                        break;
                    case "right":
                        worldX += attackArea.width;
                        break;
                }

                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;



            if(type == type_monster){
                if(gp.cChecker.checkPlayer(this)){
                    damagePlayer(attack);
                }
            }else{
                //check if monster collision has happened with the updated solid areas
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                gp.player.damageMonster(monsterIndex,this,meleeAttack,meleeWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this,gp.projectileList);
                gp.player.damageProjectile(projectileIndex);

            }

            //restore the solid areas
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > motion2_duration){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void shooting(){
        spriteCounter++;


        if(spriteCounter <= motion1_duration){
            spriteNum = 1;

        }
        if(!projectileShot&&gp.player.bullets >= gun.useCost){
            //I want to shoot a projectile and also activate the attacking animation
            //SET DEFAULT COORDINATES, DIRECTION AND USER
            gp.player.gun.set(worldX,worldY,direction,true,this);
            //SUBTRACT COST
            gp.player.gun.subtractResource(this);
            //Check vacancy
            for(int i = 0 ; i < gp.projectileList[1].length;i++){
                if(gp.projectileList[gp.currentMap][i] == null){
                    gp.projectileList[gp.currentMap][i] = gp.player.gun;
                    break;
                }
            }
            projectileShot = true;
            projectileCoolDown = 0;
            if(gp.player.currentWeapon.name.equals(OBJ_Raygun.objName)){
            gp.playSE(23);}
            else if(gp.player.currentWeapon.name.equals(OBJ_Pistol.objName)){
                gp.playSE(24);
            }
        }

        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration){
            spriteNum = 2;
        }
        if(spriteCounter > motion2_duration){
            spriteNum = 1;
            spriteCounter = 0;
            if(gp.player.bullets == 0){

                gp.playSE(25);
            }
            shooting = false;
            projectileShot = false;
        }
    }
    public void checkAttack(int rate,int straight,int horizontal){

        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch(direction){
            case "up"   : if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){targetInRange = true;}break;
            case "down" : if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){ targetInRange = true; } break;
            case "left" : if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){  targetInRange = true; }break;
            case "right": if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){ targetInRange = true; } break;

        }
        if(targetInRange){
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                projectileCoolDown = 0;
            }
        }

    }
    public boolean use(Entity entity){

        return false;
    }
    public int getDetected(Entity user, Entity[][] target, String targetName){

        int index = 999;

        //Check surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction){
            case "up"   : nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down" : nextWorldY = user.getBottomY()+gp.player.speed; break;
            case "left" : nextWorldX = user.getLeftX()-gp.player.speed; break;
            case "right": nextWorldX = user.getRightX()+gp.player.speed; break;

        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int i = 0; i < target[1].length;i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){

                    index = i;
                    break;
                }
            }
        }
        return index;

    }
    public String getOppositeDirection(String direction){
        String oppositeDirection = null;

        switch (direction){
            case "up"   : oppositeDirection = "down"; break;
            case "down" : oppositeDirection = "up"; break;
            case "left" : oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }
    public void damagePlayer(int attack){

        if(!gp.player.invincible){


            int damage = attack - gp.player.defense;

            // Get an opposite direction of this attacker
            String canGuardDirection = getOppositeDirection(direction);

            // If the player is guarding and the attacker is attacking from the opposite direction
            if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)){

                // Parry
                if(gp.player.guardCounter < 10){
                    damage =0;
                    gp.playSE(16);
                    setKnockBack(this,gp.player,knockBackPower);
                    offBalance = true;
                    spriteCounter =- 60;
                }else{
                    damage = damage/3;
                    gp.playSE(15);
                }


            }else{
                gp.playSE(6);
                if(damage < 1 ){
                    damage = 1;
                }
            }
            if(damage!=0){
                gp.player.transparent = true;
                setKnockBack(gp.player,this,knockBackPower);
            }
            gp.player.life -= damage;
            gp.player.invincible = true;

        }
    }
    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;
        int i = 5;

        if(dyingCounter <= i){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2,1f);}
        if(dyingCounter > i && dyingCounter <= i*3){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*4){changeAlpha(g2,1f);}
        if(dyingCounter > i && dyingCounter <= i*5){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*6){changeAlpha(g2,1f);}
        if(dyingCounter > i && dyingCounter <= i*7){changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*8){changeAlpha(g2,1f);}
        if(dyingCounter > i*8){
            alive = false;
        }

    }
    public void setKnockBack(Entity target,Entity attacker, int knockBackPower){

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;

    }
    public void moveTowardsPlayer(int interval){

        actionLockCounter++;

        if(actionLockCounter > interval) {


            if (getXDistance(gp.player) > getYDistance(gp.player)) {

                if(gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }else{
                    direction = "right";
                }

            } else if (getXDistance(gp.player) < getYDistance(gp.player)) {

                if(gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }else{
                    direction = "down";
                }

            } actionLockCounter = 0;

        }
    }


    // Position and Distance
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol(){
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getXDistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYDistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getCenterX(){
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY = worldY + up1.getHeight()/2;
        return centerY;
    }
    public int getTileDistance(Entity target){

        int tileDistance = (getXDistance(target) + getYDistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getScreenX(){
        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }

    //Particles
    public void generateParticle(Entity generator, Entity target){

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();


        Particle p1 = new Particle(gp,target,color,size,speed,maxLife,-2,-1);
        Particle p2 = new Particle(gp,target,color,size,speed,maxLife,2,-1);
        Particle p3 = new Particle(gp,target,color,size,speed,maxLife,-2,1);
        Particle p4 = new Particle(gp,target,color,size,speed,maxLife,2,1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }
    public Color getParticleColor(){
        Color color = null;
        return  color;

    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;

    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }

    //Pathfinding
    public void searchPath(int goalCol, int goalRow){

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow);

        if(gp.pFinder.search()){
            //Next worldX and worldY
            int nextX = gp.pFinder.pathList.get(0).col*gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row*gp.tileSize;
            //Entity's solid area's position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){//checked
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){//checked
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){//checked
                //left or right
                if(enLeftX > nextX){
                    direction = "left";
                }
                 if(enLeftX < nextX ){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //up or left
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction = "up";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //down or left
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollision();
                if(collisionOn){
                    direction = "right";
                }
            }
            //if reached the goal stop moving
            /*int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }*/
        }
    }
    public int getGoalCol(Entity target){
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    //Collision
    public void checkCollision(){
        collisionOn = false;

        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer =  gp.cChecker.checkPlayer(this);


        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);

        }
    }

    //Moving NPC
    public void move(String direction){}

}
