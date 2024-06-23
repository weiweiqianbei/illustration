package com.illustration.service;

import com.illustration.entity.dto.UploadDto;
import org.springframework.web.multipart.MultipartFile;

public interface IllustrationService {
    void handleFileUpload(MultipartFile file, UploadDto data);
}
