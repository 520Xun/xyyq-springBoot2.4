package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.sys.dao.CommentDao;
import com.xun.sys.pojo.BlogUserTypeVo;
import com.xun.sys.pojo.CommentVO;
import com.xun.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:56
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private pageProperties pp;


    @Override
    public JsonResult findComment ( CommentVO comment, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< BlogUserTypeVo > page = PageHelper.startPage ( curPage, pageSize );
        List< CommentVO > blogs = commentDao.findComment ( comment );
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( blogs );
        return new JsonResult ( pageObj );
    }

    @Override
    public Integer updateCommentState ( Integer id, Integer commentState ) {
        Assert.isEmpty ( id == null || commentState == null, "请求参数异常" );
        Integer n = commentDao.updateCommentState ( id, commentState );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer deleteComment ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "至少选择一条数据删除！" );
        int n = commentDao.deleteComment ( ids );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public List< Map< String, Object > > findObjects ( ) {
        List< Map< String, Object > > map = commentDao.findObjects ( );
        return map;
    }
}
