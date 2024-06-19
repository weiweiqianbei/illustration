package com.illustration.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Menu implements Serializable {
    private Long id;    // id
    private Long parentId;  // 父类id
    private String menuName;    // 菜单名称
    private String perms;   // 权限标识
    private Integer menuType;   // 菜单类型
    private String path;    // 路由路径
    private String componentPath;   // 组件路径
    private Integer nonLocked;  // 锁定
    private Date createTime;    // 创建时间
    private Date updateTime;    // 更新时间
}
