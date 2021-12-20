package model;

import java.awt.*;

public enum ChessPiece {
    BLACK(Color.BLACK,-1), WHITE(Color.WHITE,1); /*BLUE(new Color(60, 150, 200), 2); //转化为二维数组时黑棋-1 白棋为1*/

    private final Color color;
    private final int represent;

    ChessPiece(Color color,int represent) {
        this.color = color;
        this.represent = represent;
    }

    public Color getColor() {
        return color;
    }

    public int getRepresent() {
        return represent;
    }
}
