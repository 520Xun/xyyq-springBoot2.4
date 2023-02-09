package com.xun.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-07 20:19
 * 腾讯云发送短信
 * 参数配置类
 **/
@Data
@Configuration
@ConfigurationProperties ( prefix = "com.xun.txsms" )
public class TxProperties {
    // AppId  1400开头的
    private int AppId;
    // 短信应用SDK AppKey
    private String AppKey;
    // 短信模板ID
    private int TemplateId;
    // 签名内容
    private String signName;
}
