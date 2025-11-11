package Game.Entity;
import Game.MethMain.MethGamePanel;
import java.util.Random;
public class NPC_EasterEgg extends Entity {
    public NPC_EasterEgg(MethGamePanel gp) {
        super(gp);
        direction = "left";
        speed = 2;
        getNPCImage();
    }
    public void getNPCImage() {
        up1 = setup("/Game/Res/npc/yellowslime_1",gp.tileSize,gp.tileSize);
        up2 = setup("/Game/Res/npc/yellowslime_2",gp.tileSize,gp.tileSize);
        down1 = setup("/Game/Res/npc/yellowslime_1",gp.tileSize,gp.tileSize);
        down2 = setup("/Game/Res/npc/yellowslime_2",gp.tileSize,gp.tileSize);
        left1 = setup("/Game/Res/npc/yellowslime_1",gp.tileSize,gp.tileSize);
        left2 = setup("/Game/Res/npc/yellowslime_2",gp.tileSize,gp.tileSize);
        right1 = setup("/Game/Res/npc/yellowslime_1",gp.tileSize,gp.tileSize);
        right2 = setup("/Game/Res/npc/yellowslime_2",gp.tileSize,gp.tileSize);
    }
    public void setAction() {
        actionLockCounter++; 
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}