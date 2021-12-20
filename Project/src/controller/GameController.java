package controller;

import model.ChessPiece;
import view.*;

import javax.swing.*;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GameController implements Runnable {
    protected ChessBoardPanel gamePanel;
    protected StatusPanel statusPanel;
    protected MstatusPanel mStatusPanel;
    protected ChessPiece currentPlayer;
    protected int blackScore;
    protected int whiteScore;
    //迫不得已设定的加载时对应的现行方
    public static int current;
    private static List<int[]> steps = new ArrayList<>();
    public boolean isMachine = false;

    public static List<int[]> getSteps() {
        return steps;
    }

    public static void setSteps(List<int[]> steps) {
        GameController.steps = steps;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
        List<int[]> steps = new ArrayList<>();
        int current = 0;
    }

    public GameController() {
    }

    //swap white and black
    public void swapPlayer() {
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);

        boolean blackOver = this.blackOver();
        boolean whiteOver = this.whiteOver();

        if (blackOver && whiteOver) {
            System.out.println("结束战斗");
            countScore();
            if (blackScore > whiteScore) {
                System.out.println("黑获胜");
                JOptionPane.showMessageDialog(null, "黑方获胜");
            } else if (whiteScore > blackScore) {
                System.out.println("白获胜");
                JOptionPane.showMessageDialog(null, "白方获胜");
            } else {
                System.out.println("平局");
                JOptionPane.showMessageDialog(null, "平局");
            }
            System.exit(0);
        }

        if (currentPlayer.equals(ChessPiece.BLACK)) {
            if (blackOver) {
                currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }

        if (currentPlayer.equals(ChessPiece.WHITE)) {
            if (whiteOver) {
                currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
                statusPanel.setPlayerText(currentPlayer.name());
            }
        }
    }

    //cheating swapPlayer2
    public void swapPlayer2() {
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
        boolean blackOver = this.blackOver();
        boolean whiteOver = this.whiteOver();
        //判断游戏是否结束
        if (MachineController.randomPut() == null) {
            System.out.println("Game over");
            countScore();
            if (blackScore > whiteScore) {
                System.out.println("Black win");
                JOptionPane.showMessageDialog(null, "Black win");
            } else if (whiteScore > blackScore) {
                System.out.println("White win");
                JOptionPane.showMessageDialog(null, "White win");
            } else {
                System.out.println("draw");
                JOptionPane.showMessageDialog(null, "draw");
            }
            System.exit(0);
        }
    }


    // 计数通过二维数组计算
    public void countScore() {
        blackScore = 0;
        whiteScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.gamePanel.getChessGridsInt()[i][j] == -1) {
                    blackScore++;
                } else if (this.gamePanel.getChessGridsInt()[i][j] == 1) {
                    whiteScore++;
                }
            }
        }
        //todo: modify the countScore method
    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void readFileData(String fileName) {
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            this.convertToChessboard(fileData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = this.convertToList();
            for (String line : lines
            ) {
                writer.write(line);
                writer.write("\r\n");
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }

    public int getBlackScore() {
        return blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setCurrentPlayer(ChessPiece currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    //判断游戏是否结束 白加黑 = 游戏结束
    public boolean whiteOver() {
        boolean whiteOver = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(gamePanel.getChessGridsInt(), 1, i, j)) {
                    whiteOver = false;
                    break;
                }
            }
        }
        if (whiteOver) {
            System.out.println("执棋方：白棋你没的下了");
        }
        return whiteOver;
    }

    public boolean blackOver() {
        boolean blackOver = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canPut(gamePanel.getChessGridsInt(), -1, i, j)) {
                    blackOver = false;
                    break;
                }
            }
        }
        if (blackOver) {
            System.out.println("执棋方：黑棋你没的下了");
        }
        return blackOver;
    }

    public void gameOver() {
        boolean blackOver = this.blackOver();
        boolean whiteOver = this.whiteOver();
        if (blackOver && whiteOver) {
            System.out.println("Over");
            countScore();
            if (blackScore > whiteScore)
                JOptionPane.showMessageDialog(null, "black win");
            else if (whiteScore > blackScore) {
                JOptionPane.showMessageDialog(null, "white win");
            } else
                JOptionPane.showMessageDialog(null, "draw");
        }
    }

    // 单纯判断执棋方能否下棋
    public static boolean canPutPerDirection(int[][] board, int nextMove, int row, int col, int[] direction) {
        if (board[row][col] != 0) {
            return false;
        }
        int dx = direction[0];
        int dy = direction[1];
        int count = 0;
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
                count += 1;
            }
        }
        if (count > 0 && hasEnd) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean canPut(int[][] board, int nextMove, int row, int col) {
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

    // 完成载入棋盘的初始化
    public void convertToChessboard(List<String> readlines) {
        steps = new ArrayList<>(); // 先判断有无非法
        try {
            if (Integer.parseInt(readlines.get(0)) == 1 || Integer.parseInt(readlines.get(0)) == -1) {
                current = Integer.parseInt(readlines.get(0));
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "103: " + numberFormatException + "Missing players!", null, JOptionPane.ERROR_MESSAGE);
            // 报错 => 103
        }

        for (int i = 1; i < 9; i++) {
            String[] oneLine = readlines.get(i).split(" ");
            int[] oneL = new int[oneLine.length];
            for (int j = 0; j < oneLine.length; j++) {
                oneL[j] = Integer.parseInt(oneLine[j]);
                if (oneL[j] != 0 && oneL[j] != 1 && oneL[j] != -1) {
                    JOptionPane.showMessageDialog(null, "102: Chess error!", null, JOptionPane.ERROR_MESSAGE);
                    i = 8;
                    String filePath = JOptionPane.showInputDialog(null, "input the path here");
                    GameFrame.controller.readFileData(filePath);
                    break;
                    //oneLine -> int[] 判断有没有非法棋子 + break => 102
                }
            }

            if (oneLine.length != 8) {
                JOptionPane.showMessageDialog(null, "101: Chessboard error!", null, JOptionPane.ERROR_MESSAGE);
                String filePath = JOptionPane.showInputDialog(null, "input the path here");
                GameFrame.controller.readFileData(filePath);
                break;
                // 报错+break => 101
            }
        }

        for (int i = 9; i < readlines.size(); i++) {
            String[] step = readlines.get(i).split(" ");
            if (step.length != 4) {
                JOptionPane.showMessageDialog(null, "101: Chessboard error!", null, JOptionPane.ERROR_MESSAGE);
                String filePath = JOptionPane.showInputDialog(null, "input the path here");
                GameFrame.controller.readFileData(filePath);
                break;
                // b报错 + break =>101
            }

            int[] stepInt = new int[]{Integer.parseInt(step[0]), Integer.parseInt(step[1]), Integer.parseInt(step[2]), Integer.parseInt(step[3])};
            steps.add(stepInt);
        }
        new Thread(GameFrame.controller).start();
    }

    //将数字棋盘转化为字符串的List
    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        lines.add(String.valueOf(currentPlayer == ChessPiece.WHITE ? 1 : -1));
        for (int[] ints : this.gamePanel.getChessGridsInt()) {
            sb.setLength(0);
            for (int oneInt : ints) {
                sb.append(oneInt).append(" ");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }
        for (int[] s : steps) {
            sb.setLength(0);
            for (int oneInt : s) {
                sb.append(oneInt).append(" ");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }
        return lines;
    }

    public void gameOverFor0() {
    }

    //动态渲染实现
    public void run() {
        int[][] newBoard = new int[8][8];
        newBoard[3][3] = -1;
        newBoard[4][4] = -1;
        newBoard[3][4] = 1;
        newBoard[4][3] = 1;
        for (int i = 0; i < steps.size(); i++) {
            int[] step = steps.get(i);
            newBoard = Automatic.putAutoByStep(newBoard, step);
            this.gamePanel.setChessGridsInt(newBoard);
            ChessBoardPanel.displayBoard(newBoard);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.gamePanel.transformBoard2(this.gamePanel.getChessGridsInt());
            if (i < steps.size() - 1) {
                GameFrame.controller.setCurrentPlayer(steps.get(i + 1)[1] == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
            } else {
                GameFrame.controller.setCurrentPlayer(current == 1 ? ChessPiece.WHITE : ChessPiece.BLACK);
            }
            statusPanel.setPlayerText(currentPlayer.name());
            GameFrame.controller.countScore();
            statusPanel.setScoreText(blackScore, whiteScore);
            statusPanel.repaint();
        }
    }

}

