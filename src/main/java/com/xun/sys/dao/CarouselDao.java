package com.xun.sys.dao;

import com.xun.sys.pojo.Carousel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-02 17:47
 * <p>
 * 轮播图
 */
@Mapper
public interface CarouselDao {
    /**
     * 查询所有的轮播图
     *
     * @param c
     * @return
     */
    List< Carousel > findAllCarousel ( Carousel c );
}
