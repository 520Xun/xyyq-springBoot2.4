package com.xun.sys.controller;

import com.xun.common.exception.ServiceException;
import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Role;
import com.xun.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:42
 */
@RestController
@RequestMapping ( "role" )
public class RoleController {

    @Autowired
    private RoleService roleServiceImpl;

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping ( "findAllRole" )
    public JsonResult findAllRole ( ) {
        //查找角色
        List< Role > list = roleServiceImpl.findAllRole ( );
        return new JsonResult ( list );
    }

    @RequestMapping ( "FindAllRole" )
    public JsonResult findAllRole ( String roleName, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        //查找角色
        return roleServiceImpl.FindAllRole ( roleName, curPage, pageSize );
    }

    @RequestMapping ( "insertRole" )
    public JsonResult insertRole ( String name, String note, String user ) {
        System.out.println ( "SDdddddddddddddddddd" + user );
        int n = roleServiceImpl.insertRole ( name, note, user );
        JsonResult js = new JsonResult ( "添加成功！" );
        System.out.println ( js );
        return js;
    }

    @RequestMapping ( "updateFindById" )
    public JsonResult updateFindById ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        JsonResult js = roleServiceImpl.updateFindById ( ids );
        System.out.println ( js );
        return new JsonResult ( js );
    }

    @RequestMapping ( "updateRole" )
    public JsonResult updateRole ( Integer id, String name, String note, String user ) {
        System.out.println ( "进来了" );
        int n = roleServiceImpl.updateRole ( id, name, note, user );
        JsonResult js = new JsonResult ( "修改成功！" );
        System.out.println ( js );
        return js;
    }

    @RequestMapping ( "deleteRole" )
    public JsonResult deleteRole ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        roleServiceImpl.deleteRole ( ids );
        return new JsonResult ( "删除成功！" );
    }

    @RequestMapping ( "saveExcelCar" )
    public JsonResult importsRole ( MultipartFile file ) {
        //MultipartFile：上传处理对象
        if ( file == null ) {
            throw new ServiceException ( "上传文件不存在！" );
        }
        int n = roleServiceImpl.handlerSaveExcelRole ( file );
        return new JsonResult ( n );
    }

    @RequestMapping ( "exportsRole" )
    public JsonResult exportsRole ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        roleServiceImpl.exportsRole ( ids );
        return new JsonResult ( "导出成功！" );
    }
}
