package com.xun.service.impl;

import com.xun.dao.RoleDao;
import com.xun.pojo.Role;
import com.xun.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:50
 */
@Service
public class RoleServiceImpL implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAllRole () {
       List<Role> list= roleDao.findAllRole();
        return list;
    }
}
