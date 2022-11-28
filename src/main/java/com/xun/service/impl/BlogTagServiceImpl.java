package com.xun.service.impl;

import com.xun.common.util.Assert;
import com.xun.dao.BlogTagDao;
import com.xun.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:59
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public List< Integer > findTagByBlogId ( Integer blogId ) {
        Assert.isEmpty ( blogId == null || blogId == 0, "请选择要修改的数据！" );
        List< Integer > list = blogTagDao.findTagByBlogId ( blogId );
        return list;
    }
}
