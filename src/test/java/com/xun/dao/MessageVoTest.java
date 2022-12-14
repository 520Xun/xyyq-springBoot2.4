package com.xun.dao;

import com.xun.sys.service.MessageService;
import com.xun.sys.vo.ParentMessageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 15:02
 */
@SpringBootTest
public class MessageVoTest {

    @Autowired
    private MessageService messageService;

    /**
     * 测试留言楼
     */
    @Test
    public void testSearch ( ) {
        List< ParentMessageVo > vos = messageService.listMessageVoList ( );
        vos.forEach ( e -> {
            System.out.println ( e );
        } );
    }
}
