package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Carousel;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 15:28
 */
public interface CarouselService {
    /**
     * 查询所有的轮播图
     *
     * @param carousel
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult findAllCarousel ( Carousel carousel, Integer curPage, Integer pageSize );

    /**
     * 是否展示
     *
     * @param id
     * @param carouselServiceImpl
     * @return
     */
    Integer updateCarouselState ( Integer id, String carouselServiceImpl );

    /**
     * 将轮播如存入回收站
     *
     * @param ids
     * @return
     */
    Integer deleteCarousel ( Integer[] ids );

    /**
     * 删除轮播图
     *
     * @param ids
     * @return
     */
    Integer chealCarouse ( Integer[] ids );

    /**
     * 恢复轮播图
     *
     * @param ids
     * @return
     */
    Integer recoverCarousel ( Integer[] ids );
}
