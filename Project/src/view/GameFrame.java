package view;


import controller.Automatic;
import controller.GameController;
import controller.MachineController;
import controller.MultiPlayerController;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static GameController controller;
    public static MultiPlayerController controllerM;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private MstatusPanel mstatusPanel;
    private static int mode = 1; // 0为cheating模式 1为正常模式
    private static boolean isMachine = false;

    public static void setMachine(boolean machine) {
        isMachine = machine;
    }

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        GameFrame.mode = mode;
    }

    public GameFrame(int frameSize) {

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        Music.endMusic();
        Music.playMusic1();

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        if (getMode() != 2) {
            statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
            statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        } else {
            mstatusPanel = new MstatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
            mstatusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        }
        if (getMode() != 2) {
            if (isMachine) {
                controller = new MachineController(chessBoardPanel, statusPanel);
            } else {
                controller = new GameController(chessBoardPanel, statusPanel);
            }
            controller.setGamePanel(chessBoardPanel);
        } else {
            controller = new GameController(chessBoardPanel, statusPanel);
            controllerM = new MultiPlayerController(chessBoardPanel, mstatusPanel);
            controllerM.setGamePanel(chessBoardPanel);
        }

        this.add(chessBoardPanel);
        if (getMode() != 2) {
            this.add(statusPanel);
        } else {
            this.add(mstatusPanel);
        }


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(100, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            System.out.println("Clicked restart Btn");
            int userOption = JOptionPane.showConfirmDialog(null, "Really !?", null, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (userOption == JOptionPane.OK_OPTION) {
                System.err.println("YES");
                this.dispose();
                Music.endMusic1();
                Music.playMusic();
                InitialFrame mainFrame = new InitialFrame(700);
                mainFrame.setVisible(true);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }//确认对话框
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(100, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {

            System.out.println("Clicked Load Btn");
            for (int i = 0; i < 2; i++) {
                try {
                    String filePath = JOptionPane.showInputDialog(this, "input the path here");
                    controller.readFileData(filePath);
                } catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(this, "106: " + nullPointerException);
                    if (i == 1) {
                        dispose();
                        new GameFrame(700);
                        break;
                    }
                    String filePath = JOptionPane.showInputDialog(this, "input the path here");
                    if (filePath != null){
                        controller.readFileData(filePath);
                        break;
                    }
                }
            }
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(100, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("Clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        JButton changeModeBtn = new JButton("Change");
        changeModeBtn.setSize(100, 50);
        changeModeBtn.setLocation(saveGameBtn.getX() + saveGameBtn.getWidth() + 20, restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e -> {
            System.out.println("Clicked ChangeMode Btn");
            mode = (mode == 1) ? 0 : 1;
        });

        if (!isMachine) {
            JButton upDoBtn = new JButton("UpDo");
            upDoBtn.setSize(80, 50);
            upDoBtn.setLocation(changeModeBtn.getX() + changeModeBtn.getWidth() + 20, restartBtn.getY());
            add(upDoBtn);
            upDoBtn.addActionListener(e -> {
                if (GameController.getSteps().size() == 0) {
                    JOptionPane.showMessageDialog(null, "It is initial board");
                }
                System.out.println("Clicked UpDo Btn");
                controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
                GameController.getSteps().remove(GameController.getSteps().size() - 1);
                int[][] board = Automatic.putAuto(GameController.getSteps());
                ChessBoardPanel.setChessGridsInt(board);
                chessBoardPanel.transformBoard2(board);
                controller.countScore();
                statusPanel.setPlayerText(controller.getCurrentPlayer().name());
                statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
                repaint();
            });
        } else {
            JButton upDoBtn = new JButton("UpDo");
            upDoBtn.setSize(80, 50);
            upDoBtn.setLocation(changeModeBtn.getX() + changeModeBtn.getWidth() + 20, restartBtn.getY());
            add(upDoBtn);
            upDoBtn.addActionListener(e -> {
                if (GameController.getSteps().size() == 0) {
                    JOptionPane.showMessageDialog(null, "It is initial board");
                }
                System.out.println("Clicked UpDo Btn");
                controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
                while (GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1) {
                    GameController.getSteps().remove(GameController.getSteps().size() - 1);
                }
                GameController.getSteps().remove(GameController.getSteps().size() - 1);
                int[][] board = Automatic.putAuto(GameController.getSteps());

                ChessBoardPanel.setChessGridsInt(board);
                chessBoardPanel.transformBoard2(board);
                controller.countScore();
                controller.setCurrentPlayer(ChessPiece.BLACK);
                statusPanel.setPlayerText(controller.getCurrentPlayer().name());
                statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
                repaint();
            });
        }


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public GameFrame(int frameSize, int machine) {

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);


        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new MachineController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(100, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");
            int userOption = JOptionPane.showConfirmDialog(null, "Really !?", null, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (userOption == JOptionPane.OK_OPTION) {
                System.err.println("YES");
                this.dispose();
                GameFrame mainFrame = new GameFrame(800);
                mainFrame.setVisible(true);
            }//确认对话框
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(100, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(100, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        JButton changeModeBtn = new JButton("Change");
        changeModeBtn.setSize(100, 50);
        changeModeBtn.setLocation(saveGameBtn.getX() + saveGameBtn.getWidth() + 20, restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e -> {
            System.out.println("clicked ChangeMode Btn");
            mode = (mode == 1) ? 0 : 1;
        });


        JButton upDoBtn = new JButton("UpDo");
        upDoBtn.setSize(80, 50);
        upDoBtn.setLocation(changeModeBtn.getX() + changeModeBtn.getWidth() + 20, restartBtn.getY());
        add(upDoBtn);
        upDoBtn.addActionListener(e -> {
            if (GameController.getSteps().size() == 0) {
                JOptionPane.showMessageDialog(null, "It is initial board");
            }
            System.out.println("clicked UpDo Btn");
            controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
            GameController.getSteps().remove(GameController.getSteps().size() - 1);
            int[][] board = Automatic.putAuto(GameController.getSteps());
            ChessBoardPanel.setChessGridsInt(board);
            chessBoardPanel.transformBoard2(board);
            controller.countScore();
            statusPanel.setPlayerText(controller.getCurrentPlayer().name());
            statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
            repaint();
        });


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


}
