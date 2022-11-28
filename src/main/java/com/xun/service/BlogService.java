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
}
