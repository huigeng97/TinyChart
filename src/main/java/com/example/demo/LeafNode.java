package com.example.demo;

public class LeafNode extends Node {

    String group;
    Long value;
    String colname;
    String name;

    public LeafNode(String group, long value, String colname, String name) {
        this(name);
        this.group = group;
        this.value = value;
        this.colname = colname;
        this.name = name;
    }

    public LeafNode(String name) {
        this();
        this.name = name;
    }

    public LeafNode() {
        super();
    }

    public LeafNode(Node root) {
        this.colname = root.colname;
        this.name = root.name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }


}
