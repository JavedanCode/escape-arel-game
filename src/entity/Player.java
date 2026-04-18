package entity;

import data.Progress;
import main.GamePanel;
import main.KeyHandler;
import main.MouseListener;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{

    KeyHandler keyH;
    public MouseListener mouseL;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    boolean walking = false;



    public boolean inventoryFull = false;



    public boolean lightUpdated = false;


    public Player(GamePanel gp, KeyHandler keyH, MouseListener mouseL) {
        super(gp);

        this.keyH = keyH;
        this.mouseL = mouseL;

        screenX = gp.screenWidth/2 -gp.tileSize/2;
        screenY = gp.screenHeight/2 -gp.tileSize/2;
        //SOLID AREA
        solidArea = new Rectangle(10,16,28,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_player;


        setDefaultValues();

    }

    public void setDialogue(){
        dialogues[0][0] = "it's so dark.";
        dialogues[0][1] = "I better use my lantern.";

        dialogues[1][0] = "what was that?!";
        dialogues[1][1] = "I better get my stuff quick!!";

        dialogues[2][0] = "I can't waste any time!";
        dialogues[2][1] = "I need to get to the bus stop!";



    }
    public void getGuardImage(){
        guardUp = setup("/player/boy_guard_up",gp.tileSize, gp.tileSize);
        guardDown = setup("/player/boy_guard_down",gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/boy_guard_left",gp.tileSize, gp.tileSize);
        guardRight = setup("/player/boy_guard_right",gp.tileSize, gp.tileSize);
    }
    public void getStandImage(){
        standUp = setup("/player_standing/standing_up",gp.tileSize, gp.tileSize);
        standDown = setup("/player_standing/standing_down",gp.tileSize, gp.tileSize);
        standLeft = setup("/player_standing/standing_left",gp.tileSize, gp.tileSize);
        standRight = setup("/player_standing/standing_right",gp.tileSize, gp.tileSize);
    }
    public void getImage(){

            up1  = setup("/player_walking/walking_down_1",gp.tileSize, gp.tileSize);
            up2  = setup("/player_walking/walking_down_2",gp.tileSize, gp.tileSize);
            up3  = setup("/player_walking/walking_down_3",gp.tileSize, gp.tileSize);
            up4  = setup("/player_walking/walking_down_4",gp.tileSize, gp.tileSize);
            up5  = setup("/player_walking/walking_down_5",gp.tileSize, gp.tileSize);
            up6  = setup("/player_walking/walking_down_6",gp.tileSize, gp.tileSize);
            up7  = setup("/player_walking/walking_down_7",gp.tileSize, gp.tileSize);
            up8  = setup("/player_walking/walking_down_8",gp.tileSize, gp.tileSize);
            down1  = setup("/player_walking/walking_up_1",gp.tileSize, gp.tileSize);
            down2  = setup("/player_walking/walking_up_2",gp.tileSize, gp.tileSize);
            down3  = setup("/player_walking/walking_up_3",gp.tileSize, gp.tileSize);
            down4  = setup("/player_walking/walking_up_4",gp.tileSize, gp.tileSize);
            down5  = setup("/player_walking/walking_up_5",gp.tileSize, gp.tileSize);
            down6  = setup("/player_walking/walking_up_6",gp.tileSize, gp.tileSize);
            down7  = setup("/player_walking/walking_up_7",gp.tileSize, gp.tileSize);
            down8  = setup("/player_walking/walking_up_8",gp.tileSize, gp.tileSize);
            left1  = setup("/player_walking/walking_left_1",gp.tileSize, gp.tileSize);
            left2  = setup("/player_walking/walking_left_2",gp.tileSize, gp.tileSize);
            left3  = setup("/player_walking/walking_left_3",gp.tileSize, gp.tileSize);
            left4  = setup("/player_walking/walking_left_4",gp.tileSize, gp.tileSize);
            left5  = setup("/player_walking/walking_left_5",gp.tileSize, gp.tileSize);
            left6  = setup("/player_walking/walking_left_6",gp.tileSize, gp.tileSize);
            left7  = setup("/player_walking/walking_left_7",gp.tileSize, gp.tileSize);
            left8  = setup("/player_walking/walking_left_8",gp.tileSize, gp.tileSize);
            right1  = setup("/player_walking/walking_right_1",gp.tileSize, gp.tileSize);
            right2  = setup("/player_walking/walking_right_2",gp.tileSize, gp.tileSize);
            right3  = setup("/player_walking/walking_right_3",gp.tileSize, gp.tileSize);
            right4  = setup("/player_walking/walking_right_4",gp.tileSize, gp.tileSize);
            right5  = setup("/player_walking/walking_right_5",gp.tileSize, gp.tileSize);
            right6  = setup("/player_walking/walking_right_6",gp.tileSize, gp.tileSize);
            right7  = setup("/player_walking/walking_right_7",gp.tileSize, gp.tileSize);
            right8  = setup("/player_walking/walking_right_8",gp.tileSize, gp.tileSize);



    }
    public void getAttackImage(){



        if(currentWeapon.type == type_gun) {
            if(currentWeapon.name.equals(OBJ_Raygun.objName)){
            attackUp1 = setup("/shooting/player_raygun_up_1", gp.tileSize, gp.tileSize *2);
            attackUp2 = setup("/shooting/player_raygun_up_2", gp.tileSize, gp.tileSize*2 );
            attackDown1 = setup("/shooting/player_raygun_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/shooting/player_raygun_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/shooting/player_raygun_left_1", gp.tileSize *2, gp.tileSize);
            attackLeft2 = setup("/shooting/player_raygun_left_2", gp.tileSize *2, gp.tileSize);
            attackRight1 = setup("/shooting/player_raygun_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/shooting/player_raygun_right_2", gp.tileSize * 2, gp.tileSize);
        }else if(currentWeapon.name.equals(OBJ_Pistol.objName)){
            attackUp1 = setup("/shooting/shooting_up", gp.tileSize, gp.tileSize *2);
            attackUp2 = setup("/shooting/shooting_up", gp.tileSize, gp.tileSize*2 );
            attackDown1 = setup("/shooting/shooting_down", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/shooting/shooting_down", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/shooting/shooting_left", gp.tileSize *2, gp.tileSize);
            attackLeft2 = setup("/shooting/shooting_left", gp.tileSize *2, gp.tileSize);
            attackRight1 = setup("/shooting/shooting_right", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/shooting/shooting_right", gp.tileSize * 2, gp.tileSize);

        }}
        if(meleeWeapon != null){
            if(meleeWeapon.name.equals(OBJ_Knife.objName)){
                meleeUp1 = setup("/player/player_melee_up_1",gp.tileSize,gp.tileSize*2);
                meleeUp2 = setup("/player/player_melee_up_2",gp.tileSize,gp.tileSize*2);
                meleeDown1 = setup("/player/player_melee_down_1",gp.tileSize,gp.tileSize*2);
                meleeDown2 = setup("/player/player_melee_down_2",gp.tileSize,gp.tileSize*2);
                meleeLeft1 = setup("/player/player_melee_left_1",gp.tileSize*2,gp.tileSize);
                meleeLeft2 = setup("/player/player_melee_left_2",gp.tileSize*2,gp.tileSize);
                meleeRight1 = setup("/player/player_melee_right_1",gp.tileSize*2,gp.tileSize);
                meleeRight2 = setup("/player/player_melee_right_2",gp.tileSize*2,gp.tileSize);
        }
    }}
    public void setDefaultValues(){
        worldX= gp.tileSize*55;
        worldY= gp.tileSize*53;

        defaultSpeed = 2;
        speed = defaultSpeed;
        direction = "down";
        currentWeapon = new OBJ_Pistol(gp);
        meleeWeapon = new OBJ_Knife(gp);


        //PLAYER STATUS

        level = 1;
        maxLife = 70;
        life = maxLife;
        strength = 3;
        dexterity = 1;
        if (currentWeapon != null) {
            maxBullet = currentWeapon.maxBullet;
        }
        bullets = maxBullet;
        exp = 0;
        nextLevelExp = 10000;
        coin = 500;
        currentLight = new OBJ_Lantern(gp);
        if( currentWeapon != null&&currentWeapon.type == type_gun){
            gun = currentWeapon.bullet;
        }

        meleeAttack = getMeleeAttack();
        defense = getDefense();

        getAttackImage();
        getGuardImage();
        getStandImage();
        getImage();
        setItems();
        setDialogue();
    }
    public void setDefaultPosition(){

        gp.currentMap = 0;
        worldX= gp.tileSize*55;
        worldY= gp.tileSize*53;
        direction = "down";

    }
    public void restoreStatus(){

        life = maxLife;
        bullets = maxBullet;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        shooting = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
        Progress.secondScenePlayed = false;
        Progress.firstScenePlayed = false;
        Progress.vladDefeated = false;
        Progress.zombieKilled = false;
        Progress.stuffTaken = false;
        Progress.finalScenePlayed = false;
        Progress.universityReached = false;
        Progress.doorOpened = false;
        Progress.leftUniversity = false;
        Progress.reachedBus = false;
        setItems();
    }
    public void setItems(){

        inventory.clear();
        inventory.add(meleeWeapon);
        inventory.add(currentWeapon);
        //inventory.add(new OBJ_Pistol(gp));
       // inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(currentLight);
        inventory.add(new OBJ_Key(gp));

    }
    public void selectItems(){
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if( selectedItem.type == type_gun){

                gun = currentWeapon.bullet;

                currentWeapon = selectedItem;

                getAttackImage();

            }
            if(selectedItem.type == type_melee ){
                meleeWeapon = selectedItem;
                meleeAttack = getMeleeAttack();
            }
            /*if(selectedItem.type == type_shield){

                currentShield = selectedItem;
                defense = getDefense();
            }*/
            if(selectedItem.type == type_consumable){

               if(selectedItem.use(this)) {
                   if(selectedItem.amount >1){
                       selectedItem.amount--;
                   }else{
                   inventory.remove(itemIndex);
               }}
            }
            if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
        }
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size();i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
                break;
            }
        }return currentWeaponSlot;
    }

    public int getCurrentMeleeWeaponSlot(){
        int meleeWeaponSlot = 0;
        for(int i = 0; i < inventory.size();i++){
            if(inventory.get(i) == meleeWeapon){
                meleeWeaponSlot = i;
                break;
            }
        }return meleeWeaponSlot;
    }

    public int getMeleeAttack(){
        if(meleeWeapon.attackArea != null){
            attackArea = meleeWeapon.attackArea;
        }
        motion1_duration = meleeWeapon.motion1_duration;
        motion2_duration = meleeWeapon.motion2_duration;
        return meleeAttack = strength * meleeWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity;
    }
    public void update(){
        gp.tManager.checkTask();
        if(knockBack){

            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this,true);
            gp.cChecker.checkEntity(this,gp.npc);
            gp.cChecker.checkEntity(this,gp.monster);
            gp.cChecker.checkEntity(this,gp.iTile);

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
        }
        else if(shooting){
                shooting();
        }

        else if(keyH.spacePressed){
           /* guarding = true;
            guardCounter++;*/
        }
       else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || mouseL.shootPressed || keyH.meleePressed  ){
           walking = true;

            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            contactMonster(monsterIndex);
            //CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);

            if(currentWeapon.type == type_gun){
                gun = currentWeapon.bullet;
            }


            //CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if(!collisionOn && !keyH.enterPressed&& !mouseL.shootPressed&& !keyH.meleePressed&&!mouseL.aimPressed){

                switch (direction){
                    case "up"   : worldY-=speed; break;
                    case "down" : worldY+=speed; break;
                    case "left" : worldX-=speed; break;
                    case "right": worldX+=speed; break;
                }
            }



            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter= 0;

            spriteCounter++;
            if(spriteCounter > 8){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 4;
                }
                else if(spriteNum == 4){
                    spriteNum = 5;
                }
                else if(spriteNum == 5){
                    spriteNum = 6;
                }
                else if(spriteNum == 6){
                    spriteNum = 7;
                }
                else if(spriteNum == 7){
                    spriteNum = 8;
                }
                else if(spriteNum == 8){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
           walking = false;
            standCounter++;

            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
            guarding = false;
            guardCounter= 0;
        }
       if(mouseL.aimPressed){
        double mouseX = gp.mouseL.targetX + gp.player.worldX - gp.player.screenX;
        double mouseY = gp.mouseL.targetY + gp.player.worldY - gp.player.screenY;
        int playerCenterX = worldX + gp.tileSize / 2;
        int playerCenterY = worldY + gp.tileSize / 2;

        if (mouseY < playerCenterY && Math.abs(mouseX - playerCenterX) <= Math.abs(mouseY - playerCenterY)) {
            direction = "up";
        } else if (mouseY > playerCenterY && Math.abs(mouseX - playerCenterX) <= Math.abs(mouseY - playerCenterY)) {
            direction = "down";
        } else if (mouseX < playerCenterX && Math.abs(mouseX - playerCenterX) > Math.abs(mouseY - playerCenterY)) {
            direction = "left";
        } else if (mouseX > playerCenterX && Math.abs(mouseX - playerCenterX) > Math.abs(mouseY - playerCenterY)) {
            direction = "right";
        }}
        gp.ui.drawPlayerLife();

        /*if (mouseL.shootPressed) {
            if(currentWeapon.type == type_gun && gun != null && !shooting) {
                shooting = true;
            }else{
                attacking = true;
                gp.playSE(7);
            }
        }*/

       /*if(gun != null&&!shootPressed&&!gun.alive&& projectileCoolDown == gun.fireRate && gun.haveResource(this)){ //This code was for the projectile class
           shootPressed = true;
       }*/

        //this needs to be outside of key if statement
        if(invincible){
            invincibleCounter++ ;
            if(invincibleCounter > 60){
                invincible = false;
                transparent = false;
                invincibleCounter=0;
            }
        }
        if(gun != null &&projectileCoolDown < gun.fireRate){
            projectileCoolDown++;
        }

        if(life > maxLife){
            life = maxLife;
        }

        if(bullets > maxBullet){
            bullets = maxBullet;
        }
        if(bullets < 0){
            bullets = 0;
        }
        if(!keyH.godMode){
            if(life <= 0 ){
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
            gp.playSE(12);

        }}



    }
    public void damageProjectile(int i){
        if(i != 999){
            Entity projectile = gp.projectileList[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    public void pickUpObject(int i){
        if(i != 999){

            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][i].type == type_pickupOnly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;

            }else if(gp.obj[gp.currentMap][i].type == type_obstacle){
                if(keyH.enterPressed){
                    gp.obj[gp.currentMap][i].interact();
                }
            }



            else{


                if(canObtainItem(gp.obj[gp.currentMap][i])) {

                    gp.playSE(1);
                    gp.obj[gp.currentMap][i] = null;

                }else{
                    inventoryFull = true;
                }
            }





        }
    }
    public void interactNPC(int i){

        if(i != 999){
            if(gp.keyH.enterPressed){
            gp.npc[gp.currentMap][i].speak();
            }
            gp.npc[gp.currentMap][i].move(direction);
        }
        else if(gp.mouseL.shootPressed&& currentWeapon.type == type_gun && gun != null && !shooting){

            shooting = true;

        }
        else if(gp.keyH.meleePressed&&meleeWeapon != null){
            attacking = true;
        }

        }
    public void contactMonster(int i){
        if(i != 999){
            if(!invincible&& !gp.monster[gp.currentMap][i].dying){
                gp.playSE(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 1 ){
                    damage = 1;
                }

                life-= damage;
                invincible = true;
                transparent = true;
            }

        }
    }
    public void damageMonster(int i,Entity attacker, int attack,int knockBackPower){
        if(i != 999){
            if(gp.monster[gp.currentMap][i].shieldOn&&!gun.shooting&&!gp.monster[gp.currentMap][i].invincible){
                gp.playSE(5);
                if(gp.monster[gp.currentMap][i].shieldLife > 0){
                    gp.monster[gp.currentMap][i].shieldLife--;
                    gp.monster[gp.currentMap][i].invincible = true;
                }else{
                    gp.monster[gp.currentMap][i].shieldOn = false;
                }
            }

          if(!gp.monster[gp.currentMap][i].invincible&& !gp.monster[gp.currentMap][i].shieldOn){
              gp.playSE(5);

              if(knockBackPower > 0){
                  setKnockBack(gp.monster[gp.currentMap][i],attacker,knockBackPower);
              }
              if(gp.monster[gp.currentMap][i].offBalance){
                  attack*=3;
              }

              int damage = attack - gp.monster[gp.currentMap][i].defense;
              if(damage < 0 ){
                  damage = 0;
              }

              gp.monster[gp.currentMap][i].life -= damage;
              gp.ui.addMessage(damage+ "damage!");
              gp.monster[gp.currentMap][i].invincible = true;
              gp.monster[gp.currentMap][i].damageReaction();
              if(gp.monster[gp.currentMap][i].life <= 0){
                  gp.monster[gp.currentMap][i].dying = true;
                  gp.ui.addMessage("killed the bitch!");
                  exp += gp.monster[gp.currentMap][i].exp;
                  checkLevelUp();
              }
          }
        }
    }
    public void damageInteractiveTile(int i){
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this  )&& !gp.iTile[gp.currentMap][i].invincible){
            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            //GENERATE PARTICLE
            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0 ) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }
    public void checkLevelUp(){

        if(exp >= nextLevelExp){

            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;

            strength++;
            dexterity++;
            meleeAttack = getMeleeAttack();
            defense = getDefense();

            gp.playSE(8);

        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {

            case "up":
                if(!attacking&&!shooting&&walking){

               if(spriteNum == 1){image = up1; }
                if(spriteNum == 2){image = up2;}
                    if(spriteNum == 3){image = up3; }
                    if(spriteNum == 4){image = up4;}
                    if (spriteNum == 5){image = up5;}
                    if (spriteNum == 6){image = up6;}
                    if (spriteNum == 7){image = up7;}
                    if (spriteNum == 8){image = up8;}
                }
                if(!walking){
                    image = standUp;
                }
                if(attacking){

                    tempScreenY = screenY-gp.tileSize;

                    if(spriteNum == 1){image = meleeUp1;}
                    if(spriteNum == 2){image = meleeUp2;}
                }
                if (shooting){
                    tempScreenY = screenY-gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                if(mouseL.aimPressed){
                    tempScreenY = screenY-gp.tileSize;
                    image = attackUp1;
                }
                if(guarding){
                    image = guardUp;
                }
                break;
            case "down":
                if(!attacking&&!shooting&&walking){

                if(spriteNum == 1){ image = down1;}
                if(spriteNum == 2){ image = down2;}
                    if(spriteNum == 3){ image = down3;}
                    if(spriteNum == 4){ image = down4;}
                    if (spriteNum == 5){ image = down5;}
                    if (spriteNum == 6){ image = down6;}
                    if (spriteNum == 7){ image = down7;}
                    if (spriteNum == 8){ image = down8;}

                    }
                if(!walking){
                    image = standDown;
                }

                if(attacking){

                    if(spriteNum == 1){image = meleeDown1;}
                    if(spriteNum == 2){image = meleeDown2;
                    }
                }
                if (shooting){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                if(mouseL.aimPressed){
                    image = attackDown1;
                }
                if(guarding){
                    image = guardDown;
                }
                break;
            case "left" :
                if(!attacking&&!shooting&&walking){

                if(spriteNum == 1){ image = left1; }
                if(spriteNum == 2){ image = left2; }
                    if(spriteNum == 3){ image = left1; }
                    if(spriteNum == 4){ image = left3;}
                    if (spriteNum == 5){ image = left5;}
                    if (spriteNum == 6){ image = left6;}
                    if (spriteNum == 7){ image = left7;}
                    if (spriteNum == 8){ image = left8;}}

                if(!walking){
                    image = standLeft;
                }

                if(attacking){

                    tempScreenX = screenX-gp.tileSize;

                    if(spriteNum == 1){image = meleeLeft1;}
                    if(spriteNum == 2){image = meleeLeft2;}
                }
                if (shooting){
                    tempScreenX = screenX-gp.tileSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
                if(mouseL.aimPressed){
                    tempScreenX = screenX-gp.tileSize;
                    image = attackLeft1;
                }
                if(guarding){
                    image = guardLeft;
                }
                break;
            case "right" :
                if(!attacking&&!shooting&&walking){

                if(spriteNum == 1){ image = right1;}
                if(spriteNum == 2){image = right2; }
                    if(spriteNum == 3){ image = right1;}
                    if(spriteNum == 4){image = right3; }
                    if (spriteNum == 5){ image = right5;}
                    if (spriteNum == 6){ image = right6;}
                    if (spriteNum == 7){ image = right7;}
                    if (spriteNum == 8){ image = right8;}}
                if(!walking){
                     image = standRight;
                }


                if(attacking){

                    if(spriteNum == 1){image = meleeRight1;}
                    if(spriteNum == 2){image = meleeRight2;}
                }
                if(shooting){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                if(mouseL.aimPressed){
                    image = attackRight1;
                }
                if(guarding){
                    image = guardRight;
                }
                break;
        }
        if(transparent){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        if(drawing) {
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
        //Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }
    public int searchItemInInventory(String itemName){


        int itemIndex = 999;

        for(int i = 0; i < inventory.size();i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain = false;

        Entity newItem = gp.eGenerator.getObject(item.name);

        // CHECK IF ITEM IS STACKABLE
        if(newItem.stackable){
            int itemIndex = searchItemInInventory(newItem.name);
            if(itemIndex != 999){
                inventory.get(itemIndex).amount++;
                canObtain = true;
            }else{ //New item so need to check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }

            }
    }else{
            //NOT STACKABLE So need to check vacancy
            if(inventory.size() != maxInventorySize){
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;

}
    public void getSleepingImage(BufferedImage image) {

        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;


    }

}

