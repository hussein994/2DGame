package main;

import entity.Entities;

public class Collision {

    GameGUI gp;
 
    public Collision(GameGUI gp){
        this.gp = gp;
    }

    public void checkTile(Entities entities){ //get collision points of the entity passed

        int entityLeftWorldX = entities.posx + entities.solidArea.x;
        int entityRightWorldX = entities.posx + entities.solidArea.x + entities.solidArea.width;
        int entityTopWorldY = entities.posy + entities.solidArea.y;
        int entityBotWorldY = entities.posy + entities.solidArea.y + entities.solidArea.height;

        //the 4 solid sides of the entity's "hitbox" 
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBotRow = entityBotWorldY/gp.tileSize;

        int tileNum1, tileNum2; //only needs to check two points at a time(ex left shoulder and right shoulder)

        switch (entities.direction) {
            case "up":  //if headed up, predict if the next tile is solid
                entityTopRow = (entityTopWorldY - entities.movement)/gp.tileSize;
                tileNum1 = gp.tileMan.maplayout[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileMan.maplayout[entityRightCol][entityTopRow];
                if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision){
                    entities.collisionMode = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotWorldY + entities.movement)/gp.tileSize;
                tileNum1 = gp.tileMan.maplayout[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileMan.maplayout[entityRightCol][entityBotRow];
                if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision){
                    entities.collisionMode = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entities.movement)/gp.tileSize;
                tileNum1 = gp.tileMan.maplayout[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileMan.maplayout[entityLeftCol][entityBotRow];
                if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision){
                    entities.collisionMode = true;
                } 
               break;
            case "right":
                entityRightCol = (entityRightWorldX + entities.movement)/gp.tileSize;
                tileNum1 = gp.tileMan.maplayout[entityRightCol][entityTopRow];
                tileNum2 = gp.tileMan.maplayout[entityRightCol][entityBotRow];
                if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision){
                    entities.collisionMode = true;
                }
                break;
        }

    }

    // check collision with monster
    public int contactPlayer(Entities creature, Entities[] target) { //  so that player count monsters as an object
        int index = 50;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                creature.solidArea.x += creature.posx;
                creature.solidArea.y += creature.posy;

                target[i].solidArea.x += target[i].posx;
                target[i].solidArea.y += target[i].posy;

                switch (creature.direction) {
                    case "up":
                        creature.solidArea.y -= creature.movement;
                        if (creature.solidArea.intersects(target[i].solidArea)) {
                            creature.collisionMode = true;
                            index=i;
                        }
                        break;
                    case "right":
                        creature.solidArea.x += creature.movement;
                        if (creature.solidArea.intersects(target[i].solidArea)) {
                            creature.collisionMode = true;
                            index=i;
                        }
                        break;
                    case "left":
                        creature.solidArea.x -= creature.movement;
                        if (creature.solidArea.intersects(target[i].solidArea)) {
                            creature.collisionMode = true;
                            index=i;
                        }
                        break;
                    case "down":
                        creature.solidArea.y += creature.movement;
                        if (creature.solidArea.intersects(target[i].solidArea)) {
                            creature.collisionMode = true;
                            index=i;
                        }
                        break;
                }

                creature.solidArea.x = creature.solidAreaDefaultX;
                creature.solidArea.y = creature.solidAreaDefaultY;

                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public boolean contactMonster(Entities creature) { //  so that monsters count player as an object
        boolean contact= false;

        creature.solidArea.x += creature.posx;
        creature.solidArea.y += creature.posy;

        gp.player.solidArea.x += gp.player.posx;
        gp.player.solidArea.y += gp.player.posy;

        switch (creature.direction) {
            case "up":
                creature.solidArea.y -= creature.movement;
                break;
            case "right":
                creature.solidArea.x += creature.movement;
                break;
            case "left":
                creature.solidArea.x -= creature.movement;
                break;
            case "down":
                creature.solidArea.y += creature.movement;
                break;
        }
        if (creature.solidArea.intersects(gp.player.solidArea)) {
            creature.collisionMode = true;
            contact=true;
        }

        creature.solidArea.x = creature.solidAreaDefaultX;
        creature.solidArea.y = creature.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contact;

    }
    }
