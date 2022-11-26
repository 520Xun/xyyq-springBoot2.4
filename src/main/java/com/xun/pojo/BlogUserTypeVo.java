package com.xun.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private Integer NumberView;//被查看次数
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    /**
     * 发布时间
     */
    private String publishDate;
    /**
     * 最后一次修改时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateDate;
    private Integer essayStatus = 1;//博文状态 (0草稿,1发布 2 审核)
    private Integer essayGrade;//博文评分
    private Integer essayCollect;//博文被收藏数量
    private Integer essayTop;//被顶数量
    private Integer essayTread;//被踩数量
    private Type type;//分类对象
    private Integer deleteState = 1;//是否删除 1为正常  0为彻底删除  默认1正常 2加入回收站
    private Integer recommend = 1;//是否推荐 1为推荐 0 为不推荐
    private String blog_cover;//博文封面
}
