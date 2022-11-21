package com.example;

import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class FastdfsTestApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    void testDownload() {
        try {
            byte[] bytes = fastFileStorageClient.downloadFile("group1","M00/00/00/wKjGZGN0ds-AantNABFKYHj4E64472.png",new DownloadByteArray());
            FileOutputStream stream = new FileOutputStream("test.png");
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
