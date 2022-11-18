package com.xun.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class User {
    private Integer id;//主键
    private String username;//用户名
    private String password;//密码
    private String salt;//加密盐
    private String authorName;//作者名
    private Integer age;//年龄
    private String sex;//性别
    private String email;//邮箱
    private String phone;//电话
    private String picture;//头像
    private String personalizedSign;//个性签名
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;//注册时间
    private String registerIp;//注册的ip
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;//修改的时间
    private String address; //地址 只需要省份就行，例如： 湖南省-->湖南 (统计全国用户)
    private Integer onlineStatus = 1;//在线状态  0 下线  1 在线  默认在线
    private Integer userStatus = 1;//账号状态 0禁用 1是启用  默认开启账号
    private String createdUser;//创建者
    private String modifiedUser;//修改者
}
