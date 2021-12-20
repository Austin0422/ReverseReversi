import view.InitialFrame;
import view.Music;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InitialFrame mainFrame = new InitialFrame(700);
            mainFrame.setVisible(true);
            for (int i = 0; i < 1; i++) {
                Music.playMusic();
            }
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });
    }
}

