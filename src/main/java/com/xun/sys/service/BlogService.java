package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.countBlogTypeVo;
import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.BlogUserTypeVo;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:42
 */
public interface BlogService {
    /**
     * 统计分类文章数量
     *
     * @return
     */
    List< countBlogTypeVo > countBlogType ( );

    /**
     * 查询文章列表 按条件并且分页
     *
     * @param blog
     * @return
     */
    JsonResult findBlog ( BlogUserTypeVo blog, Integer curPage, Integer pageSize );

    /**
     * 修改文章是否推荐
     *
     * @param id
     * @param recommend
     * @return
     */
    Integer updateRecommend ( Integer id, String recommend );

    /**
     * 修改评分 文章推荐，可以根据评分进行排序
     *
     * @param id
     * @param essayGrade
     * @return
     */
    Integer updateEssayGrade ( Integer id, String essayGrade );

    /**
     * 新增博客
     *
     * @param vo
     * @param tagIds 标签id
     * @return
     */
    Integer insertBlog ( BlogUserTypeVo vo, Integer[] tagIds );

    /**
     * 将数据存入回收站
     *
     * @param ids
     * @return
     */
    int deleteBlog ( Integer[] ids );

    /**
     * 彻底删除文章
     *
     * @param ids
     * @return
     */
    Integer chealBlog ( Integer[] ids );

    /**
     * 恢复文章
     *
     * @param ids
     * @return
     */
    Integer recoverBlog ( Integer[] ids );

    /**
     * 修改文章
     *
     * @param vo
     * @param tagIds
     * @return
     */
    Integer updateBlog ( BlogUserTypeVo vo, Integer[] tagIds );

    /**
     * 审核文章
     *
     * @param id
     * @param essayStatus
     * @param recommend
     * @return
     */
    Integer updateEssayStatus ( Integer id, Integer essayStatus, Integer recommend );

    /**
     * 网站查询推荐文章
     * 根据推荐跟评分排序
     *
     * @return
     */
    List< Blog > findRecommendBlog ( );

    /**
     * 查询主页最新文章
     * 已审核，未删除
     * 根据发布时间跟评分排序
     *
     * @return
     */
    List< BlogUserTypeVo > findAllFirstPageBlog ( );

    /**
     * 根据文章id 查询所有文章信息
     *
     * @param id
     * @return
     */
    BlogUserTypeVo findBlogInfoByBlogId ( String id );

    /**
     * 根据类型id 查询文章信息显示在分类页面
     *
     * @param id
     * @return
     */
    List< BlogUserTypeVo > findBlogByTypeId ( String id );

    /**
     * 搜索博客信息
     *
     * @param query
     * @return
     */
    List< BlogUserTypeVo > getSearchBlog ( String query );
}
