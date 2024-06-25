package com.illustration.service.impl;

import com.illustration.dao.UserDao;
import com.illustration.entity.User;
import com.illustration.entity.dto.LoginDto;
import com.illustration.exception.CustomException;
import com.illustration.service.AuthService;
import com.illustration.service.UserService;
import com.illustration.utils.JsonUtil;
import com.illustration.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthService authService;
    @Autowired
    private RedisUtil redisUtil;

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

    @Override
    public void updateUser(MultipartFile file, User user) {
        try {
            if (file == null || file.getSize() == 0) {
                user.setHeadPortrait(authService.getUser().getHeadPortrait());
            } else {
                String uploadDirectory = "D:/Users/伟伟前辈/Poject/illustration/illustration-page/public/";
                String nextDirectory = "images/header/";
                String httpHeard = "http://localhost/";
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String filename = file.getOriginalFilename();
                int dotIndex = filename.lastIndexOf('.');
                String string = (dotIndex == -1) ? "" : filename.substring(dotIndex);
                if (!".jpg".equals(string) && !".png".equals(string) && !".gif".equals(string)) {
                    throw new CustomException(500, "图片格式不正确");
                }
                filename = authService.getUser().getId() + string;
                String filePath = uploadDirectory + nextDirectory + filename;
                file.transferTo(new File(filePath));
                user.setHeadPortrait(httpHeard + nextDirectory + filename);
            }
            userDao.updateUser(user);
            String uuid = "Login_" + user.getId();
            redisUtil.set(uuid, JsonUtil.serialize(user), 7, TimeUnit.DAYS);
        } catch (IOException e) {
            throw new CustomException(500, "图片上传失败");
        }

    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
