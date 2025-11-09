package Game.Entity;

import java.io.IOException;

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

            up1 = setup("/Game/Res/npc/easter_egg_up_1");
            up2 = setup("/Game/Res/npc/easter_egg_up_2");
            down1 = setup("/Game/Res/npc/easter_egg_down_1");
            down2 = setup("/Game/Res/npc/easter_egg_down_2");
            left1 = setup("/Game/Res/npc/easter_egg_left_1");
            left2 = setup("/Game/Res/npc/easter_egg_left_2");
            right1 = setup("/Game/Res/npc/easter_egg_right_1");
            right2 = setup("/Game/Res/npc/easter_egg_right_2");

    }
}
