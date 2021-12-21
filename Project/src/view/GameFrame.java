package view;

import controller.Automatic;
import controller.GameController;
import controller.MachineController;
//import controller.MultiPlayerController;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    public static GameController controller;
    //    public static MultiPlayerController controllerM;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    //    private MstatusPanel mstatusPanel;
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
        ChessBoardPanel.setChessGridsInt(chessBoardPanel.transferBoard(chessBoardPanel.getChessGrids()));
        GameController.setSteps(new ArrayList<>());
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

//        if (getMode() != 2) {
        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
//        } else {
//            mstatusPanel = new MstatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
//            mstatusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
//        }
//        if (getMode() != 2) {
        if (isMachine) {
            controller = new MachineController(chessBoardPanel, statusPanel);
        } else {
            controller = new GameController(chessBoardPanel, statusPanel);
        }
        controller.setGamePanel(chessBoardPanel);
//        } else {
//            controller = new GameController(chessBoardPanel, statusPanel);
//            controllerM = new MultiPlayerController(chessBoardPanel, mstatusPanel);
//            controllerM.setGamePanel(chessBoardPanel);
//        }

        this.add(chessBoardPanel);
//        if (getMode() != 2) {
        this.add(statusPanel);
//        } else {
//            this.add(mstatusPanel);
//        }


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(100, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);

        restartBtn.addActionListener(e -> {
            int userOption = JOptionPane.showConfirmDialog(null, "Really !?", null, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (userOption == JOptionPane.OK_OPTION) {
                this.dispose();
                Music.endMusic1();
                Music.playMusic();
                mode = 1;
                isMachine = false;
                InitialFrame mainFrame = new InitialFrame(700);
                mainFrame.setVisible(true);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }//确认对话框
        });

        JButton fresh = new JButton("Fresh");
        fresh.setSize(80, 50);
        fresh.setLocation(600, 500);
        add(fresh);
        fresh.addActionListener(e -> {
            Music.endMusic1();
            ChessBoardPanel.setChessGridsInt(ChessBoardPanel.initialBoard);
            chessBoardPanel.transformBoard2(ChessBoardPanel.initialBoard);
            GameController.setSteps(new ArrayList<>());
            dispose();
            GameFrame gameFrame = new GameFrame(700);
//            controller.countScore();
//            GameFrame.controller.setCurrentPlayer(ChessPiece.BLACK);
//            GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
//            GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
//            repaint();

        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(100, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(loadGameBtn);

        loadGameBtn.addActionListener(e -> {
            try {
                String filePath = JOptionPane.showInputDialog(this, "input the path here");
                String[] strs = filePath.split("[.]");
                if (strs[strs.length - 1].equals("txt")) {
                    controller.readFileData(filePath);
                } else {
                    JOptionPane.showMessageDialog(null, "104 : Wrong FileType", null, JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "106: Other error!(File don't exist)", null, JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(100, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            try {
                String filePath = JOptionPane.showInputDialog(this, "input the path here");
                controller.writeDataToFile(filePath);
            } catch (Exception exception) {
            }
        });

        JButton changeModeBtn = new JButton("Change");
        changeModeBtn.setSize(100, 50);
        changeModeBtn.setLocation(saveGameBtn.getX() + saveGameBtn.getWidth() + 20, restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e -> {
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
                } else {
                    controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
                    GameController.getSteps().remove(GameController.getSteps().size() - 1);
                    int[][] board = Automatic.putAuto(GameController.getSteps());
                    ChessBoardPanel.setChessGridsInt(board);
                    chessBoardPanel.transformBoard2(board);
                    controller.countScore();
                    statusPanel.setPlayerText(controller.getCurrentPlayer().name());
                    statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
                    repaint();
                }
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
                try {
                    controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
                } catch (Exception ep) {
                }
                try {
                    while (GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1) {
                        GameController.getSteps().remove(GameController.getSteps().size() - 1);
                    }
                } catch (Exception e2) {
                }
                try {
                    GameController.getSteps().remove(GameController.getSteps().size() - 1);
                } catch (Exception e3) {

                }
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
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.readFileData(filePath);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(100, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 20, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        JButton changeModeBtn = new JButton("Change");
        changeModeBtn.setSize(100, 50);
        changeModeBtn.setLocation(saveGameBtn.getX() + saveGameBtn.getWidth() + 20, restartBtn.getY());
        add(changeModeBtn);
        changeModeBtn.addActionListener(e -> {
            mode = (mode == 1) ? 0 : 1;
        });


        JButton upDoBtn = new JButton("UpDo");
        upDoBtn.setSize(80, 50);
        upDoBtn.setLocation(changeModeBtn.getX() + changeModeBtn.getWidth() + 20, restartBtn.getY());
        add(upDoBtn);
        upDoBtn.addActionListener(e -> {
            if (GameController.getSteps().size() == 0) {
                JOptionPane.showMessageDialog(null, "It is initial board");
            } else {
                controller.setCurrentPlayer(GameController.getSteps().get(GameController.getSteps().size() - 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
                GameController.getSteps().remove(GameController.getSteps().size() - 1);
                int[][] board = Automatic.putAuto(GameController.getSteps());
                ChessBoardPanel.setChessGridsInt(board);
                chessBoardPanel.transformBoard2(board);
                controller.countScore();
                statusPanel.setPlayerText(controller.getCurrentPlayer().name());
                statusPanel.setScoreText(controller.getBlackScore(), controller.getWhiteScore());
                repaint();
            }
        });


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


}
