package Game.Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

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

        
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();

    }

    public void setDefaultValues() {

        worldX = 640;
        worldY = 900;
        speed = 6;
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

            up1 = setup("/Game/Res/player/manyakis_up_1",gp.tileSize,gp.tileSize);
            up2 = setup("/Game/Res/player/manyakis_up_2",gp.tileSize,gp.tileSize);
            down1 = setup("/Game/Res/player/manyakis_down_1",gp.tileSize,gp.tileSize);
            down2 = setup("/Game/Res/player/manyakis_down_2",gp.tileSize,gp.tileSize);
            left1 = setup("/Game/Res/player/manyakis_left_1",gp.tileSize,gp.tileSize);
            left2 = setup("/Game/Res/player/manyakis_left_2",gp.tileSize,gp.tileSize);
            right1 = setup("/Game/Res/player/manyakis_right_1",gp.tileSize,gp.tileSize);
            right2 = setup("/Game/Res/player/manyakis_right_2",gp.tileSize,gp.tileSize);
    }

    public void getPlayerAttackImage() {

            attackUp1 = setup("/Game/Res/player/manyakis_attack_up_1",gp.tileSize,gp.tileSize*2);
            attackUp2 = setup("/Game/Res/player/manyakis_attack_up_2",gp.tileSize,gp.tileSize*2);
            attackDown1 = setup("/Game/Res/player/manyakis_attack_down_1",gp.tileSize,gp.tileSize*2);
            attackDown2 = setup("/Game/Res/player/manyakis_attack_down_2",gp.tileSize,gp.tileSize*2);
            attackLeft1 = setup("/Game/Res/player/manyakis_attack_left_1",gp.tileSize*2,gp.tileSize);
            attackLeft2 = setup("/Game/Res/player/manyakis_attack_left_2",gp.tileSize*2,gp.tileSize);
            attackRight1 = setup("/Game/Res/player/manyakis_attack_right_1",gp.tileSize*2,gp.tileSize);
            attackRight2 = setup("/Game/Res/player/manyakis_attack_right_2",gp.tileSize*2,gp.tileSize);
    }

    

    public void update() {

        if (attacking == true) {
            attacking();
    }
        else if (methH.enterPressed == true) {
            attacking = true;
            spriteCounter = 0;
            // Optionally reset methH.enterPressed so it doesn’t repeat next frame
            methH.enterPressed = false;
    }

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

    public void attacking() { 

        spriteCounter++;

        if(spriteCounter <=5) {
            spriteNum = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
        }

            // Save for Current World X and Y and Solid Area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeigth = solidArea.height;

            //Adjust player's worldX/Y for theattackArea
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            
                default: break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeigth;

    
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void interactNPC(int i)
    {
        //if (gp.methH.enterPressed == true) {
            
        //}
            //if(i != 999)
            // {
            //     
            // }
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
            System.out.println("Hit");
        }
    }

    public void damageMonster(int i) {
    if (i != 999) {
        if (gp.monster[i].invincible == false) {
            boolean correct = gp.monster[i].holdsCorrectAnswer;
            gp.monster[i].life -= 1;
            gp.monster[i].invincible = true;

            if (gp.monster[i].life <= 0) {
                gp.monster[i] = null;

                // Respawn the defeated monster (not necessarily correct)
                gp.spawnMonster(i, false);

                if (correct) {
                    gp.score += 67;
                    gp.ui.correctEnemySlain = true;
                    System.out.println("Defeated");

                    // ✅ Choose a *new* correct monster
                    Random random = new Random();
                    int randomIndex = random.nextInt(gp.monster.length);

                    for (int j = 0; j < gp.monster.length; j++) {
                        if (gp.monster[j] != null) {
                            // Spawn correct one again
                            gp.monster[j].holdsCorrectAnswer = (j == randomIndex);

                            // Optional: update its display text
                            if (j == randomIndex) {
                                // get correct answer from UI
                                int correctIndex = gp.ui.currentPromptIndex;
                                String correctAnswer = gp.ui.mathPromptAnswer[correctIndex];
                                gp.monster[j].displayText = "" + correctAnswer;
                            } else {
                                gp.monster[j].displayText = "" + (int)(Math.random() * 99 + 1);
                            }
                        }
                    }
                }
            }
        }
    }
}



    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int drawX = screenX;
        int drawY = screenY;
        int drawWidth = gp.tileSize;
        int drawHeight = gp.tileSize;

        switch (direction) {
            case "up":
                if(attacking == false) {
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                }
                if(attacking == true) {
                    if(spriteNum == 1) {image = attackUp1;}
                    if(spriteNum == 2) {image = attackUp2;}
                    drawHeight = gp.tileSize * 2;
                    drawY = screenY - gp.tileSize;
                }
                break;

            case "down":
                if(attacking == false) {
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                }
                if(attacking == true) {
                    if(spriteNum == 1) {image = attackDown1;}
                    if(spriteNum == 2) {image = attackDown2;}
                    drawHeight = gp.tileSize * 2;
                }
                break;

            case "left":
                if(attacking == false) {
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                }
                if(attacking == true) {
                    if(spriteNum == 1) {image = attackLeft1;}
                    if(spriteNum == 2) {image = attackLeft2;}
                    drawWidth = gp.tileSize * 2;
                    drawX = screenX - gp.tileSize;
                }
                break;

            case "right":
                if(attacking == false) {
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                }
                if(attacking == true) {
                    if(spriteNum == 1) {image = attackRight1;}
                    if(spriteNum == 2) {image = attackRight2;}
                    drawWidth = gp.tileSize * 2;
                }
                break;

            default:
                break;
        }

        if(invincible == true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, drawX, drawY, drawWidth, drawHeight, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.WHITE);
        //g2.drawString("Invincible Counter: " + invincibleCounter,10, 400);
    }
}