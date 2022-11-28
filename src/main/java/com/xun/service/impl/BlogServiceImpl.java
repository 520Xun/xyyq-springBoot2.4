package com.xun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.dao.BlogDao;
import com.xun.dao.UserDao;
import com.xun.pojo.BlogUserTypeVo;
import com.xun.pojo.User;
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
                readOnly = false )
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private pageProperties pp;

    @Override
    public JsonResult findBlog ( BlogUserTypeVo blog, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< BlogUserTypeVo > page = PageHelper.startPage ( curPage, pageSize );
        List< BlogUserTypeVo > blogs = blogDao.findBlogs ( blog );
//        blogs.stream ().forEach (e -> {
//            System.out.println (e);
//        });
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( blogs );
        return new JsonResult ( pageObj );
    }

    @Override
    public Integer updateRecommend ( Integer id, String recommend ) {
        Assert.isEmpty ( id == null || id == 0 || recommend == null || recommend.equals ( "" ), "请求参数有误！" );
        Integer userStatus = blogDao.checkRecommend ( id );
        Assert.isEmpty ( userStatus == 0, "当前文章为草稿，不能推荐！" );
        Assert.isEmpty ( userStatus == 1, "当前文章未审核，不能推荐！" );
        Integer n = blogDao.updateRecommend ( id, recommend );
        /**
         * 文章不记录修改者
         */
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer updateEssayGrade ( Integer id, String essayGrade ) {
        Assert.isEmpty ( id == null || id == 0 || essayGrade == null || essayGrade.equals ( "" ), "请求参数有误！" );
        Integer n = blogDao.updateEssayGrade ( id, essayGrade );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer insertBlog ( BlogUserTypeVo vo, Integer[] tagIds ) {
        Assert.isEmpty ( vo == null, "请填写文章信息" );
        Assert.isEmpty ( vo.getTitle ( ) == null, "必须填写文章标题！" );
        Assert.isEmpty ( vo.getContent ( ) == null, "必须填写文章内容！" );
        Assert.isEmpty ( vo.getUser ( ).getAuthorName ( ) == null, "必须填写文章内容！" );
        Assert.isEmpty ( vo.getType ( ).getId ( ) == null, "必须勾选分类！" );
        //判断文章是否已经存在
        //查询出标题和作者名
        BlogUserTypeVo blog = blogDao.findBlogByTitleAndAuthorName ( vo.getTitle ( ), vo.getContent ( ) );
        //验证有无此作者
        List< User > u2 = userDao.findUserByAuthorUser ( blog.getUser ( ).getAuthorName ( ) );
        Assert.isEmpty ( u2.size ( ) == 0, "填写的作者不存在！" );
        Assert.isEmpty ( blog != null && u2.size ( ) != 0, "文章已经存在！" );
        Integer userId = userDao.findUserIdByAuthorUser ( vo.getUser ( ).getAuthorName ( ) );
        int n = blogDao.insertBlog ( vo, userId );

        return null;
    }
}
