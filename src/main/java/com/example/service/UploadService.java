package com.example.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@Slf4j
public class UploadService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("http://fastdfs.com:8888/")
    private String baseUrl;

    /**
     * 上传图片
     */
    public String uploadImage(MultipartFile file) {
        try {
            // 校验文件
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
                throw new RuntimeException("上传的文件不是图片");
            }
        } catch (IOException e) {
            log.error("校验文件内容失败...{}", e);
            throw new RuntimeException("校验文件内容失败" + e.getMessage());
        }
        try {
            // 获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            // 上传到fastDFS
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extension,null);
            // 返回路径
            return baseUrl+storePath.getFullPath();
        } catch (IOException e){
            log.error("【文件上传】上传文件失败...{}",e);
            throw new RuntimeException("【文件上传】上传文件失败..."+e.getMessage());
        }
    }
    /**
     * 上传其他文件
     */
    public String uploadFile(MultipartFile file) {
        try {
            // 获取扩展名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            // 上传到fastDFS
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension,null);
            // 返回路径
            return baseUrl+storePath.getFullPath();
        } catch (IOException e){
            log.error("【文件上传】上传文件失败...{}",e);
            throw new RuntimeException("【文件上传】上传文件失败..."+e.getMessage());
        }
    }
}
