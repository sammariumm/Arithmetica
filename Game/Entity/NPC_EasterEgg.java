package Game.Entity;

import java.io.IOException;
import java.util.Random;

import Game.MethMain.MethGamePanel;

public class NPC_EasterEgg extends Entity
{
    public NPC_EasterEgg(MethGamePanel gp)
    {
        super(gp);

        direction = "left";
        speed = 2;

        getNPCImage();
    }

    public void getNPCImage() {



            //File f1 = new File("Game/Res/player/manyakis_up_1.png");
            //File f2 = new File("Game/Res/player/manyakis_up_2.png");
            //File f3 = new File("Game/Res/player/manyakis_down_1.png");
            //File f4 = new File("Game/Res/player/manyakis_down_2.png");
           //File f5 = new File("Game/Res/player/manyakis_left_1.png");
            //File f6 = new File("Game/Res/player/manyakis_left_2.png");
           // File f7 = new File("Game/Res/player/manyakis_right_1.png");
           //File f8 = new File("Game/Res/player/manyakis_right_2.png");

            up1 = setup("/Game/Res/npc/yellowslime_1");
            up2 = setup("/Game/Res/npc/yellowslime_2");
            down1 = setup("/Game/Res/npc/yellowslime_1");
            down2 = setup("/Game/Res/npc/yellowslime_2");
            left1 = setup("/Game/Res/npc/yellowslime_1");
            left2 = setup("/Game/Res/npc/yellowslime_2");
            right1 = setup("/Game/Res/npc/yellowslime_1");
            right2 = setup("/Game/Res/npc/yellowslime_2");

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
