package com.xun.common.config;

import com.xun.common.util.TxSmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-07 20:32
 * 自动配置类
 * 配置工具类 template
 */
@Configuration
@EnableConfigurationProperties ( { TxProperties.class } )
public class CommonsAutoConfiguration {
    /*
     * 创建发送短信的工具类
     * 将TxProperties对象注入到容器中
     * 要配置CommonsAutoConfiguration到resources/META-INF/spring.factories中
     * */
    @Bean
    public TxSmsTemplate txSmsTemplate ( TxProperties txProperties ) {
        return new TxSmsTemplate ( txProperties );
    }
}
