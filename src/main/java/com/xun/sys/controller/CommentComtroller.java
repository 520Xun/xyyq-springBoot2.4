package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.CommentVO;
import com.xun.sys.pojo.ParentCommentVo;
import com.xun.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 17:00
 */
@RestController
@RequestMapping ( "comment" )
public class CommentComtroller {
    @Autowired
    private CommentService CommentServiceImpl;

    /**
     * 测试根据文章id查询评论
     *
     * @return
     */
    @RequestMapping ( "findALL" )
    public JsonResult findAll ( ) {
        Integer id = 10;
        List< ParentCommentVo > list = CommentServiceImpl.commentVoTest ( id );
        return new JsonResult ( list );
    }

    @RequestMapping ( "findComment" )
    public JsonResult findComment ( CommentVO comment, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        return CommentServiceImpl.findComment ( comment, curPage, pageSize );
    }

    /**
     * 审核
     *
     * @param id
     * @param commentState
     * @return
     */
    @RequestMapping ( "updateCommentState" )
    public JsonResult updateCommentState ( Integer id, Integer commentState ) {
        Integer n = CommentServiceImpl.updateCommentState ( id, commentState );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 删除
     */
    @RequestMapping ( "deleteComment" )
    public JsonResult deleteComment ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = CommentServiceImpl.deleteComment ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已删除！" );
        return jr;
    }

    @RequestMapping ( "findObjectes" )
    public JsonResult findObjectes ( ) {
        List< Map< String, Object > > objects = CommentServiceImpl.findObjects ( );
        return new JsonResult ( objects );
    }
}
