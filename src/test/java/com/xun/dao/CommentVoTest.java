package com.xun.dao;

import com.xun.sys.dao.CommentDao;
import com.xun.sys.pojo.CommentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:34
 */
@SpringBootTest
public class CommentVoTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    public void test01 ( ) {
        CommentVO VO = new CommentVO ( );
        List< CommentVO > comment = commentDao.findComment ( VO );
        comment.stream ( ).forEach ( e -> {
            System.out.println ( e );
        } );
    }


    @Test
    public void test02 ( ) {
        for ( Map< String, Object > map : commentDao.findObjects ( ) ) {
            System.out.println ( map );
        }
    }
}
