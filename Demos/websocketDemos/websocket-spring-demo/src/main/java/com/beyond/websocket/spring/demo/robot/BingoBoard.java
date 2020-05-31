package com.beyond.websocket.spring.demo.robot;

import java.util.Arrays;

public class BingoBoard {

    private Player[][] board;

    private BingoResult result;

    public BingoBoard(Player[][] board) {
        this.board = board;
    }

    public void computeResult() {
        result = BingoTreeHelper.result(board);
    }

    public BingoResult getResult() {
        return result;
    }

    public Player[][] getBoard() {
        return board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BingoBoard that = (BingoBoard) o;

        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public static enum Player {
        PLAYER1, PLAYER2
    }
}
