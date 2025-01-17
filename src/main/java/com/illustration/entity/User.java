package com.illustration.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Long id;    // 主键
    private String userId;   // 用户id
    private String email;   // 邮箱
    private String password;    // 密码
    private String userName;    // 用户名
    private String headPortrait;    // 头像
    private Date createTime;    // 创建时间
    private Date updateTime;    // 修改时间

    private List<Role> roles = new ArrayList<>();
    private List<String> perms = new ArrayList<>();
}
