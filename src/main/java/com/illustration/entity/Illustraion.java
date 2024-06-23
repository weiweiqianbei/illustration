package com.illustration.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Illustraion implements Serializable {
    private Long id;    // 作品id
    private Long userId;    //作者
    private String title;   // 标题
    private String path;    // 图片地址
    private String remark;  // 说明
}
