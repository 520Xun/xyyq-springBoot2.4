package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.sys.dao.CarouselDao;
import com.xun.sys.pojo.Carousel;
import com.xun.sys.service.CarouselService;
import com.xun.sys.vo.BlogUserTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 15:31
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselDao carouselDao;

    @Autowired
    private pageProperties pp;

    @Override
    public JsonResult findAllCarousel ( Carousel carousel, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< BlogUserTypeVo > page = PageHelper.startPage ( curPage, pageSize );
        List< Carousel > blogs = carouselDao.findAllCarousel ( carousel );
//        blogs.stream ().forEach (e -> {
//            System.out.println (e);
//        });
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( blogs );
        return new JsonResult ( pageObj );
    }

    @Override
    public Integer updateCarouselState ( Integer id, String carouselState ) {
        Assert.isEmpty ( id == null, "请选择要修改数据！" );
        Assert.isEmpty ( carouselState == null || carouselState.equals ( "" ), "请求参数异常!" );
        int n = carouselDao.updateCarouselState ( id, carouselState );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer deleteCarousel ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要修改的数据！" );
        int n = carouselDao.deleteCarousel ( ids );
        Assert.isEmpty ( n == 0, "数据已被加入回收站！" );
        return n;
    }

    @Override
    public Integer chealCarouse ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要删除的数据！" );
        int n = carouselDao.chealCarouse ( ids );
        Assert.isEmpty ( n == 0, "数据已被删除！" );
        return n;
    }

    @Override
    public Integer recoverCarousel ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要恢复的数据！" );
        int n = carouselDao.recoverCarousel ( ids );
        Assert.isEmpty ( n == 0, "数据已在列表中！" );
        return n;
    }

    @Override
    public Integer updateCarousel ( Carousel carousel ) {
        Assert.isEmpty ( carousel.getId ( ) == null, "请选择要修改的数据！" );
        int n = carouselDao.updateCarousel ( carousel );
        Assert.isEmpty ( n == 0, "修改失败" );
        return n;
    }

    @Override
    public Integer saveCarousel ( Carousel carousel ) {
        int n = carouselDao.saveCarousel ( carousel );
        Assert.isEmpty ( n == 0, "添加失败" );
        return n;
    }

    @Override
    public List< Carousel > findShowCarousel ( ) {
        List< Carousel > list = carouselDao.findShowCarousel ( );
        Assert.isEmpty ( list == null || list.size ( ) == 0, "无轮播图显示！" );
        return list;
    }
}
