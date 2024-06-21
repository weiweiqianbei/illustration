package com.illustration.service.impl;

import com.illustration.dao.UserDao;
import com.illustration.entity.User;
import com.illustration.entity.dto.LoginDto;
import com.illustration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(LoginDto loginDto) {
        if (!loginDto.getAccount().matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+$")){
            return false;
        }
        if (userDao.selectUserByAccount(loginDto.getAccount(), 0) != null) {
            return false;
        }
        String key;
        do {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            key = uuid.substring(0, 8);
        } while (userDao.selectUserByAccount("user"+key, 1) != null);

        String password = passwordEncoder.encode(loginDto.getPassword());
        User user = new User();
        user.setEmail(loginDto.getAccount());
        user.setPassword(password);
        user.setUserId("user_" + key);
        user.setUserName("用户" + key);
        return userDao.insertUser(user) && userDao.insertUserRole(user);
    }
}
