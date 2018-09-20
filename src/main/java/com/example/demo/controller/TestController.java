package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello Spring";
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public void insertUser(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        User user = new User();
        user.setName(name);
        user.setSex(sex);
        userService.insertUser(user);
    }
}


