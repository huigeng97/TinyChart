package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Node {

    String name;
    String colname;
    private List<Node> children;

    public Node(String name, String colname) {
        this.name = name;
        this.colname = colname;
    }

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
