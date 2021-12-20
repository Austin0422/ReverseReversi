package view;

import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    private static int[][] chessGridsInt;
    public final static int[][] initialBoard = new int[][]{
        {0,0,0, 0, 0, 0,0,0},
        {0,0,0, 0, 0, 0,0,0},
        {0,0,0, 0, 0, 0,0,0},
        {0,0,0, 1,-1, 0,0,0},
        {0,0,0,-1, 1, 0,0,0},
        {0,0,0, 0, 0, 0,0,0},
        {0,0,0, 0, 0, 0,0,0},
        {0,0,0, 0, 0, 0,0,0}
    } ;


    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        ChessGridComponent.hintSize = (int) (ChessGridComponent.gridSize * 0.3);

        initialChessGrids();//return empty chessboard
//        if (GameFrame.getMode() != 2) {
            initialGame();//add initial four chess
            this.chessGridsInt = transferBoard(this.chessGrids);
            repaint();
//        } else {
//            initialGameM();
//            this.chessGridsInt = transferBoard3(this.chessGrids);
//            repaint();
//        }
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
    }

//    public void initialGameM() {
//        chessGrids[2][2].setChessPiece(ChessPiece.BLACK);
//        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
//        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
//        chessGrids[3][2].setChessPiece(ChessPiece.WHITE);
//        chessGrids[2][4].setChessPiece(ChessPiece.WHITE);
//        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
//        chessGrids[2][3].setChessPiece(ChessPiece.BLUE);
//        chessGrids[3][4].setChessPiece(ChessPiece.BLUE);
//        chessGrids[4][2].setChessPiece(ChessPiece.BLUE);
//    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    public void setChessGrids(ChessGridComponent[][] chessGrids) {
        this.chessGrids = chessGrids;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        int currentPlayerNumber;
        int otherNumber;
        if (currentPlayer.equals(ChessPiece.BLACK)) {
            currentPlayerNumber = -1;
            otherNumber = 1;
        } else {
            currentPlayerNumber = 1;
            otherNumber = -1;
        }
        boolean isOK = this.Ok(chessGridsInt, row, col, currentPlayerNumber, otherNumber);
        transformBoard2(chessGridsInt);
        repaint();
        return isOK;
    }

