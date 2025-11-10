package Game.Object;

import Game.MethMain.MethGamePanel;
import Game.MethMain.UtilityTool;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

public class OBJ_Heart extends SuperObject
{
    MethGamePanel gp;
    UtilityTool uTool = new UtilityTool();

    public OBJ_Heart(MethGamePanel gp)
    {
        this.gp = gp;

        name = "heart";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Game/Res/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Game/Res/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Game/Res/objects/heart_empty.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
