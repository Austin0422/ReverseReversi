package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RankingListFrame extends JFrame {
    private JButton back;
    private JLabel title;
    private JLabel word1;
    private JLabel word2;
    private ImageIcon image;

    public RankingListFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
        setBackground();
        initiation();
        this.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
        pack();

        setLayout(new BorderLayout(10, 10));

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setPreferredSize(new Dimension(0, 200));
        add(panel1, BorderLayout.NORTH);
        panel1.setOpaque(false);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setPreferredSize(new Dimension(0, 0));
        add(panel2, BorderLayout.EAST);
        panel2.setOpaque(false);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.setPreferredSize(new Dimension(0, 0));
        add(panel3, BorderLayout.WEST);
        panel3.setOpaque(false);

        JPanel panel4 = new JPanel(new FlowLayout());
        panel4.setPreferredSize(new Dimension(0, 50));
        add(panel4, BorderLayout.SOUTH);
        panel4.setOpaque(false);

        JPanel panel5 = new JPanel(new GridLayout(2, 1));
        panel5.setPreferredSize(new Dimension(300, 300));
        add(panel5, BorderLayout.CENTER);
        panel5.setOpaque(false);

        title = new JLabel("Rank List");
        title.setFont(new Font("Times New Roman",1,90));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(title);

        word1 = new JLabel("You are the Top1!");
        word1.setFont(new Font("Times New Roman",1,60));
        word1.setForeground(Color.WHITE);
        word1.setHorizontalAlignment(JLabel.CENTER);
        panel5.add(word1);
        word2 = new JLabel("No one can compete with you!");
        word2.setFont(new Font("Times New Roman",1,42));
        word2.setForeground(Color.WHITE);
        word2.setHorizontalAlignment(JLabel.CENTER);
        panel5.add(word2);

        back = new JButton("Back");
        back.setFont(new Font("Times New Roman",1,20));
        back.setBorderPainted(false);
        panel4.add(back);

        back.addActionListener(e -> {
            System.out.println("Clicked back Btn");
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
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/排行榜.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }
}
