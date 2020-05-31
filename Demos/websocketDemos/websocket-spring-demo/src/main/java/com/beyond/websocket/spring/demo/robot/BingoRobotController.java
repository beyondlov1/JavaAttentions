package com.beyond.websocket.spring.demo.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class BingoRobotController {

    @Autowired
    private BingoNode root;

    @RequestMapping("/play")
    public Object play(@RequestBody BingoBoard.Player[][] board, HttpServletRequest request){
        List<BingoNode> nodes = BingoTreeHelper.findNodes(root, board);
        BingoBoard.Player nextPlayer = BingoTreeHelper.getNextPlayer(board);
        BingoNode bestNode = BingoTreeHelper.getBestNode(nodes, nextPlayer);
        HashMap<Object, Object> result = new HashMap<>();
        result.put("player", nextPlayer);
        result.put("x",bestNode.getX());
        result.put("y",bestNode.getY());
        HttpSession session = request.getSession();
        List<BingoNode> bingoNodes = session.getAttribute("nodeChain") == null
                ? new ArrayList<BingoNode>() : (List<BingoNode>) session.getAttribute("nodeChain");
        bingoNodes.add(bestNode);
        session.setAttribute("nodeChain",bingoNodes);
        return result;
    }

    @RequestMapping("/result")
    public Object result(BingoBoard.Player losePlayer,
                         HttpServletRequest request){
        HttpSession session = request.getSession();
        List<BingoNode> bingoNodeChain = (List<BingoNode>)session.getAttribute("nodeChain");
        for (BingoNode bingoNode : bingoNodeChain) {
            bingoNode.setWinRate(losePlayer,bingoNode.getWinRate(losePlayer)*0.9);
        }
        return null;
    }
}
