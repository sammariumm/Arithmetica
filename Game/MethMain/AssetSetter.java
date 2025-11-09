package Game.MethMain;

import Game.Entity.NPC_EasterEgg;

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
        gp.npc[0] = new NPC_EasterEgg(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 10; 
    }
}
