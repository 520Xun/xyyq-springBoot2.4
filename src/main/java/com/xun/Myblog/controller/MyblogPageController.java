package com.xun.Myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xun.sys.pojo.*;
import com.xun.sys.service.*;
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

    @Autowired
    private CommentService commentServieImpl;

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;

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
        PageHelper.startPage ( curPage, 1 );// 自带page对象
        //查询最新文章 （已审核并且没回收的）
        List< BlogUserTypeVo > allFirstPageBlog = blogServiceImpl.findAllFirstPageBlog ( );
        PageInfo< BlogUserTypeVo > pageInfo = new PageInfo<> ( allFirstPageBlog );//分页
        model.addAttribute ( "pageInfo", pageInfo );
        //查询轮播图 (已审核的)
        List< Carousel > photo = carouselServiceImp.findShowCarousel ( );
        model.addAttribute ( "carousel", photo );
        //查询推荐文章
        List< Blog > recommendedBlogs = blogServiceImpl.findRecommendBlog ( );
        model.addAttribute ( "recommendedBlogs", recommendedBlogs );
        return "Myblog/index";//前台首页
    }

    /**
     * 文章详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping ( "blogInfo/{id}" )
    public String BlogInfoPage ( @PathVariable String id, Model model ) {
        BlogUserTypeVo blogInfo = blogServiceImpl.findBlogInfoByBlogId ( id );
        List< ParentCommentVo > list = commentServieImpl.commentVoTest ( Integer.parseInt ( id ) );
        model.addAttribute ( "blogInfo", blogInfo );//绑定博文信息
        model.addAttribute ( "comments", list );//文章对应的评论信息
        return "Myblog/blog";
    }

    /**
     * 分页查询分类
     *
     * @param model
     * @param curPage
     * @param id
     * @return
     */
    @RequestMapping ( "type/{id}" )
    public String blogType ( Model model, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @PathVariable String id ) {
        //查询所有分类
        List< Type > allType = typeServiceImpl.findAllType ( );
        if ( id != null || id.equals ( "0" ) ) {
            if ( ! allType.isEmpty ( ) ) {
                id = allType.get ( 0 ).getId ( ) + "";
            }
        }
        List< BlogUserTypeVo > blogInfo = blogServiceImpl.findBlogByTypeId ( id );
        return "Myblog/types";
    }
}
