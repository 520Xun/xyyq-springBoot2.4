package com.xun.service.impl;

import com.xun.dao.TagDao;
import com.xun.pojo.Tag;
import com.xun.service.TagService;
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
