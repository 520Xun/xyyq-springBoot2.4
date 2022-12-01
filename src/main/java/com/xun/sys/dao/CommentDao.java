package com.xun.sys.dao;

import com.xun.sys.pojo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:15
 */
@Mapper
public interface CommentDao {

    List< CommentVO > findComment ( CommentVO vo );
}
