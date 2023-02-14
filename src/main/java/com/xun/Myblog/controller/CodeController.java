package com.xun.Myblog.controller;

import com.xun.common.pojo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-14 8:59
 * 生成验证码的工具包
 */
@Slf4j
@RestController
@RequestMapping ( "code" )
public class CodeController {

    @RequestMapping ( "getcode" )
    public static JsonResult getCode ( HttpSession session ) {
        char[] sources = new char[]{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                        '7', '8', '9' };
        int length = sources.length;
        ThreadLocalRandom random = ThreadLocalRandom.current ( );
        StringBuilder sb = new StringBuilder ( );
        for ( int j = 0; j < 6; j++ ) {
            sb.append ( sources[ random.nextInt ( length ) ] );
        }
        String code = sb.toString ( );
        session.removeAttribute ( "code" );
        session.setAttribute ( "code", code );
        log.info ( "生成的验证码为:" + code );
        
        return new JsonResult ( code );
    }

}
