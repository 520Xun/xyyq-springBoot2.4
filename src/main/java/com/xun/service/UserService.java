package com.xun.service;

import com.xun.common.pojo.JsonResult;
import com.xun.pojo.User;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 15:25
 */
public interface UserService {
    /**
     * 查找用户 （分页查询，按条件查）
     *
     * @param user
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult findUsers (User user, Integer curPage, Integer pageSize);

    /**
     * 修改在线的状态
     *
     * @param id
     * @param onlineStatus
     * @return
     */
    Integer updateOnlineStatus (Integer id, Integer onlineStatus);

    /**
     * 修改账号的状态
     *
     * @param id
     * @param userStatus
     * @return
     */
    Integer updateUserStatus (Integer id, Integer userStatus);

    /**
     * 添加用户，并且关联用户和角色
     *
     * @param user
     * @param roleIds
     * @return
     */
    Integer insertUser (User user, Integer[] roleIds);

    /**
     * 修改用户，并更新用户与角色的关系
     */
    Integer updateUser (User user, Integer[] roleIds);

}
