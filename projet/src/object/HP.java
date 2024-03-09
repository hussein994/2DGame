package object;

import main.GameGUI;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class HP extends Obj {

    GameGUI gp;

    public HP() {
        try {   // importing image
            icon1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/health/healthball.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public HP(GameGUI gp) {   // constructor
        this.gp=gp;
        try {   // importing image
            icon1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/health/healthball.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
