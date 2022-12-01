package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:56
 */
@RestController
@RequestMapping ( "blogTag" )
public class BlogTagController {
    @Autowired
    private BlogTagService blogTagServiceImpl;

    @RequestMapping ( "findTagByBlogId" )
    public JsonResult findTagByBlogId ( Integer id ) {
        List< Integer > list = blogTagServiceImpl.findTagByBlogId ( id );
        return new JsonResult ( list );
    }

}
