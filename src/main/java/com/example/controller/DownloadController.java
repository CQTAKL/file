package com.example.controller;

import com.example.service.DownloadService;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DownloadController {
    @Autowired
    private DownloadService downloadService;

    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<byte[]> download(HttpServletResponse response) {
        HttpHeaders httpHeaders = new HttpHeaders();//new出一个httpHeader对象
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置Media类型为直接下载类型
        httpHeaders.setContentDispositionFormData("attachment", "aa.png");//设置名字和文件名
        byte[] bytes = downloadService.downloadFile("group1", "M00/00/00/wKjGZGN0ds-AantNABFKYHj4E64472.png", new DownloadByteArray());//下载
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);//返回封装实体
    }
}
