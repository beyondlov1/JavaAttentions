package com.beyond.websocket.spring.demo.robot;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class BingoTreeHelper {

    public static void calculateWinRate(BingoNode root, BingoBoard.Player player) {
        List<BingoNode> children = root.getChildren();
        if (CollectionUtils.isEmpty(children)) {
            root.setWinRate(player, root.getBingoBoard().getResult().getPlayer() == player ? 1d : 0d);
            return;
        }
        Double sumWinRate = 1d;
        for (BingoNode child : children) {
            if (child.getWinRate(player) == null) {
                calculateWinRate(child, player);
            }
            sumWinRate += child.getWinRate(player);
        }
        root.setWinRate(player, sumWinRate/children.size());

    }

    public static BingoNode getRoot() {
        BingoBoard.Player[][] players = new BingoBoard.Player[3][3];
        return new BingoNode(players);
    }

    public static void buildTree(BingoNode root) {
        BingoBoard.Player[][] board = root.getBingoBoard().getBoard();
        root.getBingoBoard().computeResult();
        if (root.getBingoBoard().getResult() != null) {
            return;
        }
        BingoBoard.Player nextPlayer = getNextPlayer(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                try {
                    BingoNode node = new BingoNode(play(nextPlayer, board, i, j));
                    node.setX(i);
                    node.setY(j);
                    root.getChildren().add(node);
                } catch (ConflictException e) {
                    // do nothing
                }
            }
        }

        for (BingoNode child : root.getChildren()) {
            buildTree(child);
        }
    }

    public static BingoResult result(BingoBoard.Player[][] board) {
        BingoResult rowResult = getRowResult(board);
        if (rowResult != null) {
//            print(board);
            return rowResult;
        }
        BingoResult columnResult = getColumnResult(board);
        if (columnResult != null) {
//            print(board);
            return columnResult;
        }

        BingoResult leftCrossResult = getLeftCrossResult(board);
        if (leftCrossResult != null) {
//            print(board);
            return leftCrossResult;
        }

        BingoResult rightCrossResult = getRightCrossResult(board);
        if (rightCrossResult != null) {
//            print(board);
            return rightCrossResult;
        }

        if (count(BingoBoard.Player.PLAYER1, board)
                + count(BingoBoard.Player.PLAYER2, board)
                == board.length * board.length) {
            return BingoResult.EVEN;
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    private static BingoResult getRowResult(BingoBoard.Player[][] board) {
        for (int i = 0; i < board.length; i++) {
            boolean isRowAllSame = true;
            BingoBoard.Player rowFirst = board[i][0];
            for (int j = 1; j < board[i].length; j++) {
                if (rowFirst == null || rowFirst != board[i][j]) {
                    isRowAllSame = false;
                    break;
                }
                rowFirst = board[i][j];
            }
            if (isRowAllSame) {
                return BingoResult.result(rowFirst);
            }
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    private static BingoResult getColumnResult(BingoBoard.Player[][] board) {
        for (int i = 0; i < board.length; i++) {
            boolean isColumnAllSame = true;
            BingoBoard.Player columnFirst = board[0][i];
            for (int j = 1; j < board[i].length; j++) {

                if (columnFirst == null || columnFirst != board[j][i]) {
                    isColumnAllSame = false;
                    break;
                }
                columnFirst = board[j][i];
            }
            if (isColumnAllSame) {
                return BingoResult.result(columnFirst);
            }
        }
        return null;
    }

    private static BingoResult getLeftCrossResult(BingoBoard.Player[][] board) {
        BingoBoard.Player leftCrossFirst = board[0][0];
        boolean isLeftCrossAllSame = true;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (i == j) {
                    if (leftCrossFirst == null || leftCrossFirst != board[i][j]) {
                        isLeftCrossAllSame = false;
                        break;
                    }
                    leftCrossFirst = board[i][j];
                }
            }
        }
        if (isLeftCrossAllSame) {
            return BingoResult.result(leftCrossFirst);
        }
        return null;
    }

    private static BingoResult getRightCrossResult(BingoBoard.Player[][] board) {
        BingoBoard.Player rightCrossFirst = board[0][board.length - 1];
        boolean isRightCrossAllSame = true;
        for (int i = 1; i < board.length; i++) {
            for (int j = board[i].length - 2; j >= 0; j--) {
                if (i == board.length - j - 1) {
                    if (rightCrossFirst == null || rightCrossFirst != board[i][j]) {
                        isRightCrossAllSame = false;
                        break;
                    }
                    rightCrossFirst = board[i][j];
                }
            }
        }
        if (isRightCrossAllSame) {
            return BingoResult.result(rightCrossFirst);
        }
        return null;
    }

    public static BingoBoard.Player getNextPlayer(BingoBoard.Player[][] board) {
        Integer player1Count = count(BingoBoard.Player.PLAYER1, board);
        Integer player2Count = count(BingoBoard.Player.PLAYER2, board);
        if (player1Count == 0 && player2Count == 0) {
            return BingoBoard.Player.PLAYER1;
        } else if (player1Count > player2Count) {
            return BingoBoard.Player.PLAYER2;
        } else {
            return BingoBoard.Player.PLAYER1;
        }
    }

    private static Integer count(BingoBoard.Player player, BingoBoard.Player[][] board) {
        int count = 0;
        for (BingoBoard.Player[] lines : board) {
            for (BingoBoard.Player line : lines) {
                if (line == player) {
                    count++;
                }
            }
        }
        return count;
    }

    public static BingoBoard play(BingoBoard.Player player, BingoBoard parentBoard, int x, int y) throws ConflictException {
        return new BingoBoard(play(player, parentBoard.getBoard(), x, y));
    }

    public static BingoBoard.Player[][] play(BingoBoard.Player player, BingoBoard.Player[][] parentBoard, int x, int y) throws ConflictException {
        if (parentBoard[x][y] != null) {
            throw new ConflictException();
        }
        BingoBoard.Player[][] newBoardArray = Arrays.copyOf(parentBoard, parentBoard.length);
        for (int i = 0; i < parentBoard.length; i++) {
            newBoardArray[i] = Arrays.copyOf(parentBoard[i], parentBoard[i].length);
        }
        newBoardArray[x][y] = player;
        return newBoardArray;
    }


    public static void print(BingoBoard board) {
        BingoBoard.Player[][] boardArray = board.getBoard();
        print(boardArray);
    }

    public static void print(BingoBoard.Player[][] boardArray) {
        for (BingoBoard.Player[] lines : boardArray) {
            for (BingoBoard.Player line : lines) {
                System.out.print(line);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static BingoNode getBestNode(BingoNode root, BingoBoard.Player player) {
        List<BingoNode> children = root.getChildren();

        BingoBoard.Player oppositePlayer = null;
        if (player == BingoBoard.Player.PLAYER1) {
            oppositePlayer = BingoBoard.Player.PLAYER2;
        } else {
            oppositePlayer = BingoBoard.Player.PLAYER1;
        }
        Double oppositeMinWinRate = 10000d;
        BingoNode oppsiteWorseNode = null;
        for (BingoNode child : children) {
            Double winRate = child.getWinRate(oppositePlayer);
            if (winRate < oppositeMinWinRate) {
                oppositeMinWinRate = winRate;
                oppsiteWorseNode = child;
            }
        }

        Double maxWinRate = -1d;
        BingoNode maxWinNode = null;
        for (BingoNode child : children) {
            Double winRate = child.getWinRate(player);
            if (winRate > maxWinRate) {
                maxWinRate = winRate;
                maxWinNode = child;
            }
        }
        if (maxWinRate>0.5){
            return maxWinNode;
        }
        return oppsiteWorseNode;
    }

    public static BingoNode getBestNode(List<BingoNode> roots, BingoBoard.Player player) {
        List<BingoNode> bestBingoNodes = new ArrayList<>();
        for (BingoNode root : roots) {
            BingoNode bestNode = getBestNode(root, player);
            if (bestNode != null) {
                bestBingoNodes.add(bestNode);
            }
        }

        BingoBoard.Player oppositePlayer = null;
        if (player == BingoBoard.Player.PLAYER1) {
            oppositePlayer = BingoBoard.Player.PLAYER2;
        } else {
            oppositePlayer = BingoBoard.Player.PLAYER1;
        }
        Double oppositeMinWinRate = 10000d;
        BingoNode oppsiteWorseNode = null;
        for (BingoNode bingoNode : bestBingoNodes) {
            Double winRate = bingoNode.getWinRate(oppositePlayer);
            if (winRate < oppositeMinWinRate) {
                oppositeMinWinRate = winRate;
                oppsiteWorseNode = bingoNode;
            }
        }

        Double maxWinRate = -1d;
        BingoNode maxWinNode = null;
        for (BingoNode child : bestBingoNodes) {
            Double winRate = child.getWinRate(player);
            if (winRate > maxWinRate) {
                maxWinRate = winRate;
                maxWinNode = child;
            }
        }
        if (maxWinRate>0.5){
            return maxWinNode;
        }
        return oppsiteWorseNode;
    }

    public static void buildNodeIndex(BingoNode root) {
        Map<BingoBoard, List<BingoNode>> descendantNodeMap = root.getDescendantNodeMap();
        if (descendantNodeMap == null) {
            descendantNodeMap = new HashMap<>();
            root.setDescendantNodeMap(descendantNodeMap);
            List<BingoNode> nodes = new ArrayList<>();
            nodes.add(root);
            descendantNodeMap.put(root.getBingoBoard(), nodes);
            for (BingoNode child : root.getChildren()) {
                buildNodeIndex(child);
                for (BingoBoard bingoBoard : child.getDescendantNodeMap().keySet()) {
                    List<BingoNode> nodes1 = descendantNodeMap.get(bingoBoard);
                    if (nodes1 == null) {
                        descendantNodeMap.put(bingoBoard, child.getDescendantNodeMap().get(bingoBoard));
                    } else {
                        nodes1.addAll(child.getDescendantNodeMap().get(bingoBoard));
                    }
                }
            }
        }
    }

    public static List<BingoNode> findNodes(BingoNode root, BingoBoard.Player[][] board) {
        return root.getDescendantNodeMap().get(new BingoBoard(board));
    }

    public static BingoNode getTree() {
        BingoNode root = getRoot();
        buildTree(root);
        buildNodeIndex(root);
        calculateWinRate(root, BingoBoard.Player.PLAYER1);
        calculateWinRate(root, BingoBoard.Player.PLAYER2);
        return root;
    }


    public static void main(String[] args) {
        BingoNode root = getRoot();
        BingoNode targetNode = new BingoNode(new BingoBoard.Player[][]{
                {BingoBoard.Player.PLAYER1, null, null},
                {BingoBoard.Player.PLAYER2, null, null},
                {null, null, BingoBoard.Player.PLAYER1}
        });
        buildTree(root);
        buildNodeIndex(root);
        calculateWinRate(root, BingoBoard.Player.PLAYER1);
        calculateWinRate(root, BingoBoard.Player.PLAYER2);

        List<BingoNode> nodes = findNodes(root, targetNode.getBingoBoard().getBoard());
        for (BingoNode node : nodes) {
            print(node.getBingoBoard());
        }

        BingoNode bingoNode = root;
        for (int i = 0; i < 9; i++) {
            bingoNode = getBestNode(bingoNode, i % 2 == 0 ? BingoBoard.Player.PLAYER2 : BingoBoard.Player.PLAYER1);
            print(bingoNode.getBingoBoard());
            System.out.println();
        }
//        System.out.println(root);
    }
}
