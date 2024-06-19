package com.illustration.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    private Long id;    // 角色id
    private String  roleLabel;  // 英文名
    private String roleName;    // 角色名
    private String remark;  //备注
    private Date createTime;    // 创建时间
    private Date updateTime;    // 修改时间
}
