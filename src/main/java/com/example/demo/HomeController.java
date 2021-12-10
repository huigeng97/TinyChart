package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
public class HomeController {

    @PersistenceContext
    protected EntityManager em;

    // return the html page in the src/main/resource/static folder
    @RequestMapping("/home")
    public String se(String name, Model model) {
        //home page of our app
        return "home.html";
    }

}
