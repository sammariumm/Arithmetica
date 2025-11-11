package Game.MethMain;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MethMain 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new BackgroundSlideshow().start());
    }
}
class BackgroundSlideshow 
{
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 576;

    private final String[] imagePaths = 
    {
        "Game/Res/background_image_1.gif",
        "Game/Res/background_image_2.gif",
        "Game/Res/background_image_3.gif",
    };

    private final String logoPath = "Game/Res/logo.png";

    private final String[] buttonImagePaths = 
    {
        "Game/Res/play_button.png",
        "Game/Res/credits_button.png",
        "Game/Res/quit_button.png"
    };

    private JFrame window;
    private FadePanel fadePanel;

    void start() 
    {
        window = new JFrame("Arithmetica");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        fadePanel = new FadePanel(loadImages(imagePaths), logoPath, buttonImagePaths);
        window.setContentPane(fadePanel);
        window.setVisible(true);

        new Timer(5000, e -> fadePanel.fadeToNext()).start();

        window.addComponentListener(new ComponentAdapter() 
        {
            @Override
            public void componentResized(ComponentEvent e) 
            {
                fadePanel.repositionComponents();
            }
        });
    }

    private ArrayList<Image> loadImages(String[] paths) 
    {
        ArrayList<Image> images = new ArrayList<>();

        for (String path : paths) 
        {
            ImageIcon icon = new ImageIcon(path);

            if (icon.getIconWidth() > 0) 
            {
                images.add(icon.getImage());
            } 
            else 
            {
                System.err.println("Could not load image: " + path);
            }
        }

        return images;
    }
}
class FadePanel extends JPanel 
{
    private final ArrayList<Image> images;
    private int currentIndex = 0;
    private float alpha = 0f;
    private Image currentImage;
    private Image nextImage;
    private Timer fadeTimer;
    private JLabel logoLabel;
    private JButton[] buttons = new JButton[3];
    private String[] buttonPaths;

    FadePanel(ArrayList<Image> images, String logoPath, String[] buttonPaths) 
    {
        this.images = images;
        this.buttonPaths = buttonPaths;

        if (!images.isEmpty()) 
        {
            currentImage = images.get(0);
        }

        setLayout(null);
        setDoubleBuffered(true);

        ImageIcon logoIcon = new ImageIcon(logoPath);
        logoLabel = new JLabel(logoIcon);
        add(logoLabel);

        for (int i = 0; i < 3; i++) 
        {
            ImageIcon btnIcon = new ImageIcon(buttonPaths[i]);
            JButton btn = new JButton(btnIcon);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int index = i;

            btn.addActionListener(e -> {
                switch (index) 
                {
                    case 0 -> SwingUtilities.invokeLater(() -> new PlayWindow().setVisible(true));
                    case 1 -> SwingUtilities.invokeLater(() -> new CreditsWindow().setVisible(true));
                    case 2 -> System.exit(0);
                }
            });

            buttons[i] = btn;
            add(btn);
        }
        repositionComponents();
    }
    
    void repositionComponents() 
    {
        if (logoLabel.getIcon() == null) return;
        int panelWidth = getWidth();
        int logoWidth = logoLabel.getIcon().getIconWidth();
        int logoHeight = logoLabel.getIcon().getIconHeight();
        int topPadding = 20;

        int logoX = (panelWidth - logoWidth) / 2;
        int logoY = topPadding;
        logoLabel.setBounds(logoX, logoY, logoWidth, logoHeight);
        int spacing = 4;
        int startY = logoY + logoHeight + 10;

        for (int i = 0; i < buttons.length; i++) 
        {
            JButton btn = buttons[i];
            ImageIcon icon = (ImageIcon) btn.getIcon();
            int btnWidth = icon.getIconWidth();
            int btnHeight = icon.getIconHeight();
            int btnX = (panelWidth - btnWidth) / 2;
            int btnY = startY + i * (btnHeight + spacing);
            btn.setBounds(btnX, btnY, btnWidth, btnHeight);
        }

        repaint();
    }

    void fadeToNext() 
    {
        if (images.size() < 2) return;
            nextImage = images.get((currentIndex + 1) % images.size());

        alpha = 0f;

        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
            fadeTimer = new Timer(40, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) 
            {
                alpha = 1f;
                currentImage = nextImage;
                currentIndex = (currentIndex + 1) % images.size();
                fadeTimer.stop();
            }
            repaint();
        });

        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (currentImage == null) return;
            Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        g2d.drawImage(currentImage, 0, 0, width, height, this);

        if (nextImage != null && alpha > 0f) 
        {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(nextImage, 0, 0, width, height, this);
        }

        g2d.dispose();
    }
}

