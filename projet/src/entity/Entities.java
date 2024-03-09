package entity;

import java.awt.image.BufferedImage;
import main.GameGUI;
import java.awt.Rectangle;
import java.awt.*;

public class Entities {
    
    GameGUI gp;
    public int posx, posy;  // x and y positioning
    public int movement;    // for the speed
    public int type;    // differentiate between entities
    public BufferedImage sprite;    // for imported images
    public String direction = "down"; // initial movement at beginning of game
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);  //the area of every entity thats solid and will collide with other things
    public Rectangle attackBox = new Rectangle(0, 0, 0, 0);  //the area of every entity thats solid and will collide with other things
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionMode = false;     // collision mode
    public int pattern = 0; // for movement patter of ibols
    //character
    public int maxHP;   // maximum amount of HP
    public int HP;  // current HP
    boolean contact;



    public Entities(GameGUI gp){
        this.gp = gp;
    }


    public void setAction(){}


    public void update(){
        setAction();

        collisionMode = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.contactMonster(this);
        contact = gp.collisionChecker.contactMonster(this); // call method

        if (this.type == 1 & contact) { // if monster is in contact take a life of player
            gp.player.interval++;
            System.out.println("You got Hit by a Monster");
            if (gp.player.interval>30){
                gp.player.HP --; // take life
                gp.player.Damage += 6; // add damage counter
                gp.player.interval=0; // reset counter
            }

        }

        if(!collisionMode){
            switch (direction) {
                case "up":
                    posy-=movement;
                    break;
                case "down":
                    posy+=movement;   
                    break;
                case "left":
                    posx-=movement;
                    break;
                case "right":
                    posx+=movement;
                    break;
            }
        }
    }

    public void draw(Graphics2D graph2D){

        // printing image sprite
        BufferedImage image = sprite;
        graph2D.drawImage(image, posx, posy, gp.tileSize, gp.tileSize, null);

        if (type==1) { // set health bar ubove monsters
            graph2D.setColor(new Color(255, 0, 0));
            graph2D.fillRect(posx-1,posy-16, gp.tileSize,8);
            graph2D.setFont(new Font("Arial",Font.BOLD,15));
            graph2D.drawString("HP",posx-1,posy-16);
        }
    }
}
