package com.xun.Myblog.controller;

import com.xun.common.util.Assert;
import com.xun.sys.service.BlogService;
import com.xun.sys.service.CommentService;
import com.xun.sys.vo.BlogUserTypeVo;
import com.xun.sys.vo.ParentCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-09 11:07
 * 前台博客评论控制器
 */
@Controller
public class MyblogCommentController {
    @Autowired
    private CommentService commentServiceImpl;
    @Autowired
    private BlogService blogServiceImpl;

    /**
     * 查询评论列表
     *
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping ( "/comments/{blogId}" )
    public String comments ( @PathVariable String blogId, Model model ) {
        List< ParentCommentVo > comments = commentServiceImpl.findParentCommentVoList ( Integer.parseInt ( blogId ) );
        model.addAttribute ( "comments", comments );
        return "blog :: commentList";
    }

    /**
     * 新增评论
     *
     * @param comment
     * @param session
     * @param model
     * @return
     */
    @PostMapping ( "/comments" )
    public String saveComment ( ParentCommentVo comment, HttpSession session, Model model ) {
        Assert.isEmpty ( comment == null, "请求参数异常" );
        commentServiceImpl.saveComent ( comment, session );
        List< ParentCommentVo > list = commentServiceImpl.findParentCommentVoList ( comment.getBlog ( ).getId ( ) );
        model.addAttribute ( "comments", list );//重新查询文章对应的评论信息
        return "Myblog/blog :: commentList";//局部刷新
    }

    //删除评论
    @RequestMapping ( "/comment/{blogId}/{id}/delete" )
    public String delete ( @PathVariable String blogId, @PathVariable String id, HttpSession session, Model model ) {
        /**
         *  是否为在线用户
         */
//        User user = ( User ) session.getAttribute ( "user" );
//        if ( user != null ) {
        commentServiceImpl.deleteCommentByCid ( id );
//        }
        BlogUserTypeVo blogInfo = blogServiceImpl.findBlogInfoByBlogId ( blogId );
        List< ParentCommentVo > comments = commentServiceImpl.findParentCommentVoList ( Integer.parseInt ( blogId ) );
        model.addAttribute ( "blogInfo", blogInfo );
        model.addAttribute ( "comments", comments );
        return "Myblog/blog";
    }

}
