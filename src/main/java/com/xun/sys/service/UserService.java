package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.saveExcel;
import com.xun.sys.pojo.User;
import com.xun.sys.vo.countUserAddressVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 15:25
 */
public interface UserService {
    /**
     * 查找用户 （分页查询，按条件查）
     *
     * @param user
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult findUsers ( User user, Integer curPage, Integer pageSize );

    /**
     * 修改在线的状态
     *
     * @param id
     * @param onlineStatus
     * @return
     */
    Integer updateOnlineStatus ( Integer id, Integer onlineStatus );

    /**
     * 修改账号的状态
     *
     * @param id
     * @param userStatus
     * @return
     */
    Integer updateUserStatus ( Integer id, Integer userStatus );

    /**
     * 添加用户，并且关联用户和角色
     *
     * @param user
     * @param roleIds
     * @return
     */
    Integer insertUser ( User user, Integer[] roleIds );

    /**
     * 修改用户，并更新用户与角色的关系
     */
    Integer updateUser ( User user, Integer[] roleIds );

    /**
     * 将用户加入回收站
     *
     * @param ids
     * @return
     */
    Integer deleteUser ( Integer[] ids );

    /**
     * 彻底删除用户。可删除多个
     *
     * @param ids
     * @return
     */
    Integer chealUser ( Integer[] ids );

    /**
     * 恢复用户
     *
     * @param ids
     * @return
     */
    Integer recoverUser ( Integer[] ids );

    /**
     * 导出所有用户
     */
    void exportAllUser ( );

    /**
     * 根据用户id导出
     */
    void exportByUserId ( Integer[] ids );

    /**
     * 导入用户
     *
     * @param file
     * @return
     */
    saveExcel handlerSaveExcelUser ( MultipartFile file );

    /**
     * 统计用户分布，在地图显示
     *
     * @return
     */
    List< countUserAddressVo > countUserAddress ( );
}
