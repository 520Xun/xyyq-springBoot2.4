package com.xun.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xun.sys.pojo.Blog;
import com.xun.sys.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 15:53
 * 评论vo表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentCommentVo implements Serializable {
    //评论信息
    private Integer id;
    private Blog blog;
    private User user;
    private Integer parentId; //父id
    private String contant;//内容
    private String commentIp;//评论的ip地址
    private Integer commentState;//审核
    private Integer commentTop;//被顶
    private Integer commentTread;//被踩
    private String soft = "ASC";//排序  ASC正序 desc 倒叙
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String createdTime;//创建时间
    //回复评论
    private List< ParentCommentVo > replyComments = new ArrayList<> ( );
    private ParentCommentVo parentComment;
    private String parentNickname; //父评论者名称

}
