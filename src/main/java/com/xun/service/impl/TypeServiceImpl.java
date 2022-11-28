package com.xun.service.impl;

import com.xun.dao.TypeDao;
import com.xun.pojo.Type;
import com.xun.service.TypeService;
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

    @Override
    public List< Type > findAllType ( ) {
        List< Type > allType = typeDao.findAllType ( );
        return allType;
    }
}
