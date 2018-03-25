package com.ximo.fileserver.util;

import com.ximo.fileserver.enums.ResultEnum;
import com.ximo.fileserver.exception.FileServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 朱文赵
 * @date 2018/3/25
 * @description
 */
@Slf4j
public class MD5Util {


    /**
     * 获得输入流中的MD5值
     *
     * @param inputStream 输入流
     * @return md5
     */
    public static String getMD5(InputStream inputStream) {
        try {
            StringBuffer md5 = new StringBuffer();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dataBytes = new byte[1024];

            int nRead;
            while ((nRead = inputStream.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nRead);
            }

            byte[] mdBytes = md.digest();

            for (byte mdByte : mdBytes) {
                //转化为16进制的字符串
                md5.append(Integer.toString((mdByte & 0xFF) + 0x100, 16).substring(1));
            }
            return md5.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            log.error("【输入流 => md5】{}", ResultEnum.MD5_ERROR, e);
            throw new FileServerException(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 通过Spring自己的方法进行输入流 转化为MD5值
     *
     * @param inputStream 输入流
     * @return md5
     */
    public static String getMD5BySpring(InputStream inputStream) {
        try {
            return DigestUtils.md5DigestAsHex(inputStream);
        } catch (IOException e) {
            log.error("【输入流 => md5】{}", ResultEnum.MD5_ERROR, e);
            throw new FileServerException(ResultEnum.INNER_ERROR);
        }
    }


}
