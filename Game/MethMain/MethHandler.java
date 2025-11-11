package Game.MethMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MethHandler implements KeyListener 
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    private MethGamePanel gamePanel;

    public MethHandler(MethGamePanel gamePanel) 
    {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
       
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) 
        {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) 
        {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) 
        {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) 
        {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_ENTER) 
        {
            enterPressed = true;
            System.out.println("Enter pressed");

            if (gamePanel.gameOver) 
            {
                gamePanel.restartGame();
            }

            System.out.println("Enter pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) 
        {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) 
        {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) 
        {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) 
        {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_ENTER) 
        {
            enterPressed = false;
        }
    }
}