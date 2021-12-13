package com.example.demo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class DataProcess {

    public static Map<String, Object[]> parameters =
            Map.of( "500" , new Object[]{3, 0.05},
            "400" , new Object[]{2, 0.08},
            "300" , new Object[]{2, 0.10},
            "200" , new Object[]{1, 0.10},
            "100" , new Object[]{1, 0.25});



    public Node processData(Node node, String dimension) {

        int dim = Integer.valueOf(dimension);
        // 100 : 1, 200 : 1, 300 : 2, 400 : 2; 500 : 3;
        int maxLevel = Math.min(4, (dim - 100) / 200 + 1);
        double minSize = (double) 20 / dim;

        long sum = getSum(node);
        long minValue = (long) (sum * minSize);
        System.out.println("minValue " + minValue);
        // merge the level if it exceeds the maxLevel;
        mergeLevel(node, maxLevel, 0);
        sortAndArrange(node, minValue, true);
        return node;
    }


    Map<Node, Long> nodeSum = new HashMap<>();
    // this is to sort the node and combine part less than the value;
    private  Node sortAndArrange(Node node, long minValue, boolean isRoot) {
        long sum = 0;
        if (nodeSum.containsKey(node)) {
            sum = nodeSum.get(node);
        } else {
            sum = getSum(node);
            nodeSum.put(node, sum);
        }
        if (node.getChildren() == null) {
            return node;
        } else if (sum < minValue) {
            LeafNode ret = new LeafNode(node);
            ret.setValue(sum);
            ret.setName(node.name);
            ret.setColname(node.colname);
            return ret;
        } else {
            // sort the childnode;
            List<Node> copyChildren = new ArrayList<>();
            for (Node child : node.getChildren()) {
                copyChildren.add(sortAndArrange(child, minValue, false));
            }
            node.setChildren(copyChildren);
            node.getChildren().sort((a,b) -> (int) (getSum(a) - getSum(b)));
            if (node.getChildren().size() == 1) {
                LeafNode ret = new LeafNode(node);
                ret.setValue(sum);
                ret.setName(node.name);
                ret.setColname(node.colname);
                return ret;
            }

            // arrange the childNode from the largest one that is smaller than the
            long subSum = 0;
            LinkedList copyChildren2 = new LinkedList<>();
            for (Node child : node.getChildren()) {
                long temp = 0;
                if (nodeSum.containsKey(child)) {
                    temp = nodeSum.get(child);
                } else {
                    temp = getSum(node);
                    nodeSum.put(child, temp);
                }
                if (temp < minValue) {
                    subSum += getSum(child);
                } else if (subSum != 0 && subSum < minValue) {
                    subSum += getSum(child);
                } else {
                    copyChildren2.addFirst(child);
                }
            }
            if (subSum != 0) {
                copyChildren2.addLast(new LeafNode("Others",subSum, node.getChildren().get(0).getColname(),null));
            }
            node.setChildren(copyChildren2);
            if (node.getChildren().size() == 1) {
                if (isRoot) {
                    node.getChildren().get(0).setName("All");
                    return node;
                } else {
                    LeafNode ret = new LeafNode(node);
                    ret.setValue(sum);
                    ret.setName(node.name);
                    ret.setColname(node.colname);
                    return ret;
                }
            }
            return node;
        }
    }


    // this is to merge the unnecessary level in the node;
    private Node mergeLevel(Node node, int maxLevel, int currLevel) {
        if (node.getChildren() == null) {
            return node;
        } else if (currLevel == maxLevel) {
            long sum = 0;
            if (nodeSum.containsKey(node)) {
                sum = nodeSum.get(node);
            } else {
                sum = getSum(node);
                nodeSum.put(node, sum);
            }
            LeafNode ret = new LeafNode(node);
            ret.setValue(sum);
            ret.setName(node.name);
            return ret;
        } else {
            List<Node> copyChildren = new ArrayList<>();
            for (Node child : node.getChildren()) {
                copyChildren.add(mergeLevel(child, maxLevel, currLevel + 1));
            }
            node.setChildren(copyChildren);
            return node;
        }

    }

    private long getSum(Node node) {
        if (nodeSum.containsKey(node)) {
            return nodeSum.get(node);
        }
        if (node.getChildren() == null) {
            LeafNode leaf = (LeafNode) node;
            return leaf.getValue();
        } else {
            long sum = 0;
            for (Node child : node.getChildren()) {
                sum += getSum(child);
            }
            nodeSum.put(node, sum);
            return sum;
        }

    }
}
