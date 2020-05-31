package com.beyond.websocket.spring.demo.robot;

public enum BingoResult {
    EVEN(null),WIN1(BingoBoard.Player.PLAYER1),WIN2(BingoBoard.Player.PLAYER2);

    private BingoBoard.Player player;

    BingoResult(BingoBoard.Player player) {
        this.player = player;
    }

    public BingoBoard.Player getPlayer() {
        return player;
    }

    public static BingoResult result(BingoBoard.Player player){

        if (player == null){
            throw new RuntimeException("player cannot be null");
        }

        if (player == BingoBoard.Player.PLAYER1){
            return WIN1;
        }

        if (player == BingoBoard.Player.PLAYER2){
            return WIN2;
        }

        return EVEN;
    }
}
