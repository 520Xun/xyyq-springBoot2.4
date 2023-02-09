package com.xun;

import com.xun.common.util.TxSmsTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-07 20:46
 */
@SpringBootTest
public class TestSms {
    @Autowired
    private TxSmsTemplate txSmsTemplate;

    /**
     * 腾讯云发送短信测试
     */
    @Test
    public void TxSmsTest ( ) {
        // 参数1: 手机号(正文模板中的参数{1})
        // 参数2: 验证码(正文模板中的参数{2})
        String Msg = txSmsTemplate.sendMesModel ( "17680316003", "123456" );
        // Msg不为null 发送成功
        // Msg为null  发送失败
        System.out.println ( Msg );
    }
}
