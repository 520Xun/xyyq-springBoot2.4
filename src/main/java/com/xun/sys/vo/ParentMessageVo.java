package com.xun.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xun.sys.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 12:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentMessageVo {
    private Integer id;
    private User user;
    private Integer parentMessageId;
    private String contant;//内容
    private String messageIp;
    /**
     * 暂时测试使用
     */
    private Integer messageState = 0;//审核
    private Integer messageTop = 0;//被顶
    private Integer messageTread = 0;//被踩
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private String createdTime;//创建时间

    //回复评论
    private List< ParentMessageVo > replyMessages = new ArrayList<> ( );
    private ParentMessageVo parentMessage;
    private String parentNickname; //父评论者名称
}
