package com.xun.sys.dao;

import com.xun.sys.pojo.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 音乐播放列表(Music)表数据库访问层
 *
 * @author xun
 * @since 2023-02-06 09:30:18
 */
@Mapper
public interface MusicDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Music queryById ( Integer id );

    /**
     * 统计总行数
     *
     * @param music 查询条件
     * @return 总行数
     */
    long count ( Music music );

    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int insert ( Music music );

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Music> 实例对象列表
     * @return 影响行数
     */
    int insertBatch ( @Param ( "entities" ) List< Music > entities );

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Music> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch ( @Param ( "entities" ) List< Music > entities );

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 影响行数
     */
    int update ( Music music );

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById ( Integer id );

    /**
     * 查询播放列表
     *
     * @param music
     * @return
     */
    List< Music > queryByPage ( Music music );
}

