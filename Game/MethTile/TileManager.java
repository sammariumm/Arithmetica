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
        tile = new Tile[50];
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
            File t14 = new File("Game/Res/Tiles/grass_bottom_left_edge_16.png");     
            File t15 = new File("Game/Res/Tiles/grass_bottom_right_edge_16.png");     
            File t16 = new File("Game/Res/Tiles/grass_left_edge_16.png");     
            File t17 = new File("Game/Res/Tiles/grass_micro_bottom_left_edge_16.png");
            File t18 = new File("Game/Res/Tiles/grass_micro_bottom_right_edge_16.png");     
            File t19 = new File("Game/Res/Tiles/grass_right_edge_16.png");
            File t20 = new File("Game/Res/Tiles/grass_top_edge_16.png");
            File t21 = new File("Game/Res/Tiles/grass_top_left_edge_16.png");     
            File t22 = new File("Game/Res/Tiles/grass_top_right_edge_16.png"); 
            File t23 = new File("Game/Res/Tiles/autumn_tree_16.png");     
            File t24 = new File("Game/Res/Tiles/grass_covered_autumn_leaves_16.png");
            File t25 = new File("Game/Res/Tiles/wood_plank_16.png");
            File t26 = new File("Game/Res/Tiles/waterfall_top_16.png");     
            File t27 = new File("Game/Res/Tiles/waterfall_bottom_16.png"); 
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(t0);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(t1);
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(t2);
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(t3);
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(t4);
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(t5);
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(t6);
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(t7);
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(t8);
            tile[8].collision = true;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(t9);
            tile[9].collision = true;
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(t10);
            tile[10].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(t11);
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(t12);
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(t13);
            tile[13].collision = true;
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(t14);
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(t15);
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(t16);
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(t17);
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(t18);
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(t19);
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(t20);
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(t21);
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(t22);
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(t23);
            tile[23].collision = true;
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(t24);
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(t25);
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(t26);
            tile[26].collision = true;
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(t27);
            tile[27].collision = true;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadMap() {
        try {
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
        } 
        catch (Exception e) {

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
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null); 
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}