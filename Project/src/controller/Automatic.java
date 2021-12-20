package controller;

import view.ChessBoardPanel;
import view.GameFrame;

import javax.swing.*;
import java.util.List;

public class Automatic {

    public static int[][] putAuto(List<int[]> arr) {
        int[][] board = new int[8][8];
        board[3][3] = -1;
        board[4][4] = -1;
        board[3][4] = 1;
        board[4][3] = 1;
        for (int[] e : arr) {
            if (e[0] == 1) {
                ChessBoardPanel.Ok(board, e[2], e[3], e[1], e[1] == 1 ? -1 : 1);
            } else {
                ChessBoardPanel.Ok(board, e[2], e[3], e[1], e[1] == 1 ? -1 : 1);
                board[e[2]][e[3]] = e[1];
            }
        }
        ChessBoardPanel.displayBoard(board);
        return board;
    }


    public static int[][] putAutoByStep(int[][] board, int[] e) {
        if (e[0] == 1) {
            if (!ChessBoardPanel.Ok(board, e[2], e[3], e[1], e[1] == 1 ? -1 : 1)) {
                JOptionPane.showMessageDialog(null, "105: Illegal move! " + "Normal mode", null, JOptionPane.ERROR_MESSAGE);
                String filePath = JOptionPane.showInputDialog(null, "input the path here");
                GameFrame.controller.readFileData(filePath);
                // 报错 => 105
            }
        } else {
            if (!ChessBoardPanel.Ok(board, e[2], e[3], e[1], e[1] == 1 ? -1 : 1)) {
                board[e[2]][e[3]] = e[1];
                JOptionPane.showMessageDialog(null, "105: Illegal move! " + "Cheating mode", null, JOptionPane.ERROR_MESSAGE);
                String filePath = JOptionPane.showInputDialog(null, "input the path here");
                GameFrame.controller.readFileData(filePath);
                // 报错 => 105
            }
        }

        return board;
    }
}
