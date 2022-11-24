package com.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @GetMapping("/home")
    public String homePath(){
        return "Home";
    }

    @GetMapping("/welcome")
    public String welcomePath(){
        return "welcome";
    }

    @GetMapping("/user")
    public String userPath(){
        return "User";
    }

    @GetMapping("/admin")
    public String adminPath(){
        return "Admin";
    }


}
