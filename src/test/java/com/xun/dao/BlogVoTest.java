package com.xun.dao;

import com.xun.pojo.BlogUserTypeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:26
 */
@SpringBootTest
public class BlogVoTest {

    @Autowired
    private BlogDao dao;

    @Test
    public void findBlogTest () {
        List<BlogUserTypeVo> blogs = dao.findBlogs (null);
        blogs.stream ().forEach (e -> {
            System.out.println (e);
        });
    }

}
