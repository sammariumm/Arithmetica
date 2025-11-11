package Game.MethMain;

import Game.Entity.NPC_EasterEgg;
import Game.monster.MON_YellowSlime;

public class AssetSetter 
{
    MethGamePanel gp;
    
    public AssetSetter(MethGamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {

    }

    public void setNPC()
    {
        //gp.npc[0] = new NPC_EasterEgg(gp);
        //gp.npc[0].worldX = gp.tileSize * 2;
        //gp.npc[0].worldY = gp.tileSize * 3; 

        //gp.npc[1] = new NPC_EasterEgg(gp);
        //gp.npc[1].worldX = gp.tileSize * 6;
        //gp.npc[1].worldY = gp.tileSize * 7; 
    }

    public void setMonster()
    {
        gp.monster[0] = new MON_YellowSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 10;
        gp.monster[0].worldY = gp.tileSize * 10;
        gp.monster[0].holdsCorrectAnswer = true;

        gp.monster[1] = new MON_YellowSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 14;
        gp.monster[1].worldY = gp.tileSize * 14;
        gp.monster[1].holdsCorrectAnswer = false;

        gp.monster[2] = new MON_YellowSlime(gp);
        gp.monster[2].worldX = gp.tileSize * 24;
        gp.monster[2].worldY = gp.tileSize * 24;
        gp.monster[2].holdsCorrectAnswer = false;

        gp.monster[3] = new MON_YellowSlime(gp);
        gp.monster[3].worldX = gp.tileSize * 25;
        gp.monster[3].worldY = gp.tileSize * 25;
        gp.monster[3].holdsCorrectAnswer = false;
    }
}
