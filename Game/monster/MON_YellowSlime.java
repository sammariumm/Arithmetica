package Game.monster;

import java.io.IOException;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Color;

import Game.Entity.Entity;
import Game.MethMain.MethGamePanel;

public class MON_YellowSlime extends Entity
{
    MethGamePanel gp;

    public MON_YellowSlime(MethGamePanel gp)
    {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Yellow Slime";
        speed = 1;
        direction = "down";
        maxLife = 4;
        life = maxLife;
        boolean holdsCorrectAnswer;
        displayText = "";
        showText = false;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }



    public void getImage()
    {
        //up1 = setup("/Game/Res/monster/yellowslime_1");
        //up2 = setup("/Game/Res/monster/yellowslime_2");

        //down1 = setup("/Game/Res/monster/yellowslime_1");
        //down2 = setup("/Game/Res/monster/yellowslime_2");

        //left1 = setup("/Game/Res/monster/yellowslime_1");
        //left2 = setup("/Game/Res/monster/yellowslime_2");

        //right1 = setup("/Game/Res/monster/yellowslime_1");
        //right2 = setup("/Game/Res/monster/yellowslime_2");

        up1 = setup("/Game/Res/npc/easter_egg_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/Game/Res/npc/easter_egg_up_2",gp.tileSize,gp.tileSize);

        down1 = setup("/Game/Res/npc/easter_egg_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/Game/Res/npc/easter_egg_down_2",gp.tileSize,gp.tileSize);

        left1 = setup("/Game/Res/npc/easter_egg_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/Game/Res/npc/easter_egg_left_2",gp.tileSize,gp.tileSize);

        right1 = setup("/Game/Res/npc/easter_egg_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/Game/Res/npc/easter_egg_right_2",gp.tileSize,gp.tileSize);
    }

    public void setAction()
    {
        actionLockCounter++; 

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25)
            {
                direction = "up";
            }
            if(i > 25 && i <= 50)
            {
                direction = "down";
            }
            if(i > 50 && i <= 75)
            {
                direction = "left";
            }
            if(i > 75 && i <= 100)
            {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
