package com.illustration.controller;

import com.illustration.entity.dto.LoginDto;
import com.illustration.result.ReturnValue;
import com.illustration.result.StateMsg;
import com.illustration.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
// 解决跨域问题
//@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;
    /**
     * 登录
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public ReturnValue login(@RequestBody LoginDto loginDto) {
        log.info("parameter================》{}", loginDto);
        String login = authService.login(loginDto);
        return new ReturnValue(StateMsg.StatusMsg_200, login);
    }
}
