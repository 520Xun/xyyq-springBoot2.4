package com.xun.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2023-02-14 9:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String username;
    private String password;
    private String code;//验证码
    private boolean isRememberMe;//是否记住你
}
