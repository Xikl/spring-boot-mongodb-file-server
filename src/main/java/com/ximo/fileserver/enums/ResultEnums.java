package com.ximo.fileserver.enums;

import lombok.Getter;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description
 */
public enum ResultEnums {

    /** 成功 */
    SUCCESS(0, "操作成功"),
    INNER_ERROR(-1, "内部错误"),
    MD5_ERROR(1, "获得输入流MD5异常"),
    RESOURCE_NOT_FOUND(2, "资源不存在"),
    ;
    @Getter
    private Integer code;
    @Getter
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
