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

        // mathPrompt
        mathPrompt[0] = "12 + 7";
        mathPrompt[1] = "8 x 5";
        mathPrompt[2] = "45 - 23";
        mathPrompt[3] = "9 x 7";
        mathPrompt[4] = "33 + 21";
        mathPrompt[5] = "14 - 18";
        mathPrompt[6] = "6 x 9";
        mathPrompt[7] = "27 + 15";
        mathPrompt[8] = "5 x 8";
        mathPrompt[9] = "40 - 12";
        mathPrompt[10] = "11 + 34";
        mathPrompt[11] = "7 x 6";
        mathPrompt[12] = "50 - 55";
        mathPrompt[13] = "8 x 9";
        mathPrompt[14] = "23 + 12";
        mathPrompt[15] = "9 x 5";
        mathPrompt[16] = "67 - 34";
        mathPrompt[17] = "6 x 7";
        mathPrompt[18] = "14 + 29";
        mathPrompt[19] = "8 x 5";
        mathPrompt[20] = "78 - 42";
        mathPrompt[21] = "9 x 8";
        mathPrompt[22] = "19 + 17";
        mathPrompt[23] = "5 x 6";
        mathPrompt[24] = "33 - 40";
        mathPrompt[25] = "7 x 9";
        mathPrompt[26] = "22 + 18";
        mathPrompt[27] = "8 x 7";
        mathPrompt[28] = "60 - 25";
        mathPrompt[29] = "6 x 5";
        mathPrompt[30] = "44 + 11";
        mathPrompt[31] = "9 x 6";
        mathPrompt[32] = "30 - 35";
        mathPrompt[33] = "7 x 8";
        mathPrompt[34] = "28 + 19";
        mathPrompt[35] = "5 x 9";
        mathPrompt[36] = "49 - 20";
        mathPrompt[37] = "6 x 8";
        mathPrompt[38] = "33 + 27";
        mathPrompt[39] = "8 x 6";
        mathPrompt[40] = "21 - 30";
        mathPrompt[41] = "9 x 7";
        mathPrompt[42] = "15 + 44";
        mathPrompt[43] = "7 x 5";
        mathPrompt[44] = "55 - 22";
        mathPrompt[45] = "8 x 8";
        mathPrompt[46] = "18 + 23";
        mathPrompt[47] = "6 x 6";
        mathPrompt[48] = "39 - 45";
        mathPrompt[49] = "9 x 5";
        mathPrompt[50] = "25 + 37";
        mathPrompt[51] = "7 x 9";
        mathPrompt[52] = "61 - 28";
        mathPrompt[53] = "8 x 6";
        mathPrompt[54] = "14 + 42";
        mathPrompt[55] = "5 x 8";
        mathPrompt[56] = "50 - 30";
        mathPrompt[57] = "6 x 7";
        mathPrompt[58] = "17 + 26";
        mathPrompt[59] = "9 x 9";
        mathPrompt[60] = "40 - 55";
        mathPrompt[61] = "7 x 6";
        mathPrompt[62] = "21 + 33";
        mathPrompt[63] = "8 x 5";
        mathPrompt[64] = "36 - 18";
        mathPrompt[65] = "6 x 9";
        mathPrompt[66] = "29 + 14";
        mathPrompt[67] = "9 x 7";
        mathPrompt[68] = "48 - 53";
        mathPrompt[69] = "5 x 6";
        mathPrompt[70] = "22 + 41";
        mathPrompt[71] = "7 x 8";
        mathPrompt[72] = "33 - 39";
        mathPrompt[73] = "8 x 9";
        mathPrompt[74] = "16 + 27";
        mathPrompt[75] = "6 x 5";
        mathPrompt[76] = "60 - 47";
        mathPrompt[77] = "9 x 8";
        mathPrompt[78] = "12 + 34";
        mathPrompt[79] = "7 x 6";
        mathPrompt[80] = "28 - 35";
        mathPrompt[81] = "5 x 9";
        mathPrompt[82] = "39 + 22";
        mathPrompt[83] = "6 x 7";
        mathPrompt[84] = "45 - 50";
        mathPrompt[85] = "8 x 5";
        mathPrompt[86] = "23 + 17";
        mathPrompt[87] = "9 x 6";
        mathPrompt[88] = "31 - 40";
        mathPrompt[89] = "7 x 8";
        mathPrompt[90] = "14 + 36";
        mathPrompt[91] = "6 x 9";
        mathPrompt[92] = "55 - 33";
        mathPrompt[93] = "8 x 7";
        mathPrompt[94] = "26 + 15";
        mathPrompt[95] = "9 x 5";
        mathPrompt[96] = "41 - 60";
        mathPrompt[97] = "7 x 6";
        mathPrompt[98] = "19 + 29";
        mathPrompt[99] = "5 x 8";

        mathPromptAnswer[0] = "19";
        mathPromptAnswer[1] = "40";
        mathPromptAnswer[2] = "22";
        mathPromptAnswer[3] = "63";
        mathPromptAnswer[4] = "54";
        mathPromptAnswer[5] = "-4";
        mathPromptAnswer[6] = "54";
        mathPromptAnswer[7] = "42";
        mathPromptAnswer[8] = "40";
        mathPromptAnswer[9] = "28";
        mathPromptAnswer[10] = "45";
        mathPromptAnswer[11] = "42";
        mathPromptAnswer[12] = "-5";
        mathPromptAnswer[13] = "72";
        mathPromptAnswer[14] = "35";
        mathPromptAnswer[15] = "45";
        mathPromptAnswer[16] = "33";
        mathPromptAnswer[17] = "42";
        mathPromptAnswer[18] = "43";
        mathPromptAnswer[19] = "40";
        mathPromptAnswer[20] = "36";
        mathPromptAnswer[21] = "72";
        mathPromptAnswer[22] = "36";
        mathPromptAnswer[23] = "30";
        mathPromptAnswer[24] = "-7";
        mathPromptAnswer[25] = "63";
        mathPromptAnswer[26] = "40";
        mathPromptAnswer[27] = "56";
        mathPromptAnswer[28] = "35";
        mathPromptAnswer[29] = "30";
        mathPromptAnswer[30] = "55";
        mathPromptAnswer[31] = "54";
        mathPromptAnswer[32] = "-5";
        mathPromptAnswer[33] = "56";
        mathPromptAnswer[34] = "47";
        mathPromptAnswer[35] = "45";
        mathPromptAnswer[36] = "29";
        mathPromptAnswer[37] = "48";
        mathPromptAnswer[38] = "60";
        mathPromptAnswer[39] = "48";
        mathPromptAnswer[40] = "-9";
        mathPromptAnswer[41] = "63";
        mathPromptAnswer[42] = "59";
        mathPromptAnswer[43] = "35";
        mathPromptAnswer[44] = "33";
        mathPromptAnswer[45] = "64";
        mathPromptAnswer[46] = "41";
        mathPromptAnswer[47] = "36";
        mathPromptAnswer[48] = "-6";
        mathPromptAnswer[49] = "45";
        mathPromptAnswer[50] = "62";
        mathPromptAnswer[51] = "63";
        mathPromptAnswer[52] = "33";
        mathPromptAnswer[53] = "48";
        mathPromptAnswer[54] = "56";
        mathPromptAnswer[55] = "40";
        mathPromptAnswer[56] = "20";
        mathPromptAnswer[57] = "42";
        mathPromptAnswer[58] = "43";
        mathPromptAnswer[59] = "81";
        mathPromptAnswer[60] = "-15";
        mathPromptAnswer[61] = "42";
        mathPromptAnswer[62] = "54";
        mathPromptAnswer[63] = "40";
        mathPromptAnswer[64] = "18";
        mathPromptAnswer[65] = "54";
        mathPromptAnswer[66] = "43";
        mathPromptAnswer[67] = "63";
        mathPromptAnswer[68] = "-5";
        mathPromptAnswer[69] = "30";
        mathPromptAnswer[70] = "63";
        mathPromptAnswer[71] = "56";
        mathPromptAnswer[72] = "-6";
        mathPromptAnswer[73] = "72";
        mathPromptAnswer[74] = "43";
        mathPromptAnswer[75] = "30";
        mathPromptAnswer[76] = "13";
        mathPromptAnswer[77] = "72";
        mathPromptAnswer[78] = "46";
        mathPromptAnswer[79] = "42";
        mathPromptAnswer[80] = "-7";
        mathPromptAnswer[81] = "45";
        mathPromptAnswer[82] = "61";
        mathPromptAnswer[83] = "42";
        mathPromptAnswer[84] = "-5";
        mathPromptAnswer[85] = "40";
        mathPromptAnswer[86] = "40";
        mathPromptAnswer[87] = "54";
        mathPromptAnswer[88] = "-9";
        mathPromptAnswer[89] = "56";
        mathPromptAnswer[90] = "50";
        mathPromptAnswer[91] = "54";
        mathPromptAnswer[92] = "22";
        mathPromptAnswer[93] = "56";
        mathPromptAnswer[94] = "41";
        mathPromptAnswer[95] = "45";
        mathPromptAnswer[96] = "-19";
        mathPromptAnswer[97] = "42";
        mathPromptAnswer[98] = "48";
        mathPromptAnswer[99] = "40";

        Random rand = new Random();
        int randomIndex = rand.nextInt(100);
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
            currentPromptIndex = random.nextInt(100);
            currentPrompt = mathPrompt[currentPromptIndex];
            correctEnemySlain = false;

            gp.aSetter.setMonster();
        }

        return currentPrompt; // return last shown prompt
    }

}