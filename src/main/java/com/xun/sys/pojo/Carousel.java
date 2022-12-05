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
    @JsonFormat ( pattern = "yyyy-MM-dd" )
    private Date createdTime;
    @JsonFormat ( pattern = "yyyy-MM-dd" )
    private Date updatedTime;
    private Integer carouselState = 1;//是否展示 1展示 0 不展示
    private Integer deleteState = 1;//是否删除
}
