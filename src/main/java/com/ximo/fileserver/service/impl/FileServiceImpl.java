package com.ximo.fileserver.service.impl;

import com.ximo.fileserver.domain.File;
import com.ximo.fileserver.repository.FileRepository;
import com.ximo.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/3/20
 * @description
 */
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileRepository fileRepository;

    /**
     * 保存文件
     *
     * @param file 文件类
     * @return 文件
     */
    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    /**
     * 删除文件
     *
     * @param id 要删除文件的id
     */
    @Override
    public void removeFile(String id) {
        fileRepository.delete(id);
    }

    /**
     * 查找文件
     *
     * @param id 文件id
     * @return 文件
     */
    @Override
    public File findOne(String id) {
        return fileRepository.findOne(id);
    }

    /**
     * 查找所有的文件 并分页
     *
     * @param pageIndex 第几页
     * @param pageSize  总数
     * @return 所有的文件
     */
    @Override
    public List<File> findAllByPage(int pageIndex, int pageSize) {
        return fileRepository.findAll(new PageRequest(pageIndex, pageSize,
                new Sort(Sort.Direction.DESC, "uploadTime"))).getContent();
    }
}
