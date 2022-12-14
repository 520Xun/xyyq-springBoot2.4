package com.xun.sys.dao;

import com.xun.sys.vo.CommentVO;
import com.xun.sys.vo.ParentCommentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:15
 */
@Mapper
public interface CommentDao {
    /**
     * 查询所有评论
     *
     * @param vo
     * @return
     */
    List< CommentVO > findComment ( CommentVO vo );

    /**
     * 查询所有评论 及父评论
     *
     * @return
     */
    List< Map< String, Object > > findObjects ( );


    /**
     * 审核
     *
     * @param id
     * @param commentState
     * @return
     */
    @Update ( "update comment set commentState=#{commentState} where id=#{id}" )
    Integer updateCommentState ( Integer id, Integer commentState );

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteComment ( Integer[] ids );

    /**
     * 查询父评论
     *
     * @param blogId
     * @param parentId
     * @return
     */
    List< ParentCommentVo > findByBlogIdParentIdNull ( @Param ( "blogId" ) Integer blogId, @Param ( "parentId" ) Integer parentId );

    /**
     * 查询一级评论
     *
     * @param blogId
     * @param parentId
     * @return
     */
    List< ParentCommentVo > findByBlogIdParentIdNotNull ( @Param ( "blogId" ) Integer blogId, @Param ( "parentId" ) Integer parentId );

    /**
     * 根据子一级评论的id找到子二级评论
     */
    List< ParentCommentVo > findByBlogIdAndReplayId ( @Param ( "blogId" ) Integer blogId, @Param ( "parentId" ) Integer parentId );

    /**
     * 提交评论
     *
     * @param comment
     * @return
     */
    Integer saveComment ( ParentCommentVo comment );

    /**
     * 根据父评论id查找评论信息
     *
     * @param parentId
     * @return
     */
    List< ParentCommentVo > findCommentInfoByParentId ( Integer parentId );

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @Delete ( " delete from comment  where id = #{id}" )
    int deleteCommentByCid ( String id );

    /**
     * 统计文章评论数量
     *
     * @param blogId
     * @return
     */
    @Select ( "select count(1) from `comment` where blog_id=#{blogId}" )
    Integer countByBlogId ( Integer blogId );

    @Delete ( " delete from comment  where parentId = #{id}" )
    Integer deleteCommentByReplay ( String id );
}
