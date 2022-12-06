package com.xun.sys.dao;

import com.xun.sys.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 查找所有角色 展示角色列表
     *
     * @param pageSize
     * @param start
     * @return
     */
    List< Role > FindAllRole ( @Param ( "roleName" ) String roleName, @Param ( "start" ) int start, @Param ( "pageSize" ) Integer pageSize );

    /**
     * 查找条数
     *
     * @param roleName
     * @return
     */
    Integer getRoleCount ( String roleName );

    /**
     * 根据角色名查找有没有这个角色
     *
     * @param name
     * @return
     */
    Integer findInsertByName ( String name );

    /**
     * 添加角色
     *
     * @param name
     * @param note
     * @param user
     * @return
     */
    @Insert ( "insert into roles(roleName, note, createdTime, createdUser) value(#{name}, #{note}, now(), #{user})" )
    int insertRole ( String name, String note, String user );

    /**
     * 通过id查找要修改角色的数据
     *
     * @param ids
     * @return
     */
    List< Role > updateFindById ( Integer[] ids );

    /**
     * 修改角色
     *
     * @param id
     * @param name
     * @param note
     * @return
     */
    int updateRole ( @Param ( "id" ) Integer id, @Param ( "name" ) String name, @Param ( "note" ) String note, @Param ( "user" ) String user );

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    int deleteRole ( Integer[] ids );

    int insertRoleList ( List< Role > roleList );

}
