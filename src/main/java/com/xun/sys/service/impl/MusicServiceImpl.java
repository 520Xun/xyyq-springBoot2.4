package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.config.pageProperties;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.util.Assert;
import com.xun.sys.dao.MusicDao;
import com.xun.sys.pojo.Music;
import com.xun.sys.service.MusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 音乐播放列表(Music)表服务实现类
 *
 * @author xun
 * @since 2023-02-06 09:30:19
 */
@Service ( "musicService" )
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicDao musicDao;

    @Resource
    private pageProperties pp;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Music queryById ( Integer id ) {
        return this.musicDao.queryById ( id );
    }


    /**
     * 新增数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music insert ( Music music ) {
        this.musicDao.insert ( music );
        return music;
    }

    /**
     * 修改数据
     *
     * @param music 实例对象
     * @return 实例对象
     */
    @Override
    public Music update ( Music music ) {
        this.musicDao.update ( music );
        return this.queryById ( music.getId ( ) );
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById ( Integer id ) {
        return this.musicDao.deleteById ( id ) > 0;
    }

    /**
     * 分页查询
     *
     * @param music    筛选条件
     * @param curPage  当前页
     * @param pageSize 页数
     * @return 查询结果
     */
    @Override
    public JsonResult queryByPage ( Music music, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< Music > page = PageHelper.startPage ( curPage, pageSize );
        List< Music > blogs = musicDao.queryByPage ( music );
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( blogs );
        return new JsonResult ( pageObj );
    }
}
