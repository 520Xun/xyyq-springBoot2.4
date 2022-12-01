package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Role;
import com.xun.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:42
 */
@RestController
@RequestMapping ( "role" )
public class RoleController {

    @Autowired
    private RoleService roleServiceImpl;

    @RequestMapping ( "findAllRole" )
    public JsonResult findAllRole ( ) {
        //查找角色
        List< Role > list = roleServiceImpl.findAllRole ( );
        return new JsonResult ( list );
    }
}
