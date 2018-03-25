package com.ximo.fileserver.enums;

import lombok.Getter;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description
 */
public enum  ResultEnum {

    INNER_ERROR("内部错误"),
    MD5_ERROR("获得输入流MD5异常"),
    ;


    @Getter
    private String msg;

    ResultEnum(String msg) {
        this.msg = msg;
    }
}
