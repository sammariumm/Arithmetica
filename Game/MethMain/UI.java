package Game.MethMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

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
    
    public String[] mathPrompt = new String[100];
    public String[] mathPromptAnswer = new String[100];
    public String currentPrompt = "";   // stores the last shown math prompt
    public boolean correctEnemySlain = false; // persist between frames
    public int currentPromptIndex = 0;
    
    public UI(MethGamePanel gp)
    {
        this.gp = gp;

        font = new Font("Arial", Font.PLAIN, 40);

        // create new obj
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;

        // Initialize prompts
        mathPrompt[0] = "3 + 7";
        mathPrompt[1] = "1 + 5";
        mathPrompt[2] = "2 x 7";
        mathPrompt[3] = "6 x 5";
        mathPrompt[4] = "8 - 4";
        mathPrompt[5] = "3 - 1";
        mathPrompt[6] = "9 x 8";
        mathPrompt[7] = "9 + 6";
        mathPrompt[8] = "4 - 2";
        mathPrompt[9] = "4 x 5";

        // answers
        mathPromptAnswer[0] = "10";
        mathPromptAnswer[1] = "6";
        mathPromptAnswer[2] = "14";
        mathPromptAnswer[3] = "30";
        mathPromptAnswer[4] = "4";
        mathPromptAnswer[5] = "2";
        mathPromptAnswer[6] = "72";
        mathPromptAnswer[7] = "15";
        mathPromptAnswer[8] = "2";
        mathPromptAnswer[9] = "20";

        // start with one prompt
        currentPrompt = mathPrompt[2];
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        //g2.setFont(font);
        //g2.setColor(Color.white);

        // game state
        drawPlayerLife();
        String mp = setMathPrompt();
        drawMathPrompt(mp);
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

    public void drawMathPrompt(String mp)
    {
        // window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 2;

        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;

        g2.drawString(mp, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public String setMathPrompt()
    {
        // change the prompt if the correct enemy was slain
        if (correctEnemySlain)
        {
            Random random = new Random();
            currentPromptIndex = random.nextInt(10);
            currentPrompt = mathPrompt[currentPromptIndex];
            correctEnemySlain = false;

            gp.aSetter.setMonster();
        }

        return currentPrompt; // return last shown prompt
    }

}