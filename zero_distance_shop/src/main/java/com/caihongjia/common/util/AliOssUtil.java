package com.caihongjia.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes,String objectName){

        // 创建OSSClient 实例
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObject请求
        try{
            build.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        }catch(Exception e){
            System.out.println(e);
        }finally {
            if(build != null){
                build.shutdown();
            }
        }

        // 上传文件
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("文件上传到: {}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
