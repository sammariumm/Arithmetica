package Game.MethMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.Object.OBJ_Heart;
import Game.MethMain.MethGamePanel;
import Game.Entity.Entity;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class UI 
{
    MethGamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage heart_full, heart_half, heart_empty;
    
    public String[] mathPrompt = new String[50];
    public String[] mathPromptAnswer = new String[50];
    public String currentPrompt = "";   // stores the last shown math prompt
    public boolean correctEnemySlain = false; // persist between frames
    public int currentPromptIndex = 0;
    
    public UI(MethGamePanel gp)
    {
        this.gp = gp;

        font = new Font("Monospaced", Font.BOLD, 24);

        // create new obj
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;

        mathPrompt[0] = "12 + 97"; mathPromptAnswer[0] = "109";
        mathPrompt[1] = "85 - 23"; mathPromptAnswer[1] = "62";
        mathPrompt[2] = "9 x 6"; mathPromptAnswer[2] = "54";
        mathPrompt[3] = "-44 - 12"; mathPromptAnswer[3] = "-56";
        mathPrompt[4] = "7 x 8"; mathPromptAnswer[4] = "56";
        mathPrompt[5] = "132 - 55"; mathPromptAnswer[5] = "77";
        mathPrompt[6] = "3 x 9"; mathPromptAnswer[6] = "27";
        mathPrompt[7] = "-90 + 78"; mathPromptAnswer[7] = "-12";
        mathPrompt[8] = "65 + 38"; mathPromptAnswer[8] = "103";
        mathPrompt[9] = "2 x 17"; mathPromptAnswer[9] = "34";
        mathPrompt[10] = "58 - 91"; mathPromptAnswer[10] = "-33";
        mathPrompt[11] = "4 x 10"; mathPromptAnswer[11] = "40";
        mathPrompt[12] = "73 + 21"; mathPromptAnswer[12] = "94";
        mathPrompt[13] = "10 - 67"; mathPromptAnswer[13] = "-57";
        mathPrompt[14] = "6 x 5"; mathPromptAnswer[14] = "30";
        mathPrompt[15] = "117 - 79"; mathPromptAnswer[15] = "38";
        mathPrompt[16] = "8 x 9"; mathPromptAnswer[16] = "72";
        mathPrompt[17] = "-99 + 48"; mathPromptAnswer[17] = "-51";
        mathPrompt[18] = "55 + 44"; mathPromptAnswer[18] = "99";
        mathPrompt[19] = "1 x 10"; mathPromptAnswer[19] = "10";
        mathPrompt[20] = "82 - 95"; mathPromptAnswer[20] = "-13";
        mathPrompt[21] = "7 x 13"; mathPromptAnswer[21] = "91";
        mathPrompt[22] = "60 + 29"; mathPromptAnswer[22] = "89";
        mathPrompt[23] = "5 x 16"; mathPromptAnswer[23] = "80";
        mathPrompt[24] = "-34 - 20"; mathPromptAnswer[24] = "-54";
        mathPrompt[25] = "9 x 2"; mathPromptAnswer[25] = "18";
        mathPrompt[26] = "-76 - 19"; mathPromptAnswer[26] = "-95";
        mathPrompt[27] = "-100 - 45"; mathPromptAnswer[27] = "-145";
        mathPrompt[28] = "8 x 4"; mathPromptAnswer[28] = "32";
        mathPrompt[29] = "66 + 27"; mathPromptAnswer[29] = "93";
        mathPrompt[30] = "3 x 7"; mathPromptAnswer[30] = "21";
        mathPrompt[31] = "-88 + 47"; mathPromptAnswer[31] = "-41";
        mathPrompt[32] = "5 x 9"; mathPromptAnswer[32] = "45";
        mathPrompt[33] = "120 - 35"; mathPromptAnswer[33] = "85";
        mathPrompt[34] = "2 x 10"; mathPromptAnswer[34] = "20";
        mathPrompt[35] = "-150 + 229"; mathPromptAnswer[35] = "79";
        mathPrompt[36] = "6 x 7"; mathPromptAnswer[36] = "42";
        mathPrompt[37] = "97 + 13"; mathPromptAnswer[37] = "110";
        mathPrompt[38] = "9 x 1"; mathPromptAnswer[38] = "9";
        mathPrompt[39] = "-55 + 0"; mathPromptAnswer[39] = "-55";
        mathPrompt[40] = "8 x 14"; mathPromptAnswer[40] = "112";
        mathPrompt[41] = "102 + 17"; mathPromptAnswer[41] = "119";
        mathPrompt[42] = "4 x 3"; mathPromptAnswer[42] = "12";
        mathPrompt[43] = "-63 + 36"; mathPromptAnswer[43] = "-27";
        mathPrompt[44] = "7 x 2"; mathPromptAnswer[44] = "14";
        mathPrompt[45] = "88 + 33"; mathPromptAnswer[45] = "121";
        mathPrompt[46] = "6 x 8"; mathPromptAnswer[46] = "48";
        mathPrompt[47] = "-50 + 25"; mathPromptAnswer[47] = "-25";
        mathPrompt[48] = "5 x 10"; mathPromptAnswer[48] = "50";
        mathPrompt[49] = "90 - 200"; mathPromptAnswer[49] = "-110";

        Random rand = new Random();
        int randomIndex = rand.nextInt(50);
        currentPrompt = mathPrompt[randomIndex];
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

        // Center text
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int textWidth = metrics.stringWidth(mp);
        int textHeight = metrics.getHeight();

        // x position: center horizontally
        int textX = x + (width - textWidth) / 2;

        // y position: center vertically (baseline)
        int textY = y + (height - textHeight) / 2 + metrics.getAscent();

        g2.drawString(mp, textX, textY);
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
            currentPromptIndex = random.nextInt(50);
            currentPrompt = mathPrompt[currentPromptIndex];
            correctEnemySlain = false;

            gp.aSetter.setMonster();
        }

        return currentPrompt; // return last shown prompt
    }

}