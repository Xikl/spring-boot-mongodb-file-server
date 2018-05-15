package com.ximo.fileserver.controller;

import com.ximo.fileserver.config.ServerConfig;
import com.ximo.fileserver.domain.File;
import com.ximo.fileserver.enums.ResultEnums;
import com.ximo.fileserver.service.FileService;
import com.ximo.fileserver.util.MD5Util;
import com.ximo.fileserver.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.ximo.fileserver.constants.FileConstants.ATTACHMENT;

/**
 * @author 朱文赵
 * @date 2018/3/20
 * @description 文件控制器 允许跨域请求
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Slf4j
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("files", fileService.findAllByPage(0, 20).getContent());
        return "index";
    }

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/{pageIndex}/{pageSize}")
    @ResponseBody
    public ResultVO<List<File>> listFilesByPage(@PathVariable("pageIndex") Integer pageIndex,
                                               @PathVariable("pageSize") Integer pageSize) {
        return ResultVO.success(fileService.findAllByPage(pageIndex - 1, pageSize).getContent());
    }

    /**
     * 获取文件片信息
     * 附件形式下载
     *
     * @param id 文件id
     * @return 下载该文件
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFile(@PathVariable("id") String id) {
        File file = fileService.findOne(id);
        if (file != null) {
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT + new String(file.getName().getBytes("UTF-8"),
                                "ISO-8859-1") + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                        .header(HttpHeaders.CONNECTION, "close")
                        .body(file.getContent().getData());
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file encode error");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
        }
    }

    /**
     * 在线显示文件
     *
     * @param id
     * @return
     */
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFileOnline(@PathVariable("id") String id) {
        File file = fileService.findOne(id);
        if (file != null) {
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + new String(file.getName().getBytes("UTF-8"),
                                "ISO-8859-1") + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                        .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                        .header(HttpHeaders.CONNECTION, "close")
                        .body(file.getContent().getData());
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file encode error");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        File returnFile;
        try {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()));
            f.setMd5(MD5Util.getMD5(file.getInputStream()));
            returnFile = fileService.saveFile(f);
//            String path = "//" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/view/" + returnFile.getId();
            return ResponseEntity.ok().body(MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "serveFileOnline", returnFile.getId())
                    .build().toString());
        } catch (IOException e) {
            log.error("【上传文件】上传文件出现异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultEnums.INNER_ERROR.getMsg());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@PathVariable("id") String id) {
        fileService.removeFile(id);
        return ResponseEntity.ok().body("Delete Success!");
    }

}
