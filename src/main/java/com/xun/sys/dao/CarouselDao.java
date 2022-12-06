package com.xun.sys.dao;

import com.xun.sys.pojo.Carousel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 修改轮播图
     *
     * @param carousel
     * @return
     */
    @Update ( "UPDATE `myblog`.`carousel` SET `name` =#{name}, `soft` = #{soft}, `photo` =#{photo}, `carousel_state` = #{carouselState}, `delete_state` = #{deleteState}, `updateTime` = now() WHERE `id` =#{id};" )
    int updateCarousel ( Carousel carousel );

    @Insert ( "INSERT INTO `myblog`.`carousel`(`id`, `name`, `soft`, `photo`, `carousel_state`, `delete_state`, `createdTime`, `updateTime`) VALUES (null, #{name}, #{soft}, #{photo}, #{carouselState}, 1, now(), #{updateTime})" )
    int saveCarousel ( Carousel carousel );

    @Select ( "select name,photo from carousel where delete_state=1 and carousel_state=1 ORDER BY soft desc" )
    List< Carousel > findShowCarousel ( );
}
