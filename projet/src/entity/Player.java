package entity;

import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

import main.GameGUI;
import main.UserInput;

public class Player extends Entities {

    UserInput input;
    public int Damage;
    int interval=0;
    public int test;
    public int level;

    public Player(GameGUI gp, UserInput input){

        super(gp);
        this.input = input;
        level=0;
        type=0;
        solidArea = new Rectangle(8, 16, 30, 30);

        attackBox.width=36; // attack range
        attackBox.height=36; // attack range

        setDefaultValues(); // sets values for player
        getPlayerSprite(); // loads sprite image

    }

    public void setDefaultValues(){
        posx = 0;   // coordinates
        posy = 0;   // coordinates
        type = 0; // player
        movement=4;    // speed
        direction = "up";
        maxHP=5;    // setting Max HP to 5 hearts
        HP=maxHP;   // current HP is max at start
    }

    public void getPlayerSprite(){  // load image

        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update(){   // update movement direction

        if (HP==0) {
            System.out.println("Game Over! You Died");
            System.exit(0);
        }

        if(input.up || input.down || input.left || input.right){

            if (input.left)
        {
            //posx-=movement;
            direction = "left";
        }
        if (input.right)
        {
            //posx+=movement;
            direction = "right";
        }
        if (input.up)
        {
            //posy-=movement;
            direction = "up";
        }
        if (input.down)
        {
            //posy+=movement;
            direction = "down";
        }

        //check collision with tile
        collisionMode = false;
        gp.collisionChecker.checkTile(this); //passes this player class as entity


        // collision with monster
        test = gp.collisionChecker.contactPlayer(this,gp.monster);
        hittingMonster(test);

        //if it is false player can move
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
    }

    public void hittingMonster(int test) {

        if (test != 50) { // if different then 50 then player is in contact
            interval++;
            System.out.println("You got Hit");

            if (interval > 20) {    // to have an interval and not die instantly
                HP--; // one life lost
                Damage += 6;
                interval=0;

            }
        }
    }

    public void draw(Graphics2D graph2D){   // draw character on map
        BufferedImage image = sprite;
        graph2D.drawImage(image, posx, posy, gp.tileSize, gp.tileSize, null);
    }
}
