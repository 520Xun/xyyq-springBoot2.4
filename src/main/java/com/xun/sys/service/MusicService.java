package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Music;

/**
 * 音乐播放列表(Music)表服务接口
 *
 * @author xun
 * @since 2023-02-06 09:30:19
 */
public interface MusicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Music queryById ( Integer id );


    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music insert ( Music music );

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    Music update ( Music music );

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById ( Integer id );

    /**
     * 查询播放列表
     *
     * @param music
     * @param curPage
     * @param pageSize
     * @return
     */
    JsonResult queryByPage ( Music music, Integer curPage, Integer pageSize );
}
