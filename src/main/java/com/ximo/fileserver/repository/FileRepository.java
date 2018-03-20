package com.ximo.fileserver.repository;

import com.ximo.fileserver.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 朱文赵
 * @date 2018/3/20
 * @description 文件储存库
 */
public interface FileRepository extends MongoRepository<File, String> {

}
