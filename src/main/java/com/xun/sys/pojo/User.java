package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-09 20:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;//主键
    private String username;//用户名
    private String password;//密码
    private String salt;//加密盐
    private String authorName;//作者名
    private Integer age = 18;//年龄   未使用，默认18岁
    private String sex;//性别
    private String email;//邮箱
    private String phone;//电话
    //http://localhost/images/20221206165941.png
    private String picture;//头像  默认一张灰白头像
    private String personalizedSign;//个性签名
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date registerTime;//注册时间
    private String registerIp;//注册的ip
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;//修改的时间
    private String address; //地址 只需要省份就行，例如： 湖南省-->湖南 (统计全国用户)
    private Integer onlineStatus = 1;//在线状态  0 下线  1 在线  默认在线
    private Integer userStatus = 1;//账号状态 0禁用 1是启用  默认开启账号
    private String createdUser;//创建者
    private String modifiedUser;//修改者
    private Integer deleteState = 1;//是否删除 1为正常  0为彻底删除  默认1正常 2加入回收站

    public User ( String authorName ) {
        this.authorName = authorName;
    }

    /**
     * 测试用
     *
     * @param id
     * @param username
     * @param password
     * @param salt
     * @param authorName
     */
    public User ( Integer id, String username, String password, String salt, String authorName ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.authorName = authorName;
    }
}
