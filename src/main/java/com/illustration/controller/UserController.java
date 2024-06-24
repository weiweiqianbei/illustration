package com.illustration.controller;

import com.illustration.entity.User;
import com.illustration.entity.dto.LoginDto;
import com.illustration.result.ReturnValue;
import com.illustration.service.UserService;
import com.illustration.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PatchMapping
    public ReturnValue alterUser(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("data") String data) {
        log.info("parameter================》{}", data);
        User user = JsonUtil.deserialize(data, User.class);
        userService.updateUser(file, user);
        return new ReturnValue();
    }
}
