package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-14 20:30
 * 记录每个用户发布文章的历史
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class memoryBlog {
    private Integer id;
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date publishDate;
    private String title;
    private String blogCover;//文章封面
    private User user;
}