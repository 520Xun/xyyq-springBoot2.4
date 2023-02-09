package com.xun.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-19 11:26
 * <p>
 * 配置静态资源访问路径
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //静态资源映射

    public void addResourceHandlers ( ResourceHandlerRegistry registry ) {
        //把本地静态资源映射到项目
        try {
            registry.addResourceHandler ( "/images/**" ).addResourceLocations ( "file:E:/images/" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            System.out.print ( "错误映射:" + e );
        }
    }
}
