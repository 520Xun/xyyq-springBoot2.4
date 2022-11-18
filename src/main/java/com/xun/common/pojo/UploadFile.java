package com.xun.common.pojo;

import java.util.Date;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 19:39
 */
public class UploadFile {
    private Integer id;
    /**
     * 文件实际名称
     */
    private String realName;
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件主名称
     */
    private String primaryName;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 存放路径
     */
    private String path;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 上传人
     */
    private String uploader;
    private Date createTime;
    @Override
    public String toString() {
        return "UploadFile{" +
                "fileName='" + fileName + '\'' +
                ", uploader='" + uploader + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
