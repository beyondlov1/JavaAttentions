package com.beyond.tree;

import com.sun.istack.internal.NotNull;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Node root = constructTree();
        List<Object> childrenData = root.getChildrenData();
        System.out.println(childrenData);

        root.dispatchData(childrenData);
        System.out.println(root.getNotMineData());
        for (Node child : root.getChildren()) {
            System.out.println(child.getNotMineData());
        }
    }

    @NotNull
    private static Node constructTree() {
        Node root = new Node();
        root.setData(0);
        // 第一层
        for (int i = 0; i < 4; i++) {
            Node node1 = new Node();
            node1.setData(1+i);
            root.addChild(node1);

            // 第二层
            for (int j = 0; j < 10; j++) {
                Node node2 = new Node();
                node2.setData(5+j);
                node1.addChild(node2);

                // 第三层
                for (int k = 0; k < 10; k++) {
                    Node node3 = new Node();
                    node3.setData(15+k);
                    node2.addChild(node3);
                }
            }
        }
        return root;
    }
}
