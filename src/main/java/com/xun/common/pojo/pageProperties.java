package com.xun.common.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xun
 */
@Data
@ConfigurationProperties(prefix = "com.xun.page")
@Configuration//对象创建交给容器管理，加了该注解标识这个类是配置类
public class pageProperties {
    private Integer pageSize;
}
