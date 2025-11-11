package Game.MethMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Game.Entity.Entity;
import Game.Entity.Player;
import Game.MethTile.TileManager;
import Game.monster.MON_YellowSlime;

public class MethGamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // 16 by 16 tiles
    final int scale = 3; // since its a 2D game NES only covers 256 by 256 so 16 by 16 looks very small in 1920 by 1080

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenrow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenrow;

    // World settings
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Frames natin
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    MethHandler methH = new MethHandler(this);

    // Sounds
    Sound sound = new Sound();
    AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Entities and Objects
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[10];

    ArrayList<Entity> entityList = new ArrayList<>();

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Player player = new Player(this, methH);

    int timeLeft = 60;
    public int score = 0;
    long lastTimerCheck = System.currentTimeMillis();
    boolean gameOver = false;

    public MethGamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(methH);
        this.setFocusable(true);
    }

    public void setupGame() {
        playMusic(0);
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // public void run() {
    //    double drawInterval = 1000000000 / FPS;
    //    double delta = 0;
    //    long lastTime = System.nanoTime();
    //    long currentTime;
    //    long timer = 0;
    //    int drawCount = 0;

    //    while (gameThread != null) {

    //        currentTime = System.nanoTime();
    //        delta += (currentTime - lastTime) / drawInterval;
    //        timer += (currentTime - lastTime);
    //        lastTime = currentTime;

    //        if (delta >= 1) {
    //            update();
    //           repaint();
    //            delta--;
    //            drawCount++;
    //        }

    //        if (timer >= 1000000000) {
    //            System.out.println("FPS: " + drawCount);
    //           drawCount = 0;
    //            timer = 0;
    //        }
    //    }
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
        if (gameOver) return;

        player.update();

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) npc[i].update();
        }

        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) monster[i].update();
        }

        if (System.currentTimeMillis() - lastTimerCheck >= 1000) {
            timeLeft--;
            lastTimerCheck = System.currentTimeMillis();
        }

        if (timeLeft <= 0 || player.life <= 0) {
            gameOver = true;
            stopMusic();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        // add entities
        entityList.add(player);

        // npc
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) entityList.add(npc[i]);
        }

        // monster
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) entityList.add(monster[i]);
        }

        // sort
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                return Integer.compare(e1.worldY, e2.worldY);
            }
        });

        // draw entities
        for (Entity e : entityList) {
            e.draw(g2);
        }

        // empty entity list
        entityList.clear();
        g2.setColor(Color.white);

        // 
        g2.setFont(new Font("Monospaced", Font.BOLD, 24));

        String timerText = "Time: " + timeLeft;
        int timerX = screenWidth / 2 - g2.getFontMetrics().stringWidth(timerText) / 2;
        g2.drawString(timerText, timerX, 40);

        String scoreText = "Score: " + score;
        int scoreX = screenWidth - g2.getFontMetrics().stringWidth(scoreText) - 20;
        g2.drawString(scoreText, scoreX, 40);

        ui.draw(g2);

        if (gameOver) {
            g2.setColor(new Color(0, 0, 0, 200));
            g2.fillRect(0, 0, screenWidth, screenHeight);

            g2.setColor(Color.white);

            g2.setFont(new Font("Monospaced", Font.BOLD, 18));
            String text = "HAHAHAHHA BOBO AMP";
            int x = screenWidth / 2 - g2.getFontMetrics().stringWidth(text) / 2;
            int y = screenHeight / 2 - 100;
            g2.drawString(text, x, y);

            g2.setFont(new Font("Monospaced", Font.BOLD, 18));
            String scoreSummary = "Your Score: " + score;
            x = screenWidth / 2 - g2.getFontMetrics().stringWidth(scoreSummary) / 2;
            y += 60;
            g2.drawString(scoreSummary, x, y);

            String retryPrompt = "Press ENTER to play again";
            x = screenWidth / 2 - g2.getFontMetrics().stringWidth(retryPrompt) / 2;
            y += 80;
            g2.drawString(retryPrompt, x, y);
        }

         // player
        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void restartGame() {
        gameOver = false;
        timeLeft = 60;
        score = 0;
        player.life = player.maxLife;
        player.setDefaultValues();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
    }

    public void spawnMonster(int index) {
    MON_YellowSlime newMonster = new MON_YellowSlime(this);
    int maxX = tileSize * maxScreenCol - tileSize;
    int maxY = tileSize * maxScreenrow - tileSize;
    newMonster.worldX = (int)(Math.random() * maxX);
    newMonster.worldY = (int)(Math.random() * maxY);
    monster[index] = newMonster;
    }      
}