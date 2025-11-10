package Game.MethTile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Game.MethMain.MethGamePanel;

public class TileManager {

    MethGamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(MethGamePanel gp) {

        this.gp = gp;

        tile = new Tile[20];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();

    }
    
    public void getTileImage() {

        try {
            
            File t0 = new File("Game/Res/Tiles/main_grass_16.png");
            File t1 = new File("Game/Res/Tiles/cobble_wall_16.png");
            File t2 = new File("Game/Res/Tiles/final_water_16.png");
            File t3 = new File("Game/Res/Tiles/final_rock_16.png");     
            File t4 = new File("Game/Res/Tiles/tree_16.png");     
            File t5 = new File("Game/Res/Tiles/grass_bottom_edge_16.png");     
            File t6 = new File("Game/Res/Tiles/cliff_side_16.png");     
            File t7 = new File("Game/Res/Tiles/autumn_leave_16.png");     
            File t8 = new File("Game/Res/Tiles/leaves_on_water_16.png");
            File t9 = new File("Game/Res/Tiles/lily_pad_16.png");     
            File t10 = new File("Game/Res/Tiles/top_left_autumn_tree.png");
            File t11 = new File("Game/Res/Tiles/top_right_autumn_tree.png");
            File t12 = new File("Game/Res/Tiles/bottom_left_autumn_tree.png");     
            File t13 = new File("Game/Res/Tiles/bottom_right_autumn_tree.png"); 
            
            tile[0] = new Tile(); // malupet na grass
            tile[0].image = ImageIO.read(t0);
            
            tile[1] = new Tile(); // cobble
            tile[1].image = ImageIO.read(t1);
            tile[1].collision = true;

            tile[2] = new Tile(); // water
            tile[2].image = ImageIO.read(t2);
            tile[2].collision = true;

            tile[3] = new Tile(); // bato
            tile[3].image = ImageIO.read(t3);
            tile[3].collision = true;

            tile[4] = new Tile(); //puno
            tile[4].image = ImageIO.read(t4);
            tile[4].collision = true;
            
            tile[5] = new Tile(); //dulo ng grass
            tile[5].image = ImageIO.read(t5);

            tile[6] = new Tile(); //cliff_side
            tile[6].image = ImageIO.read(t6);
            tile[6].collision = true;

            tile[7] = new Tile(); //madahon dahon na grass
            tile[7].image = ImageIO.read(t7);

            tile[8] = new Tile(); //madahon dahon na tubeg
            tile[8].image = ImageIO.read(t8);
            tile[8].collision = true;

            tile[9] = new Tile(); //lilipad :)
            tile[9].image = ImageIO.read(t9);
            tile[9].collision = true;
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(t10);
            tile[10].collision = true;

            tile[11] = new Tile(); //malaki puno top right
            tile[11].image = ImageIO.read(t11);
            tile[11].collision = true;

            tile[12] = new Tile(); //malaki puno bottom left
            tile[12].image = ImageIO.read(t12);
            tile[12].collision = true;

            tile[13] = new Tile(); //malaki puno bottom right
            tile[13].image = ImageIO.read(t13);
            tile[13].collision = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap() {

        try {

            // WAG PALITAN PLSSDKFJDSLAFAKLWEFHJK
            InputStream is = getClass().getResourceAsStream("/Game/Res/Maps/map02.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }


    public void draw(Graphics2D g2) {

       int worldCol = 0;
       int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp .maxWorldRow) {
            
            int tileNum = mapTileNum [worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            //    worldX - gp.tileSize < gp.player./worldX + gp.player.screenX &&
            //    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            //    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null); 

                worldCol++;

                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            
            
        }
    }
}
