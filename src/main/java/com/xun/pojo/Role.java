package com.xun.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Role {
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
        /**
         * 角色创建时间
         */
        private Date createdTime;
        /**
         * 修改时间
         */
        private Date modifedTime;
        /**
         * 创建者
         */
        private String createdUser;
        /**
         * 修改者
         */
        private  String modifedUser;
}
