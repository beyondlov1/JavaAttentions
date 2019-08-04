package com.beyond.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Node parent;
    private List<Node> children = new ArrayList<Node>();
    private Object data;
    private List<Object> notMineData = new ArrayList<Object>();

    public List<Object> getChildrenData() {
        List<Object> totalData = new ArrayList<Object>();
        for (Node child : children) {
            totalData.add(child.getData());
            totalData.addAll(child.getChildrenData());
        }
        return totalData;
    }


    public void dispatchData(List<Object> data) {
        notMineData.addAll(data);
        notMineData.remove(this.data);
        for (Node child : children) {
            child.dispatchData(data);
        }
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
        for (Node child : children) {
            child.setParent(this);
        }
    }

    public List<Object> getNotMineData() {
        return notMineData;
    }

    public void setNotMineData(List<Object> notMineData) {
        this.notMineData = notMineData;
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
    }

    public void addChild(Node node) {
        children.add(node);
        node.setParent(this);
    }
}
