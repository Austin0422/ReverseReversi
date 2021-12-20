package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModeFrame extends JFrame {
    private JButton normal;
//    private JButton multiUser;
    private JButton humanVSMachine;
    private JButton back;
    private JLabel title;
    private ImageIcon image;

    public ModeFrame(int frameSize) {
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

        JPanel panel2 = new JPanel(new GridLayout(3, 1, 2, 2));
        add(panel2);
        panel2.setOpaque(false);

        title = new JLabel("Mode");
        title.setFont(new Font("Times New Roman", 1, 100));
        title.setForeground(Color.BLACK);
        panel1.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        normal = new JButton("Normal");
//        normal.setSize(100, 50);
        normal.setFont(new Font("Times New Roman", 1, 30));
        normal.setBorderPainted(false);
        panel2.add(normal);

//        multiUser = new JButton("3P GO GO GO !");
////        multiUser.setSize(100, 50);
//        multiUser.setFont(new Font("Times New Roman", 1, 30));
//        multiUser.setBorderPainted(false);
//        panel2.add(multiUser);

        humanVSMachine = new JButton("Human VS Machine");
//        humanVSMachine.setSize(100, 50);
        humanVSMachine.setFont(new Font("Times New Roman", 1, 30));
        humanVSMachine.setBorderPainted(false);
        panel2.add(humanVSMachine);

        back = new JButton("Back");
//        back.setSize(80, 50);
        back.setFont(new Font("Times New Roman", 1, 30));
        back.setBorderPainted(false);
        panel2.add(back);

        normal.addActionListener(e -> {
            GameFrame.setMachine(false);
            dispose();
            GameFrame gameFrame = new GameFrame(700);
            gameFrame.setVisible(true);
//            gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

//        multiUser.addActionListener(e -> {
//            GameFrame.setMode(2);
//            dispose();
//            GameFrame gameFrame = new GameFrame(700);
//            gameFrame.setVisible(true);
////            gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
//            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        });

        humanVSMachine.addActionListener(e -> {
            GameFrame.setMachine(true);
            dispose();
            DifficultyLevelFrame difficultyLevelFrame = new DifficultyLevelFrame(700);
            difficultyLevelFrame.setVisible(true);
            difficultyLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            GameFrame gameFrame = new GameFrame(700);
//            gameFrame.setVisible(true);
//            ChessGridComponent.setIsComputer();
//            gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
//            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            dispose();
            JFrame back = new StartFrame(700);
            back.setVisible(true);
            back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            back.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
        });
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
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/黑白棋2.jpeg");
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
