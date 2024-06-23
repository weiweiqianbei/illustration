package com.illustration.controller;

import com.illustration.entity.dto.UploadDto;
import com.illustration.result.ReturnValue;
import com.illustration.service.IllustrationService;
import com.illustration.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/illustration")
@Slf4j
public class IllustrationController {
    @Autowired
    private IllustrationService illustrationService;

    @PostMapping("/upload")
    public ReturnValue handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) {
        UploadDto uploadData = JsonUtil.deserialize(data, UploadDto.class);
        log.info("parameter================》{}", uploadData);
        illustrationService.handleFileUpload(file, uploadData);
        return new ReturnValue();
    }
}