class PlayWindow extends JFrame 
{
    public PlayWindow() 
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Arithmetica");

        MethGamePanel gamePanel = new MethGamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}

class CreditsWindow extends JFrame 
{
    public CreditsWindow() 
    {
        setTitle("Credits");
        setSize(960, 576);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(new CreditsPanel());
    }

    private static class CreditsPanel extends JPanel 
    {
        private final String[] credits = 
        {
            "NCP 3105 / NCP 3106 FINAL PROJECT",
            "",
            "",
            "",
            "Arithmetica: An RPG-Like Math Game",
            "",
            "",
            "",
            "Created by:",
            "",
            "Dass, Archer Troy D.",
            "Francisco, Ljuvojevic G., Jr.",
            "Manzano, Shera Mae R.",
            "Masangya, Novie Micoh B.",
            "",
            "3CPE-1B",
            "",
            "",
            "",
            "Submitted to:",
            "",
            "Engr. John Vincent R. Trinidad",
            "Engr. Benedict N. Zurbito",
            "",
            "Professors",
            "",
            "",
            "",
            "Thanks for playing!"
        };

        private int y;
        private javax.swing.Timer timer;
        private ImageIcon backgroundGif;
        private ImageIcon logoIcon;
        private Font retroFont;
        private float fadeToBlack = 0f;
        private boolean finished = false;

        CreditsPanel() 
        {
            backgroundGif = new ImageIcon("Game/Res/background_image_4.gif");
            logoIcon = new ImageIcon("Game/Res/logo.png");
            setDoubleBuffered(true);
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            try 
            {
                retroFont = Font.createFont(Font.TRUETYPE_FONT,
                        new java.io.File("Game/Res/PressStart2P.ttf"))
                        .deriveFont(Font.PLAIN, 16);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(retroFont);
            } 
            catch (Exception e) 
            {
                retroFont = new Font("Monospaced", Font.BOLD, 18);
                System.err.println("8-bit font not found, using fallback font.");
            }
            y = 576;
            timer = new javax.swing.Timer(16, e -> updateAnimation());
            timer.setCoalesce(true);
            timer.start();
        }
        
        private void updateAnimation() 
        {
            if (!finished) 
            {
                y -= 2;
                int totalScrollHeight = y + credits.length * getFontMetrics(retroFont).getHeight() + 400;
                if (totalScrollHeight < 200) 
                {
                    finished = true;
                }
            } 
            else if (fadeToBlack < 1f) 
            {
                fadeToBlack += 0.01f;
            } 
            else 
            {
                timer.stop();
            }

            repaint();
        }

        private float clamp(float val, float min, float max) 
        {
            return Math.max(min, Math.min(max, val));
        }

        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            if (backgroundGif != null) 
            {
                g2.drawImage(backgroundGif.getImage(), 0, 0, width, height, this);
            }
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(retroFont);
            g2.setColor(Color.WHITE);

            int lineHeight = g2.getFontMetrics().getHeight();
            int centerX = width / 2;

            for (int i = 0; i < credits.length; i++) 
            {
                int textWidth = g2.getFontMetrics().stringWidth(credits[i]);
                int x = centerX - textWidth / 2;
                int yPos = y + i * lineHeight;
                float alpha;

                if (yPos < 100)
                    alpha = (float) yPos / 100f;
                else if (yPos > height - 150)
                    alpha = (float) (height - yPos) / 100f;
                else
                    alpha = 1f;

                alpha = clamp(alpha, 0f, 1f);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.drawString(credits[i], x, yPos);
            }

            if (logoIcon != null) 
            {
                int logoWidth = logoIcon.getIconWidth();
                int logoHeight = logoIcon.getIconHeight();
                int logoStartY = y + credits.length * lineHeight + 60;
                int logoX = (width - logoWidth) / 2;
                float alpha;

                if (logoStartY < 100)
                    alpha = (float) logoStartY / 100f;
                else if (logoStartY > height - 150)
                    alpha = (float) (height - logoStartY) / 100f;
                else
                    alpha = 1f;
                
                    alpha = clamp(alpha, 0f, 1f);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.drawImage(logoIcon.getImage(), logoX, logoStartY, this);
                
                String footer = "© 2025 Arithmetica. All Rights Reserved.";
                g2.setFont(retroFont.deriveFont(Font.PLAIN, 14f));
                
                int footerWidth = g2.getFontMetrics().stringWidth(footer);
                int footerY = logoStartY + logoHeight + 25;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2.drawString(footer, (width - footerWidth) / 2, footerY);
            }
            
            if (fadeToBlack > 0f) 
            {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, clamp(fadeToBlack, 0f, 1f)));
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, width, height);
            }
            
            g2.dispose();
        }
    }
}