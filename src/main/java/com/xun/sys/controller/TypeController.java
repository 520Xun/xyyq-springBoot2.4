package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Type;
import com.xun.sys.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:43
 */
@RestController
@RequestMapping ( "type" )
public class TypeController {
    @Autowired
    private TypeService typeServiceImpl;

    @RequestMapping ( "findAllType" )
    public JsonResult findAllType ( ) {
        List< Type > list = typeServiceImpl.findAllType ( );
        return new JsonResult ( list );
    }
}
