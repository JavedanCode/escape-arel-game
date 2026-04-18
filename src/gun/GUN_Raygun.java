package gun;

import entity.Entity;
import entity.Gun;
import main.GamePanel;
import object.OBJ_Raygun;

import java.awt.*;

public class GUN_Raygun extends Gun {
    GamePanel gp;

    public static final String gunName = "Raygun";



    public GUN_Raygun(GamePanel gp) {
        super(gp);
        this.gp = gp;



        fireRate = 10; //The less, the faster
        name = gunName;
        speed = 25;
        maxLife = 40;
        life =maxLife;
        attack = 5;
        useCost = 1;
        alive = false;
        getImage();
        knockBackPower = 1;
    }

    public void getImage(){
        up1    = setup("/projectile/raygun_up_1",gp.tileSize, gp.tileSize);
        up2    = setup("/projectile/raygun_up_2",gp.tileSize, gp.tileSize);
        down1  = setup("/projectile/raygun_down_1",gp.tileSize, gp.tileSize);
        down2  = setup("/projectile/raygun_down_2",gp.tileSize, gp.tileSize);
        left1  = setup("/projectile/raygun_left_1",gp.tileSize, gp.tileSize);
        left2  = setup("/projectile/raygun_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/raygun_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/raygun_right_2",gp.tileSize, gp.tileSize);

    }

    public boolean haveResource(Entity user){


        boolean haveResource = user.bullets >= useCost;

        return haveResource;
    }

    public void subtractResource(Entity user){
         user.bullets -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(49, 101, 101);
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
