package Game.MethMain;

import javax.swing.JFrame;

public class MethMain {
    
    public static void main(String[] args) {

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
