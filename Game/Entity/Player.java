package Game.Entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Game.MethMain.MethGamePanel;
import Game.MethMain.MethHandler;


public class Player extends Entity{
    
    MethGamePanel gp;
    MethHandler methH;

    public Player(MethGamePanel gp, MethHandler methH) {

        this.gp = gp;
        this.methH = methH;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {

        try {

            File f1 = new File("Game/Res/player/manyakis_up_1.png");
            File f2 = new File("Game/Res/player/manyakis_up_2.png");
            File f3 = new File("Game/Res/player/manyakis_down_1.png");
            File f4 = new File("Game/Res/player/manyakis_down_2.png");
            File f5 = new File("Game/Res/player/manyakis_left_1.png");
            File f6 = new File("Game/Res/player/manyakis_left_2.png");
            File f7 = new File("Game/Res/player/manyakis_right_1.png");
            File f8 = new File("Game/Res/player/manyakis_right_2.png");

            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (methH.upPressed == true || methH.downPressed == true || methH.leftPressed == true || methH.rightPressed == true ) {

            if (methH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if (methH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            else if (methH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            else if (methH.rightPressed == true) {
                direction = "right";
                x += speed;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
