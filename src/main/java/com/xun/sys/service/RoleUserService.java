package com.xun.sys.service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-20 2:07
 */
public interface RoleUserService {
    /**
     * 根据用户id查询用户所对应的角色信息
     *
     * @param id
     * @return
     */
    List< Integer > findRoleByUserId ( Integer id );
}
