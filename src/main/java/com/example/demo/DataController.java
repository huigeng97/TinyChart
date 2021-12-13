package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
public class DataController {

    @PersistenceContext
    protected EntityManager em;

    // return a POJ class text of Node (will show in JSON format);
    @RequestMapping("/data/{id}/{dimension}")
    public Node se2(String name, Model model, @PathVariable String id, @PathVariable String dimension) {

        JSONParser jsonParser = new JSONParser();

        Object treeMapData = null;

        try (FileReader reader = new FileReader("src/main/java/com/example/demo/data" + id + ".json"))
        {
            //Read JSON file
            treeMapData = jsonParser.parse(reader);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Node node = null;
        if (id.equals("1")) {
            node = parseCVSTreeMapObject((JSONArray) treeMapData);
        } else {
            node = parseTreeMapObject((JSONObject) treeMapData);
        }

        // TODO: sort the node, rearrange based on the size of the chart;
        if (Integer.valueOf(dimension) <= 500 && Integer.valueOf(dimension) >= 100) {
            DataProcess.processData(node, dimension);
        }
        return node;
    }

    private static Node parseCVSTreeMapObject(JSONArray treeMapData) {
        Map<String, Node> memo = new HashMap<>();
        JSONObject rootJson = (JSONObject) treeMapData.get(0);
        String name = (String) rootJson.get("id");
        Node root = new Node(name);
        root.setChildren(new ArrayList<Node>());
        memo.put(name, root);
        for (int i = 1; i < treeMapData.size(); i++) {
            JSONObject curr = (JSONObject) treeMapData.get(i);
            // this is a leafNode;
            String id = (String) curr.get("id");
            System.out.println(id);
            String[] ids = id.split("\\.");
            String currName = ids[ids.length - 1];
            String parName = ids[ids.length - 2];
            Node par = memo.get(parName);
            System.out.println("par" + par.getName());
            if (curr.get("value") != null) {
                long value = (long) curr.get("value");
                LeafNode currNode = new LeafNode(currName, value);
                par.getChildren().add(currNode);
            }
            // this is a Node;
            else {
                Node currNode = new Node(currName);
                par.getChildren().add(currNode);
                currNode.setChildren(new ArrayList<Node>());
                memo.put(currName, currNode);
            }
        }
        return root;
    }


    private static Node parseTreeMapObject(JSONObject treeMapData) {

        String name = (String) treeMapData.get("name");
        String colname = (String) treeMapData.get("colname");
        JSONArray children = (JSONArray) treeMapData.get("children");
        Node root = new Node(name, colname);
        root.colname = colname;
        if (children != null) {
            List<Node> nodeChildren = new ArrayList<>();
            for (Object node : children) {
                nodeChildren.add(parseTreeMapObject((JSONObject) node));
            }
            root.setChildren(nodeChildren);
            return root;
        } else {
            LeafNode leaf = new LeafNode();
            leaf.setName(root.name);
            leaf.setColname(root.colname);
            leaf.setGroup((String) treeMapData.get("group"));
            leaf.setValue((Long) treeMapData.get("value"));
            return leaf;
        }
    }
}
