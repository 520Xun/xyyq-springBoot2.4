package com.xun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-20 2:12
 */
@Mapper
public interface RoleUserDao {
    /**
     * 根据用户id查询角色id
     *
     * @param id
     * @return
     */
    @Select ("select rolesId from user_roles where userId=#{id}")
    List<Integer> findRoleByUserId (Integer id);
}
