package com.caihongjia.controller;

import com.caihongjia.common.result.Result;
import com.caihongjia.common.util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/images")
@Slf4j
public class ImageController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 上传图片,存储到OSS中
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("图片上传: {}",file);

        // 获得文件名
        String originalFilename = file.getOriginalFilename();
        // 获得文件后缀名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 构建文件新名
        String newName = UUID.randomUUID().toString() + extension;

        String upload = "";

        try{
             upload = aliOssUtil.upload(file.getBytes(), newName);
        }catch(Exception e){
            log.info("文件上传失败: {}",e);
        }

        return Result.success(upload);

    }
}
