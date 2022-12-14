package com.xun.sys.dao;

import com.xun.sys.vo.ParentMessageVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 14:35
 */
@Mapper
public interface MessageDao {

    /**
     * 查询留言
     * 依据父id查询
     *
     * @param parentId
     * @return
     */
    List< ParentMessageVo > findByParentIdNotNull ( Integer parentId );

    /**
     * 插入留言
     *
     * @param messageVo
     * @return
     */
    Integer saveMessage ( ParentMessageVo messageVo );

    /**
     * 根据留言id 和 用户id 删除
     *
     * @param id
     * @param userId
     * @return
     */
    @Delete ( "delete from message where id=#{id} and user_id=#{userId}" )
    Integer deleteMessageByIdAndUserId ( @Param ( "id" ) String id, @Param ( "userId" ) String userId );

    /**
     * 删除父id 为留言id的留言
     * 即删除子留言
     *
     * @param id
     * @return
     */
    @Delete ( "delete from message where parent_message_id=#{id}" )
    Integer deleteMessageByReplay ( String id );
}
