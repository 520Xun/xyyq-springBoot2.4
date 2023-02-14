package com.xun.Myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.Carousel;
import com.xun.sys.pojo.NewComment;
import com.xun.sys.pojo.memoryBlog;
import com.xun.sys.service.*;
import com.xun.sys.vo.BlogTypeVo;
import com.xun.sys.vo.BlogUserTypeVo;
import com.xun.sys.vo.ParentCommentVo;
import com.xun.sys.vo.ParentMessageVo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j //日志打印
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

    @Autowired
    private MessageService messageServiceImpl;


    public MyblogPageController ( ) {
        System.out.println ( "欢迎访问xunblog星球" );
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
        System.out.println ( "前台页面cpu算法" + n );
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
        //查询最新评论
        List< NewComment > newComments = commentServieImpl.findNewComment ( );
        model.addAttribute ( "newComment", newComments );
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
        List< ParentCommentVo > list = commentServieImpl.findParentCommentVoList ( Integer.parseInt ( id ) );
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
    public String blogType ( Model model, @RequestParam ( required = false, defaultValue = "1", name = "curPage" ) Integer curPage, @PathVariable ( name = "id" ) String id ) {
        //查询所有分类
        List< BlogTypeVo > allType = typeServiceImpl.findAllTypeAndBlog ( );
        //为0代表 从首页跳转到了分类页面
        if ( id.equals ( "0" ) ) {
            if ( ! allType.isEmpty ( ) ) {
                id = allType.get ( 0 ).getId ( ) + "";
            }
        }
        model.addAttribute ( "types", allType );
        PageHelper.startPage ( curPage, 100 );
        //根据分类查询文章
        List< BlogUserTypeVo > blogInfo = blogServiceImpl.findBlogByTypeId ( id );
        PageInfo< BlogUserTypeVo > pageInfo = new PageInfo<> ( blogInfo );
        model.addAttribute ( "blogInfo", pageInfo );
        model.addAttribute ( "activeTypeId", Integer.parseInt ( id ) );
        return "Myblog/types";
    }

    /**
     * 搜索博客信息
     */
    @RequestMapping ( "/search" )
    public String search ( Model model, @RequestParam ( defaultValue = "1", value = "curPage" ) Integer curPage, @RequestParam ( required = false ) String query ) {
        PageHelper.startPage ( curPage, 1 );
        List< BlogUserTypeVo > searchBlog = blogServiceImpl.getSearchBlog ( query );

        PageInfo< BlogUserTypeVo > pageInfo = new PageInfo<> ( searchBlog );
        model.addAttribute ( "blogInfo", pageInfo );
        model.addAttribute ( "query", query );
        return "Myblog/search";
    }

    /**
     * 跳转到音乐盒
     */
    @RequestMapping ( "music" )
    public String musicPageUI ( ) {
        return "Myblog/music";
    }

    /**
     * 留言，问答页面
     *
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping ( value = "message" )
    public String messagePageUI ( Model model, @RequestParam ( defaultValue = "1", value = "pageNum" ) Integer pageNum ) {
        PageHelper.startPage ( pageNum, 10 );
        List< ParentMessageVo > messageVos = messageServiceImpl.listMessageVoList ( );
        PageInfo< ParentMessageVo > pageInfo = new PageInfo<> ( messageVos );
        model.addAttribute ( "messages", pageInfo );
        return "Myblog/message";
    }

    @RequestMapping ( "archives" )
    public String archivesPageUI ( Model model, @RequestParam ( defaultValue = "1", value = "pageNum" ) Integer pageNum ) {
        /**
         *  根据当前在线用户id查询用户的文章发布信息
         */
        PageHelper.startPage ( pageNum, 10 );
        List< memoryBlog > listMemory = blogServiceImpl.findListMemory ( 39 );
        listMemory.forEach ( System.out :: println );
        PageInfo< memoryBlog > pageList = new PageInfo<> ( listMemory );
        model.addAttribute ( "memorys", pageList );
        return "Myblog/archives";
    }

}
