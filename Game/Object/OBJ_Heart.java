package Game.Object;

import Game.MethMain.MethGamePanel;
import Game.MethMain.UtilityTool;
import Game.Entity.Entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

public class OBJ_Heart extends Entity
{
    public OBJ_Heart(MethGamePanel gp)
    {
        super(gp);

        name = "heart";

        image = setup("/Game/Res/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Game/Res/objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/Game/Res/objects/heart_empty",gp.tileSize,gp.tileSize);
    }
}
