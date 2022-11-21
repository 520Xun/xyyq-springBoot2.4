package com.xun.dao;

import com.xun.pojo.User;
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
     * 查找所有的用户
     * 倒叙排列
     *
     * @param user
     * @return
     */
    List<User> findUsers (User user);

    /**
     * 修改账号在线状态前，判断账号是否被禁用
     * 检查账号是否被禁用
     */
    @Select ("select user_status from user where id=#{id} ")
    Integer checkUserStatus (Integer id);

    /**
     * 修改在线状态
     *
     * @param id
     * @param onlineStatus
     * @param username
     * @return
     */
    @Update ("update user set online_status=#{onlineStatus}, modifiedUser=#{username} where id=#{id}")
    Integer updateOnlineStatus (@Param ("id") Integer id, @Param ("onlineStatus") Integer onlineStatus, @Param ("username") String username);

    /**
     * 修改账号状态
     * 如果账号被禁用了，在线状态也要下线
     *
     * @param id
     * @param userStatus
     * @param username
     * @return
     */
    @Update ("update user set user_status=#{userStatus},online_status=#{userStatus}, modifiedUser=#{username} where id=#{id}")
    Integer updateUserStatus (@Param ("id") Integer id, @Param ("userStatus") Integer userStatus, @Param ("username") String username);

    /**
     * 查找用户名是否已存在
     *
     * @param username
     * @return
     */
    @Select ("select username from user where username=#{username}")
    User findUserByName (String username);

    /**
     * 查找作者名字是否存在
     *
     * @param authorName
     * @return
     */
    @Select ("select authorName from user where authorName=#{authorName}")
    User findUserByAuthorUser (String authorName);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */

    int insertUser (User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser (User user);

    /**
     * 根据用户id删除用户
     *
     * @param ids
     * @return
     */
    int deleteUseByIds (Integer[] ids);
}
