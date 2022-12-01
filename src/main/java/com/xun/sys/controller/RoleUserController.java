package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-20 2:03
 * 用户角色关系的控制类
 */
@RestController
@RequestMapping ( "userRole" )
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserServiceImpl;

    /**
     * 根据用户id查询用户所对应的角色
     *
     * @param id
     * @return
     */
    @RequestMapping ( "findRoleByUserId" )
    public JsonResult findRoleByUserId ( Integer id ) {
        List< Integer > list = roleUserServiceImpl.findRoleByUserId ( id );
        return new JsonResult ( list );
    }
}
