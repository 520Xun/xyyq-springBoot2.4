package com.xun.controller;

import com.xun.common.exception.ServiceException;
import com.xun.common.pojo.JsonResult;
import com.xun.pojo.User;
import com.xun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 新增用户
     *
     * @param user
     * @param roleIds
     * @return
     */
    @RequestMapping ("saveUser")
    public JsonResult InsertUser (User user, @RequestParam ("roleIds[]") Integer[] roleIds) {
        Integer n = userService.insertUser (user, roleIds);
        JsonResult jr = new JsonResult (n);
        jr.setMsg ("添加成功！");
        return jr;
    }

    /**
     * 修改用户
     *
     * @param user
     * @param roleIds
     * @return
     */
    @RequestMapping ("updateUser")
    public JsonResult updateUser (User user, @RequestParam (value = "roleIds[]", required = false) Integer[] roleIds) {
        System.out.println ("********修改数据");
        Integer n = userService.updateUser (user, roleIds);
        JsonResult jr = new JsonResult (n);
        jr.setMsg ("修改成功！");
        return jr;
    }

    /**
     * 将用户加入到回收站内
     */
    @RequestMapping ("deleteUser")
    public JsonResult deleteUser (@RequestParam ("ids[]") Integer[] ids) {
        int n = userService.deleteUser (ids);
        JsonResult jr = new JsonResult (n);
        jr.setMsg (n + "条数据已加入回收站！");
        return jr;
    }

    /**
     * 恢复用户
     *
     * @return
     */
    @RequestMapping ("recoverUser")
    public JsonResult recoverUser (@RequestParam ("ids[]") Integer[] ids) {
        int n = userService.recoverUser (ids);
        JsonResult jr = new JsonResult (n);
        jr.setMsg (n + "条数据已恢复！");
        return jr;
    }

    /**
     * 彻底删除用户
     *
     * @param ids
     * @return
     */
    @RequestMapping ("chealUser")
    public JsonResult chealUser (@RequestParam ("ids[]") Integer[] ids) {
        int n = userService.chealUser (ids);
        JsonResult jr = new JsonResult (n);
        jr.setMsg (n + "条数据已彻底删除！！！");
        return jr;
    }

    /**
     * 一键导出所有
     *
     * @return
     */
    @RequestMapping ("exportAllUser")
    public JsonResult exportAllUser () {
        userService.exportAllUser ();
        return new JsonResult ("导出成功！");
    }

    /**
     * 根据id 导出用户
     *
     * @param ids
     * @return
     */
    @RequestMapping ("exportByUserId")
    public JsonResult exportByUserId (@RequestParam ("ids[]") Integer[] ids) {
        userService.exportByUserId (ids);
        return new JsonResult ("导出成功！");
    }

    /**
     * 导入用户
     */
    @RequestMapping ("saveExcelCar")
    public JsonResult saveExcelCar (MultipartFile file) {
        //MultipartFile：上传处理对象
        if (file == null) {
            throw new ServiceException ("上传文件不存在！");
        }
        int n = userService.handlerSaveExcelUser (file);
        return new JsonResult (n);
    }
}