//    public boolean canClickGridM(int row, int col, ChessPiece currentPlayer) {
//        int currentPlayerNumber;
//        if (currentPlayer.equals(ChessPiece.BLACK)) {
//            currentPlayerNumber = -1;
//        } else if (currentPlayer.equals(ChessPiece.WHITE)) {
//            currentPlayerNumber = 1;
//        } else {
//            currentPlayerNumber = 2;
//        }
//        boolean isOKM = OkM(chessGridsInt, row, col, currentPlayerNumber);
//        transformBoard4(chessGridsInt);
//        repaint();
//        return isOKM;
//    }

    public static boolean Ok(int[][] board, int m, int j, int my, int other) {
        boolean isOk = false;
        if (m + 2 < board.length && board[m + 1][j] == other) {
            for (int i = m + 2; i < board.length; i++) {
                if (board[i][j] == my) {
                    isOk = true;
                    board[m][j] = my;
                    for (int k = m + 1; k < i; k++) {
                        board[k][j] = my;
                    }
                    break;
                } else if (board[i][j] == 0)
                    break;
            }
        }
        if (m - 2 >= 0 && board[m - 1][j] == other) {
            for (int i = m - 2; i >= 0; i--) {
                if (board[i][j] == my) {
                    isOk = true;
                    board[m][j] = my;
                    for (int k = m - 1; k > i; k--) {
                        board[k][j] = my;
                    }
                    break;
                } else if (board[i][j] == 0)
                    break;
            }
        }
        if (j + 2 < 8 && board[m][j + 1] == other) {
            for (int i = j + 2; i < 8; i++) {
                if (board[m][i] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = j + 1; k < i; k++) {
                        board[m][k] = my;
                    }
                    break;
                } else if (board[m][i] == 0)
                    break;
            }
        }
        if (j - 2 >= 0 && board[m][j - 1] == other) {
            for (int i = j - 2; i >= 0; i--) {
                if (board[m][i] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = j - 1; k > i; k--) {
                        board[m][k] = my;
                    }
                    break;
                } else if (board[m][i] == 0)
                    break;
            }
        }
        if (m + 2 < 8 && j + 2 < 8 && board[m + 1][j + 1] == other) {
            int i = m + 2;
            int l = j + 2;
            for (; i < 8 && l < 8; ) {
                if (board[i][l] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = m + 1, p = j + 1; k < i; k++) {
                        board[k][p] = my;
                        p++;
                    }
                    break;
                } else if (board[i][l] == 0)
                    break;
                i++;
                l++;
            }
        }
        if (m - 2 >= 0 && j - 2 >= 0 && board[m - 1][j - 1] == other) {
            int i = m - 2;
            int l = j - 2;
            for (; i >= 0 && l >= 0; ) {
                if (board[i][l] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = m - 1, p = j - 1; k > i; k--) {
                        board[k][p] = my;
                        p--;
                    }
                    break;
                } else if (board[i][l] == 0)
                    break;
                i--;
                l--;
            }
        }
        if (m - 2 >= 0 && j + 2 < 8 && board[m - 1][j + 1] == other) {
            int i = m - 2;
            int l = j + 2;
            for (; i >= 0 && l < 8; ) {
                if (board[i][l] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = m - 1, p = j + 1; k > i; k--) {
                        board[k][p] = my;
                        p++;
                    }
                    break;
                } else if (board[i][l] == 0)
                    break;
                i--;
                l++;
            }
        }
        if (m + 2 < 8 && j - 2 >= 0 && board[m + 1][j - 1] == other) {
            int i = m + 2;
            int l = j - 2;
            for (; i < 8 && l >= 0; ) {
                if (board[i][l] == my) {
                    board[m][j] = my;
                    isOk = true;
                    for (int k = m + 1, p = j - 1; k < i; k++) {
                        board[k][p] = my;
                        p--;
                    }
                    break;
                } else if (board[i][l] == 0)
                    break;
                i++;
                l--;
            }
        }
        if (isOk) {
            board[m][j] = my;
        }
        return isOk;
    }

