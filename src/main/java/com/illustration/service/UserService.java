package com.illustration.service;

import com.illustration.entity.dto.LoginDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    boolean addUser(LoginDto loginDto);
}
