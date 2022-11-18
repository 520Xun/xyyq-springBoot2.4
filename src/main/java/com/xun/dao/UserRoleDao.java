package com.xun.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-17 17:01
 */
@Mapper
public interface UserRoleDao {
    /**
     * 插入用户与角色的关系
     *
     * @param id
     * @param roleIds
     * @return
     */
    Integer insertUserRole (@Param ("userId") Integer id, @Param ("roleIds") Integer[] roleIds);

    /**
     * 根据用户id删除用户与角色的关系
     *
     * @param id
     * @return
     */
    @Delete ("delete from user_roles where userId=#{id}")
    Integer deteleUserRoleByUserId (Integer id);
}
