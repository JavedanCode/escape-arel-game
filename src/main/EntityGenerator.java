package main;

import entity.Entity;
import object.OBJ_Math;
import object.*;

public class EntityGenerator {
    GamePanel gp;
    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }
    public Entity getObject(String itemName){

        Entity obj = null;

        switch(itemName){
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
            case OBJ_Key.objName:  obj = new OBJ_Key(gp); break;
            case OBJ_Lantern.objName:  obj = new OBJ_Lantern(gp); break;
            case OBJ_Bullets.objName: obj = new OBJ_Bullets(gp); break;
            case OBJ_Potion_Red.objName:  obj = new OBJ_Potion_Red(gp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
            case OBJ_Raygun.objName: obj = new OBJ_Raygun(gp); break;
            case OBJ_Pistol.objName: obj = new OBJ_Pistol(gp); break;
            case OBJ_Knife.objName: obj = new OBJ_Knife(gp); break;
            case OBJ_Math.objName: obj = new OBJ_Math(gp); break;
        }
        return obj;
    }
}
