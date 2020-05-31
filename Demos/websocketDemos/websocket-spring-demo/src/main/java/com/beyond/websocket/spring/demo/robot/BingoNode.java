package com.beyond.websocket.spring.demo.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BingoNode {
    private final BingoBoard bingoBoard;

    private Map<BingoBoard.Player,Double> winRateMap = new HashMap<>(2);

    private List<BingoNode> children = new ArrayList<>();

    private Map<BingoBoard, List<BingoNode>> descendantNodeMap;

    private Integer x;

    private Integer y;

    public BingoNode(BingoBoard.Player[][] board) {
        bingoBoard = new BingoBoard(board);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Map<BingoBoard, List<BingoNode>> getDescendantNodeMap() {
        return descendantNodeMap;
    }

    public void setDescendantNodeMap(Map<BingoBoard, List<BingoNode>> descendantNodeMap) {
        this.descendantNodeMap = descendantNodeMap;
    }

    public List<BingoNode> getChildren() {
        return children;
    }

    public void setChildren(List<BingoNode> children) {
        this.children = children;
    }

    public BingoBoard getBingoBoard() {
        return bingoBoard;
    }

    public void setWinRate(BingoBoard.Player player, Double winRate){
        winRateMap.put(player, winRate);
    }

    public Double getWinRate(BingoBoard.Player player){
        return winRateMap.get(player);
    }
}

