package components;

import controller.GameController;
import controller.MachineController;
import model.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.ChessBoardPanel;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);

    private ChessPiece chessPiece;
    private int row;
    private int col;

    public static Color hintColor = new Color(250, 73, 73);
    public static int hintSize;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        /*if (GameFrame.getMode() == 1 || GameFrame.getMode() == 0) {
            System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        } else if (GameFrame.getMode() == 2) {
            System.out.printf("%s clicked (%d, %d)\n", GameFrame.controllerM.getCurrentPlayer(), row, col);
        }*/
        playMusic();
        if (this.chessPiece == null) {
            if (!GameFrame.controller.isMachine) {
                if (GameFrame.getMode() == 1) {
                    if (GameFrame.controller.canClick(row, col)) {
                        this.chessPiece = GameFrame.controller.getCurrentPlayer();
                        GameFrame.controller.swapPlayer();
                        int[] arr = new int[]{1, this.chessPiece == ChessPiece.BLACK ? -1 : 1, row, col};
                        GameController.getSteps().add(arr);
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong place", null, JOptionPane.ERROR_MESSAGE);
                    }
                    repaint();
                } else if (GameFrame.getMode() == 0) {
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    GameFrame.controller.getGamePanel().getChessGridsInt()[row][col] = this.chessPiece.getRepresent();
                    boolean change = GameFrame.controller.canClick(row, col);
                    GameFrame.controller.swapPlayer2();
                    repaint();
                    int[] arr = new int[]{0, this.chessPiece == ChessPiece.BLACK ? -1 : 1, row, col};
                    GameController.getSteps().add(arr);
                }
//                else if (GameFrame.getMode() == 2) {
//                    if (GameFrame.controllerM.canClickM(row, col)) {
//                        this.chessPiece = GameFrame.controllerM.getCurrentPlayer();
//                        GameFrame.controllerM.swapPlayerM();
////                    int[] arr = new int[]{1, this.chessPiece == ChessPiece.BLACK ? -1 : 1, row, col};
////                    GameController.getSteps().add(arr);
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Wrong place", null, JOptionPane.ERROR_MESSAGE);
//                    }
//                    repaint();
//                }
            } else {
                if (GameFrame.getMode() == 1) {
                    if (GameFrame.controller.canClick(row, col)) {
                        this.chessPiece = ChessPiece.BLACK;
                        int[] arr = new int[]{1, -1, row, col};
                        GameController.getSteps().add(arr);
                        GameFrame.controller.countScore();
                        GameFrame.controller.setCurrentPlayer(ChessPiece.WHITE);
                        GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
                        GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
                        GameFrame.controller.gameOver();
                        new Thread(GameFrame.controller).start();
                        if (!MachineController.machineCanPut(ChessBoardPanel.getChessGridsInt())) {
                            GameFrame.controller.setCurrentPlayer(ChessPiece.BLACK);
                            GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
                            GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
                        }
                    }
                    repaint();
                } else if (GameFrame.getMode() == 0) {
                    this.chessPiece = ChessPiece.BLACK;
                    GameFrame.controller.getGamePanel().getChessGridsInt()[row][col] = this.chessPiece.getRepresent();
                    int[] arr = new int[]{0, -1, row, col};
                    GameController.getSteps().add(arr);
                    GameFrame.controller.countScore();
                    GameFrame.controller.setCurrentPlayer(ChessPiece.WHITE);
                    GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
                    GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
                    GameFrame.controller.gameOverFor0();
                    new Thread(GameFrame.controller).start();
                    GameFrame.controller.setCurrentPlayer(ChessPiece.BLACK);
                    GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
                    GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
                    repaint();
                }
            }
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (GameController.canPut(ChessBoardPanel.getChessGridsInt(), GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK ? -1 : 1, row, col)) {
            g.setColor(hintColor);
            g.fillOval((gridSize - hintSize) / 2, (gridSize - hintSize) / 2, hintSize, hintSize);
        }
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }

//    public void drawPiece2(Graphics g) {
//        int current;
//        g.setColor(gridColor);
//        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
//        if(GameFrame.controllerM.getCurrentPlayer()==ChessPiece.BLUE){
//            current = 2;
//        }else if(GameFrame.controllerM.getCurrentPlayer()==ChessPiece.BLACK){
//            current =-1;
//        }else {
//            current = 1;
//        }
//        if (GameController.canPut(ChessBoardPanel.getChessGridsInt(), current , row, col)) {
//            g.setColor(hintColor);
//            g.fillOval((gridSize - hintSize) / 2, (gridSize - hintSize) / 2, hintSize, hintSize);
//        }
//        if (this.chessPiece != null) {
//            g.setColor(chessPiece.getColor());
//            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
//        }
//    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
//        if (GameFrame.getMode() != 2) {
            drawPiece(g);
//        } else {
//            drawPiece2(g);
//        }
    }

    public static void playMusic() {
        try {
            FileInputStream fileau = new FileInputStream("src/下棋.wav");
            AudioStream as = new AudioStream(fileau);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
