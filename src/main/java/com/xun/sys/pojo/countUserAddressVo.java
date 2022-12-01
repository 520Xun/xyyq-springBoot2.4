package com.xun.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-30 1:45
 * 统计全球分布用户数量,显示在地图上
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class countUserAddressVo {
    private String name;
    private String value;
}
