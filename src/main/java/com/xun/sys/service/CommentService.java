package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.vo.CommentVO;
import com.xun.sys.vo.ParentCommentVo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:54
 * <p>
 * 评论接口类
 */
public interface CommentService {
    /**
     * 查询评论
     *
     * @param comment
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult findComment ( CommentVO comment, Integer curPage, Integer pageSize );

    /**
     * 审核
     *
     * @param id
     * @param recommend
     * @return
     */
    Integer updateCommentState ( Integer id, Integer recommend );

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Integer deleteComment ( Integer[] ids );

    /**
     * 查询多级评论
     */
    List< ParentCommentVo > findParentCommentVoList ( Integer id );

    /**
     * 添加评论
     *
     * @param comment
     * @param session
     * @return
     */
    Integer saveComent ( ParentCommentVo comment, HttpSession session );

    Integer deleteCommentByCid ( String id );
}
