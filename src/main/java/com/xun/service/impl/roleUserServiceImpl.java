package com.xun.service.impl;

import com.xun.common.util.Assert;
import com.xun.dao.RoleUserDao;
import com.xun.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-20 2:07
 */
@Service
public class roleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public List<Integer> findRoleByUserId (Integer id) {
        Assert.isEmpty (id == null, "请选择要修改的数据！");
        List<Integer> list = roleUserDao.findRoleByUserId (id);
        return list;
    }
}
