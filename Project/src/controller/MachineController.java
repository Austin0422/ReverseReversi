package controller;

import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;
import view.StatusPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MachineController extends GameController {

    private static int machineMode = 10;

    public static void setMachineMode(int machineMode) {
        MachineController.machineMode = machineMode;
    }

    public MachineController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        super(gamePanel, statusPanel);
        isMachine = true;
    }

    public static int[][] weight = {
            {70, -20, 20, 20, 20, 20, -15, 70},
            {-20, -30, 5, 5, 5, 5, -30, -15},
            {20, 5, 1, 1, 1, 1, 5, 20},
            {20, 5, 1, 1, 1, 1, 5, 20},
            {20, 5, 1, 1, 1, 1, 5, 20},
            {20, 5, 1, 1, 1, 1, 5, 20},
            {-20, -30, 5, 5, 5, 5, -30, -15},
            {70, -15, 20, 20, 20, 20, -15, 70}
    };


    public static int[] AICanPut(int[][] board) {
        int[] whereToPut = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(board, 1, i, j)) {
                    whereToPut = new int[]{i, j};
                    return whereToPut;
                }
            }
        }
        return null;
    }

    public static int[] AICanPut2(int[][] board) {
        int[] whereToPut = null;
        int max = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (countCanPut(board, 1, i, j) > max) {
                    max = countCanPut(board, 1, i, j);
                    whereToPut = new int[]{i, j};
                }
            }
        }
        return whereToPut;
    }

    public static int[] AICanPut3(int[][] board) {
        int[] whereToPut = null;
        int[] max = new int[2];
        int all = Integer.MIN_VALUE;
        int maxPoint = 0;
        int minPoint = 0;
        int[][] copyBoard = copy(board);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                max = new int[]{i, j};
                maxPoint = countCanPut2(board, 1, i, j);
                boolean minCanPut = ChessBoardPanel.Ok(copyBoard, i, j, 1, -1);
                for (int m = 0; m < 8; m++) {
                    for (int n = 0; n < 8; n++) {
                        minPoint = countCanPut2(copyBoard, -1, m, n);
                        if (minPoint + maxPoint > all) {
                            all = maxPoint + minPoint;
                            whereToPut = new int[]{i, j};
                        }
                        copyBoard = copy(board);
                    }
                }
            }
        }
        return whereToPut;
    }

    public void AI() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] AIPut = AICanPut(ChessBoardPanel.getChessGridsInt());
        if (AIPut != null) {
            ChessBoardPanel.Ok(ChessBoardPanel.getChessGridsInt(), AIPut[0], AIPut[1], 1, -1);
            int[] arr = new int[]{GameFrame.getMode(), 1, AIPut[0], AIPut[1]};
            GameController.getSteps().add(arr);
            System.out.println("white");
            ChessBoardPanel.displayBoard(ChessBoardPanel.getChessGridsInt());
            GameFrame.controller.getGamePanel().transformBoard2(ChessBoardPanel.getChessGridsInt());
        }
    }

    public void AI2() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] AIPut = AICanPut2(ChessBoardPanel.getChessGridsInt());
        if (AIPut != null) {
            ChessBoardPanel.Ok(ChessBoardPanel.getChessGridsInt(), AIPut[0], AIPut[1], 1, -1);
            ChessBoardPanel.displayBoard(ChessBoardPanel.getChessGridsInt());
            int[] arr = new int[]{GameFrame.getMode(), 1, AIPut[0], AIPut[1]};
            GameController.getSteps().add(arr);
            GameFrame.controller.getGamePanel().transformBoard2(ChessBoardPanel.getChessGridsInt());
        }
    }

    public void AI3() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] AIPut = AICanPut3(ChessBoardPanel.getChessGridsInt());
        if (AIPut != null) {
            ChessBoardPanel.Ok(ChessBoardPanel.getChessGridsInt(), AIPut[0], AIPut[1], 1, -1);
            ChessBoardPanel.displayBoard(ChessBoardPanel.getChessGridsInt());
            int[] arr = new int[]{GameFrame.getMode(), 1, AIPut[0], AIPut[1]};
            GameController.getSteps().add(arr);
            GameFrame.controller.getGamePanel().transformBoard2(ChessBoardPanel.getChessGridsInt());
        }
    }

    public static int countPutPerDirection(int[][] board, int nextMove, int row, int col, int[] direction) {
        if (board[row][col] != 0) {
            return 0;
        }
        int dx = direction[0];
        int dy = direction[1];
        int count = 0;
        boolean hasEnd = false;
        while (row + dx < 8 && row + dx >= 0 && col + dy < 8 && col + dy >= 0) {
            row += dx;
            col += dy;
            if (board[row][col] == 0) {
                count = 0;
                break;
            } else if (board[row][col] == nextMove) {
                hasEnd = true;
                break;
            } else {
                count += 1;
            }
        }
        if (hasEnd == true) {
            return count;
        }
        return 0;
    }

    public static int countCanPut(int[][] board, int nextMove, int row, int col) {
        int[][] directions = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        int all = 0;

        for (int i = 0; i < 8; i++) {
            all += countPutPerDirection(board, nextMove, row, col, directions[i]);
        }
        return all;
    }

    // 用来计算权重
    public static int countPutPerDirection2(int[][] board, int nextMove, int row, int col, int[] direction) {
        if (board[row][col] != 0) {
            return 0;
        }
        int initialRow = row;
        int initialCol = col;
        int dx = direction[0];
        int dy = direction[1];
        int point = weight[row][col];
        boolean hasEnd = false;
        while (row + dx < 8 && row + dx >= 0 && col + dy < 8 && col + dy >= 0) {
            row += dx;
            col += dy;
            if (board[row][col] == 0) {
                break;
            } else if (board[row][col] == nextMove) {
                hasEnd = true;
                break;
            } else {
                point += weight[row][col];
            }
        }
        if (point > 0 && hasEnd) {
            return point;
        } else {
            return weight[initialRow][initialCol];
        }
    }

    public static int countCanPut2(int[][] board, int nextMove, int row, int col) {
        int point = 0;
        int[][] directions = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int i = 0; i < 8; i++) {
            if (canPutPerDirection(board, nextMove, row, col, directions[i])) {
                point += countPutPerDirection2(board, nextMove, row, col, directions[i]);
            }
        }
        return point;
    }

    public void run() {
        if (machineMode == 10) {
            AI();
        } else if (machineMode == 100) {
            AI2();
        } else if (machineMode == 1000) {
            AI3();
        }
        GameFrame.controller.countScore();
        while(MachineController.machineCanPut(ChessBoardPanel.getChessGridsInt())&& !MachineController.humaneCanPut(ChessBoardPanel.getChessGridsInt())){
            if (machineMode == 10) {
                AI();
            } else if (machineMode == 100) {
                AI2();
            } else if (machineMode == 1000) {
                AI3();
            }
            GameFrame.controller.countScore();
            GameFrame.controller.gameOver();
        }
        GameFrame.controller.countScore();
        if (MachineController.humaneCanPut(ChessBoardPanel.getChessGridsInt())) {
            GameFrame.controller.setCurrentPlayer(ChessPiece.BLACK);
            GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
        }
        GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
    }

    public static boolean humaneCanPut(int[][] board) {
        boolean can = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(board, -1, i, j)) {
                    can = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean machineCanPut(int[][] board) {
        boolean can = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(board, 1, i, j)) {
                    can = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] randomPut() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ChessBoardPanel.getChessGridsInt()[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void gameOverFor0() {
        int[] a = randomPut();
        if (a == null) {
            System.out.println("GameOver");
            countScore();
            if (GameFrame.controller.getBlackScore() > GameFrame.controller.getWhiteScore())
                JOptionPane.showMessageDialog(null, "black win");
            else if (GameFrame.controller.getBlackScore() < GameFrame.controller.getWhiteScore()) {
                JOptionPane.showMessageDialog(null, "white win");
            } else
                JOptionPane.showMessageDialog(null, "draw");
        }


    }

    public void convertToChessboard(List<String> readlines) {
        GameController.setSteps(new ArrayList<>());
        current = Integer.parseInt(readlines.get(0));
        for (int i = 9; i < readlines.size(); i++) {
            String[] step = readlines.get(i).split(" ");
            int[] stepInt = new int[]{Integer.parseInt(step[0]), Integer.parseInt(step[1]), Integer.parseInt(step[2]), Integer.parseInt(step[3])};
            GameController.getSteps().add(stepInt);
        }
        int[][] board = Automatic.putAuto(GameController.getSteps());
        ChessBoardPanel.setChessGridsInt(board);
        GameFrame.controller.getGamePanel().transformBoard2(board);
        GameFrame.controller.setCurrentPlayer(current == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
        GameFrame.controller.getStatusPanel().setPlayerText(GameFrame.controller.getCurrentPlayer().name());
        GameFrame.controller.getStatusPanel().setScoreText(GameFrame.controller.getBlackScore(), GameFrame.controller.getWhiteScore());
    }

    public static int[][] copy(int[][] board) {
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }


}
