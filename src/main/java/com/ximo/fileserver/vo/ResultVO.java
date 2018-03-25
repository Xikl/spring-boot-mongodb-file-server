package com.ximo.fileserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /** 返回码*/
    private Integer code;
    /** 提示消息*/
    private String msg;
    /** 数据本身*/
    private T data;

}
