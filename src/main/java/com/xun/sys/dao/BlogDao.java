package com.xun.sys.dao;

import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.BlogUserTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 20:51
 * <p>
 * 博文持久层
 */
@Mapper
public interface BlogDao {
    @Select ( "select * from blog where id=#{id}" )
    Blog findBlogById ( int id );

    /**
     * 查询文章 （按标题查）
     *
     * @param blog
     * @return
     */
    List< BlogUserTypeVo > findBlogs ( BlogUserTypeVo blog );

    /**
     * 根据id 查看博文的状态
     *
     * @param id
     * @return
     */
    @Select ( "select  essayStatus  from blog where id=#{id}" )
    Integer checkRecommend ( Integer id );

    /**
     * 修改是否推荐
     * 不需要记录修改时间
     *
     * @param id
     * @param recommend
     * @return
     */
    @Update ( "update blog set recommend=#{recommend} where id=#{id}" )
    Integer updateRecommend ( Integer id, String recommend );

    /**
     * 修改博文评分
     * 不需要记录修改时间
     *
     * @param id
     * @param essayGrade
     * @return
     */
    @Update ( "update blog set essayGrade=#{essayGrade} where id=#{id} " )
    Integer updateEssayGrade ( Integer id, String essayGrade );

    /**
     * 验证文章是否存在
     * 根据文章标题与文章内容去匹配
     *
     * @param title
     * @param content
     * @return
     */
    BlogUserTypeVo findBlogByTitleAndAuthorName ( @Param ( "title" ) String title, @Param ( "content" ) String content );

    /**
     * 插入文章
     *
     * @param vo
     * @param userId
     * @return
     */
    int insertBlog ( @Param ( "blog" ) BlogUserTypeVo vo, @Param ( "userId" ) Integer userId );

    /**
     * 将数据存入回收站
     *
     * @param ids
     * @return
     */
    int deleteBlogByIds ( Integer[] ids );

    /**
     * 删除文章
     *
     * @param ids
     * @return
     */
    int chealBlog ( Integer[] ids );

    /**
     * 恢复文章
     *
     * @param ids
     * @return
     */
    int recoverBlog ( Integer[] ids );

    /**
     * 修改文章
     *
     * @param vo
     * @return
     */
    int updateBlog ( BlogUserTypeVo vo );

    /**
     * 审核文章
     *
     * @param id
     * @param essayStatus
     * @param recommend
     * @return
     */
    @Update ( "update blog set essayStatus=#{essayStatus} where id=#{id}" )
    int updateEssayStatus ( Integer id, Integer essayStatus, Integer recommend );
}
