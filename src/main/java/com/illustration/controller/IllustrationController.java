package com.illustration.controller;

import com.illustration.entity.dto.UploadDto;
import com.illustration.result.ReturnValue;
import com.illustration.service.IllustrationService;
import com.illustration.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
        map.put("illustraions", illustrationService.getIllustraions(p, null, null));
        map.put("total", illustrationService.getIllustraionsCount(null, null));
        map.put("currentPage", p);
        return new ReturnValue(StatusMsg_200, map);
    }

    @GetMapping("/{findkey}")
    public ReturnValue<Map> getIllustrationsByKey(@PathVariable String findkey, @RequestParam int p) {
        log.info("parameter================》{}, {}", findkey, p);
        findkey = "%" + findkey + "%";
        HashMap<String, Object> map = new HashMap<>();
        map.put("illustraions", illustrationService.getIllustraions(p, findkey, null));
        map.put("total", illustrationService.getIllustraionsCount(findkey, null));
        map.put("currentPage", p);
        return new ReturnValue<>(StatusMsg_200, map);
    }

    @GetMapping("/users/{uid}")
    public ReturnValue<Map> getIllustrationsByUser(@PathVariable Long uid, @RequestParam int p) {
        log.info("parameter================》{}, {}", uid, p);
        HashMap<String, Object> map = new HashMap<>();
        map.put("illustraions", illustrationService.getIllustraions(p, null, uid));
        map.put("total", illustrationService.getIllustraionsCount(null, uid));
        map.put("currentPage", p);
        return new ReturnValue<>(StatusMsg_200, map);
    }

    @DeleteMapping("/{id}")
    public ReturnValue deleteIllustrations(@PathVariable Long id) {
        log.info("parameter================》{}", id);
        illustrationService.deleteIllustraion(id);
        return new ReturnValue();
    }
}
