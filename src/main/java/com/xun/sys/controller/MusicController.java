package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Music;
import com.xun.sys.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 音乐播放列表(Music)表控制层
 *
 * @author xun
 * @since 2023-02-06 09:30:18
 */
@RestController
@RequestMapping ( "music" )
public class MusicController {
    /**
     * 服务对象
     */
    @Resource
    private MusicService musicService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping ( "{id}" )
    public JsonResult queryById ( @PathVariable ( "id" ) Integer id ) {
        return new JsonResult ( musicService.queryById ( id ) );
    }

    /**
     * 查询播放列表
     *
     * @param music
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping ( "queryByPage" )
    public JsonResult queryByPage ( Music music, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        return musicService.queryByPage ( music, curPage, pageSize );
    }

    /**
     * 新增数据
     *
     * @param music 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity< Music > add ( Music music ) {
        return ResponseEntity.ok ( this.musicService.insert ( music ) );
    }

    /**
     * 编辑数据
     *
     * @param music 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity< Music > edit ( Music music ) {
        return ResponseEntity.ok ( this.musicService.update ( music ) );
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity< Boolean > deleteById ( Integer id ) {
        return ResponseEntity.ok ( this.musicService.deleteById ( id ) );
    }
}

