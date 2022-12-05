package com.xun.sys.dao;

import com.xun.sys.pojo.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 是否展示
     *
     * @param id
     * @param carouselState
     * @return
     */
    @Update ( "update carousel set carousel_state=#{carouselState} where id=#{id} and delete_state=1" )
    int updateCarouselState ( Integer id, String carouselState );

    /**
     * 将轮播图存入回收站
     *
     * @param ids
     * @return
     */
    int deleteCarousel ( Integer[] ids );

    /**
     * 删除轮播图
     *
     * @param ids
     * @return
     */
    int chealCarouse ( Integer[] ids );

    /**
     * 恢复轮播图
     *
     * @param ids
     * @return
     */
    int recoverCarousel ( Integer[] ids );
}
