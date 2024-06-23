package com.illustration.service.impl;

import com.illustration.dao.IllustraionDao;
import com.illustration.entity.Illustraion;
import com.illustration.entity.dto.UploadDto;
import com.illustration.exception.CustomException;
import com.illustration.service.AuthService;
import com.illustration.service.IllustrationService;
import com.illustration.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class IllustrationServiceImpl implements IllustrationService {
    @Autowired
    private AuthService authService;
    @Autowired
    private IllustraionDao illustraionDao;

    @Override
    public void handleFileUpload(MultipartFile file, UploadDto data) {
        String uploadDirectory = "D:/Users/伟伟前辈/Poject/illustration/illustration-page/public/";
        String nextDirectory = "images/illustration/";
        String httpHeard = "http://localhost/";

        Illustraion illustraion = new Illustraion();
        Long userId = authService.getUser().getId();
        try {
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String filename = file.getOriginalFilename();
            int dotIndex = filename.lastIndexOf('.');
            String string = (dotIndex == -1) ? "" : filename.substring(dotIndex);
            if (!".jpg".equals(string) && !".png".equals(string) && !".gif".equals(string)) {
                throw new CustomException(500, "图片格式不正确");
            }
            filename = RandomUtil.randomNumbers(6) + string;
            String filePath = uploadDirectory + nextDirectory + filename;
            log.info("fileName===========>{}", filePath);
            file.transferTo(new File(filePath));

            illustraion.setUserId(userId);
            illustraion.setPath(httpHeard + nextDirectory + filename);
            illustraion.setTitle(data.getTitle());
            illustraion.setRemark(data.getRemark());
            illustraionDao.addIllustraion(illustraion);
        } catch (IOException e) {
            throw new CustomException(500, "图片上传失败");
        }
    }
}
