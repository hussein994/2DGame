package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop prog when window is closed
        window.setResizable(false);
        window.setTitle("Jeu 2d");
        GameGUI gameGUI = new GameGUI();
        window.add(gameGUI);
        window.pack();
        window.setLocationRelativeTo(null); // null == center of screen
        window.setVisible(true);
        gameGUI.setupGame(); // set objects before starting game
        gameGUI.Thread(); // run game
    }
}