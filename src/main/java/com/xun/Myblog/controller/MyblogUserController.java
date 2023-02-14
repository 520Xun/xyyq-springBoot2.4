package com.xun.Myblog.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.common.util.LoginUser;
import com.xun.sys.controller.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-14 9:08
 * 登录用户控制器
 */
@Controller
@RequestMapping ( "/signin" )
public class MyblogUserController {

    @Resource
    private UserController userController;

    @RequestMapping ( "/login" )
    public String myblogLoginPage ( ) {
        return "Myblog/login";
    }

    @RequestMapping ( "handlerLogin" )
    public JsonResult handlerLogin ( LoginUser loginUser, HttpSession session ) {
        String code = ( String ) session.getAttribute ( "code" );//验证码
        //验证码错误！
        if ( loginUser.getCode ( ) != code ) {
            return new JsonResult ( "验证码错误" );
        } else {
            //验证码成功！校验登录方式
            checkUserLoginMethod ( loginUser );
        }
        return null;
    }

    /**
     * 检查用户登录方式
     *
     * @param loginUser
     */
    private static void checkUserLoginMethod ( LoginUser loginUser ) {
        String username = loginUser.getUsername ( );
        String password = loginUser.getPassword ( );
        String ph = "^[1][34578]\\d{9}$";//手机号
        String zh = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$"; //普通账号
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//邮箱
        if ( username.matches ( ph ) ) { //手机号登录
            //校验手机号与密码是否存在
            System.out.println ( "手机号登录" );
        } else if ( username.matches ( em ) ) {//邮箱登录
            System.out.println ( "邮箱登录" );
        } else if ( username.matches ( zh ) ) {//账号登录
            System.out.println ( "账号登录" );
        } else {
            System.out.println ( "账号格式有误！请重新输入！" );
        }
    }

    public static void main ( String[] args ) {
        LoginUser loginUser = new LoginUser ( "3296497389@qq.com", "123456", "5lcaAW", true );
        checkUserLoginMethod ( loginUser );
    }
}
