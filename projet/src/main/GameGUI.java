package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.*;
import javax.swing.JPanel;

import entity.Entities;
import entity.Player;
import tile.TileMang;




public class GameGUI extends JPanel implements Runnable {

    TileMang tileMan = new TileMang(this); // pass this GamePanel class to manage tiles
    public static final String map1=("/res/mapslayout/map1.txt"); // map1
    public static final String map2=("/res/mapslayout/map2.txt"); // map2
    public static final String map3=("/res/mapslayout/map3.txt"); // map3
    public static final String map4=("/res/mapslayout/map4.txt"); // map4
    List lvls = new List(); // levels list
    Thread th; // thread
    public Collision collisionChecker = new Collision(this); // pass this gamepanel class
    UserInput input = new UserInput(); // User input
    public Player player = new Player(this, input); // pass this gamepanel class
    int playery,playerx;
    public UserInterface ui = new UserInterface(this); // pass this GamePanel class
    public Assets asset = new Assets(this); // pass this gamepanel class

    // array of monsters for each level
    public Entities[] monster = new Entities[10];
    public Entities[] monsterlvl2 = new Entities[10];
    public Entities[] monsterlvl3 = new Entities[10];
    public Entities[] monsterlvl4 = new Entities[0];
    ArrayList<Entities> creatures = new ArrayList<>();
    int Frame=60;
    int originalTileSize = 16;
    int scale = 3;
    public final int tileSize = originalTileSize * scale; // size of tiles used
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels



    public GameGUI() { // constructor

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // setting size
        this.setBackground(Color.black); // background color
        this.setDoubleBuffered(true); // use a Buffer to paint

        this.addKeyListener(input); // listen for key inputs
        this.setFocusable(true);    // concentrate on input
    }


    public void setupGame(){ // set objects on map before running it

        // adds monsters to map
        asset.setOnMap();

        // add maps to list of levels
        lvls.add(map1);
        lvls.add(map2);
        lvls.add(map3);
        lvls.add(map4);
    }


    public void Thread()
    {
        // passing the entire class // instantiating the thread
        th=new Thread(this);

        // start program (game) --- calls (run) method
        th.start();
    }



    public void run() { // Game loop

        double interval = 1000000000/Frame; // Framming
        double delta = 0;
        long LastTime = System.nanoTime();
        long currentTime;

        while(th!=null){ // if thread is null

            currentTime = System.nanoTime();

            delta += (currentTime - LastTime) / interval; //time passed

            LastTime = currentTime;

            if(delta>=1){ //everytime delta reaches draw interval we update
                update();
                repaint();
                delta--;
            }
        }
    }



    public void update() { // all movements

        // player movement update
        player.update();

        // temporarry variables
        playerx = player.posx;
        playery = player.posy;

        if (player.posx != 0){ // monsters begin to move only when user entered his first input
            for (Entities entities : monster) { // update monster movement
                if (entities != null) {
                    entities.update();
                }
            }
        }

        // check if player is at the exit of a map with switch
        switch (player.level){
            case 0: {   // x: 725 y: 430 exit map1
                if (playery > 419 & playerx > 710) {
                    player.level++;
                    tileMan.insertingmap(lvls.getItem(player.level));
                    player.posx=0;
                    player.posy=0;
                    monster = monsterlvl2;
                }
            }
            case 1: {   // x: 390 y: 520 exit map2
                if (playery > 515 & playerx > 379) {
                    player.level++;
                    tileMan.insertingmap(lvls.getItem(player.level));
                    player.posx=0;
                    player.posy=0;
                    monster = monsterlvl3;
                }
            }
            case 2: { // x: 576y: -12
                if (playery < 5 & playerx > 574) {
                    player.level++;
                    player.posx=0;
                    player.posy=0;
                    tileMan.insertingmap(lvls.getItem(player.level));
                    monster= monsterlvl4;
                }
            }
        }

    }




    public void paint(Graphics graph)
    {
        super.paintComponent(graph);

        // instanciate Graphics2D to use its methods
        Graphics2D graph2D = (Graphics2D) graph;

        // draw tiles
        tileMan.draw(graph2D);

        // add player
        creatures.add(player);

        // if list not empty add all entities
        for (Entities entities : monster) {
            if (entities != null) {
                creatures.add(entities);
            }
        }

        creatures.sort(new Comparator<Entities>() {

            @Override
            public int compare(Entities e1, Entities e2) {
                return Integer.compare(e1.posy, e2.posy);
            }

        });

        //draw entity list
        for (Entities entities : creatures) {
            entities.draw(graph2D);
        }



        //empty entity list
        for(int i = 0; i < creatures.size(); i++){
            creatures.remove(i);
        }

        ui.draw(graph2D); //


        // display HP
        graph2D.setFont(new Font("Arial",Font.BOLD,26));
        graph2D.setColor(Color.white);
        graph2D.drawString("HP:" + player.HP + "/5",20,490);

        // display Damge
        graph2D.setFont(new Font("Arial",Font.BOLD,20));
        graph2D.setColor(Color.white);
        graph2D.drawString("Damage Taken: " + player.Damage + "pts",130,490);

        // display Level
        if (player.level==3) {
            graph2D.setFont(new Font("Arial",Font.BOLD,20));
            graph2D.setColor(Color.white);
            graph2D.drawString("Status: You Are Safe",280,550);
            System.out.println("You win!");

        }else {
            graph2D.setFont(new Font("Arial",Font.BOLD,20));
            graph2D.setColor(Color.white);
            graph2D.drawString("Status: level " + player.level,280,550);
        }


        graph2D.dispose();
    }


}
