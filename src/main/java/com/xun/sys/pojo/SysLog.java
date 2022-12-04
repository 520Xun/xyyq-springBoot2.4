package com.xun.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//日志类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {
    /**
     * 序列化常量
     */
    private static final long serialVersionUID = - 3874082614385402572L;
    private int id;
    private String username;//用户名
    private String operation;//用户操作
    private String method;//请求方法
    private String params;//请求参数
    private Long time;//执行时常
    private String ip;//ip地址
    private Date createdTime;//创建时间


}
