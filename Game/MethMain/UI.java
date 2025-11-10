package Game.MethMain;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Game.Object.OBJ_Heart;
import Game.MethMain.MethGamePanel;
import Game.Entity.Entity;

import java.awt.Font;
import java.awt.Graphics2D;

public class UI 
{
    MethGamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage heart_full, heart_half, heart_empty;
    
    public UI(MethGamePanel gp)
    {
        this.gp = gp;

        font = new Font("Arial", Font.PLAIN, 40);

        // create new obj
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        //g2.setFont(font);
        //g2.setColor(Color.white);

        // game state
        drawPlayerLife();
    }

    public void drawPlayerLife()
    {  
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // draw max
        while(i < gp.player.maxLife / 2)
        {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // draw current
        while(i < gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
}
