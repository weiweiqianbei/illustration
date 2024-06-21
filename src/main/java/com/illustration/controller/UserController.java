package com.illustration.controller;

import com.illustration.entity.dto.LoginDto;
import com.illustration.result.ReturnValue;
import com.illustration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.illustration.result.StateMsg.StatusMsg_400;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ReturnValue addUser(@RequestBody LoginDto loginDto) {
        log.info("parameter================》{}", loginDto);
        if (userService.addUser(loginDto)) {
            return new ReturnValue();
        }
        return new ReturnValue(StatusMsg_400);
    }
}
