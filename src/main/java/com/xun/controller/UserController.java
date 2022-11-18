package com.xun.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.pojo.User;
import com.xun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-09 20:05
 */
@RestController
@RequestMapping ("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查找用户列表，实现分页，查询
     *
     * @param user
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping ("findUser")
    public JsonResult findUsers (User user, @RequestParam (required = false, defaultValue = "1") Integer curPage, @RequestParam (required = false, defaultValue = "5") Integer pageSize) {
        return userService.findUsers (user, curPage, pageSize);
    }

    /**
     * 修改在线状态
     *
     * @param id
     * @param onlineStatus
     * @return
     */
    @RequestMapping ("updateOnlineStatus")
    public JsonResult updateOnlineStatus (Integer id, Integer onlineStatus) {
        Integer n = userService.updateOnlineStatus (id, onlineStatus);
        JsonResult jr = new JsonResult (n);
        jr.setMsg ("修改成功！");
        return jr;
    }

    /**
     * 修改账号状态
     *
     * @param id
     * @param userStatus
     * @return
     */
    @RequestMapping ("updateUserStatus")
    public JsonResult updateUserStatus (Integer id, Integer userStatus) {
        Integer n = userService.updateUserStatus (id, userStatus);
        JsonResult jr = new JsonResult (n);
        jr.setMsg ("修改成功！");
        return jr;
    }

    @RequestMapping ("saveUser")
    public JsonResult InsertUser (User user, @RequestParam ("roleIds[]") Integer[] roleIds) {
        int n = userService.insertUser (user, roleIds);
        JsonResult jr = new JsonResult (n);
        jr.setMsg ("添加成功！");
        return jr;
    }


}
