package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.CommentVO;
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
    private CommentService commentService;

    @RequestMapping ( "findComment" )
    public JsonResult findComment ( CommentVO comment, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        return commentService.findComment ( comment, curPage, pageSize );
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
        Integer n = commentService.updateCommentState ( id, commentState );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 删除
     */
    @RequestMapping ( "deleteComment" )
    public JsonResult deleteComment ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = commentService.deleteComment ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已删除！" );
        return jr;
    }

    @RequestMapping ( "findObjectes" )
    public JsonResult findObjectes ( ) {
        List< Map< String, Object > > objects = commentService.findObjects ( );
        return new JsonResult ( objects );
    }
}
