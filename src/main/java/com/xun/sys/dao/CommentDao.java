package com.xun.sys.dao;

import com.xun.sys.pojo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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
}
