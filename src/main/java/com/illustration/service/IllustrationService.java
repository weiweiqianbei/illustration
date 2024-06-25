package com.illustration.service;

import com.illustration.entity.dto.UploadDto;
import com.illustration.entity.vo.IllustraionVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IllustrationService {
    void handleFileUpload(MultipartFile file, UploadDto data);

    List<IllustraionVO> getIllustraions(int page, String key, Long uid);

    Long getIllustraionsCount(String key, Long uid);

    void deleteIllustraion(Long id);
}
