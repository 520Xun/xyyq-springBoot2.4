package com.xun.sys.service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:58
 */
public interface BlogTagService {
    /**
     * 根据文章id
     * 查找文章对应的标签
     *
     * @param blogId
     * @return
     */
    List< Integer > findTagByBlogId ( Integer blogId );
}
