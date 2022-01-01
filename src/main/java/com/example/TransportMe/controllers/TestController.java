package com.example.TransportMe.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    static List<String> testings = new ArrayList<String>();

    @GetMapping("/test")
    String test(){
        return "test";
    }

    @GetMapping("/test/all")
    List<String> tests(){
        return testings;
    }

    // I will take testing from the url as an argument
    @PostMapping("/add/{testing}")
    @ResponseBody
    boolean addTest( @PathVariable("testing") String test ){
        testings.add(test);
        return true;
    }

    @PutMapping("/update/{testId}/{newValue}")
    @ResponseBody
    boolean updateTest( @PathVariable("testId") int id , @PathVariable("newValue") String val)
    {
        testings.remove(id);
        testings.add(id-1, val);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    boolean deleteTest( @PathVariable("id") int id ){
        testings.remove(id-1);
        return true;
    }


}
