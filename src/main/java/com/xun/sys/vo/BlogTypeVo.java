package com.xun.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-12 12:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTypeVo {
    private Integer id;
    private String name;
    private Integer countTypeBlog;//文章的数量
}
