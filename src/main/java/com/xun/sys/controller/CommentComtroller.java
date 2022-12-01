package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.CommentVO;
import com.xun.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
