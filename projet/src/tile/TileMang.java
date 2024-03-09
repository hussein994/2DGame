package tile;

import java.io.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.util.Objects;

import main.GameGUI;

public class TileMang {

    GameGUI gp;
    public Tile[] tile;

    public int[][] maplayout;
    public static final String map1=("/res/mapslayout/map1.txt");

    public TileMang(GameGUI gp){
        this.gp = gp;

        tile = new Tile[20];
        maplayout= new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        insertingmap(map1);

    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/wall.png"))); //load image of tile
            tile[0].collision = true;
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/floor.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/golden.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/purple.png")));

        } catch (IOException e) {
           e.printStackTrace();
        }

    }


     public void insertingmap(String path){
         try {
             InputStream inputSt= getClass().getResourceAsStream(path);    // import text file
             assert inputSt != null;
             BufferedReader buffread = new BufferedReader(new InputStreamReader(inputSt));   // read the text file (format)

             int col = 0;
             int row = 0;

             while(col<gp.maxScreenCol && row<gp.maxScreenRow) {
                 String txtline = buffread.readLine();   // read single line from txt file

                 while(col<gp.maxScreenCol)
                 {
                     String[] nmbrs = txtline.split(" ");  // store line in nmbres array

                     int num = Integer.parseInt(nmbrs[col]); // cast into int
                     maplayout[col][row] = num;
                     col++;
                 }
                 if (col == gp.maxScreenCol)  // if true jump to next line
                 {
                     col=0;
                     row++;
                 }
             }
             buffread.close();

         } catch (Exception ignored) {}
     }

    public void draw(Graphics2D graph2D){


        int col = 0;
        int row = 0;
        int x=0;
        int y=0;

        while(col<gp.maxScreenCol && row<gp.maxScreenRow){
            int tileindex = maplayout[col][row];    // everything is now storeed inside maplayout

            graph2D.drawImage(tile[tileindex].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+= gp.tileSize;

            if (col == gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y+= gp.tileSize;
            }
        }
    }
    
}
