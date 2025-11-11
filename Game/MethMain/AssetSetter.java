package Game.MethMain;

import java.util.Random;

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

    public void setMonster() {
    // Choose which monster will have the correct answer
    int correctMonsterIndex = (int)(Math.random() * 4);

    for (int i = 0; i < 4; i++) {
        boolean isCorrect = (i == correctMonsterIndex);
        gp.spawnMonster(i, isCorrect);
    }
}

}
