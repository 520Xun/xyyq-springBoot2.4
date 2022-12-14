package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 11:50
 * 留言
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class message {
    private Integer id;
    private User user;
    private Integer parentMessageId;
    private String contant;//内容
    private String messageIp;
    private Integer messageState;//审核
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String createdTime;//创建时间
}
