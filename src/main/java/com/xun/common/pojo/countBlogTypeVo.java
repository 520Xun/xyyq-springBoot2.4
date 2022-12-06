package com.xun.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 20:37
 * <p>
 * 统计分类文章数量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class countBlogTypeVo implements Serializable {
    private String name;
    private String value;
}
