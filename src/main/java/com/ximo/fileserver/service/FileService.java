package com.ximo.fileserver.service;

import com.ximo.fileserver.domain.File;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/3/20
 * @description 文件操作service接口类
 */
public interface FileService {

    /**
     * 保存文件
     *
     * @param file 文件类
     * @return 文件
     */
    File saveFile(File file);

    /**
     * 删除文件
     *
     * @param id 要删除文件的id
     */
    void removeFile(String id);

    /**
     * 查找文件
     *
     * @param id 文件id
     * @return 文件
     */
    File findOne(String id);

    /**
     * 查找所有的文件 并分页
     *
     * @param pageIndex 第几页
     * @param pageSize  总数
     * @return 所有的文件
     */
    Page<File> findAllByPage(int pageIndex, int pageSize);
}
