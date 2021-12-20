package view;

import controller.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartFrame extends JFrame {
    private JButton newGame;
    private JButton loadGame;
    private JButton rankingList;
    private JButton back;
    private JLabel title;
    private ImageIcon image;

    public StartFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
        setBackground();
        initiation();
        this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        pack();

        setLayout(new GridLayout(2, 1));
        JPanel panel1 = new JPanel(new FlowLayout());
        add(panel1);
        panel1.setOpaque(false);

        JPanel panel2 = new JPanel(new GridLayout(2, 2, 20, 10));
        add(panel2);
        panel2.setOpaque(false);

        title = new JLabel("Start");
        title.setFont(new Font("Times New Roman", 1, 90));
        title.setForeground(Color.BLACK);
        panel1.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        newGame = new JButton("New Game");
//        newGame.setSize(80, 50);
        newGame.setFont(new Font("Times New Roman", 1, 30));
        newGame.setBorderPainted(false);
        panel2.add(newGame);

        loadGame = new JButton("Load Game");
//        loadGame.setSize(80, 50);
        loadGame.setFont(new Font("Times New Roman", 1, 30));
        loadGame.setBorderPainted(false);
        panel2.add(loadGame);

        rankingList = new JButton("Ranking List");
//        rankingList.setSize(100, 50);
        rankingList.setFont(new Font("Times New Roman", 1, 30));
        rankingList.setBorderPainted(false);
        panel2.add(rankingList);

        back = new JButton("Back");
//        back.setSize(80, 50);
        back.setFont(new Font("Times New Roman", 1, 30));
        back.setBorderPainted(false);
        panel2.add(back);

        newGame.addActionListener(e -> {
            System.out.println("Clicked newGame Btn");
            dispose();
            JFrame mode = new ModeFrame(700);
            mode.setVisible(true);
//            mode.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
            mode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        loadGame.addActionListener(e -> {
            System.out.println("Clicked Load Btn");
            GameFrame gameFrame = new GameFrame(700);
            gameFrame.setVisible(false);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int i = 0; i < 2; i++) {
                try {
                    String filePath = JOptionPane.showInputDialog(this, "input the path here");
                    dispose();
                    gameFrame.setVisible(true);
                    GameFrame.controller.readFileData(filePath);
                } catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(this, "106: " + nullPointerException);
                    if (i == 1) {
                        dispose();
                        new StartFrame(700);
                        break;
                    }
                    String filePath = JOptionPane.showInputDialog(this, "input the path here");
                    if (filePath != null) {
                        GameFrame.controller.readFileData(filePath);
                        break;
                    }
                }
            }

        });

        rankingList.addActionListener(e -> {
            System.out.println("Clicked rankingList Btn");
            dispose();
            JFrame rankListFrame = new RankingListFrame(700);
            rankListFrame.setVisible(true);
            rankListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            System.out.println("Clicked back Btn");
            dispose();
            JFrame back = new IntroductionFrame(700);
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
        ImageIcon image = new ImageIcon("src/开始.png");
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
