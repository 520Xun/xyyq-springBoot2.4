package com.xun.Myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.BlogUserTypeVo;
import com.xun.sys.pojo.Carousel;
import com.xun.sys.service.BlogService;
import com.xun.sys.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 14:00
 */
@Controller
@RequestMapping ( "myblog" )
public class MyblogPageController {
    //该对象利用CPU的算法保证了线程安全
    private final AtomicLong times = new AtomicLong ( );

    @Autowired
    private CarouselService carouselServiceImp;

    @Autowired
    private BlogService blogServiceImpl;

    public MyblogPageController ( ) {
        System.out.println ( "欢迎访问博客" );
    }

    /**
     * 主页
     * 采用 pagehelper 分页插件
     *
     * @param model
     * @return
     */
    @RequestMapping ( "index" )
    public String webIndexPageUI ( Model model, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage ) {
        long n = times.incrementAndGet ( );//加1的方法
        System.out.println ( "cpu算法" + n );
        PageHelper.startPage ( curPage, 5 );// 自带page对象
        //查询最新文章 （已审核并且没回收的）
        List< BlogUserTypeVo > allFirstPageBlog = blogServiceImpl.findAllFirstPageBlog ( );
        PageInfo< BlogUserTypeVo > pageInfo = new PageInfo<> ( allFirstPageBlog );//分页
        model.addAttribute ( "pageInfo", pageInfo );
        //查询轮播图
        List< Carousel > photo = carouselServiceImp.findShowCarousel ( );
        System.out.println ( photo );
        model.addAttribute ( "carousel", photo );
        //查询推荐文章
        List< Blog > recommendedBlogs = blogServiceImpl.findRecommendBlog ( );
        model.addAttribute ( "recommendedBlogs", recommendedBlogs );
        return "Myblog/index";//前台首页
    }

    @RequestMapping ( "blogInfo/{id}" )
    public String webBlogPageUI ( @PathVariable ( value = "id", required = false ) Integer id, Model model ) {
        System.out.println ( id + "怎么说" );
        BlogUserTypeVo blogInfo = blogServiceImpl.findBlogInfoByBlogId ( id );
        System.out.println ( blogInfo );
        return "Myblog/blog";
    }
}
