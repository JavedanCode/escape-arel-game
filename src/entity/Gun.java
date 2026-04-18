package entity;

import main.GamePanel;

public class Gun extends Entity{

    Entity user;

    public int fireRate;
    public double targetX, targetY;
    public boolean shooting = false;
    public double directionX;
    public double directionY;
   // public double worldX, worldY;





    public Gun(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX,int worldY,String direction,boolean alive,Entity user){


          switch(direction){
                case "up" :  this.worldY = worldY-25; this.worldX = worldX; break;
                case "down" : this.worldY = worldY+25; this.worldX = worldX; break;
                case "left" : this.worldX = worldX-40; this.worldY = worldY; break;
                case "right" : this.worldX = worldX+40; this.worldY = worldY; break;
            }

        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        shooting = true;

        if(user == gp.player){
            this.targetX = gp.mouseL.targetX + this.worldX - gp.player.screenX -25;
            this.targetY = gp.mouseL.targetY + this.worldY - gp.player.screenY -25 ;
        }else {
            this.targetX = gp.player.worldX;
            this.targetY = gp.player.worldY;
        }

        // Calculate the direction vector
        double dx = targetX - worldX;
        double dy = targetY - worldY;

        // Normalize the direction vector
        double distance = Math.sqrt(dx * dx + dy * dy);
         directionX = dx / distance;
         directionY = dy / distance;




    }

    public void update() {
        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
                generateParticle(user.gun, gp.monster[gp.currentMap][monsterIndex]);
                shooting = false;
                alive = false;
            }
        } else {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.gun, user.gun);
                shooting = false;
                alive = false;
            }
        }

        // Update the projectile's position using the normalized direction vector

        worldX += (int) (directionX * speed);
        worldY += (int) (directionY * speed);

        // Decrease life and check if the projectile is still alive


        life--;
        if (life <= 0) {
            shooting = false;
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }


    public boolean haveResource(Entity user){

        return false;
    }

    public void subtractResource(Entity user){

    }
}
