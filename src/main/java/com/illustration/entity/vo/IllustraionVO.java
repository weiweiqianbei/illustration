package com.illustration.entity.vo;

import com.illustration.entity.User;
import lombok.Data;

@Data
public class IllustraionVO {
    private Long id;    // 作品id
    private Long userId;    //作者
    private String title;   // 标题
    private String path;    // 图片地址
    private String remark;  // 说明

    private User user;
}
