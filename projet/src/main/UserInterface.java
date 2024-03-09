package main;

import object.HP;
import object.Obj;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UserInterface {   // User Interface (map)

    GameGUI gp;
    Graphics2D graphics2D;
    BufferedImage HP;
    public UserInterface(GameGUI gp) {   // constructor
        this.gp= gp;
        Obj HP  = new HP(gp);
        this.HP = HP.icon1;
    }

    public void draw(Graphics2D graphics2D) { // draw objects on map when called
        this.graphics2D = graphics2D;
        displayHP(); // calls for the drawing method of HP hearts
    }

    private void displayHP() {  // positioning of HP hearts on Screen
        int x = 0;
        int y= 500;

        for (int i = 0; i < gp.player.HP; i++) { // drawing HP on screen
            graphics2D.drawImage(HP,x,y,null);
            x += gp.tileSize; // moves one tile
        }
    }

}
