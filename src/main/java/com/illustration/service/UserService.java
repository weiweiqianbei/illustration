package com.illustration.service;

import com.illustration.entity.User;
import com.illustration.entity.dto.LoginDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    @Transactional
    boolean addUser(LoginDto loginDto);

    void updateUser(MultipartFile file, User user);

    User getUserById(Long id);
}
