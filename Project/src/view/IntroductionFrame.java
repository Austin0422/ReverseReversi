package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IntroductionFrame extends JFrame {
    private JButton next;
    private JButton back;
    private JLabel title;
    private JLabel intro;
    private JLabel intro1;
    private JLabel intro2;
    private JLabel intro3;
    private JLabel intro4;
    private JLabel intro5;
    private JLabel intro6;
    private JLabel intro7;
    private JLabel intro8;
    private JLabel intro9;
    private JLabel intro0;
    private JLabel intro01;
    private ImageIcon image;

    public IntroductionFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
        setBackground();
        initiation();
        this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        pack();

        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setPreferredSize(new Dimension(0, 30));
        add(panel1, BorderLayout.SOUTH);
        panel1.setOpaque(false);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setPreferredSize(new Dimension(1000, 900));
        add(panel2, BorderLayout.CENTER);
        panel2.setOpaque(false);

        JPanel panel3 = new JPanel(new GridLayout(1, 2, 20, 10));
        panel3.setPreferredSize(new Dimension(0, 40));
        add(panel3, BorderLayout.SOUTH);
        panel3.setOpaque(false);

        JPanel panel4 = new JPanel(new GridLayout(1, 2, 20, 10));
        panel4.setPreferredSize(new Dimension(18, 0));
        add(panel4, BorderLayout.WEST);
        panel4.setOpaque(false);
        JPanel panel5 = new JPanel(new GridLayout(1, 2, 20, 10));
        panel5.setPreferredSize(new Dimension(18, 0));
        add(panel5, BorderLayout.EAST);
        panel5.setOpaque(false);

        title = new JLabel("Introduction");
        title.setFont(new Font("Times New Roman", 1, 50));
        title.setForeground(Color.BLACK);
        panel2.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        intro = new JLabel("Each flip game begins with four pieces crossed on the board.");
        intro.setFont(new Font("Times New Roman", 1, 23));
        intro.setForeground(Color.BLACK);
        panel2.add(intro);
        intro.setHorizontalAlignment(JLabel.CENTER);

        intro1 = new JLabel(" Two of them are black and the other two are white. Black always goes first.");
        intro1.setFont(new Font("Times New Roman", 1, 23));
        intro1.setForeground(Color.BLACK);
        panel2.add(intro1);
        intro1.setHorizontalAlignment(JLabel.CENTER);

        intro2 = new JLabel("When your pieces surround your opponent's pieces in a straight line,");
        intro2.setFont(new Font("Times New Roman", 1, 23));
        intro2.setForeground(Color.BLACK);
        panel2.add(intro2);
        intro2.setHorizontalAlignment(JLabel.CENTER);

        intro3 = new JLabel("you can flip the colors of those pieces so that they become your colors.");
        intro3.setFont(new Font("Times New Roman", 1, 23));
        intro3.setForeground(Color.BLACK);
        panel2.add(intro3);
        intro3.setHorizontalAlignment(JLabel.CENTER);

        intro4 = new JLabel("For example, if you play black and you see a black at one end of a row of whites,");
        intro4.setFont(new Font("Times New Roman", 1, 23));
        intro4.setForeground(Color.BLACK);
        panel2.add(intro4);
        intro4.setHorizontalAlignment(JLabel.CENTER);

        intro5 = new JLabel("then when you put a black at the other end of the row, all whites will flip and");
        intro5.setFont(new Font("Times New Roman", 1, 23));
        intro5.setForeground(Color.BLACK);
        panel2.add(intro5);
        intro5.setHorizontalAlignment(JLabel.CENTER);

        intro7 = new JLabel("become black!All linear directions are valid: horizontal, vertical and oblique.");
        intro7.setFont(new Font("Times New Roman", 1, 23));
        intro7.setForeground(Color.BLACK);
        panel2.add(intro7);
        intro7.setHorizontalAlignment(JLabel.CENTER);

        intro8 = new JLabel("The only rule of chess is to surround and flip your opponent's pieces.");
        intro8.setFont(new Font("Times New Roman", 1, 23));
        intro8.setForeground(Color.BLACK);
        panel2.add(intro8);
        intro8.setHorizontalAlignment(JLabel.CENTER);

        intro9 = new JLabel("You must flip at least one of your opponent's pieces each turn.");
        intro9.setFont(new Font("Times New Roman", 1, 23));
        intro9.setForeground(Color.BLACK);
        panel2.add(intro9);
        intro9.setHorizontalAlignment(JLabel.CENTER);

        intro0 = new JLabel("The rule is to abstain from the turn when no further moves can be made.");
        intro0.setFont(new Font("Times New Roman", 1, 23));
        intro0.setForeground(Color.BLACK);
        panel2.add(intro0);
        intro0.setHorizontalAlignment(JLabel.CENTER);

        intro01 = new JLabel("This move will be given to the opponent.");
        intro01.setFont(new Font("Times New Roman", 1, 23));
        intro01.setForeground(Color.BLACK);
        panel2.add(intro01);
        intro01.setHorizontalAlignment(JLabel.CENTER);

        next = new JButton("Next");
        next.setFont(new Font("Times New Roman", 1, 25));
        next.setBorderPainted(false);
        panel3.add(next);

        back = new JButton("Back");
        back.setFont(new Font("Times New Roman", 1, 25));
        back.setBorderPainted(false);
        panel3.add(back);

        next.addActionListener(e -> {
            dispose();
            JFrame next = new StartFrame(700);
            next.setVisible(true);
            next.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            dispose();
            JFrame back = new InitialFrame(700);
            back.setVisible(true);
            back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        ImageIcon image = new ImageIcon("src/教程.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }
}
