package com.example.demo;

public class LeafNode extends Node {

    String group;
    Long value;
    String colname;
    String name;

    public LeafNode(String name, long value, String colname, String group) {
        this.group = group;
        this.value = value;
        this.colname = colname;
        this.name = name;
    }

    public LeafNode(String name) {
        this.name = name;
    }

    public LeafNode() {}

    public LeafNode(Node root) {
        this.colname = root.colname;
        this.name = root.name;
    }

    public LeafNode(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String getColname() {
        return colname;
    }

    @Override
    public void setColname(String colname) {
        this.colname = colname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
