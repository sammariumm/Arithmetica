package Game.MethTile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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

        tile = new Tile[10];
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenrow];

        getTileImage();
        loadMap("Game/Res/Maps/map01.txt");

    }
    
    public void getTileImage() {

        try {
            
            File t1 = new File("Game/Res/Tiles/grass_16.png");
            File t2 = new File("Game/Res/Tiles/cobble_wall_16.png");
            File t3 = new File("Game/Res/Tiles/water_16.png");
            File t4 = new File("Game/Res/Tiles/final_rock_16.png");
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(t1);
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(t2);
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(t3);
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(t4);
            tile[2].collision = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {

        try {

            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenrow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }
    public void draw(Graphics2D g2) {

       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;

        while (col < gp.maxScreenCol && row < gp .maxScreenrow) {
            
            int tileNum = mapTileNum [col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row ++;
                y += gp.tileSize; 
                
            }
        }
    }
}
