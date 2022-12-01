package com.xun.sys.service;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.CommentVO;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:54
 * <p>
 * 评论接口类
 */
public interface CommentService {
    JsonResult findComment ( CommentVO comment, Integer curPage, Integer pageSize );
}
