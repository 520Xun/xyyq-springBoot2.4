package com.xun.sys.service;

import com.xun.sys.pojo.Type;
import com.xun.sys.vo.BlogTypeVo;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-28 10:46
 * <p>
 * 类别业务层
 */
public interface TypeService {
    /**
     * 查找所有的类别
     *
     * @return
     */
    List< Type > findAllType ( );

    /**
     * 查询所有的分类和文章
     *
     * @return
     */
    List< BlogTypeVo > findAllTypeAndBlog ( );
}
