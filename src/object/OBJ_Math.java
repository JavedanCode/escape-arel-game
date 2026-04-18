package object;

import entity.Entity;
import entity.Gun;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class OBJ_Math extends Gun {

    GamePanel gp;
    String mathAttackPath;


    public static final String objName = "Math";




    public OBJ_Math(GamePanel gp) {
        super(gp);
        this.gp = gp;



        fireRate = 10; //The less, the faster
        name = objName;
        speed = 8;
        maxLife = 120;
        life =maxLife;
        attack = 7;
        useCost = 0;
        alive = false;
        mathAttackChanger();
        getImage();
        knockBackPower = 2;

        // Calculate the direction vector
        double dx = targetX - worldX;
        double dy = targetY - worldY;

        // Normalize the direction vector
        double distance = Math.sqrt(dx * dx + dy * dy);
         directionX = dx / distance;
         directionY = dy / distance;

         gp.playSE(30);



    }
   /* public void update() {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if (!gp.player.invincible && contactPlayer) {
            damagePlayer(attack);
            generateParticle(this, this);
        }



        // Update the projectile's position using the normalized direction vector
        worldX +=  (directionX * speed);
        worldY +=  (directionY * speed);

        // Decrease life and check if the projectile is still alive
        life--;
        if (life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
                mathAttackChanger();
                getImage();
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }*/

    public void getImage(){

        up1 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        up2 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        down1 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        down2 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        left1 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        left2 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        right1 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);
        right2 = setup(mathAttackChanger(),gp.tileSize, gp.tileSize);



    }

    public String mathAttackChanger(){
        //create a counter then change the image based on the counter
        //there are 9 images;
        int i  = new Random().nextInt(225)+1;
        if(i > 1){
            mathAttackPath = "/vlad/math_attack_1";
        }
        if(i < 25){
            mathAttackPath = "/vlad/math_attack_2";
        }
        if(25 < i &&i < 50){
            mathAttackPath = "/vlad/math_attack_3";
        }
        if(50 < i &&i < 75){
            mathAttackPath = "/vlad/math_attack_4";
        }
        if(75 < i &&i < 100){
            mathAttackPath = "/vlad/math_attack_5";
        }
        if(100 < i &&i < 125){
            mathAttackPath = "/vlad/math_attack_6";
        }
        if(125 < i &&i < 150){
            mathAttackPath = "/vlad/math_attack_7";
        }
        if(150 < i &&i < 175){
            mathAttackPath = "/vlad/math_attack_8";
        }
        if(175 < i &&i < 200){
            mathAttackPath = "/vlad/math_attack_9";
        }

        return mathAttackPath;

    }

    public boolean haveResource(Entity user){


        boolean haveResource = user.bullets >= useCost;

        return haveResource;
    }

    public void subtractResource(Entity user){
        user.bullets -= useCost;

    }

    public Color getParticleColor(){
        Color color = new Color(22, 81, 1);
        return  color;

    }
    public int getParticleSize(){
        int size = 10;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;

    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
