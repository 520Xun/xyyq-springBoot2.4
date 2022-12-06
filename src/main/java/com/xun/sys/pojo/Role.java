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
 * @date: 2022-11-16 20:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色备注
     */
    private String note;
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    /**
     * 创建时间
     */
    private Date createdTime;
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;
    /**
     * 修改用户
     */
    private String createdUser;
    /**
     * 创建用户
     */
    private String modifiedUser;
}
