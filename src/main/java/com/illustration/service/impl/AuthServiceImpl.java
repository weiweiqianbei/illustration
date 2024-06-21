package com.illustration.service.impl;

import com.illustration.entity.User;
import com.illustration.entity.dto.LoginDto;
import com.illustration.entity.vo.LoginUserVO;
import com.illustration.exception.CustomException;
import com.illustration.service.AuthService;
import com.illustration.utils.JsonUtil;
import com.illustration.utils.JwtUtil;
import com.illustration.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.illustration.result.StateMsg.StatusMsg_432;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 登录方法
     * @param loginDto
     * @return
     */
    @Override
    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getAccount(), loginDto.getPassword());
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        }
        catch (AuthenticationException e) {
            throw new CustomException(StatusMsg_432);
        }
        LoginUserVO loginUser = (LoginUserVO)authenticate.getPrincipal();
        loginUser.getUser().setPassword(null);
        Map<String, Object> map = new HashMap<>();
        String uuid = "Login_" + loginUser.getUser().getId();
        map.put("token", uuid);
        // 将用户信息存入Redis
        redisUtil.set(uuid, JsonUtil.serialize(loginUser.getUser()), 7, TimeUnit.DAYS);
        String token = jwtUtil.createToken(map);
        return token;
    }

    @Override
    public User getUser() {
        // 获取Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取用户
        LoginUserVO loginUserVO = (LoginUserVO)authentication.getPrincipal();
        return loginUserVO.getUser();
    }
}
