package com.illustration.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    private String account;
    private String password;
}
