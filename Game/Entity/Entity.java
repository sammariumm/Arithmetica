package Game.Entity;
import Game.MethMain.MethGamePanel;
import Game.MethMain.UtilityTool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Entity {
    MethGamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int worldX, worldY; 
    public boolean collision = false;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public int invincibleCounter = 0;
    public int maxLife;
    public String name;
    public int speed;
    public int life;
    public int actionLockCounter = 0;
    public int type;
    public boolean holdsCorrectAnswer = false;
    public String displayText = "";
    public boolean showText = false;
    public Entity(MethGamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {

    }
    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkEntity(this, gp.monster);
        gp.collisionChecker.checkEntity(this, gp.npc);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true) {
            if(gp.player.invincible == false) {
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        if(collisionOn == false) {
            switch(direction) {
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
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                    break;
                default:
                    break;
            }
            if(invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null); 
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (showText && displayText != null && !displayText.isEmpty()) {
            g2.setFont(new Font("Monospaced", Font.BOLD, 16));
            g2.setColor(Color.WHITE);
            int textWidth = g2.getFontMetrics().stringWidth(displayText);
            int textX = screenX + (gp.tileSize / 2) - (textWidth / 2);
            int textY = screenY - 10;
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRoundRect(textX - 4, textY - 16, textWidth + 8, 18, 6, 6);
            g2.setColor(Color.WHITE);
            g2.drawString(displayText, textX, textY);
        }
    }
    public BufferedImage setup(String imageName, int width, int height) {   
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            System.out.println("Trying to load: " + imageName + ".png");
            System.out.println("Resource found: " + getClass().getResource(imageName + ".png"));

            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch(IOException e) {   
            e.printStackTrace();
        }
        return image;
    }
}