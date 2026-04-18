package gun;

import entity.Entity;
import entity.Gun;
import main.GamePanel;
import object.OBJ_Raygun;

import java.awt.*;

public class GUN_Pistol extends Gun {
    GamePanel gp;

    public static final String gunName = "Pistol";



    public GUN_Pistol(GamePanel gp) {
        super(gp);
        this.gp = gp;



        fireRate = 10; //The less, the faster
        name = gunName;
        speed = 25;
        maxLife = 40;
        life =maxLife;
        attack = 7;
        useCost = 1;
        alive = false;
        getImage();
        knockBackPower = 1;
    }

    public void getImage(){
        up1    = setup("/projectile/pistol_bullet_up",gp.tileSize, gp.tileSize);
        up2    = setup("/projectile/pistol_bullet_up",gp.tileSize, gp.tileSize);
        down1  = setup("/projectile/pistol_bullet_down",gp.tileSize, gp.tileSize);
        down2  = setup("/projectile/pistol_bullet_down",gp.tileSize, gp.tileSize);
        left1  = setup("/projectile/pistol_bullet_left",gp.tileSize, gp.tileSize);
        left2  = setup("/projectile/pistol_bullet_left",gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/pistol_bullet_right",gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/pistol_bullet_right",gp.tileSize, gp.tileSize);

    }

    public boolean haveResource(Entity user){


        boolean haveResource = user.bullets >= useCost;

        return haveResource;
    }

    public void subtractResource(Entity user){
        user.bullets -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(69, 5, 12);
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
