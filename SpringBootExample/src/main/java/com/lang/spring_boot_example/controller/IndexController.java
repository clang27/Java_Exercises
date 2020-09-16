package com.lang.spring_boot_example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public String hello() {
        return "This is the index page!";
    }

    @RequestMapping(value = "/info" /* Default: method = RequestMethod.GET */)
    public ResponseEntity<Object> getInfo() {
        return new ResponseEntity<>(new ArrayList<Integer>().add(3), HttpStatus.OK);
    }
}
