package monster;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import entity.Entities;
import main.GameGUI;

public class Ibol extends Entities {
    
    public Ibol(GameGUI gp){
        super(gp);
        // speed
        movement = 3;
        // type
        type=1;
        // HP
        maxHP=2;
        HP=maxHP;

        // area it occupies
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // load image
        getImage();
    }

    public void getImage(){ // load image
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/monster/ibol.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(){ // set movement pattern for the monsters
        pattern++;
        if(pattern == 100){
            Random random = new Random();
            int i = random.nextInt(70)+1;

            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
//
//            if(i>50 && i<=75){
//                direction = "left";
//            }
//            if(i > 75){
//                direction = "right";
//            }

            pattern = 50;
        }
    }

}
