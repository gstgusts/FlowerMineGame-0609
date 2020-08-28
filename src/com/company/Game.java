package com.company;

public class Game {
    public static final int FieldSize = 3;
    private FieldStatus[][] board = new FieldStatus[FieldSize][FieldSize];
    private GeneratedFieldValue[][] values = new GeneratedFieldValue[FieldSize][FieldSize];

    public Game() {
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < FieldSize; i++) {
            for (int j = 0; j < FieldSize; j++) {
                board[i][j] = FieldStatus.CLOSED;
                values[i][j] = GeneratedFieldValue.EMPTY;
            }
        }

        generateValues();
    }

    public FieldStatus[][] getBoard() {
        return board;
    }

    public FieldStatus getFieldStatus(int row, int col) {
        return board[row][col];
    }

    public FieldStatus fieldClicked(int row, int col) {
        if(board[row][col] != FieldStatus.CLOSED) {
            return board[row][col];
        }

        switch (values[row][col]) {
            case EMPTY -> {
                return FieldStatus.OPENED;
            }
            case MINE -> {
                return FieldStatus.MINE;
            }
            case FLOWER -> {
                return FieldStatus.FLOWER;
            }
        }

        return FieldStatus.OPENED;
    }

    private void generateValues() {
       // generate location of mine
        var row = getRandomNumber(0,3);
        var col = getRandomNumber(0,3);
        values[row][col] = GeneratedFieldValue.MINE;

       //generate location for flower
        var flowerCounter = 0;
        while (flowerCounter < 2) {
            var r = getRandomNumber(0, 3);
            var c = getRandomNumber(0, 3);

            if(values[r][c] == GeneratedFieldValue.MINE || values[r][c] == GeneratedFieldValue.FLOWER) {
                continue;
            }

            values[r][c] = GeneratedFieldValue.FLOWER;
            ++flowerCounter;
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
