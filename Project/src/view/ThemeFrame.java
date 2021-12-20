package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ThemeFrame extends JFrame {
    private JButton concise;
    private JButton stars;
    private JButton darkForest;
    private JButton back;
    private JLabel title;
    private ImageIcon image;
    private static int theme = 1;

    public ThemeFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
//        playMusic();
        setBackground();
        initiation();
        this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        pack();

        setLayout(new GridLayout(2, 1));

        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        panel1.setOpaque(false);

        JPanel panel2 = new JPanel(new GridLayout(2, 2, 5, 5));
        add(panel2);
        panel2.setOpaque(false);

        title = new JLabel("Theme");
        title.setFont(new Font("Times New Roman",1,100));
        title.setForeground(Color.BLACK);
        panel1.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        concise = new JButton("Concise");
        concise.setFont(new Font("Times New Roman",1,40));
        concise.setBorderPainted(false);
        panel2.add(concise);

        stars = new JButton("Stars");
        stars.setFont(new Font("Times New Roman",1,40));
        stars.setBorderPainted(false);
        panel2.add(stars);

        darkForest = new JButton("Dark Forest");
        darkForest.setFont(new Font("Times New Roman",1,40));
        darkForest.setBorderPainted(false);
        panel2.add(darkForest);

        back = new JButton("Back");
        back.setFont(new Font("Times New Roman",1,40));
        back.setBorderPainted(false);
        panel2.add(back);

        concise.addActionListener(e -> {
            theme = 1;
            System.out.println("Clicked concise Btn");
            dispose();
            JFrame initial = new InitialFrame(700);
            initial.setVisible(true);
            initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        stars.addActionListener(e -> {
            theme = 2;
            System.out.println("Clicked stars Btn");
            dispose();
            JFrame initial = new InitialFrame(700);
            initial.setVisible(true);
            initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        darkForest.addActionListener(e -> {
            theme = 3;
            System.out.println("Clicked darkForest Btn");
            dispose();
            JFrame initial = new InitialFrame(700);
            initial.setVisible(true);
            initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            System.out.println("Clicked back Btn");
            dispose();
            JFrame back = new InitialFrame(700);
            back.setVisible(true);
            back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    public static int getTheme() {
        return theme;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void initiation() {
        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(100, 200, 100, 200));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.setVisible(true);
    }

    public void setBackground() {
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/樱花.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }

//    public static void playMusic() {
//        try {
//            FileInputStream fileau = new FileInputStream("ProjFinal/src/背景音1.wav");
//            AudioStream as = new AudioStream(fileau);
//            AudioPlayer.player.start(as);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
