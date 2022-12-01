package com.xun.sys.service;

import com.xun.sys.pojo.Tag;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:29
 * <p>
 * 标签业务接口
 */
public interface TagService {

    List< Tag > findAllTag ( );
}
