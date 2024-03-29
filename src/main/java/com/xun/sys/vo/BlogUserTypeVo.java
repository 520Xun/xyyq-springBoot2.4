package com.xun.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xun.sys.pojo.Type;
import com.xun.sys.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-24 23:06
 * 博文实体类别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserTypeVo implements Serializable {
    private Integer id;//主键
    private String title; //文章标题
    private User user;//作者
    private String content;//博文
    private Integer NumberView = 1;//被查看次数
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String publishDate;//发布时间
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String updateDate;//修改时间
    private Integer essayStatus = 1;//博文状态 0 存为草稿无需审核 1 已发布，未审核 2 已发布，已审核  3回收站)
    private String essayGrade = "2";//博文评分
    private Integer essayCollect = 0;//博文被收藏数量
    private Integer essayTop = 0;//被顶数量
    private Integer essayTread = 0;//被踩数量
    /**
     * 查询文章评论数量，数据库无字段，依据评论表查询
     */
    private Integer countComment;//文章评论数量
    private List< String > BlogTags;
    private Type type;//分类对象
    private Integer deleteState = 1;//是否删除 1为正常  0为彻底删除  默认1正常 2加入回收站
    /**
     * 当文章没有审核时，不允许推荐
     */
    private Integer recommend = 0;//是否推荐 1为推荐 0 为不推荐
    private String blogCover;//博文封面
}
