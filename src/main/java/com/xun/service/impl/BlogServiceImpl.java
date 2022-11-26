package com.xun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.dao.BlogDao;
import com.xun.pojo.BlogUserTypeVo;
import com.xun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:46
 */
@Service
@Transactional (
        isolation = Isolation.READ_COMMITTED,
        timeout = 100,
        rollbackFor = RuntimeException.class,
        readOnly = false)
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private pageProperties pp;

    @Override
    public JsonResult findBlog (BlogUserTypeVo blog, Integer curPage, Integer pageSize) {
        Assert.isEmpty (curPage == null || pageSize == null, "查询参数异常！！！");
        pageSize = pageSize == 0 ? pp.getPageSize () : pageSize;
        Page<BlogUserTypeVo> page = PageHelper.startPage (curPage, pageSize);
        List<BlogUserTypeVo> blogs = blogDao.findBlogs (blog);
//        blogs.stream ().forEach (e -> {
//            System.out.println (e);
//        });
        Pagination pageObj = new Pagination (curPage, (int) page.getTotal (), pageSize);
        pageObj.setPageData (blogs);
        return new JsonResult (pageObj);
    }
}
