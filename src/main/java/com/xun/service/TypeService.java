package com.xun.service;

import com.xun.pojo.Type;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:46
 * <p>
 * 类别业务层
 */
public interface TypeService {
    /**
     * 查找所有的类别
     *
     * @return
     */
    List< Type > findAllType ( );
}
