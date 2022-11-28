package com.xun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 11:02
 */
@Mapper
public interface BlogTagDao {
    /**
     * 根据文章id
     * 查找文章对应的标签
     *
     * @param blogId
     * @return
     */
    @Select ( "select tag_id from blog_tag where blog_id=#{blogId}" )
    List< Integer > findTagByBlogId ( Integer blogId );
}
