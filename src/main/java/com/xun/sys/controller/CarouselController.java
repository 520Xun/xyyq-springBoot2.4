package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Carousel;
import com.xun.sys.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 15:27
 * <p>
 * 轮播图控制类
 */
@RestController
@RequestMapping ( "carousel" )
public class CarouselController {
    @Autowired
    private CarouselService carouselServiceImpl;

    /**
     * 查询所有的轮播图
     *
     * @param carousel
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping ( "findAllCarousel" )
    public JsonResult findAllCarousel ( Carousel carousel, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        return carouselServiceImpl.findAllCarousel ( carousel, curPage, pageSize );
    }

    /**
     * 是否展示修改
     */
    @RequestMapping ( "updateCarouselState" )
    public JsonResult updateCarouselState ( Integer id, String carouselState ) {
        Integer n = carouselServiceImpl.updateCarouselState ( id, carouselState );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 轮播图存入回收站
     *
     * @param ids
     * @return
     */
    @RequestMapping ( "deleteCarousel" )
    public JsonResult deleteCarousel ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        Integer n = carouselServiceImpl.deleteCarousel ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已加入回收站！" );
        return jr;
    }

    /**
     * 删除轮播图
     *
     * @param ids
     * @return
     */
    @RequestMapping ( "chealCarouse" )
    public JsonResult chealCarouse ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        Integer n = carouselServiceImpl.chealCarouse ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已彻底删除！" );
        return jr;
    }

    @RequestMapping ( "recoverCarousel" )
    public JsonResult recoverCarousel ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        Integer n = carouselServiceImpl.recoverCarousel ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已恢复到列表！" );
        return jr;
    }
}
