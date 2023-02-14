package com.xun.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-13 10:24
 * 查询最新的评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewComment {
    private Integer id;
    private Blog blog;
    private String contant;

}
