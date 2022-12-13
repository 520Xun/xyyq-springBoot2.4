package com.xun.sys.service.impl;

import com.xun.sys.dao.BlogDao;
import com.xun.sys.dao.TypeDao;
import com.xun.sys.pojo.BlogTypeVo;
import com.xun.sys.pojo.Type;
import com.xun.sys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:46
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private BlogDao blogDao;

    @Override
    public List< Type > findAllType ( ) {
        List< Type > allType = typeDao.findAllType ( );
        return allType;
    }

    @Override
    public List< BlogTypeVo > findAllTypeAndBlog ( ) {
        List< BlogTypeVo > allTypeAndBlog = typeDao.findAllTypeAndBlog ( );

        return allTypeAndBlog;
    }

}
