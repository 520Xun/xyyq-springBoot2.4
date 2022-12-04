package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.SysLog;

public interface SysLogService {

    /**
     * 通过用户名查找日志信息
     *
     * @param username 用户名
     * @return
     */
    JsonResult findLogByUsername ( String username, String operation, Integer curPage, Integer pageSize );

    /**
     * 删除日志（有权限，权限后面再写）
     *
     * @param ids 日志id数组
     * @return
     */
    int doDeleteLog ( Integer[] ids );

    /**
     * 插入日志信息
     */
    void insertLog ( SysLog log );

}
