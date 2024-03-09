package main;

import monster.Ibol;

public class Assets { // positions

    GameGUI gp;

    public Assets(GameGUI gp){
        this.gp = gp;
    }

    public void setOnMap(){ // positioning entities on map

        // map1 monsters

        // TODO: add position randomizer
        gp.monster[0] = new Ibol(gp);
        gp.monster[0].posx = gp.tileSize*4;
        gp.monster[0].posy = gp.tileSize*6;

        // TODO: add position randomizer
        gp.monster[1] = new Ibol(gp);
        gp.monster[1].posx = gp.tileSize*9;
        gp.monster[1].posy = gp.tileSize*4;

        // TODO: add position randomizer
        gp.monster[2] = new Ibol(gp);
        gp.monster[2].posx = gp.tileSize*4;
        gp.monster[2].posy = gp.tileSize*2;


        // map2 monsters:
        gp.monsterlvl2[0] = new Ibol(gp);
        gp.monsterlvl2[0].posx = gp.tileSize*4;
        gp.monsterlvl2[0].posy = gp.tileSize*2;

        gp.monsterlvl2[1] = new Ibol(gp);
        gp.monsterlvl2[1].posx = gp.tileSize*13;
        gp.monsterlvl2[1].posy = gp.tileSize*6;

        gp.monsterlvl2[2] = new Ibol(gp);
        gp.monsterlvl2[2].posx = gp.tileSize*11;
        gp.monsterlvl2[2].posy = gp.tileSize*5;

        gp.monsterlvl2[3] = new Ibol(gp);
        gp.monsterlvl2[3].posx = gp.tileSize*2;
        gp.monsterlvl2[3].posy = gp.tileSize*2;

        gp.monsterlvl2[4] = new Ibol(gp);
        gp.monsterlvl2[4].posx = gp.tileSize*10;
        gp.monsterlvl2[4].posy = gp.tileSize*6;

         // map3 monsters:
         gp.monsterlvl3[0] = new Ibol(gp);
         gp.monsterlvl3[0].posx = gp.tileSize*3;
         gp.monsterlvl3[0].posy = gp.tileSize*2;
 
         gp.monsterlvl3[1] = new Ibol(gp);
         gp.monsterlvl3[1].posx = gp.tileSize*11;
         gp.monsterlvl3[1].posy = gp.tileSize*6;

        gp.monsterlvl3[2] = new Ibol(gp);
        gp.monsterlvl3[2].posx = gp.tileSize*12;
        gp.monsterlvl3[2].posy = gp.tileSize*6;

        gp.monsterlvl3[3] = new Ibol(gp);
        gp.monsterlvl3[3].posx = gp.tileSize*13;
        gp.monsterlvl3[3].posy = gp.tileSize*7;

        gp.monsterlvl3[4] = new Ibol(gp);
        gp.monsterlvl3[4].posx = gp.tileSize*2;
        gp.monsterlvl3[4].posy = gp.tileSize*4;

        gp.monsterlvl3[5] = new Ibol(gp);
        gp.monsterlvl3[5].posx = gp.tileSize*7;
        gp.monsterlvl3[5].posy = gp.tileSize*10;



    }
    
}
