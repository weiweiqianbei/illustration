package com.illustration.service;

import com.illustration.entity.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
