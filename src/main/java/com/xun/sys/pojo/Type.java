package com.xun.sys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Integer id;
    private String name;
    private Integer parentId;
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createdTime;
    @JsonFormat ( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

    public Type ( Integer id ) {
        this.id = id;
    }
}
