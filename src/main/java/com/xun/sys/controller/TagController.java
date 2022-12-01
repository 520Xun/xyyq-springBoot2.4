package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Tag;
import com.xun.sys.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:27
 */
@RestController
@RequestMapping ( "tag" )
public class TagController {
    @Autowired
    private TagService tagerviceImpl;

    /**
     * 查找所有标签
     *
     * @return
     */
    @RequestMapping ( "findAllTag" )
    public JsonResult findAllTag ( ) {
        List< Tag > list = tagerviceImpl.findAllTag ( );
        return new JsonResult ( list );
    }
}
