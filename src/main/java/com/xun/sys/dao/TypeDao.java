package com.xun.sys.dao;

import com.xun.sys.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:19
 */
@Mapper
public interface TypeDao {

    @Select ( "select * from type where id=#{id}" )
    Type findTypeById ( Integer id );

    @Select ( "select * from type" )
    List< Type > findAllType ( );

}
