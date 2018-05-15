package com.ximo.fileserver.exception;

import com.ximo.fileserver.enums.ResultEnums;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description 统一的业务异常
 */
public class FileServerException extends RuntimeException{

    public FileServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileServerException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
    }
}
