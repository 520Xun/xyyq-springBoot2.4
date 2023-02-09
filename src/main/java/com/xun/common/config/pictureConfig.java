package com.xun.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-02 10:12
 * 默认头像图片配置
 */
@Data
@ConfigurationProperties ( prefix = "com.xun.picture" )
@Configuration
public class pictureConfig {
    private String userPrctrue;
}
