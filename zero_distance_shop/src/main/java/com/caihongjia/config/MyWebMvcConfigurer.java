package com.caihongjia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 注册拦截器,设置拦截路径和不拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/zero/user/login")
                .excludePathPatterns("/zero/user/register");
    }

    // 使用Cors, 解决跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 设置服务器哪些路径可以跨域请求
                .allowedMethods("GET","POST","DELETE","PUT"); // 配置运行跨域访问的方法类型
    }
}
