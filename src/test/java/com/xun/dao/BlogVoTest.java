package com.xun.dao;

import com.xun.sys.dao.BlogDao;
import com.xun.sys.pojo.BlogUserTypeVo;
import com.xun.sys.pojo.Type;
import com.xun.sys.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
    public void findBlogTest ( ) {
        List< BlogUserTypeVo > blogs = dao.findBlogs ( null );
        blogs.stream ( ).forEach ( e -> {
            System.out.println ( e );
        } );
    }

    @Test
    public void insertBlogTest ( ) {
        BlogUserTypeVo vo = new BlogUserTypeVo ( 3, "1111", new User ( ), "11111111111", 999,
                        "2022-11-26 03:35:14", "2022-11-26 03:35:14", 1, "2.5", 999, 999,
                        999, new Type ( 2, "", 1, new Date ( ), new Date ( ), "", "" ), 1, 1, "" );
        int i = dao.insertBlog ( vo, 21 );
        System.out.println ( vo );
        System.out.println ( i );
    }

}
