package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.countBlogTypeVo;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.sys.dao.BlogDao;
import com.xun.sys.dao.BlogTagDao;
import com.xun.sys.dao.CommentDao;
import com.xun.sys.dao.UserDao;
import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.User;
import com.xun.sys.service.BlogService;
import com.xun.sys.vo.BlogUserTypeVo;
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
    private BlogTagDao blogTagDao;

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private pageProperties pp;

    @Override
    public List< countBlogTypeVo > countBlogType ( ) {
        List< countBlogTypeVo > blogTypeVos = blogDao.countBlogType ( );
        return blogTypeVos;
    }

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
        //     Assert.isEmpty ( userStatus == 3, "当前文章已被回收，不能推荐！" );
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
        List< User > u2 = userDao.findUserByAuthorUser ( vo.getUser ( ).getAuthorName ( ) );
        Assert.isEmpty ( u2.size ( ) == 0, "填写的作者不存在！" );
        Assert.isEmpty ( blog != null && u2.size ( ) != 0, "文章已经存在！" );
        Integer userId = userDao.findUserIdByAuthorUser ( vo.getUser ( ).getAuthorName ( ) );
        int n = blogDao.insertBlog ( vo, userId );
        //插入文章与标签之间的关系
        blogTagDao.insertBlogTag ( vo.getId ( ), tagIds );
        return n;
    }

    @Override
    public int deleteBlog ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要删除的数据" );
        int n = blogDao.deleteBlogByIds ( ids );
        Assert.isEmpty ( n == 0, "数据已被加入回收站！" );
        return n;
    }

    @Override
    public Integer chealBlog ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要删除的数据" );
        int n = blogDao.chealBlog ( ids );
        Assert.isEmpty ( n == 0, "数据已被删除！" );
        return n;
    }

    @Override
    public Integer recoverBlog ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要恢复的数据" );
        int n = blogDao.recoverBlog ( ids );
        Assert.isEmpty ( n == 0, "数据已被恢复！" );
        return n;
    }

    @Override
    public Integer updateBlog ( BlogUserTypeVo vo, Integer[] tagIds ) {
        Assert.isEmpty ( vo == null, "请填写文章信息" );
        Assert.isEmpty ( vo.getTitle ( ) == null, "必须填写文章标题！" );
        Assert.isEmpty ( vo.getContent ( ) == null, "必须填写文章内容！" );
        Assert.isEmpty ( vo.getUser ( ).getAuthorName ( ) == null, "必须填写文章内容！" );
        Assert.isEmpty ( vo.getType ( ).getId ( ) == null, "必须勾选分类！" );
        //判断文章是否已经存在
        //查询出标题和作者名
        BlogUserTypeVo blog = blogDao.findBlogByTitleAndAuthorName ( vo.getTitle ( ), vo.getContent ( ) );
        //验证有无此作者
        List< User > u2 = userDao.findUserByAuthorUser ( vo.getUser ( ).getAuthorName ( ) );
        Assert.isEmpty ( u2.size ( ) == 0, "填写的作者不存在！" );
        //  Assert.isEmpty ( blog != null && u2.size ( ) != 0, "文章已经存在！" );
        blogTagDao.deteleBlogTagByBlogId ( vo.getId ( ) );
        blogTagDao.insertBlogTag ( vo.getId ( ), tagIds );
        int n = blogDao.updateBlog ( vo );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer updateEssayStatus ( Integer id, Integer essayStatus, Integer recommend ) {
        Assert.isEmpty ( id == null, "请选择要修改的数据！" );
        Assert.isEmpty ( essayStatus == null, "参数异常！" );
        int n = blogDao.updateEssayStatus ( id, essayStatus, recommend );
        Assert.isEmpty ( n == 0, "当前状态不能审批！" );
        return n;
    }

    @Override
    public List< Blog > findRecommendBlog ( ) {
        List< Blog > blogList = blogDao.findRecommendBlog ( );
        return blogList;
    }

    @Override
    public List< BlogUserTypeVo > findAllFirstPageBlog ( ) {
        return blogDao.findAllFirstPageBlog ( );
    }

    @Override
    public BlogUserTypeVo findBlogInfoByBlogId ( String id ) {
        Assert.isEmpty ( id == null || id.equals ( "" ), "请求参数有误！" );
        BlogUserTypeVo blogInfo = blogDao.findBlogInfoByBlogId ( id );
        //获取文章的评论数量
        Integer countNumber = commentDao.countByBlogId ( blogInfo.getId ( ) );
        blogInfo.setCountComment ( countNumber );
        //获取文章的标签
        List< String > BlogTags = blogTagDao.findTagInfoByBlogId ( blogInfo.getId ( ) );
        blogInfo.setBlogTags ( BlogTags );
        Assert.isEmpty ( blogInfo == null || blogInfo.getClass ( ) == null, "文章拔腿跑了！" );
        return blogInfo;
    }

    @Override
    public List< BlogUserTypeVo > findBlogByTypeId ( String id ) {
        Assert.isEmpty ( id == null || id.equals ( "" ), "请求参数异常！" );
        return blogDao.findBlogByTypeId ( id );
    }

    @Override
    public List< BlogUserTypeVo > getSearchBlog ( String query ) {
        return blogDao.getSearchBlog ( query );
    }
}
