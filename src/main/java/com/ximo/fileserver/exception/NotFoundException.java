package com.ximo.fileserver.exception;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description 请求资源不存在
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
