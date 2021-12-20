package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChooseColorFrame extends JFrame {
    private JButton black;
    private JButton white;
    private JButton back;
    private JLabel title;
    private ImageIcon image;

    public ChooseColorFrame(int frameSize) {
        super("2021F CS102A Project Reversi");
//        Insets inset = this.getInsets();
//        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);
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

        title = new JLabel("Chess");
        title.setFont(new Font("Times New Roman",1,60));
        title.setForeground(Color.BLACK);
        panel1.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        black = new JButton("Black");
        black.setFont(new Font("Times New Roman",1,30));
        black.setBorderPainted(false);
        panel2.add(black);

        white = new JButton("White");
        white.setFont(new Font("Times New Roman",1,30));
        white.setBorderPainted(false);
        panel2.add(white);

        back = new JButton("Back");
//        back.setSize(80, 50);
        back.setFont(new Font("Times New Roman",1,30));
        back.setBorderPainted(false);
        panel2.add(back);

        black.addActionListener(e -> {
            dispose();
            GameFrame gameFrame = new GameFrame(700);
            gameFrame.setVisible(true);
//            gameFrame.setExtendedState(Frame.MAXIMIZED_BOTH);//最大化屏幕
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            dispose();
            JFrame back = new DifficultyLevelFrame(700);
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
        ImageIcon image = new ImageIcon("src/黑白棋3.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }
}
