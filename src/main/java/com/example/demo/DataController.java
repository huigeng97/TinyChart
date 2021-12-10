package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
public class DataController {

    @PersistenceContext
    protected EntityManager em;

    // return a POJ class text of Node (will show in JSON format);
    @RequestMapping("/data")
    public Node se2(String name, Model model) {

        JSONParser jsonParser = new JSONParser();

        JSONObject treeMapData = new JSONObject();

        try (FileReader reader = new FileReader("src/main/java/com/example/demo/data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            treeMapData = (JSONObject) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseTreeMapObject(treeMapData);
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
