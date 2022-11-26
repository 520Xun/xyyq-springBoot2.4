package com.xun.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.pojo.BlogUserTypeVo;
import com.xun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:51
 */
@RestController
@RequestMapping ("blog")
public class BlogController {

    @Autowired
    private BlogService blogServiceImpl;

    @RequestMapping ("findBlog")
    public JsonResult findBlog (BlogUserTypeVo blog, @RequestParam (required = false, defaultValue = "1") Integer curPage, @RequestParam (required = false, defaultValue = "5") Integer pageSize) {
        JsonResult jr = blogServiceImpl.findBlog (blog, curPage, pageSize);
        return jr;
    }
}
