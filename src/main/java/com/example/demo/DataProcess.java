package com.example.demo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcess {

    public static HashMap<String, Double[]> parameters = new HashMap<>(
            Map.of( "500" , new double[]{3, 0.05},
                    "400" , new double[]{2, 0.08},
                    "300" , new double[]{2, 0.10},
                    "200" , new double[]{1, 0.15},
                    "100" , new double[]{1, 0.25}
    );



    public static Node processData(Node node, String dimension) {

        Double[] pars = parameters.get(dimension);
        if (pars != null) {
            int maxLevel = Integer.valueOf(pars[0].toString());
            double minSize = pars[1];
            long sum = getSum(node);
            long minValue = (long) (sum * minSize);
            // merge the level if it exceeds the maxLevel;
            mergeLevel(node, maxLevel, 0);
            sortAndArrange(node, minValue);
            return node;
        } else {
            return node;
        }
    }

    // this is to sort the node and combine part less than the value;
    private static Node sortAndArrange(Node node, long minValue) {
        long sum = getSum(node);
        if (sum < minValue) {
            LeafNode ret = new LeafNode(node);
            ret.setValue(sum);
            return ret;
        } else {
            // sort the childnode;
            List<Node> copyChildren = new ArrayList<>();
            for (Node child : node.getChildren()) {
                copyChildren.add(sortAndArrange(child, minValue));
            }
            node.setChildren(copyChildren);
            node.getChildren().sort((a,b) -> (int) (getSum(a) - getSum(b)));
            // arrange the childNode from the largest one that is smaller than the

            long subSum = 0;
            for (Node child : node.getChildren()) {
                long temp = getSum(child);
                if (temp < minValue) {
                    subSum += getSum(child);
                } else {

                }
                copyChildren.add(sortAndArrange(child, minValue));
            }

            return node;


            return node;
        }
    }


    // this is to merge the unnecessary level in the node;
    private static Node mergeLevel(Node node, int maxLevel, int currLevel) {
        if (node.getChildren() == null) {
            return node;
        } else if (currLevel == maxLevel) {
            long sum = getSum(node);
            LeafNode ret = new LeafNode(node);
            ret.setValue(sum);
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

    private static long getSum(Node node) {
        if (node.getChildren() == null) {
            LeafNode leaf = (LeafNode) node;
            return leaf.getValue();
        } else {
            long sum = 0;
            for (Node child : node.getChildren()) {
                sum += getSum(child);
            }
            return sum;
        }

    }
}
