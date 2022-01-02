package com.example.TransportMe.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.*;

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

    // request handled with request parameters
    @DeleteMapping("/delete")
    @ResponseBody
    boolean deleteTest( @RequestParam("id") int id ){
        testings.remove(id-1);
        return true;
    }


}
