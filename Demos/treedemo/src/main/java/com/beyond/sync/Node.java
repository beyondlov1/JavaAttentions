package com.beyond.sync;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Node parent;
    private List<Node> children = new ArrayList<Node>();
    private Object data;

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
        for (Node child : children) {
            child.setParent(this);
        }
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Node node) {
        children.add(node);
        node.setParent(this);
    }
}
