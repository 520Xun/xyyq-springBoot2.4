package com.xun.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-02 17:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carousel {
    private Integer id;
    private String name;
    private Integer soft;
    private String photo;//图片
}
