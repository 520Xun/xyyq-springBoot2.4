package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:49
 */
public interface RoleService {
    /**
     * 查找所有角色, 分配给用户
     *
     * @return
     */
    List< Role > findAllRole ( );

    /**
     * 插入角色
     *
     * @param name
     * @param note
     * @param user
     * @return
     */
    int insertRole ( String name, String note, String user );

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    int deleteRole ( Integer[] ids );

    /**
     * 修改角色
     *
     * @param id
     * @param name
     * @param note
     * @param user
     * @return
     */
    int updateRole ( Integer id, String name, String note, String user );

    /**
     * 将角色回收
     *
     * @param ids
     * @return
     */
    JsonResult updateFindById ( Integer[] ids );

    int handlerSaveExcelRole ( MultipartFile file );

    int exportsRole ( Integer[] ids );

    /**
     * 查询所有的角色 展示在角色列表
     *
     * @param roleName
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult FindAllRole ( String roleName, Integer curPage, Integer pageSize );
}
