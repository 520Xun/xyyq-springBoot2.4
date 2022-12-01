package com.xun.sys.service.impl;

import com.xun.sys.dao.TagDao;
import com.xun.sys.pojo.Tag;
import com.xun.sys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:30
 */
@Service
public class TagerviceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List< Tag > findAllTag ( ) {
        List< Tag > list = tagDao.findAllTag ( );
        return list;
    }
}
