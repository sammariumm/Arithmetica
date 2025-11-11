package Game.MethMain;
import java.util.Random;
import Game.Entity.NPC_EasterEgg;
import Game.monster.MON_YellowSlime;
public class AssetSetter {
    MethGamePanel gp;   
    public AssetSetter(MethGamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {

    }
    public void setNPC() {
        gp.npc[0] = new NPC_EasterEgg(gp);
        gp.npc[0].worldX = gp.tileSize * 2;
        gp.npc[0].worldY = gp.tileSize * 3; 
        gp.npc[1] = new NPC_EasterEgg(gp);
        gp.npc[1].worldX = gp.tileSize * 6;
        gp.npc[1].worldY = gp.tileSize * 7; 
        gp.npc[2] = new NPC_EasterEgg(gp);
        gp.npc[2].worldX = gp.tileSize * 15;
        gp.npc[2].worldY = gp.tileSize * 16; 
        gp.npc[3] = new NPC_EasterEgg(gp);
        gp.npc[3].worldX = gp.tileSize * 21;
        gp.npc[3].worldY = gp.tileSize * 22; 
        gp.npc[4] = new NPC_EasterEgg(gp);
        gp.npc[4].worldX = gp.tileSize * 30;
        gp.npc[4].worldY = gp.tileSize * 31;
    }
    public void setMonster() {
        int correctMonsterIndex = (int)(Math.random() * 10);
        for (int i = 0; i < 10; i++) {
            boolean isCorrect = (i == correctMonsterIndex);
            gp.spawnMonster(i, isCorrect);
        }
    }
}