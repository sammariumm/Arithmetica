package Game.MethMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Game.Entity.Player;
import Game.MethTile.TileManager;

public class MethGamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; // 16 by 16 tiles
    final int scale = 3; // since its a 2D game NES only covers 256 by 256 so 16 by 16 looks very small in 1920 by 1080

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenrow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenrow;

    // Frames natin
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    MethHandler methH = new MethHandler();
    Thread gameThread;
    Player player = new Player (this,methH);

    

    public MethGamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(methH);
        this.setFocusable(true);

    }
    
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    // public void run( ) {

    //     double drawInterval = 1000000000/FPS;
    //     double nextDrawTime = System.nanoTime() + drawInterval;


    //     while (gameThread != null) {
            
    //         long currentTime = System.nanoTime();
    //         System.out.println("Time: "+currentTime);

            
    //         //update info in frames
    //         update();

    //         //redraw the screen based on the update
    //         repaint();


    //         try {

    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;

    //             if(remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long) remainingTime);

    //             nextDrawTime += drawInterval;

    //         } catch (InterruptedException e) {

    //             e.printStackTrace();

    //         }

    //     }
    // }

    public void run( ) {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;



         while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {

                update();
                repaint();
                delta--; 
                drawCount++;

            }

            if(timer >= 1000000000) {

                System.out.println("FPS: "+ drawCount);
                drawCount = 0;
                timer = 0;
                
            }


        }
    }

    public void update() {
        
        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g; 

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();


    }

}
