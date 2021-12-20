package view;

import controller.MachineController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DifficultyLevelFrame extends JFrame {
    private JButton easy;
    private JButton middle;
    private JButton hard;
    private JButton back;
    private JLabel title;
    private ImageIcon image;

    public DifficultyLevelFrame(int frameSize) {
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

        JPanel panel2 = new JPanel(new GridLayout(2, 2, 2, 2));
        panel2.setPreferredSize(new Dimension(0, 200));
        add(panel2, BorderLayout.SOUTH);
        panel2.setOpaque(false);

        JPanel panel3 = new JPanel(new GridLayout(3, 1, 2, 2));
        panel3.setPreferredSize(new Dimension(200, 0));
        add(panel3, BorderLayout.EAST);
        panel3.setOpaque(false);

        JPanel panel4 = new JPanel(new GridLayout(3, 1, 2, 2));
        panel4.setPreferredSize(new Dimension(200, 0));
        add(panel4, BorderLayout.WEST);
        panel4.setOpaque(false);
        title = new JLabel("Level");
        title.setFont(new Font("Times New Roman",1,90));
        title.setForeground(Color.BLACK);
        panel1.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        easy = new JButton("Easy");
//        easy.setSize(80, 50);
        easy.setFont(new Font("Times New Roman",1,40));
        easy.setBorderPainted(false);
        panel2.add(easy);

        middle = new JButton("Middle");
//        easy.setSize(80, 50);
        middle.setFont(new Font("Times New Roman",1,40));
        middle.setBorderPainted(false);
        panel2.add(middle);

        hard = new JButton("Hard");
//        hard.setSize(80, 50);
        hard.setFont(new Font("Times New Roman",1,40));
        hard.setBorderPainted(false);
        panel2.add(hard);

        back = new JButton("Back");
//        back.setSize(80, 50);
        back.setFont(new Font("Times New Roman",1,40));
        back.setBorderPainted(false);
        panel2.add(back);

        easy.addActionListener(e -> {
            MachineController.setMachineMode(10);
            dispose();
            ChooseColorFrame chooseColor = new ChooseColorFrame(700);
            chooseColor.setVisible(true);
            chooseColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        middle.addActionListener(e -> {
            MachineController.setMachineMode(100);
            dispose();
            ChooseColorFrame chooseColor = new ChooseColorFrame(700);
            chooseColor.setVisible(true);
            chooseColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        hard.addActionListener(e -> {
            MachineController.setMachineMode(1000);
            dispose();
            ChooseColorFrame chooseColor = new ChooseColorFrame(700);
            chooseColor.setVisible(true);
            chooseColor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

        back.addActionListener(e -> {
            dispose();
            JFrame back = new ModeFrame(700);
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
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon image = new ImageIcon("src/人机对战.jpeg");
        setImage(image);
        JLabel background = new JLabel(image);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.image.getIconWidth(), this.image.getIconHeight());
    }
}
