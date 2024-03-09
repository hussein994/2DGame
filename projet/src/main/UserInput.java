package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener {

    public boolean down,right,left,up; // variable to store inputs in

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int numb_key = e.getKeyCode();

        // set as True
        if (numb_key==KeyEvent.VK_RIGHT)    // right arrow
        {
            right=true;
        }
        if (numb_key==KeyEvent.VK_LEFT)    // left arrow key
        {
            left=true;
        }
        if (numb_key==KeyEvent.VK_UP)       // up arrow key
        {
            up=true;
        }
        if (numb_key==KeyEvent.VK_DOWN)     // down arrow key
        {
            down=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int numb_key = e.getKeyCode();


        // set as False
        if (numb_key==KeyEvent.VK_RIGHT)    // right arrow
        {
            right=false;
        }
        if (numb_key==KeyEvent.VK_LEFT)    // left arrow key
        {
            left=false;
        }
        if (numb_key==KeyEvent.VK_UP) // up arrow key
        {
            up=false;
        }
        if (numb_key==KeyEvent.VK_DOWN) // down arrow key
        {
            down=false;
        }

    }
}