//    public static boolean OkM(int[][] board, int m, int j, int my) {
//        boolean isOkM = false;
//        if (m + 2 < board.length && board[m + 1][j] != my && board[m + 1][j] != 0) {
//            for (int i = m + 2; i < board.length; i++) {
//                if (board[i][j] == my) {
//                    isOkM = true;
//                    board[m][j] = my;
//                    for (int k = m + 1; k < i; k++) {
//                        board[k][j] = my;
//                    }
//                    break;
//                } else if (board[i][j] == 0)
//                    break;
//            }
//        }
//        if (m - 2 >= 0 && board[m - 1][j] != my && board[m - 1][j] != 0) {
//            for (int i = m - 2; i >= 0; i--) {
//                if (board[i][j] == my) {
//                    isOkM = true;
//                    board[m][j] = my;
//                    for (int k = m - 1; k > i; k--) {
//                        board[k][j] = my;
//                    }
//                    break;
//                } else if (board[i][j] == 0)
//                    break;
//            }
//        }
//        if (j + 2 < 8 && board[m][j + 1] != my && board[m][j + 1] != 0) {
//            for (int i = j + 2; i < 8; i++) {
//                if (board[m][i] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = j + 1; k < i; k++) {
//                        board[m][k] = my;
//                    }
//                    break;
//                } else if (board[m][i] == 0)
//                    break;
//            }
//        }
//        if (j - 2 >= 0 && board[m][j - 1] != my && board[m][j - 1] != 0) {
//            for (int i = j - 2; i >= 0; i--) {
//                if (board[m][i] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = j - 1; k > i; k--) {
//                        board[m][k] = my;
//                    }
//                    break;
//                } else if (board[m][i] == 0)
//                    break;
//            }
//        }
//        if (m + 2 < 8 && j + 2 < 8 && board[m + 1][j + 1] != my && board[m + 1][j + 1] != 0) {
//            int i = m + 2;
//            int l = j + 2;
//            for (; i < 8 && l < 8; ) {
//                if (board[i][l] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = m + 1, p = j + 1; k < i; k++) {
//                        board[k][p] = my;
//                        p++;
//                    }
//                    break;
//                } else if (board[i][l] == 0)
//                    break;
//                i++;
//                l++;
//            }
//        }
//        if (m - 2 >= 0 && j - 2 >= 0 && board[m - 1][j - 1] != my && board[m - 1][j - 1] != 0) {
//            int i = m - 2;
//            int l = j - 2;
//            for (; i >= 0 && l >= 0; ) {
//                if (board[i][l] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = m - 1, p = j - 1; k > i; k--) {
//                        board[k][p] = my;
//                        p--;
//                    }
//                    break;
//                } else if (board[i][l] == 0)
//                    break;
//                i--;
//                l--;
//            }
//        }
//        if (m - 2 >= 0 && j + 2 < 8 && board[m - 1][j + 1] != my && board[m - 1][j + 1] != 0) {
//            int i = m - 2;
//            int l = j + 2;
//            for (; i >= 0 && l < 8; ) {
//                if (board[i][l] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = m - 1, p = j + 1; k > i; k--) {
//                        board[k][p] = my;
//                        p++;
//                    }
//                    break;
//                } else if (board[i][l] == 0)
//                    break;
//                i--;
//                l++;
//            }
//        }
//        if (m + 2 < 8 && j - 2 >= 0 && board[m + 1][j - 1] != my && board[m + 1][j - 1] != 0) {
//            int i = m + 2;
//            int l = j - 2;
//            for (; i < 8 && l >= 0; ) {
//                if (board[i][l] == my) {
//                    board[m][j] = my;
//                    isOkM = true;
//                    for (int k = m + 1, p = j - 1; k < i; k++) {
//                        board[k][p] = my;
//                        p--;
//                    }
//                    break;
//                } else if (board[i][l] == 0)
//                    break;
//                i++;
//                l--;
//            }
//        }
//        return isOkM;
//    }

    // 用于将棋盘转化为二维数组 方便使用方法判断位置是否合法
    public int[][] transferBoard(ChessGridComponent[][] board1) {
        int[][] board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board1[i][j].getChessPiece() == null) {
                    board[i][j] = 0;
                } else if (board1[i][j].getChessPiece().equals(ChessPiece.WHITE)) {
                    board[i][j] = 1;
                } else if (board1[i][j].getChessPiece().equals(ChessPiece.BLACK)) {
                    board[i][j] = -1;
                }
            }
        }
        return board;
    }

//    public int[][] transferBoard3(ChessGridComponent[][] board1) {
//        int[][] board = new int[8][8];
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (board1[i][j].getChessPiece() == null) {
//                    board[i][j] = 0;
//                } else if (board1[i][j].getChessPiece().equals(ChessPiece.WHITE)) {
//                    board[i][j] = 1;
//                } else if (board1[i][j].getChessPiece().equals(ChessPiece.BLACK)) {
//                    board[i][j] = -1;
//                } else if (board1[i][j].getChessPiece().equals(ChessPiece.BLUE)) {
//                    board[i][j] = 2;
//                }
//            }
//        }
//        return board;
//    }

    //棋盘的反向转化
    public void transformBoard2(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    this.chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
                if (board[i][j] == -1) {
                    this.chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }
                if (board[i][j] == 0) {
                    this.chessGrids[i][j].setChessPiece(null);
                }
            }
            repaint();
        }
    }

//    public void transformBoard4(int[][] board) {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (board[i][j] == 1) {
//                    this.chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
//                }
//                if (board[i][j] == -1) {
//                    this.chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
//                }
//                if (board[i][j] == 0) {
//                    this.chessGrids[i][j].setChessPiece(null);
//                }
//                if (board[i][j] == 2) {
//                    this.chessGrids[i][j].setChessPiece(ChessPiece.BLUE);
//                }
//                this.chessGrids[i][j].repaint();
//            }
//            repaint();
//        }
//    }

    // 取用数组版本的棋盘
    public static int[][] getChessGridsInt() {
        return chessGridsInt;
    }

    public static void setChessGridsInt(int[][] chessGridsInt) {
        ChessBoardPanel.chessGridsInt = chessGridsInt;
    }

    //看看数字棋盘是啥样子
   /* public static void displayBoard(int[][] chessBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%3d", chessBoard[i][j]);
            }
            System.out.print("\n");
        }
    }*/
}
