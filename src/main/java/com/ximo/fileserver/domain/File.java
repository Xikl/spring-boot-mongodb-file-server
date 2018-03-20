package com.ximo.fileserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author 朱文赵
 * @date 2018/3/20
 * @description 文件类
 */
@Data
@Document
@NoArgsConstructor
public class File {

    /** 主键id */
    @Id
    private String id;
    /** 文件名称 */
    private String name;
    /** 文件类型 */
    private String contentType;
    /** 文件大小 */
    private Long size;
    /** 上传时间 */
    private Date uploadTime;
    /** md5加密后的值 */
    private String md5;
    /** 文件内容 写入二进制 */
    private Binary content;
    /** 文件路径 */
    private String path;

    /** 构造器 */
    public File(String name, String contentType, Long size, Binary content) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.uploadTime = new Date();
        this.content = content;
    }
}