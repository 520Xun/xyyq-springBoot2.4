package com.xun.sys.service;

import com.xun.sys.vo.ParentMessageVo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 14:19
 */
public interface MessageService {
    /**
     * 查询所有的留言
     *
     * @return
     */
    List< ParentMessageVo > listMessageVoList ( );

    /**
     * 添加留言
     *
     * @param messageVo
     * @param session
     * @return
     */
    Integer saveMessage ( ParentMessageVo messageVo, HttpSession session );

    /**
     * 根据用户id 和 留言id 删除留言
     *
     * @param id
     * @param userId
     * @return
     */
    Integer deleteMessageByIdAndUserId ( String id, String userId );
}
