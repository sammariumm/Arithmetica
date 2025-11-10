package Game.Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.MethMain.MethGamePanel;
import Game.MethMain.MethHandler;


public class Player extends Entity{
    
    MethHandler methH;

    public final int screenX;
    public final int screenY;

    public Player(MethGamePanel gp, MethHandler methH) {
        
        super(gp);
        
        this.methH = methH;

        // for default square set as x = 0, y = 0, gp.tileSize, gp.tileSize
        solidArea = new Rectangle(8, 16, 32, 32); 

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";

        // player stat
        maxLife = 6;
        life = maxLife;

    }

    public void getPlayerImage() {

            //File f1 = new File("Game/Res/player/manyakis_up_1.png");
            //File f2 = new File("Game/Res/player/manyakis_up_2.png");
            //File f3 = new File("Game/Res/player/manyakis_down_1.png");
            //File f4 = new File("Game/Res/player/manyakis_down_2.png");
           //File f5 = new File("Game/Res/player/manyakis_left_1.png");
            //File f6 = new File("Game/Res/player/manyakis_left_2.png");
           // File f7 = new File("Game/Res/player/manyakis_right_1.png");
           //File f8 = new File("Game/Res/player/manyakis_right_2.png");

            up1 = setup("/Game/Res/player/manyakis_up_1");
            up2 = setup("/Game/Res/player/manyakis_up_2");
            down1 = setup("/Game/Res/player/manyakis_down_1");
            down2 = setup("/Game/Res/player/manyakis_down_2");
            left1 = setup("/Game/Res/player/manyakis_left_1");
            left2 = setup("/Game/Res/player/manyakis_left_2");
            right1 = setup("/Game/Res/player/manyakis_right_1");
            right2 = setup("/Game/Res/player/manyakis_right_2");
    }

    

    public void update() {

        if (methH.upPressed == true || methH.downPressed == true || methH.leftPressed == true || methH.rightPressed == true ) {

            if (methH.upPressed == true) {
                direction = "up";
            }
            else if (methH.downPressed == true) {
                direction = "down"; 
            }
            else if (methH.leftPressed == true) { 
                direction = "left";  
            }
            else if (methH.rightPressed == true) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // Check npc collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // If collision is false, player can move else not
            if(collisionOn == false)
            {
                switch(direction)
                {
                    case "up": 
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;

            if(spriteCounter > 14) {

                if(spriteNum == 1) {
                    spriteNum = 2;
                }

                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(invincible == true)
        {
            invincibleCounter++;

            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void interactNPC(int i)
    {
        if(i != 999)
        {
            
        }
    }

    public void contactMonster(int i)
    {
        if(i != 999)
        {
            if(invincible == false)
            {
                life -= 1;
                invincible = true;
            }
            life -= 1;
            System.out.println("BURAT");
        }
    }

    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }

                if(spriteNum == 2) {
                    image = up2;
                }
                break;

            case "down":

                 if(spriteNum == 1) {
                    image = down1;
                }

                if(spriteNum == 2) {
                    image = down2;
                }
                break;

            case "left":

                 if(spriteNum == 1) {
                    image = left1;
                }

                if(spriteNum == 2) {
                    image = left2;
                }
                break;

            case "right":

                 if(spriteNum == 1) {
                    image = right1;
                }

                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        
            default:
                break;
        }

        if(invincible == true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.WHITE);
        //g2.drawString("Invincible Counter: " + invincibleCounter,10, 400);
    }
}
