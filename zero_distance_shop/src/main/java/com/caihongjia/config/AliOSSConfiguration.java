package com.caihongjia.config;


import com.caihongjia.common.util.AliOssUtil;
import com.caihongjia.config.properties.AliOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliOssUtil属性的配置类
 */
@Configuration
@Slf4j
public class AliOSSConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("阿里云OSS实例化");
        return new AliOssUtil(aliOssProperties.getEndpoint(),aliOssProperties.getAccessKeyId()
                ,aliOssProperties.getAccessKeySecret(),aliOssProperties.getBucketName());
    }
}
