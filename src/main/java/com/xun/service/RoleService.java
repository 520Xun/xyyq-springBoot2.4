package com.xun.service;

import com.xun.pojo.Role;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:49
 */
public interface RoleService {
    /**
     * 查找所有角色
     * @return
     */
    List<Role> findAllRole ();
}
