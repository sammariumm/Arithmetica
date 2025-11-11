package Game.MethTile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Game.MethMain.MethGamePanel;

public class TileManager 
{
    MethGamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(MethGamePanel gp) 
    {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }
    
    public void getTileImage() 
    {
        try 
        {
            // define helper function to load image
            java.util.function.Function<String, java.awt.image.BufferedImage> load = path -> {
                try (InputStream is = getClass().getResourceAsStream("/" + path)) {
                    return ImageIO.read(is);
                } catch (Exception e) {
                    System.out.println("Could not load: " + path);
                    return null;
                }
            };

            tile[0] = new Tile(); // grass
            tile[0].image = load.apply("Game/Res/Tiles/main_grass_16.png");

            tile[1] = new Tile(); // cobble
            tile[1].image = load.apply("Game/Res/Tiles/cobble_wall_16.png");
            tile[1].collision = true;

            tile[2] = new Tile(); // water
            tile[2].image = load.apply("Game/Res/Tiles/final_water_16.png");
            tile[2].collision = true;

            tile[3] = new Tile(); // rock
            tile[3].image = load.apply("Game/Res/Tiles/final_rock_16.png");
            tile[3].collision = true;

            tile[4] = new Tile(); // tree
            tile[4].image = load.apply("Game/Res/Tiles/tree_16.png");
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = load.apply("Game/Res/Tiles/grass_bottom_edge_16.png");

            tile[6] = new Tile();
            tile[6].image = load.apply("Game/Res/Tiles/cliff_side_16.png");
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = load.apply("Game/Res/Tiles/autumn_leave_16.png");

            tile[8] = new Tile();
            tile[8].image = load.apply("Game/Res/Tiles/leaves_on_water_16.png");
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = load.apply("Game/Res/Tiles/lily_pad_16.png");
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = load.apply("Game/Res/Tiles/top_left_autumn_tree.png");
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = load.apply("Game/Res/Tiles/top_right_autumn_tree.png");
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = load.apply("Game/Res/Tiles/bottom_left_autumn_tree.png");
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = load.apply("Game/Res/Tiles/bottom_right_autumn_tree.png");
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = load.apply("Game/Res/Tiles/grass_bottom_left_edge_16.png");

            tile[15] = new Tile();
            tile[15].image = load.apply("Game/Res/Tiles/grass_bottom_right_edge_16.png");

            tile[16] = new Tile();
            tile[16].image = load.apply("Game/Res/Tiles/grass_left_edge_16.png");

            tile[17] = new Tile();
            tile[17].image = load.apply("Game/Res/Tiles/grass_micro_bottom_left_edge_16.png");

            tile[18] = new Tile();
            tile[18].image = load.apply("Game/Res/Tiles/grass_micro_bottom_right_edge_16.png");

            tile[19] = new Tile();
            tile[19].image = load.apply("Game/Res/Tiles/grass_right_edge_16.png");

            tile[20] = new Tile();
            tile[20].image = load.apply("Game/Res/Tiles/grass_top_edge_16.png");

            tile[21] = new Tile();
            tile[21].image = load.apply("Game/Res/Tiles/grass_top_left_edge_16.png");

            tile[22] = new Tile();
            tile[22].image = load.apply("Game/Res/Tiles/grass_top_right_edge_16.png");

            tile[23] = new Tile();
            tile[23].image = load.apply("Game/Res/Tiles/autumn_tree_16.png");
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = load.apply("Game/Res/Tiles/grass_covered_autumn_leaves_16.png");

            tile[25] = new Tile();
            tile[25].image = load.apply("Game/Res/Tiles/wood_plank_16.png");

            tile[26] = new Tile();
            tile[26].image = load.apply("Game/Res/Tiles/waterfall_top_16.png");
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = load.apply("Game/Res/Tiles/waterfall_bottom_16.png");
            tile[27].collision = true;

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void loadMap()
    {
        try 
        {
            // WAG PALITAN PLSSDKFJDSLAFAKLWEFHJK
            InputStream is = getClass().getResourceAsStream("/Game/Res/Maps/map02.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) 
            {
                String line = br.readLine();

                while (col < gp.maxWorldCol) 
                {
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } 
        catch (Exception e) 
        {

        }

    }


    public void draw(Graphics2D g2) 
    {
       int worldCol = 0;
       int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp .maxWorldRow)
        {
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

            if (worldCol == gp.maxWorldCol) 
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
