package com.xun.sys.service;
//自动导包设置

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Message;
import com.xun.sys.vo.ParentMessageVo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author xun
 * @since 2023-02-07 09:12:16
 */
public interface MessageService {
    /**
     * 查询所有的留言，前台页面查询
     *
     * @return
     */
    List< ParentMessageVo > listMessageVoList ( );

    /**
     * 根据用户id 和 留言id 删除留言
     *
     * @param id
     * @param userId
     * @return
     */
    Integer deleteMessageByIdAndUserId ( String id, String userId );

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById ( Integer id );

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param curPage 当前页    pageSize  页数
     * @return 查询结果
     */
    JsonResult queryByPage ( Message message, Integer curPage, Integer pageSize );

    /**
     * 新增数据
     *
     * @param messageVo
     * @param session
     * @return 实例对象
     */
    Integer saveMessage ( ParentMessageVo messageVo, HttpSession session );

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update ( Message message );

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById ( Integer id );

    /**
     * 审核留言
     *
     * @param id
     * @param messageState
     * @return
     */
    Integer updateMessageState ( Integer id, Integer messageState );

    /**
     * 批量删除，或者单删留言
     *
     * @param ids
     * @return
     */
    Integer deleteMessage ( Integer[] ids );
}
