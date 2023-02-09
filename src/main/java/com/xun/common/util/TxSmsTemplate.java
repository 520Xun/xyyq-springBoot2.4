package com.xun.common.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.xun.common.config.TxProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-07 20:21
 * 腾讯云发送短信模板对象,封装了发送短信的api
 */
@Slf4j
public class TxSmsTemplate {

    private final TxProperties txProperties;

    public TxSmsTemplate ( TxProperties txProperties ) {
        this.txProperties = txProperties;
    }

    /**
     * 指定正文模板id发送短信
     *
     * @param number 用户的手机号码
     * @return OK 成功  null 失败
     */
    public String sendMesModel ( String number, String value ) {
        try {
            // 接收生成的验证码，设置5分钟内填写
            String[] params = { value, "5" };

            // 构建短信发送器
            SmsSingleSender ssender = new SmsSingleSender ( txProperties.getAppId ( ), txProperties.getAppKey ( ) );

            SmsSingleSenderResult result = ssender.sendWithParam ( "86", number,
                            txProperties.getTemplateId ( ), params, txProperties.getSignName ( ), "", "" ); // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print ( result );

            return result.errMsg; //OK
        } catch ( HTTPException e ) {
            // HTTP响应码错误
            log.info ( "短信发送失败,HTTP响应码错误!" );
            // e.printStackTrace();
        } catch ( JSONException e ) {
            // json解析错误
            log.info ( "短信发送失败,json解析错误!" );
            //e.printStackTrace();
        } catch ( IOException e ) {
            // 网络IO错误
            log.info ( "短信发送失败,网络IO错误!" );
            // e.printStackTrace();
        }
        return null;
    }
}
