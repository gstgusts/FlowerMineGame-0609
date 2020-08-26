package com.company;

public class Game {
    public static final int FieldSize = 3;
    private FieldStatus[][] board = new FieldStatus[FieldSize][FieldSize];

    public Game() {
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < FieldSize; i++) {
            for (int j = 0; j < FieldSize; j++) {
                board[i][j] = FieldStatus.CLOSED;
            }
        }
    }

    public FieldStatus[][] getBoard() {
        return board;
    }

    public FieldStatus getFieldStatus(int row, int col) {
        return board[row][col];
    }
}
