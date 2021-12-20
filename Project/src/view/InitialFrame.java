package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InitialFrame extends JFrame {
    private JButton start;
    private JButton theme;
    private JButton exit;
    private JLabel title;
    private ImageIcon icon;
    private JPanel panel2 = null;
    private ImageIcon image;
    private final static Color bg = new Color(0, 20, 60);

    public InitialFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
//        playMusic();
        this.setSize(1000, 650);
        if (ThemeFrame.getTheme() == 1) {
            this.getContentPane().setBackground(bg);

            setLayout(new BorderLayout(10, 10));

            JPanel panel1 = new JPanel(new FlowLayout());
            panel1.setPreferredSize(new Dimension(0, 150));
            add(panel1, BorderLayout.NORTH);
            panel1.setOpaque(false);

            panel2 = new MyPanel();
            panel2.setPreferredSize(new Dimension(600, 600));
            add(panel2, BorderLayout.CENTER);
            panel2.repaint();
            panel2.setOpaque(false);

            JPanel panel3 = new JPanel(new FlowLayout());
            panel3.setPreferredSize(new Dimension(0, 0));
            add(panel3, BorderLayout.WEST);
            panel3.setOpaque(false);

            JPanel panel4 = new JPanel(new GridLayout(3, 1, 60, 60));
            panel4.setPreferredSize(new Dimension(280, 0));
            add(panel4, BorderLayout.EAST);
            panel4.setOpaque(false);

            JPanel panel5 = new JPanel(new FlowLayout());
            panel5.setPreferredSize(new Dimension(0, 50));
            add(panel5, BorderLayout.SOUTH);
            panel5.setOpaque(false);

            title = new JLabel("Welcome to Reversi");
            title.setFont(new Font("Times New Roman", 1, 75));//设置字体为黑体，字体大小为12，1代表样式(1是粗体，0是平常的)
            title.setForeground(Color.white);//设置字体颜色
            panel1.add(title);
            title.setHorizontalAlignment(JLabel.CENTER);

            start = new JButton("Start");
            start.setFont(new Font("Times New Roman", 1, 75));
            start.setLocation((this.getWidth() - panel3.getWidth()) / 2, (this.getHeight() + panel3.getHeight()) / 2);
                start.setForeground(Color.white);
                start.setBackground(bg);
            start.setBorderPainted(false);
            panel4.add(start);

            this.theme = new JButton("Theme");
            this.theme.setFont(new Font("Times New Roman", 1, 75));
            this.theme.setLocation((this.getWidth() - panel3.getWidth()) / 2, (this.getHeight() + panel3.getHeight()) / 2);
                this.theme.setForeground(Color.white);
                this.theme.setBackground(bg);
            this.theme.setBorderPainted(false);
            panel4.add(this.theme);

            exit = new JButton("Exit");
            exit.setFont(new Font("Times New Roman", 1, 75));
                exit.setForeground(Color.white);
                exit.setBackground(bg);
            exit.setBorderPainted(false);
            panel4.add(exit);
        } else if (ThemeFrame.getTheme() == 2) {
            setBackground2();
            initiation();
            this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
            pack();
            setLayout(new GridLayout(2, 1));
            JPanel panel1 = new JPanel(new FlowLayout());
            add(panel1);
            panel1.setOpaque(false);

            JPanel panel2 = new JPanel(new GridLayout(3, 1, 20, 10));
            add(panel2);
            panel2.setOpaque(false);

            title = new JLabel("Welcome to Reversi");
            title.setFont(new Font("Times New Roman", 1, 68));//设置字体为黑体，字体大小为12，1代表样式(1是粗体，0是平常的)
            title.setForeground(Color.white);//设置字体颜色
            panel1.add(title);
            title.setHorizontalAlignment(JLabel.CENTER);

            start = new JButton("Start");
            start.setFont(new Font("Times New Roman", 1, 45));
            start.setBorderPainted(false);
            panel2.add(start);

            this.theme = new JButton("Theme");
            this.theme.setFont(new Font("Times New Roman", 1, 45));
            this.theme.setBorderPainted(false);
            panel2.add(this.theme);

            exit = new JButton("Exit");
            exit.setFont(new Font("Times New Roman", 1, 45));
            exit.setBorderPainted(false);
            panel2.add(exit);

        } else if (ThemeFrame.getTheme() == 3) {
            setBackground3();
            initiation();
            this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
            pack();

            setLayout(new GridLayout(2, 1));
            JPanel panel1 = new JPanel(new FlowLayout());
            add(panel1);
            panel1.setOpaque(false);

            JPanel panel2 = new JPanel(new GridLayout(3, 1, 20, 10));
            add(panel2);
            panel2.setOpaque(false);

            title = new JLabel("Welcome to Reversi");
            title.setFont(new Font("Times New Roman", 1, 68));//设置字体为黑体，字体大小为12，1代表样式(1是粗体，0是平常的)
            title.setForeground(Color.white);//设置字体颜色
            panel1.add(title);
            title.setHorizontalAlignment(JLabel.CENTER);

            start = new JButton("Start");
            start.setFont(new Font("Times New Roman", 1, 45));
            start.setBorderPainted(false);
            panel2.add(start);

            this.theme = new JButton("Theme");
            this.theme.setFont(new Font("Times New Roman", 1, 45));
            this.theme.setBorderPainted(false);
            panel2.add(this.theme);

            exit = new JButton("Exit");
            exit.setFont(new Font("Times New Roman", 1, 45));
            exit.setBorderPainted(false);
            panel2.add(exit);
        }

        start.addActionListener(e -> {
            dispose();
            JFrame introGame = new IntroductionFrame(700);
            introGame.setVisible(true);
//            startGame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
            introGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        this.theme.addActionListener(e -> {
            dispose();
            JFrame themeFrame = new ThemeFrame(700);
            themeFrame.setVisible(true);
//            startGame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
            themeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        exit.addActionListener(e -> {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(icon.getImage(), 0, 0, 350, 89, panel2);
    }

    class MyPanel extends JPanel {
        ImageIcon icon = new ImageIcon("src/黑白棋1.jpeg");

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);    //To change body of overridden methods use File | Settings | File Templates.
            g.drawImage(icon.getImage(), 0, 0, 600, 450, null);
        }
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

    public void setBackground2() {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/星空.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }

    public void setBackground3() {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/黑暗森林.jpeg");
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

