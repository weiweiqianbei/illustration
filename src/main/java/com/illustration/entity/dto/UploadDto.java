package com.illustration.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadDto implements Serializable {
    private String title;
    private String remark;
}
