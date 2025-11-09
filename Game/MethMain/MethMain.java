package Game.MethMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MethMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BackgroundSlideshow().start());
    }
}
class BackgroundSlideshow {
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 576;
    private final String[] imagePaths = {
        "C:/Arithmetica/Game/Res/background_image_1.gif",
        "C:/Arithmetica/Game/Res/background_image_2.gif",
        "C:/Arithmetica/Game/Res/background_image_3.gif",
    };
    private final String logoPath = "C:/Arithmetica/Game/Res/logo.png";
    private final String[] buttonImagePaths = {
        "C:/Arithmetica/Game/Res/play_button.png",
        "C:/Arithmetica/Game/Res/credits_button.png",
        "C:/Arithmetica/Game/Res/quit_button.png"
    };
    private JFrame window;
    private FadePanel fadePanel;
    void start() {
        window = new JFrame("Arithmetica");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        fadePanel = new FadePanel(loadImages(imagePaths), logoPath, buttonImagePaths);
        window.setContentPane(fadePanel);
        window.setVisible(true);
        new Timer(5000, e -> fadePanel.fadeToNext()).start();
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                fadePanel.repositionComponents();
            }
        });
    }
    private ArrayList<Image> loadImages(String[] paths) {
        ArrayList<Image> images = new ArrayList<>();
        for (String path : paths) {
            ImageIcon icon = new ImageIcon(path);
            if (icon.getIconWidth() > 0) {
                images.add(icon.getImage());
            } else {
                System.err.println("Could not load image: " + path);
            }
        }
        return images;
    }
}
class FadePanel extends JPanel {
    private final ArrayList<Image> images;
    private int currentIndex = 0;
    private float alpha = 0f;
    private Image currentImage;
    private Image nextImage;
    private Timer fadeTimer;
    private JLabel logoLabel;
    private JButton[] buttons = new JButton[3];
    private String[] buttonPaths;
    FadePanel(ArrayList<Image> images, String logoPath, String[] buttonPaths) {
        this.images = images;
        this.buttonPaths = buttonPaths;
        if (!images.isEmpty()) {
            currentImage = images.get(0);
        }
        setLayout(null);
        setDoubleBuffered(true);
        ImageIcon logoIcon = new ImageIcon(logoPath);
        logoLabel = new JLabel(logoIcon);
        add(logoLabel);
        for (int i = 0; i < 3; i++) {
            ImageIcon btnIcon = new ImageIcon(buttonPaths[i]);
            JButton btn = new JButton(btnIcon);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int index = i;
            btn.addActionListener(e -> {
                switch (index) {
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
    void repositionComponents() {
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
        for (int i = 0; i < buttons.length; i++) {
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
    void fadeToNext() {
        if (images.size() < 2) return;
        nextImage = images.get((currentIndex + 1) % images.size());
        alpha = 0f;
        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
        fadeTimer = new Timer(40, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage == null) return;
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        g2d.drawImage(currentImage, 0, 0, width, height, this);
        if (nextImage != null && alpha > 0f) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(nextImage, 0, 0, width, height, this);
        }
        g2d.dispose();
    }
}
class PlayWindow extends JFrame {
    public PlayWindow() {
       JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Meth");
        MethGamePanel gamePanel= new MethGamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
class CreditsWindow extends JFrame {
    public CreditsWindow() {
        setTitle("Play");
        setSize(960, 576);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("SHERA LUBO DASS NOBI", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}