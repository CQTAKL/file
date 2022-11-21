package com.example.service;

import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DownloadService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("http://fastdfs.com:8888/")
    private String baseUrl;

    /**
     * 下载文件
     */
    public byte[] downloadFile(String group, String path, DownloadByteArray downloadByteArray) {
        byte[] bytes = fastFileStorageClient.downloadFile(group,path,new DownloadByteArray());
        return bytes;
    }
}
