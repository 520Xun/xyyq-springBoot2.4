package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 音乐播放列表(Music)实体类
 *
 * @author xun
 * @since 2023-02-06 09:30:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music implements Serializable {
    private static final long serialVersionUID = 167983414037510490L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 歌曲名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 文件地址
     */
    private String url;
    /**
     * 封面图
     */
    private String pic;
    /**
     * 歌词
     */
    private String lrc;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 修改者
     */
    private String updateUser;
    /**
     * 创建时间
     */
    @JsonFormat ( pattern = "yyyy-MM-dd" )
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat ( pattern = "yyyy-MM-dd" )
    private Date updateTime;
    /**
     * 是否删除(0-未删, 1-已删)
     */
    private Integer isDeleted;

}
