package com.xun.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-05 8:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class saveExcel {
    private Integer SuccessNumber;
    private List< Integer > ErrorNumber;

}
