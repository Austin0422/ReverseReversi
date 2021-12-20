package controller;

import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;
import view.MstatusPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerController extends GameController {
    protected int blueScore;
    public boolean isMachine = false;

    public MultiPlayerController(ChessBoardPanel gamePanel, MstatusPanel mstatusPanel) {
        super();
        this.gamePanel = gamePanel;
        this.mStatusPanel = mstatusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 3;
        whiteScore = 3;
        blueScore = 3;
        List<int[]> steps = new ArrayList<>();
    }

    public void swapPlayerM() {
        countScoreM();
        if (currentPlayer == ChessPiece.BLACK) {
            currentPlayer = ChessPiece.WHITE;
        } else if (currentPlayer == ChessPiece.WHITE) {
            currentPlayer = ChessPiece.BLUE;
        } else if (currentPlayer == ChessPiece.BLUE) {
            currentPlayer = ChessPiece.BLACK;
        }
        mStatusPanel.setPlayerText(currentPlayer.name());
        mStatusPanel.setScoreTextM(blackScore, whiteScore, blueScore);
        boolean blackOver = this.blackOver();
        boolean whiteOver = this.whiteOver();
        boolean blueOver = this.blueOver();
        if (currentPlayer.equals(ChessPiece.BLACK)) {
            if (blackOver) {
                currentPlayer = ChessPiece.WHITE;
                mStatusPanel.setPlayerText(currentPlayer.name());
            }
        }

        if (currentPlayer.equals(ChessPiece.WHITE)) {
            if (whiteOver) {
                currentPlayer = ChessPiece.BLUE;
                mStatusPanel.setPlayerText(currentPlayer.name());
            }
        }
        if (currentPlayer.equals(ChessPiece.BLUE)) {
            if (blueOver) {
                currentPlayer = ChessPiece.BLACK;
                mStatusPanel.setPlayerText(currentPlayer.name());
            }

        }

        if (blackOver && whiteOver && blueOver) {
            System.out.println("Game over!");
            countScoreM();
            if (blackScore > whiteScore && blackScore > blueScore) {
                System.out.println("Black win!");
                JOptionPane.showMessageDialog(null, "Black win!");
            } else if (whiteScore > blackScore && whiteScore > blueScore) {
                System.out.println("White win!");
                JOptionPane.showMessageDialog(null, "White win!");
            } else if (blueScore > blackScore && blueScore > whiteScore) {
                System.out.println("Blue win!");
                JOptionPane.showMessageDialog(null, "Blue win!");
            } else {
                System.out.println("No winner!");
                JOptionPane.showMessageDialog(null, "No winner!");
            }
            System.exit(0);
//            todo: 销毁当前界面构造新的开始界面
//            InitialFrame mainFrame = new InitialFrame(800);
//            mainFrame.setVisible(true);
//            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (currentPlayer.equals(ChessPiece.BLACK)) {
            if (blackOver) {
                System.out.println("执棋方：黑棋你没的下了");
                if (currentPlayer == ChessPiece.BLACK) {
                    currentPlayer = ChessPiece.WHITE;
                } else if (currentPlayer == ChessPiece.WHITE) {
                    currentPlayer = ChessPiece.BLUE;
                } else {
                    currentPlayer = ChessPiece.BLACK;
                }
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }

        if (currentPlayer.equals(ChessPiece.WHITE)) {
            if (whiteOver) {
                System.out.println("执棋方：白棋你没的下了");
                if (currentPlayer == ChessPiece.BLACK) {
                    currentPlayer = ChessPiece.WHITE;
                } else if (currentPlayer == ChessPiece.WHITE) {
                    currentPlayer = ChessPiece.BLUE;
                } else {
                    currentPlayer = ChessPiece.BLACK;
                }
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }

        if (currentPlayer.equals(ChessPiece.BLUE)) {
            if (blueOver) {
                System.out.println("执棋方：蓝棋你没的下了");
                if (currentPlayer == ChessPiece.BLACK) {
                    currentPlayer = ChessPiece.WHITE;
                } else if (currentPlayer == ChessPiece.WHITE) {
                    currentPlayer = ChessPiece.BLUE;
                } else {
                    currentPlayer = ChessPiece.BLACK;
                }
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }
    }

    public void countScoreM() {
        blackScore = 0;
        whiteScore = 0;
        blueScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.gamePanel.getChessGridsInt()[i][j] == -1) {
                    blackScore++;
                } else if (this.gamePanel.getChessGridsInt()[i][j] == 1) {
                    whiteScore++;
                } else if (this.gamePanel.getChessGridsInt()[i][j] == 2) {
                    blueScore++;
                }
            }
        }
    }

    public boolean canClickM(int row, int col) {
        return gamePanel.canClickGridM(row, col, currentPlayer);
    }

    public static boolean canPutM(int[][] board, int nextMove, int row, int col) {
        int[][] directions = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int i = 0; i < 8; i++) {
            if (canPutPerDirection(board, nextMove, row, col, directions[i])) {
                return true;
            }
        }
        return false;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public boolean blueOver() {
        boolean blueOver = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(gamePanel.getChessGridsInt(), -1, i, j)) {
                    blueOver = false;
                    break;
                }
            }
        }
        return blueOver;
    }

    public void gameOverM() {
        boolean blackOver = this.blackOver();
        boolean whiteOver = this.whiteOver();
        boolean blueOver = this.blueOver();
        if (blackOver && whiteOver && blueOver) {
            System.out.println("Over");
            countScore();
            if (blackScore > whiteScore && blackScore > blueScore)
                JOptionPane.showMessageDialog(null, "Black win");
            else if (whiteScore > blackScore && whiteScore > blueScore) {
                JOptionPane.showMessageDialog(null, "White win");
            } else if (blueScore > blackScore && blueScore > whiteScore) {
                JOptionPane.showMessageDialog(null, "Blue win");
            } else
                JOptionPane.showMessageDialog(null, "draw");
        }
    }

    // 完成载入棋盘的初始化并且选择合适的执棋方
    public void convertToChessboardM(List<String> readlines) {
        int[][] newBoard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            String[] pieces = readlines.get(i).split(" ");
            newBoard[i] = new int[pieces.length];
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = Integer.parseInt(pieces[j]);
            }
        }
        this.gamePanel.setChessGridsInt(newBoard);
        this.gamePanel.transformBoard2(this.gamePanel.getChessGridsInt());
        GameFrame.controllerM.swapPlayerM();
        GameFrame.controllerM.countScoreM();
    }


}
