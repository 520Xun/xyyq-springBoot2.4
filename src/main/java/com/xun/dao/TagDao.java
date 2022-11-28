package com.xun.dao;

import com.xun.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:34
 */
@Mapper
public interface TagDao {
    /**
     * 查找所有标签
     *
     * @return
     */
    @Select ( "SELECT * from tag" )
    List< Tag > findAllTag ( );
}
