package com.xun.sys.dao;


import com.xun.sys.pojo.Message;
import com.xun.sys.vo.ParentMessageVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author xun
 * @since 2023-02-07 09:21:00
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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById ( Integer id );

    /**
     * 查询指定行数据
     *
     * @param message 查询条件
     * @return 对象列表
     */
    List< ParentMessageVo > queryByPage ( Message message );

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     */
    int insertBatch ( @Param ( "entities" ) List< Message > entities );

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch ( @Param ( "entities" ) List< Message > entities );

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update ( Message message );

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById ( Integer id );

    /**
     * 审核留言
     *
     * @param id
     * @param messageState
     * @return
     */
    @Update ( "update message set messageState=#{messageState} where id=#{id}" )
    Integer updateMessageState ( Integer id, Integer messageState );

    Integer deleteMessage ( Integer[] ids );
}

