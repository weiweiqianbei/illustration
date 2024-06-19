package com.illustration.controller;

import com.illustration.entity.User;
import com.illustration.result.ReturnValue;
import com.illustration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ReturnValue addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/test")
    public ReturnValue test() {
        System.out.println("成功访问");
        System.out.println(new ReturnValue<>());
        return new ReturnValue<>();
    }
}
