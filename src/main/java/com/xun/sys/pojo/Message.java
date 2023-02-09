package com.xun.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Message)实体类
 *
 * @author xun
 * @since 2023-02-07 08:44:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 927635449329066329L;
    /**
     * 主健
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 父id
     */
    private Integer parentMessageId;
    /**
     * 留言信息
     */
    private String contant;
    /**
     * 消息ip
     */
    private String messageip;
    /**
     * 留言审核
     */
    private Integer messagestate;
    /**
     * 踩
     */
    private Integer messagetread;
    /**
     * 点赞
     */
    private Integer messagetop;
    /**
     * 创建时间
     */
    private Date createdtime;

}
