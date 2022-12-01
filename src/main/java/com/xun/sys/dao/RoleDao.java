package com.xun.sys.dao;

import com.xun.sys.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:51
 */
@Mapper
public interface RoleDao {
    /**
     * 查找所有角色
     *
     * @return
     */
    @Select ( "select * from roles" )
    List< Role > findAllRole ( );
}
