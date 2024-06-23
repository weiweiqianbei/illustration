package com.illustration.controller;

import com.illustration.entity.dto.UploadDto;
import com.illustration.entity.vo.IllustraionVO;
import com.illustration.result.ReturnValue;
import com.illustration.service.IllustrationService;
import com.illustration.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.illustration.result.StateMsg.StatusMsg_200;

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

    @GetMapping
    public ReturnValue<Map> getIllustrations(@RequestParam int p) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("illustraions", illustrationService.getIllustraions(p));
        map.put("total", illustrationService.getIllustraionsCount());
        map.put("currentPage", p);
        return new ReturnValue(StatusMsg_200, map);
    }
}
