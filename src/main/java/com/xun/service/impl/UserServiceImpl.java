package com.xun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.pageProperties;
import com.xun.common.util.Assert;
import com.xun.common.util.IPUtils;
import com.xun.dao.UserDao;
import com.xun.dao.UserRoleDao;
import com.xun.pojo.User;
import com.xun.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 15:27
 */
@Service
//事务注解
@Transactional (
        isolation = Isolation.READ_COMMITTED,
        timeout = 100,
        rollbackFor = RuntimeException.class,
        readOnly = false)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private pageProperties pp;

    public JsonResult findUsers (User user, Integer curPage, Integer pageSize) {
        //排除非法参数
        Assert.isEmpty (curPage == null || pageSize == null, "查询参数异常！！！");
        pageSize = pageSize == 0 ? pp.getPageSize () : pageSize;
        Page<User> page = PageHelper.startPage (curPage, pageSize);
        List<User> users = userDao.findUsers (user);
        Pagination pageObj = new Pagination (curPage, (int) page.getTotal (), pageSize);
        pageObj.setPageData (users);
        return new JsonResult (pageObj);
    }

    @Override
    public Integer updateOnlineStatus (Integer id, Integer onlineStatus) {
        Assert.isEmpty (id == null || id == 0 || onlineStatus == null, "请求参数有误！");
        Integer userStatus = userDao.checkUserStatus (id);
        Assert.isEmpty (userStatus == 0, "当前账号已被禁用无法修改！");
        //对数据的修改者
        String username = "admin";
        Integer n = userDao.updateOnlineStatus (id, onlineStatus, username);
        Assert.isEmpty (n == 0, "修改失败！");
        return n;
    }

    @Override
    public Integer updateUserStatus (Integer id, Integer userStatus) {
        Assert.isEmpty (id == null || id == 0 || userStatus == null, "请求参数有误！");
        String username = "admin";
        Integer n = userDao.updateUserStatus (id, userStatus, username);
        Assert.isEmpty (n == 0, "修改失败！");
        return n;
    }

    @Override
    public Integer insertUser (User user, Integer[] roleIds) {
        Assert.isEmpty (user == null, "请填写用户信息！");
        Assert.isEmpty (user.getUsername () == null, "必须填写用户名！");
        Assert.isEmpty (user.getPassword () == null, "必须填写密码！");
        Assert.isEmpty (roleIds == null || roleIds.length == 0, "请至少选择一个角色！");
        //UUID是uitL的包
        String salt = UUID.randomUUID ().toString ();//加盐，32位的随机盐值
        // 加密方式 原密码 盐 加密次数
        SimpleHash sh = new SimpleHash ("MD5", user.getPassword (), salt, 2);
        String password = sh.toHex ();//转为32位的字符串
        user.setPassword (password);
        user.setSalt (salt);
        //判断用户是否存在
        User u1 = userDao.findUserByName (user.getUsername ());
        Assert.isEmpty (u1 != null, "用户名已存在！");
        User u2 = userDao.findUserByAuthorUser (user.getAuthorName ());
        Assert.isEmpty (u2 != null, "作者名已存在！");
        String ip = IPUtils.getIpAddr ();
        user.setRegisterIp (ip);
        String username = "xun";//创建者
        user.setCreatedUser (username);
        //插入用户
        int n = userDao.insertUser (user);
        Assert.isEmpty (n == 0, "新增用户失败！");
        //插入用户与角色的关系
        userRoleDao.insertUserRole (user.getId (), roleIds);
        return n;
    }

    @Override
    public Integer updateUser (User user, Integer[] roleIds) {
        Assert.isEmpty (user == null || user.getId () == null, "请选择要修改的用户信息！");
        Assert.isEmpty (user.getUsername () == null || user.getUsername ().equals (""), "请填写要修改的用户信息！");
        Assert.isEmpty (roleIds == null || roleIds.length == 0, "请至少选择一个角色！");
        user.setModifiedUser ("admin");
        userRoleDao.deteleUserRoleByUserId (user.getId ());
        userRoleDao.insertUserRole (user.getId (), roleIds);
        int n = userDao.updateUser (user);
        Assert.isEmpty (n == 0, "修改失败！");
        return n;
    }

    @Override
    public Integer deleteUser (Integer[] ids) {
        Assert.isEmpty (ids == null || ids.length == 0, "请选择要删除的数据");
        int n = userDao.deleteUseByIds (ids);
        Assert.isEmpty (n == 0, "数据已被删除！");
        return n;
    }

}
