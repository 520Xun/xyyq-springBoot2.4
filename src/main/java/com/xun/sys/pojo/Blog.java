package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    private Integer id;//主键
    private String title; //文章标题
    private Integer userId;//作者Id
    private String content;//博文
    private Integer NumberView;//被查看次数
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String publishDate;//发布时间
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String updateDate;//修改时间
    private Integer essayStatus = 1;//博文状态 0 存为草稿无需审核 1 已发布，未审核 2 已发布，已审核  3回收站)
    private String essayGrade = "2";//博文评分
    private Integer essayCollect = 0;//博文被收藏数量
    private Integer essayTop = 0;//被顶数量
    private Integer essayTread = 0;//被踩数量
    private Integer typeId;//分类id
    private Integer deleteState = 1;//是否删除 1为正常  0为彻底删除  默认1正常 2加入回收站
    /**
     * 当文章没有审核时，不允许推荐
     */
    private Integer recommend = 0;//是否推荐 1为推荐 0 为不推荐
    private String blogCover;//博文封面
}
