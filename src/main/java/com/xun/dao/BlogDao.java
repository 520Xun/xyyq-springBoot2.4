package com.xun.dao;

import com.xun.pojo.BlogUserTypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 20:51
 * <p>
 * 博文持久层
 */
@Mapper
public interface BlogDao {
    /**
     * 查询文章 （按标题查）
     *
     * @param blog
     * @return
     */
    List<BlogUserTypeVo> findBlogs (BlogUserTypeVo blog);
}
