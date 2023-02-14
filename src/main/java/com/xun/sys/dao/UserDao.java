package com.xun.sys.dao;

import com.xun.sys.pojo.User;
import com.xun.sys.vo.countUserAddressVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 13:46
 */

@Mapper
public interface UserDao {
    /**
     * 查找所有的用户，按条件查
     * 倒叙排列
     *
     * @param user
     * @return
     */
    List< User > findUsers ( User user );

    /**
     * 修改账号在线状态前，判断账号是否被禁用
     * 检查账号是否被禁用
     */
    @Select ( "select user_status from user where id=#{id} and delete_state=1  " )
    Integer checkUserStatus ( Integer id );

    /**
     * 修改在线状态
     *
     * @param id
     * @param onlineStatus
     * @param username
     * @return
     */
    @Update ( "update user set online_status=#{onlineStatus}, modifiedUser=#{username} where id=#{id} and delete_state=1" )
    Integer updateOnlineStatus ( @Param ( "id" ) Integer id, @Param ( "onlineStatus" ) Integer onlineStatus, @Param ( "username" ) String username );

    /**
     * 修改账号状态
     * 如果账号被禁用了，在线状态也要下线
     *
     * @param id
     * @param userStatus
     * @param username
     * @return
     */
    @Update ( "update user set user_status=#{userStatus},online_status=#{userStatus}, modifiedUser=#{username} where id=#{id}" )
    Integer updateUserStatus ( @Param ( "id" ) Integer id, @Param ( "userStatus" ) Integer userStatus, @Param ( "username" ) String username );

    /**
     * 查找用户名是否已存在
     *
     * @param username
     * @return
     */
    @Select ( "select username from user where username=#{username}" )
    User findUserByName ( String username );

    /**
     * 查找作者名字是否存在
     *
     * @param authorName
     * @return
     */
    @Select ( "select authorName from user where authorName=#{authorName}" )
    List< User > findUserByAuthorUser ( String authorName );

    /**
     * 新增用户
     *
     * @param user
     * @return
     */

    int insertUser ( User user );

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser ( User user );

    /**
     * 根据用户id将用户加入回收站
     *
     * @param ids
     * @return
     */
    int deleteUseByIds ( Integer[] ids );

    /**
     * 彻底删除用户
     *
     * @param ids
     * @return
     */
    int chealUser ( Integer[] ids );

    /**
     * 恢复数据
     *
     * @param ids
     * @return
     */
    int recoverUser ( Integer[] ids );

    /**
     * 导出所有用户
     *
     * @return
     */
    @Select ( "select * from user" )
    List< User > exportAllUser ( );

    /**
     * 根据用户id导出信息
     *
     * @param ids
     * @return
     */
    List< User > exportByUserId ( Integer[] ids );

    /**
     * 导入插入用户
     *
     * @param userList
     * @return
     */
    int insertUserList ( List< User > userList );

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Select ( "select * from user where id=#{id} and  delete_state=1" )
    User findUserById ( Integer id );

    /**
     * 根据作者名查询其id
     *
     * @param authorName
     * @return
     */
    @Select ( "select id from user where authorName=#{authorName}" )
    Integer findUserIdByAuthorUser ( String authorName );

    /**
     * 统计用户分布，在地图上显示
     * userStatus
     *
     * @return
     */
    @Select ( "select count(1) value,address name from user where delete_state=1 GROUP BY address" )
    List< countUserAddressVo > countUserAddress ( );

    /**
     * 查询用户名 和作者名是否存在
     *
     * @param username
     * @param authorName
     * @return
     */
    @Select ( "select * from user where username=#{username} and authorName=#{authorName}" )
    User findUserByNameAndAuthorName ( String username, String authorName );
}
