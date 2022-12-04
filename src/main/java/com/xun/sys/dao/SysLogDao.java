package com.xun.sys.dao;

import com.xun.sys.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//日志数据层
@Mapper
public interface SysLogDao {

    /**
     * 按用户名查找日志总条数
     *
     * @param username
     * @return
     */
    int getRowCount ( String username );

    /**
     * 按用户名、用户操作查找日志信息且分页
     *
     * @param username 用户名
     * @return
     */
    List< SysLog > findLogObject ( @Param ( "username" ) String username, @Param ( "operation" ) String operation );

    /**
     * 按id删除日志信息
     *
     * @param ids
     * @return
     */
    int deleteLogByIds ( Integer[] ids );
    
    @Insert ( "INSERT INTO sys_logs(`id`, `username`, `operation`, `method`, `params`, `time`, `ip`, `createdTime`) VALUES (null, #{username},#{operation},#{method}, #{params},#{time},#{ip}, now());" )
    int insertLog ( SysLog log );
}
