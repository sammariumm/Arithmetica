package Game.monster;

import Game.Entity.Entity;
import Game.MethMain.MethGamePanel;

public class MON_YellowSlime extends Entity
{
    public MON_YellowSlime(MethGamePanel gp)
    {
        super(gp);

        name = "Yellow Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
    }
}
