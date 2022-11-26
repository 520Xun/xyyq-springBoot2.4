package com.xun.service;

import com.xun.common.pojo.JsonResult;
import com.xun.pojo.BlogUserTypeVo;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:42
 */
public interface BlogService {
    /**
     * 查询文章列表 按条件并且分页
     *
     * @param blog
     * @return
     */
    JsonResult findBlog (BlogUserTypeVo blog, Integer curPage, Integer pageSize);
}
